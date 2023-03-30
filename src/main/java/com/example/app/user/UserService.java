package com.example.app.user;

import com.example.app.exceptions.form.ConfirmPassFieldIsEmptyException;
import com.example.app.exceptions.form.MissingFormFieldsException;
import com.example.app.exceptions.form.PasswordsAreNotTheSameException;
import com.example.app.exceptions.token.SessionHasExpiredException;
import com.example.app.helpers.Code;
import com.example.app.helpers.Token;
import com.example.app.mail.Email;
import com.example.app.mail.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto register(UserDto user) {
        User userToSave = UserDtoMapper.map(user);
        userToSave.setToken(Token.generateToken());
        userToSave.setCode(Code.generateCode());
        userToSave.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userToSave.setVerified(0);
        userToSave.setVerified_at(null);
        userToSave.setUpdated_at(LocalDateTime.now());
        userToSave.setCreated_at(LocalDateTime.now());

        User savedUser = userRepository.save(userToSave);
        return UserDtoMapper.map(savedUser);
    }

    @Transactional
    public void verifyAccount(String token, Integer code) {
        Optional<User> optionalUser = userRepository.findUserByTokenAndCode(token, code);
        optionalUser.ifPresentOrElse(
                user -> {
                    user.setToken(null);
                    user.setCode(null);
                    user.setVerified(1);
                    user.setVerified_at(LocalDateTime.now());
                    user.setUpdated_at(LocalDateTime.now());
                },
                () -> {
                    throw new SessionHasExpiredException();
                }
        );
    }

    public void sendVerifyEmailTo(UserVerifyingDto user) throws MessagingException {
        String token = user.getToken();
        Integer code = user.getCode();
        String emailBody = Email.getHtmlEmailTemplate(token, code);
        EmailService.sendEmail("alojz.kleks@company.com", user.getEmail(), "Verify Account", emailBody);
    }

    public void checkFormErrors(BindingResult result, UserRegistrationDto user, String confirmPassword) {
        String password = user.getPassword();

        if (result.hasErrors() && password.isEmpty()) {
            throw new MissingFormFieldsException();
        } else if (result.hasErrors() && confirmPassword.isEmpty()) {
            throw new ConfirmPassFieldIsEmptyException();
        } else if (result.hasErrors() && !password.equals(confirmPassword)) {
            throw new PasswordsAreNotTheSameException();
        } else if (result.hasErrors()) {
            throw new MissingFormFieldsException();
        } else if (!password.equals(confirmPassword)) {
                throw new PasswordsAreNotTheSameException();
        }
    }

}

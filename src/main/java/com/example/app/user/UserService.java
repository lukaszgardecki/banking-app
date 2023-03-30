package com.example.app.user;

import com.example.app.exceptions.form.ConfirmPassFieldIsEmptyException;
import com.example.app.exceptions.form.MissingFormFieldsException;
import com.example.app.exceptions.form.PasswordsAreNotTheSameException;
import com.example.app.exceptions.token.SessionHasExpiredException;
import com.example.app.helpers.Code;
import com.example.app.helpers.Token;
import com.example.app.mail.Email;
import com.example.app.mail.EmailService;
import com.example.app.user.dto.UserCredentialsDto;
import com.example.app.user.dto.UserRegistrationDto;
import com.example.app.user.dto.UserVerifyingDto;
import com.example.app.user.mappers.UserCredentialsDtoMapper;
import com.example.app.user.mappers.UserVerifyingDtoMapper;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    public static final String DEFAULT_USER_ROLE = "USER";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserVerifyingDto register(UserRegistrationDto userRegistration) {
        User userToSave = getUserToSaveWithDefaultRole(userRegistration);
        User savedUser = userRepository.save(userToSave);
        return UserVerifyingDtoMapper.map(savedUser);
    }

    @Transactional
    public void verifyAccount(String token, Integer code) {
//        userRepository.findUserByTokenAndCode(token, code)
//            .ifPresentOrElse(
//                user -> {
//                    user.setToken(null);
//                    user.setCode(null);
//                    user.setVerified(1);
//                    user.setVerified_at(LocalDateTime.now());
//                    user.setUpdated_at(LocalDateTime.now());
//                },
//                    // TODO: 29.03.2023 Replace this to method reference
//                () -> {
//                    throw new SessionHasExpiredException();
//                }
//            );

        userRepository.findUserByTokenAndCode(token, code)
                .ifPresentOrElse(
                        this::verifyUser,
                        // TODO: 29.03.2023 Replace this to method reference
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

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }

    private User getUserToSaveWithDefaultRole(UserRegistrationDto userRegistration) {
        UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User user = new User();
        user.setFirst_name(userRegistration.getFirst_name());
        user.setLast_name(userRegistration.getLast_name());
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.getRoles().add(defaultRole);
        user.setToken(Token.generateToken());
        user.setCode(Code.generateCode());
        user.setVerified(0);
        user.setVerified_at(null);
        user.setUpdated_at(LocalDateTime.now());
        user.setCreated_at(LocalDateTime.now());
        return user;
    }

    private void verifyUser(User user) {
        user.setToken(null);
        user.setCode(null);
        user.setVerified(1);
        user.setVerified_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());
    }
}
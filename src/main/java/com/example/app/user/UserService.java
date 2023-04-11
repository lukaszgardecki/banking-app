package com.example.app.user;

import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.token.SessionHasExpiredException;
import com.example.app.helpers.Code;
import com.example.app.helpers.Message;
import com.example.app.helpers.Token;
import com.example.app.mail.Email;
import com.example.app.mail.EmailService;
import com.example.app.user.dto.UserCredentialsDto;
import com.example.app.user.dto.UserDashboardDto;
import com.example.app.user.dto.UserRegistrationDto;
import com.example.app.user.dto.UserVerifyingDto;
import com.example.app.user.mappers.UserCredentialsDtoMapper;
import com.example.app.user.mappers.UserDashboardDtoMapper;
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
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.addressRepository = addressRepository;
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
            throw new EmptyFieldException(Message.MISSING_FIELDS);
        } else if (result.hasErrors() && confirmPassword.isEmpty()) {
            throw new EmptyFieldException(Message.CONFIRM_PASS_REQUIRED);
        } else if (result.hasErrors() && !password.equals(confirmPassword)) {
            throw new EmptyFieldException(Message.PASSWORDS_ARE_NOT_SAME);
        } else if (result.hasErrors()) {
            throw new EmptyFieldException(Message.MISSING_FIELDS);
        } else if (!password.equals(confirmPassword)) {
                throw new EmptyFieldException(Message.PASSWORDS_ARE_NOT_SAME);
        }
    }

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }

    public Optional<UserDashboardDto> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(UserDashboardDtoMapper::map);
    }

    public Boolean isUserVerified(String email) {
        Integer verified = userRepository.isUserVerified(email);
        if (verified == 1) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    private User getUserToSaveWithDefaultRole(UserRegistrationDto userRegistration) {
        UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User user = new User();
        user.setFirst_name(userRegistration.getFirst_name());
        user.setLast_name(userRegistration.getLast_name());

        Address address = new Address();
        address.setStreet(userRegistration.getStreet());
        address.setCity(userRegistration.getCity());
        address.setZipCode(userRegistration.getZipCode());
        Address savedAddress = addressRepository.save(address);

        user.setAddress(savedAddress);
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

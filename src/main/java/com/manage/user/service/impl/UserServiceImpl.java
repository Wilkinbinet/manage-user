package com.manage.user.service.impl;

import com.manage.user.config.UserRegistrationConfig;
import com.manage.user.dto.PhoneDto;
import com.manage.user.dto.UserRegistrationRequest;
import com.manage.user.model.Phone;
import com.manage.user.model.User;
import com.manage.user.repository.UserRepository;
import com.manage.user.security.jwt.JwtTokenProvider;
import com.manage.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.manage.user.util.ConstantsUtil.*;

/**
 * This class implements the UserService interface and provides user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRegistrationConfig config;

    /**
     * Constructs an instance of UserServiceImpl.
     *
     * @param userRepository  the UserRepository instance for user operations
     * @param passwordEncoder the PasswordEncoder instance for password encoding
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider,
                           UserRegistrationConfig config) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.config = config;
    }

    /**
     * Registers a new user.
     *
     * @param userDto the User object representing the user to register
     * @return the registered user
     */
    @Override
    public User registerUser(UserRegistrationRequest userDto) {

        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new IllegalArgumentException(EMAIL_EXIST);
        }

        if (!userDto.getEmail().matches(config.getEmailPattern())) {
            throw new IllegalArgumentException(INVALID_EMAIL);
        }
        if (!userDto.getPassword().matches(config.getPasswordPattern())) {
            throw new IllegalArgumentException(INVALID_PASSWORD);
        }

        User user = new User();
        Phone phone;
        Set<Phone> phones = new HashSet<>();
        BeanUtils.copyProperties(userDto, user);

        for (PhoneDto phoneDto : userDto.getPhones()) {
            phone = new Phone();
            BeanUtils.copyProperties(phoneDto, phone);
            phone.setUser(user);
            phones.add(phone);
        }

        user.setPhones(phones);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(user.getCreated());
        user.setToken(jwtTokenProvider.createToken(user));
        return userRepository.save(user);
    }
}

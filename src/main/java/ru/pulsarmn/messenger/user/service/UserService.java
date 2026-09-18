package ru.pulsarmn.messenger.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pulsarmn.messenger.user.domain.User;
import ru.pulsarmn.messenger.user.dto.request.BirthdateUpdateRequest;
import ru.pulsarmn.messenger.user.dto.request.DisplayNameUpdateRequest;
import ru.pulsarmn.messenger.user.dto.request.UsernameUpdateRequest;
import ru.pulsarmn.messenger.user.dto.response.PageResponse;
import ru.pulsarmn.messenger.user.dto.response.UserDto;
import ru.pulsarmn.messenger.user.dto.response.UserProfileResponse;
import ru.pulsarmn.messenger.user.dto.response.UserSearchResponse;
import ru.pulsarmn.messenger.user.exception.UserNotFoundException;
import ru.pulsarmn.messenger.user.mapper.UserMapper;
import ru.pulsarmn.messenger.user.repository.UserRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;


@Service
public class UserService {

    private final Clock clock;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(Clock clock, UserMapper userMapper, UserRepository userRepository) {
        this.clock = clock;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public PageResponse<UserSearchResponse> findUsers(String query, Pageable pageable) {
        return PageResponse.from(userRepository.searchUsers(query, pageable)
                .map(userMapper::mapToSearchResponse));
    }

    public UserProfileResponse getUserProfile(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapToProfileResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id '%s' was not found".formatted(userId)));
    }

    public UserDto getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new UserNotFoundException("User with id '%s' was not found".formatted(userId)));
    }

    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new UserNotFoundException("User with username '%s' was not found".formatted(username)));
    }

    @Transactional
    public UserProfileResponse updateUsername(UUID userId, UsernameUpdateRequest request) {
        return update(userId, user -> saveNewUsernameIfNecessary(user, request));
    }

    private User saveNewUsernameIfNecessary(User user, UsernameUpdateRequest request) {
        String newUsername = request.newUsername();
        if (!Objects.equals(user.getUsername(), newUsername)) {
            user.setUsername(newUsername);
            userRepository.saveAndFlush(user);
            log.info("The username for the user with id '{}' has been successfully updated to '{}'", user.getId(), newUsername);
        }
        return user;
    }

    @Transactional
    public UserProfileResponse updateDisplayName(UUID userId, DisplayNameUpdateRequest request) {
        return update(userId, user -> saveNewDisplayNameIfNecessary(user, request));
    }

    private User saveNewDisplayNameIfNecessary(User user, DisplayNameUpdateRequest request) {
        String newDisplayName = request.newDisplayName();
        if (!Objects.equals(user.getDisplayName(), newDisplayName)) {
            user.setDisplayName(newDisplayName);
            userRepository.saveAndFlush(user);
            log.info("The display name for the user with id '{}' has been successfully updated to '{}'", user.getId(), newDisplayName);
        }
        return user;
    }

    @Transactional
    public UserProfileResponse updateBirthdate(UUID userId, BirthdateUpdateRequest request) {
        return update(userId, user -> saveNewBirthdateIfNecessary(user, request));
    }

    private User saveNewBirthdateIfNecessary(User user, BirthdateUpdateRequest request) {
        LocalDate newBirthdate = request.newBirthdate();
        if (isInvalidBirthdate(newBirthdate)) {
            throw new IllegalArgumentException("Invalid birthdate: " + newBirthdate);
        }

        if (!Objects.equals(user.getBirthdate(), newBirthdate)) {
            user.setBirthdate(newBirthdate);
            userRepository.saveAndFlush(user);
            log.info("The birthdate for the user with id '{}' has been successfully updated to '{}'", user.getId(), newBirthdate);
        }
        return user;
    }

    private boolean isInvalidBirthdate(LocalDate birthdate) {
        return birthdate.isAfter(LocalDate.now(clock)); // TODO: extract to BirthdateValidator or something similar
    }

    private UserProfileResponse update(UUID userId, Function<User, User> updateFunction) {
        return userRepository.findById(userId)
                .map(updateFunction)
                .map(userMapper::mapToProfileResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id '%s' not found".formatted(userId)));
    }
}

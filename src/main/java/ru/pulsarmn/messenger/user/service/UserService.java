package ru.pulsarmn.messenger.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pulsarmn.messenger.user.dto.*;
import ru.pulsarmn.messenger.user.exception.UserNotFoundException;
import ru.pulsarmn.messenger.user.mapper.UserMapper;
import ru.pulsarmn.messenger.user.repository.UserRepository;

import java.util.UUID;


@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
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
                .orElseThrow(() -> new UserNotFoundException("User with id '%s' was not found"));
    }

    @Transactional
    public UserProfileResponse updateUsername(UUID userId, UsernameUpdateRequest request) {
        return userRepository.findById(userId)
                .map(user -> {
                    if (!(user.getUsername()).equals(request.newUsername())) {
                        user.setUsername(request.newUsername());
                        userRepository.save(user);
                    }
                    return user;
                })
                .map(userMapper::mapToProfileResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id '%s' not found".formatted(userId)));
    }

    @Transactional
    public UserProfileResponse updateDisplayName(UUID userId, DisplayNameUpdateRequest request) {
        return userRepository.findById(userId)
                .map(user -> {
                    if (!(user.getDisplayName()).equals(request.newDisplayName())) {
                        user.setDisplayName(request.newDisplayName());
                        userRepository.save(user);
                    }
                    return user;
                })
                .map(userMapper::mapToProfileResponse)
                .orElseThrow(() -> new UserNotFoundException("User with id '%s' not found".formatted(userId)));
    }
}

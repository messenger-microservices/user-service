package ru.pulsarmn.messenger.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pulsarmn.messenger.user.dto.PageResponse;
import ru.pulsarmn.messenger.user.dto.UserProfileResponse;
import ru.pulsarmn.messenger.user.dto.UserSearchResponse;
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
}

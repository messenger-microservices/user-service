package ru.pulsarmn.messenger.user.mapper;

import org.springframework.stereotype.Component;
import ru.pulsarmn.messenger.user.domain.User;
import ru.pulsarmn.messenger.user.dto.response.UserDto;
import ru.pulsarmn.messenger.user.dto.response.UserProfileResponse;
import ru.pulsarmn.messenger.user.dto.response.UserSearchResponse;


@Component
public class UserMapper {

    public UserSearchResponse mapToSearchResponse(User user) {
        return new UserSearchResponse(user.getUsername());
    }

    public UserProfileResponse mapToProfileResponse(User user) {
        return new UserProfileResponse(user.getUsername(), user.getPhoneNumber(), user.getDisplayName(), user.getBirthdate());
    }

    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .phoneNumber(user.getPasswordHash())
                .displayName(user.getDisplayName())
                .birthdate(user.getBirthdate())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}

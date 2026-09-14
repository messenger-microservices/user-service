package ru.pulsarmn.messenger.user.mapper;

import org.springframework.stereotype.Component;
import ru.pulsarmn.messenger.user.domain.User;
import ru.pulsarmn.messenger.user.dto.UserSearchResponse;


@Component
public class UserMapper {

    public UserSearchResponse mapToSearchResponse(User user) {
        return new UserSearchResponse(user.getUsername());
    }
}

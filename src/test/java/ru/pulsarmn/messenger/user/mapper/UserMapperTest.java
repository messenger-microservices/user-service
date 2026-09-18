package ru.pulsarmn.messenger.user.mapper;

import org.junit.jupiter.api.Test;
import ru.pulsarmn.messenger.user.domain.User;
import ru.pulsarmn.messenger.user.dto.response.UserSearchResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void mapToSearchResponse_whenCorrectUser_shouldMapUserToResponse() {
        User user = User.builder()
                .username("correct-username")
                .build();

        UserSearchResponse actualResult = userMapper.mapToSearchResponse(user);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.username()).isEqualTo(user.getUsername());
    }

    @Test
    void mapToSearchResponse_whenNullUser_shouldThrowNullPointerException() {
        assertThatThrownBy(() -> userMapper.mapToSearchResponse(null))
                .isInstanceOf(NullPointerException.class);
    }
}

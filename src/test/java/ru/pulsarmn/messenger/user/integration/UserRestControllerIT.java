package ru.pulsarmn.messenger.user.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.pulsarmn.messenger.user.dto.response.PageResponse;
import ru.pulsarmn.messenger.user.dto.response.UserSearchResponse;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


public class UserRestControllerIT extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUpWebClient() {
        webTestClient = WebTestClient.bindToServer()
                .responseTimeout(Duration.ofMinutes(5))
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    void findUsersByUsername_whenExistingUsernameInQuery_shouldReturnFoundedUsers() {
        String queryUsername = "pulsar";

        PageResponse<UserSearchResponse> response = webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/users/search")
                        .queryParam("query", queryUsername)
                        .build())
                .exchangeSuccessfully()
                .expectBody(new ParameterizedTypeReference<PageResponse<UserSearchResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        assertThat(response).isNotNull();
        assertThat(response.content()).hasSize(2);
        assertThat(response.totalElements()).isEqualTo(2);
        assertThat(response.content().getFirst().username()).contains(queryUsername);
    }

    @Test
    void findUsersByUsername_whenNonExistingUsernameInQuery_shouldReturnEmptyList() {
        String queryUsername = "not_existing_username";

        PageResponse<UserSearchResponse> response = webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/users/search")
                        .queryParam("query", queryUsername)
                        .build())
                .exchangeSuccessfully()
                .expectBody(new ParameterizedTypeReference<PageResponse<UserSearchResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        assertThat(response).isNotNull();
        assertThat(response.content()).isEmpty();
        assertThat(response.totalElements()).isEqualTo(0);
    }

    @Test
    void findUsersByUsername_whenEmptyQuery_shouldReturnBadRequest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/users/search")
                        .build())
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

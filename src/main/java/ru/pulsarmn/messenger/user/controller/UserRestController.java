package ru.pulsarmn.messenger.user.controller;

import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pulsarmn.messenger.user.domain.UserPrincipal;
import ru.pulsarmn.messenger.user.dto.PageResponse;
import ru.pulsarmn.messenger.user.dto.UserProfileResponse;
import ru.pulsarmn.messenger.user.dto.UserSearchResponse;
import ru.pulsarmn.messenger.user.service.UserService;


@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    ResponseEntity<@NonNull PageResponse<UserSearchResponse>> findUsersByUsername(@RequestParam @Size(min = 2) String query,
                                                                                  @PageableDefault(sort = "username") Pageable pageable) {
        PageResponse<UserSearchResponse> response = userService.findUsers(query, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    ResponseEntity<UserProfileResponse> getProfile(UserPrincipal userPrincipal) {
        UserProfileResponse response = userService.getUserProfile(userPrincipal.userId());
        return ResponseEntity.ok(response);
    }
}

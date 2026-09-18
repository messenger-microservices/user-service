package ru.pulsarmn.messenger.user.dto.response;

import java.time.LocalDate;


public record UserProfileResponse(
        String username,
        String phoneNumber,
        String displayName,
        LocalDate birthdate
) {
}

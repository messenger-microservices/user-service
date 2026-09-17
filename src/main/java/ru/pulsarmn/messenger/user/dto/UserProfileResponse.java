package ru.pulsarmn.messenger.user.dto;

import java.time.LocalDate;


public record UserProfileResponse(
        String username,
        String phoneNumber,
        String displayName,
        LocalDate birthdate
) {
}

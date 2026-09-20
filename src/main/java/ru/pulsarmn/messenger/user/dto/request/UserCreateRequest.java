package ru.pulsarmn.messenger.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;


public record UserCreateRequest(

        @NotNull
        UUID id,

        @NotBlank
        @Size(min = 2, max = 32)
        String username,

        String phoneNumber,
        String displayName,
        LocalDate birthdate) {
}

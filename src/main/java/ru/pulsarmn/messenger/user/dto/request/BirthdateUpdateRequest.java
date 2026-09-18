package ru.pulsarmn.messenger.user.dto.request;

import java.time.LocalDate;


public record BirthdateUpdateRequest(LocalDate newBirthdate) {
}

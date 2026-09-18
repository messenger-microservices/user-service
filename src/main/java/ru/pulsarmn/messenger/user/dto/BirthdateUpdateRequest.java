package ru.pulsarmn.messenger.user.dto;

import java.time.LocalDate;


public record BirthdateUpdateRequest(LocalDate newBirthdate) {
}

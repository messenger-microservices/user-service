package ru.pulsarmn.messenger.user.dto;

import org.springframework.data.domain.Page;

import java.util.List;


public record PageResponse<T>(
        List<T> content,
        int size,
        int number,
        long totalElements,
        int totalPages
) {

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(page.getContent(), page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
    }
}

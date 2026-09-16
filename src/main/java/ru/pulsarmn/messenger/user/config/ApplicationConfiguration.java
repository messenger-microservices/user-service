package ru.pulsarmn.messenger.user.config;

import org.springframework.data.web.config.EnableSpringDataWebSupport;


@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApplicationConfiguration {
}

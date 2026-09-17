package ru.pulsarmn.messenger.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.pulsarmn.messenger.user.controller.resolver.UserPrincipalHandlerArgumentResolver;

import java.util.List;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final UserPrincipalHandlerArgumentResolver userPrincipalHandlerArgumentResolver;

    public WebConfiguration(UserPrincipalHandlerArgumentResolver userPrincipalHandlerArgumentResolver) {
        this.userPrincipalHandlerArgumentResolver = userPrincipalHandlerArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userPrincipalHandlerArgumentResolver);
    }
}

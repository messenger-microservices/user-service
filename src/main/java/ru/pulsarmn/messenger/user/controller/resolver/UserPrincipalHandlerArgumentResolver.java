package ru.pulsarmn.messenger.user.controller.resolver;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.pulsarmn.messenger.user.exception.InvalidHeaderException;
import ru.pulsarmn.messenger.user.exception.UnauthenticatedException;
import ru.pulsarmn.messenger.user.domain.UserPrincipal;

import java.util.UUID;


@Component
public class UserPrincipalHandlerArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String USER_ID_HEADER = "X-User-Id";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameter().getType() == UserPrincipal.class;
    }

    @Override
    public @Nullable Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        String userId = webRequest.getHeader(USER_ID_HEADER);
        if (userId == null) {
            throw new UnauthenticatedException();
        }

        try {
            return new UserPrincipal(UUID.fromString(userId));
        } catch (Exception e) {
            return new InvalidHeaderException("Invalid user id header: " + userId, userId);
        }
    }
}

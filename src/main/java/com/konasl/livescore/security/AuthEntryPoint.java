package com.konasl.livescore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konasl.livescore.dto.ErrorResponse;
import com.konasl.livescore.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out,
                ErrorResponse.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .status(HttpStatus.UNAUTHORIZED.name())
                        .message(authException.getMessage())
                        .errors(List.of(AppConstants.UNAUTHORIZED_ERROR_MESSAGE))
                        .build()
        );
        out.flush();
    }
}
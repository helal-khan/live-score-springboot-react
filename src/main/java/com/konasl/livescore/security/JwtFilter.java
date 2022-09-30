package com.konasl.livescore.security;

import com.konasl.livescore.util.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(AppConstants.AUTHORIZATION_HEADER);

        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(AppConstants.BEARER_PREFIX)) {
            final String token = bearerToken.substring(7);
            if (jwtProvider.validateToken(token)) {
                SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(token));
            }
        }
        filterChain.doFilter(request, response);
    }
}
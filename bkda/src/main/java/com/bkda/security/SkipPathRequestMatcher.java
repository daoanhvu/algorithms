package com.bkda.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher orRequestMatcher;
    private RequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath ) {
        if( pathsToSkip == null ) {
            throw new IllegalArgumentException("PathToSkip must not be null");
        }

        List<RequestMatcher> matchers = pathsToSkip.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
        orRequestMatcher = new OrRequestMatcher(matchers);
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        return !orRequestMatcher.matches(httpServletRequest) && processingMatcher.matches(httpServletRequest);
    }
}

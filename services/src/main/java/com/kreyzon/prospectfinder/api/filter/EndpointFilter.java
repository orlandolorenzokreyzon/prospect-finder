package com.kreyzon.prospectfinder.api.filter;

import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.response.AuthenticationResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Component
public class EndpointFilter extends GenericFilterBean  {
    @Autowired
    @Qualifier("outside")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
        HttpServletRequest httpServletRequest = ((HttpServletRequest) request);



        final String authorizationHeader = httpServletRequest.getHeader(Constant.HEADER_AUTHORIZATION);
        //if (StringUtils.isBlank(authorizationHeader) || !authorizationHeader.startsWith(Constant.TOKEN_PREFIX)) {
          //  httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        //}

        String token = null;
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith(Constant.TOKEN_PREFIX)) {
            token = authorizationHeader.substring(7);
        }

        // final String token = authorizationHeader.substring(7);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(map, headers);

        try {
            if ( token != null) {
                ResponseEntity<AuthenticationResponse> authenticationResponse = restTemplate
                        .postForEntity(
                                "http://localhost:9091/prospectfinder/api/v1/auth/login/validate-token",
                                entity,
                                AuthenticationResponse.class
                        );

                Authentication authentication = new PreAuthenticatedAuthenticationToken(authenticationResponse.getBody().getAppUserDto().id(), authenticationResponse.getBody().getAppUserDto().email());
                authentication.setAuthenticated(true);
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        } catch(HttpClientErrorException | HttpServerErrorException ex) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
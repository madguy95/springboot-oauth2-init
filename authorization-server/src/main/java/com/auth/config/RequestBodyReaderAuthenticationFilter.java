package com.auth.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	 
    private static final Log LOG = LogFactory.getLog(RequestBodyReaderAuthenticationFilter.class);
 
    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";
 
    private static final String USERNAME_KEY = "username";
    
    private static final String PASSWORD_KEY = "password";
    
    private final ObjectMapper objectMapper = new ObjectMapper();
 
    public RequestBodyReaderAuthenticationFilter() {
    }
 
    public RequestBodyReaderAuthenticationFilter(AuthenticationManager authenticationManagerBean) {
		this.setAuthenticationManager(authenticationManagerBean);
	}

	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String requestBody;
        try {
//            requestBody = IOUtils.toString(request.getReader());
//            request.getParameter("username");
            LoginRequest authRequest = new LoginRequest();
            authRequest.setUsername(request.getParameter(USERNAME_KEY));
            authRequest.setPassword(request.getParameter(PASSWORD_KEY));
            // TO DO CUSTOM VALIDATE USER REQUEST HERER
// 
//            UsernamePasswordAuthenticationToken token
//                = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
 
            // Allow subclasses to set the "details" property
//            setDetails(request, token);
            return super.attemptAuthentication(request, response);
//            return this.getAuthenticationManager().authenticate(token);
        } catch(Exception e) {
            LOG.error(ERROR_MESSAGE, e);
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }
    }
}
package org.codelanka.authserver.service.impl;

import org.codelanka.authserver.models.AuthenticationRequest;
import org.codelanka.authserver.models.AuthenticationResponse;
import org.codelanka.authserver.service.Authentication;
import org.codelanka.authserver.utils.AuthTokenUtil;
import org.codelanka.authserver.utils.RefreshTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationImpl implements Authentication {
    @Qualifier("myUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthTokenUtil authTokenUtil;

    @Autowired
    private RefreshTokenUtil refreshTokenUtil;

    @Override
    public AuthenticationResponse getAuthenticationResponse(AuthenticationRequest authenticationRequest) {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String authToken = authTokenUtil.generateToken(userDetails);
        final String refreshToken = refreshTokenUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAuthToken(authToken);
        authenticationResponse.setRefreshToken(refreshToken);
        authenticationResponse.setUserId(userDetails.getUsername());
        authenticationResponse.setUserRole(String.valueOf(userDetails.getAuthorities()));
        return authenticationResponse;
    }

    @Override
    public Boolean validateToken(String token) {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authTokenUtil.extractUsername(token));
        final Boolean validateToken = authTokenUtil.validateToken(token,userDetails);
        return validateToken;
    }

    @Override
    public AuthenticationResponse renewAuthToken(String refreshToken) {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(refreshTokenUtil.extractUsername(refreshToken));
        final String authToken = authTokenUtil.generateToken(userDetails);
        final String newRefreshToken = refreshTokenUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAuthToken(authToken);
        authenticationResponse.setRefreshToken(newRefreshToken);
        authenticationResponse.setUserId(userDetails.getUsername());
        authenticationResponse.setUserRole(String.valueOf(userDetails.getAuthorities()));
        return authenticationResponse;
    }
}

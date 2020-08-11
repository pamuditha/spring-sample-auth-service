package org.codelanka.authserver.service;

import org.codelanka.authserver.models.AuthenticationRequest;
import org.codelanka.authserver.models.AuthenticationResponse;

public interface Authentication {
    AuthenticationResponse getAuthenticationResponse(final AuthenticationRequest authenticationRequest);

    Boolean validateToken(final String token);

    AuthenticationResponse renewAuthToken(final String refreshToken);
}

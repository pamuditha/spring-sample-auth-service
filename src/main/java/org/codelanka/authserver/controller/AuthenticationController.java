package org.codelanka.authserver.controller;

import org.codelanka.authserver.models.AuthenticationRequest;
import org.codelanka.authserver.models.AuthenticationResponse;
import org.codelanka.authserver.service.Authentication;
import org.codelanka.authserver.service.impl.MyUserDetailsService;
import org.codelanka.authserver.utils.AuthTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private Authentication authentication;

    /**
     *
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final AuthenticationResponse authResponse = authentication.getAuthenticationResponse(authenticationRequest);

        return ResponseEntity.ok(authResponse);
    }

    /**
     *
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/validatetoken", method = RequestMethod.GET)
    public ResponseEntity<?> validateAuthenticationToken(@RequestParam String token) throws Exception {
        return ResponseEntity.ok(authentication.validateToken(token));
    }

    /**
     *
     * @param refreshToken
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/renewauthtoken", method = RequestMethod.GET)
    public ResponseEntity<?> renewAuthToken(@RequestParam String refreshToken) throws Exception {
        return ResponseEntity.ok(authentication.renewAuthToken(refreshToken));
    }

}


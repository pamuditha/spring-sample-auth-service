package org.codelanka.authserver.controller;

import org.codelanka.authserver.Exception.exceptions.InvalidRequestException;
import org.codelanka.authserver.models.PasswordDto;
import org.codelanka.authserver.models.User;
import org.codelanka.authserver.models.UserRegistation;
import org.codelanka.authserver.service.UserRegistationService;
import org.codelanka.authserver.service.impl.MyUserDetailsService;
import org.codelanka.authserver.utils.AuthTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 */
@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRegistationService userRegistationService;
    private String userName;

    /**
     *
     * @param Authorization
     * @param userName
     * @return
     * @throws Exception
     */
    @GetMapping("/users/{userName}")
    @PreAuthorize("hasAnyRole('USER') || hasAnyRole('ADMIN')")
    public ResponseEntity<Optional<UserRegistation>> getUser(@RequestHeader("Authorization") String Authorization, @PathVariable("userName") String userName) throws Exception {

        return ResponseEntity.ok(userRegistationService.getUserDetails(userName));
    }

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<UserRegistation> createUser(@RequestBody UserRegistation user) throws Exception {

        final UserRegistation userRes = userRegistationService.createUser(user);
        return ResponseEntity.ok(userRes);
    }

    /**
     * @param Authorization
     * @param userName
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users/{userName}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('USER') || hasAnyRole('ADMIN')")
    public ResponseEntity<UserRegistation> updateUser(@RequestHeader("Authorization") String Authorization, @PathVariable("userName") String userName, @RequestBody UserRegistation user) throws Exception {
        if (!userName.equals(user.getUserName())) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "4001", "");
        }
        return ResponseEntity.ok(userRegistationService.updateUser(userName, user));
    }

    /**
     *
     * @param Authorization
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users/{userName}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('USER') || hasAnyRole('ADMIN')")
    @Transactional
    public ResponseEntity<Boolean> deleteUser(@RequestHeader("Authorization") String Authorization, @PathVariable("userName") String userName) throws Exception {

        userRegistationService.deleteUser(userName);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "users/changePassword", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('USER') || hasAnyRole('ADMIN')")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String Authorization, @RequestBody PasswordDto passwordDto) throws Exception {
        if (passwordDto.getNewPassword().equals(passwordDto.getOldPassword())) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "4001", "Old Password and new password equal");
        }
        return ResponseEntity.ok(userRegistationService.updatePassword(passwordDto));
    }

    @RequestMapping(value = "users/resetPassword",method = RequestMethod.POST)
    public ResponseEntity<?> getResetPasswordToken(@RequestBody User user){
        userRegistationService.getPasswordResetToken(user.getUserName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "users/password/{token}",method = RequestMethod.PUT)
    public ResponseEntity getResetPasswordToken(@RequestHeader("ResetToken") String Token ,@RequestBody PasswordDto passwordDto){
        return ResponseEntity.ok(userRegistationService.validatePasswordResetToken(Token,passwordDto));
    }
}

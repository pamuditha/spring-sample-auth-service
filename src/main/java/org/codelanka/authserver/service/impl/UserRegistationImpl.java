package org.codelanka.authserver.service.impl;

import org.codelanka.authserver.Exception.exceptions.InvalidRequestException;
import org.codelanka.authserver.Exception.exceptions.ResourceNotFoundExecption;
import org.codelanka.authserver.Exception.exceptions.UnauthorizedException;
import org.codelanka.authserver.models.PasswordDto;
import org.codelanka.authserver.models.PasswordResetToken;
import org.codelanka.authserver.models.User;
import org.codelanka.authserver.models.UserRegistation;
import org.codelanka.authserver.repository.RegistationRepository;
import org.codelanka.authserver.repository.PasswordTokenRepository;
import org.codelanka.authserver.service.UserRegistationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRegistationImpl implements UserRegistationService {

    @Autowired
    private RegistationRepository registationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Autowired
    private MailClient mailClient;

    @Override
    public Optional<UserRegistation> getUserDetails(final String userName) {
        Optional<UserRegistation> userResUser = null;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (userName.equalsIgnoreCase(authentication.getName()) || authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            userResUser = Optional.ofNullable(registationRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundExecption(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "User not Found")));
        }
        return userResUser;
    }

    @Override
    public UserRegistation createUser(final UserRegistation userRegistation) {
        UserRegistation userRes;
        try {
            userRegistation.setPassword(passwordEncoder.encode(userRegistation.getPassword()));
            userRes = registationRepository.save(userRegistation);
        } catch (Exception e) {
            throw new InvalidRequestException(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), "Some thing missing");

        }
        return userRes;
    }

    @Override
    public UserRegistation updateUser(final String userName, final UserRegistation user) {
        UserRegistation userResUser = null;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (user.getUserName().equalsIgnoreCase(authentication.getName()) || authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            if (registationRepository.findByUserName(userName).isPresent()) {
                userResUser = registationRepository.save(user);
            } else {
                throw new ResourceNotFoundExecption(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), "User not found");
            }
        } else {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Please check user access");
        }
        return userResUser;
    }

    @Override
    public void deleteUser(final String userName) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userName.equalsIgnoreCase(authentication.getName()) || authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            registationRepository.deleteByUserName(userName).orElseThrow(() -> new ResourceNotFoundExecption(404, "4002", "user not found"));
        } else {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Please check user access");
        }
    }

    @Override
    public boolean updatePassword(final PasswordDto passwordDto){
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserRegistation> userDetails = Optional.ofNullable(registationRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundExecption(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "User not Found")));
        System.out.println(passwordEncoder.encode(passwordDto.getNewPassword()));
        System.out.println(userDetails.get().getPassword());
        if(passwordEncoder.matches(passwordDto.getOldPassword(),userDetails.get().getPassword())){
            userDetails.get().setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
             registationRepository.save(userDetails.get());
        }else {
            throw new UnauthorizedException(403,"4008","Invalid Password");
        }

        return true;
    }
     @Override
    public void getPasswordResetToken(final String userName){

         Optional<UserRegistation> userRegistation = Optional.ofNullable(registationRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundExecption(404, HttpStatus.NOT_FOUND.getReasonPhrase(), "user not Found")));
         User user = new User();
         user.setId(userRegistation.get().getId());
         user.setActive(userRegistation.get().isActive());
         final String token = UUID.randomUUID().toString();
         PasswordResetToken myToken = new PasswordResetToken(token,user);
         passwordTokenRepository.save(myToken);
         mailClient.prepareAndSend(userRegistation.get().getEmail(),"<h1> Token </h2>" + token);

    }

    @Override
    public Boolean validatePasswordResetToken(final String token,final PasswordDto passwordDto){
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        return !isTokenFound(passToken) ? false
                : isTokenExpired(passToken) ? false
                : updateUserPassword(passToken,passwordDto) ;
    }

    private boolean updateUserPassword(final PasswordResetToken passwordResetToken , final PasswordDto passwordDto){
      final String userName =  passwordResetToken.getUser().getUserName();
        Optional<UserRegistation> userResUser = registationRepository.findByUserName(userName);
        if(!passwordEncoder.matches(passwordDto.getNewPassword(),userResUser.get().getPassword())){
            userResUser.get().setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            registationRepository.save(userResUser.get());
        }else {
            throw new UnauthorizedException(403,"4008","Invalid Password");
        }
        return true;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }


    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}

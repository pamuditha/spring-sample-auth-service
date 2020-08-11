package org.codelanka.authserver.service;

import org.codelanka.authserver.models.PasswordDto;
import org.codelanka.authserver.models.UserRegistation;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

public interface UserRegistationService {
    Optional<UserRegistation> getUserDetails(final String userName) throws ChangeSetPersister.NotFoundException;

    UserRegistation createUser(final UserRegistation userRegistation);

    UserRegistation updateUser(final String userName, final UserRegistation user);

    void deleteUser(final String userName);

    boolean updatePassword(final PasswordDto passwordDto);

    void getPasswordResetToken(final String userName);

    Boolean validatePasswordResetToken(final String token,final PasswordDto passwordDto);
}

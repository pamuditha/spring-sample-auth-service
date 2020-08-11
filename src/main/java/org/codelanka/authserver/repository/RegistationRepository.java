package org.codelanka.authserver.repository;

import org.codelanka.authserver.models.UserRegistation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistationRepository extends JpaRepository <UserRegistation,Integer>{
    Optional<UserRegistation> findByUserName(final String userName);

    Optional<UserRegistation> deleteByUserName(final String userName);
}

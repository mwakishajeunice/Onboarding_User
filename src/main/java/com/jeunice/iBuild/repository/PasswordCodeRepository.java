package com.jeunice.iBuild.repository;

import com.jeunice.iBuild.model.AppUser;
import com.jeunice.iBuild.model.PasswordToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface PasswordCodeRepository extends JpaRepository<PasswordToken, Long> {

    @Query("select p from PasswordToken p where p.code=?1")
    Optional<PasswordToken> checkCode(String code);
}

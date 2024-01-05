package com.jeunice.iBuild.repository;

import com.jeunice.iBuild.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {

    Optional<Verification> findByCode(String code);
}

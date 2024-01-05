package com.jeunice.iBuild.repository;

import com.jeunice.iBuild.model.Employers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employers, Integer> {
}

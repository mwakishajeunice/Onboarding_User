package com.jeunice.iBuild.repository;

import com.jeunice.iBuild.model.Titles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitlesRepository extends JpaRepository<Titles, Long> {
}

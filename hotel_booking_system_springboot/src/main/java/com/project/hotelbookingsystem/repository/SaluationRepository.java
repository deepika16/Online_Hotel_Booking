package com.project.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotelbookingsystem.model.Saluation;

@Repository
public interface SaluationRepository extends JpaRepository<Saluation, Long> {

}

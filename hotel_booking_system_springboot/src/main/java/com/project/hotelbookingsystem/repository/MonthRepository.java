package com.project.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotelbookingsystem.model.Month;

@Repository
public interface MonthRepository extends JpaRepository<Month, Long> {

}

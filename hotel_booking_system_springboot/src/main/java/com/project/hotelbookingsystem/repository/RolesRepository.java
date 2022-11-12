package com.project.hotelbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotelbookingsystem.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}

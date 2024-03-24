package com.example.reviewservice.repository;

import com.example.reviewservice.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    RolesEntity findByRole(String role);
}

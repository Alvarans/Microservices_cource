package com.example.userservice.repository;

import com.example.userservice.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
    RolesEntity findByRoleId(int id);

    RolesEntity findByRole(String role);
}

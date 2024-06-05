package com.pms.repository;

import com.pms.entity.Permission;
import com.pms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionsRepo extends JpaRepository<Permission, Long> {
    Optional<Permission> findByPermissionName(String permission);
}

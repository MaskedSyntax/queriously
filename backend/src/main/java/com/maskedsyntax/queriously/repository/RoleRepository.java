package com.maskedsyntax.queriously.repository;

import com.maskedsyntax.queriously.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);
}

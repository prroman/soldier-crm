package com.rpr.soldierscrm.repository;

import com.rpr.soldierscrm.entity.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
    SystemRole findByName(String name);
}

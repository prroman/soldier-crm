package com.rpr.soldierscrm.repository;

import com.rpr.soldierscrm.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    Optional<SystemUser> findByUserName(String userName);
}

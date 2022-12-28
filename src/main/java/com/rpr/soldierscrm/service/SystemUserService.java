package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.entity.SystemRole;
import com.rpr.soldierscrm.entity.SystemUser;

import java.util.List;

public interface SystemUserService {

    SystemUser saveUser(SystemUser systemUser);
    SystemRole saveRole(SystemRole systemRole);
    void addRoleToUser(String userName, String roleName);
    SystemUser getUser(String userName);
    List<SystemUser> getUsers();
}

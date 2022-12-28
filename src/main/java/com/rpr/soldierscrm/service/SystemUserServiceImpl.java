package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.entity.SystemRole;
import com.rpr.soldierscrm.entity.SystemUser;
import com.rpr.soldierscrm.repository.SystemRoleRepository;
import com.rpr.soldierscrm.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SystemUserServiceImpl implements SystemUserService, UserDetailsService {

    private final SystemUserRepository systemUserRepository;
    private final SystemRoleRepository systemRoleRepository;

    @Override
    public SystemUser saveUser(SystemUser systemUser) {
        return systemUserRepository.save(systemUser);
    }

    @Override
    public SystemRole saveRole(SystemRole systemRole) {
        return systemRoleRepository.save(systemRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        Optional<SystemUser> systemUser = systemUserRepository.findByUserName(userName);
        systemUser.ifPresent(user -> user.getRoles().add(systemRoleRepository.findByName(roleName)));
    }

    @Override
    public SystemUser getUser(String userName) {
        return systemUserRepository
                .findByUserName(userName).orElseThrow(() -> new RuntimeException("User with username " + userName + "not found"));
    }

    @Override
    public List<SystemUser> getUsers() {
        return systemUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.
                findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        systemUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(systemUser.getUserName(), systemUser.getPassword(), authorities);
    }
}

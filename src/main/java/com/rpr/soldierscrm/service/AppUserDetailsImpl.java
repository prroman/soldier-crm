package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.entity.AppUser;
import com.rpr.soldierscrm.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserDetailsImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);
        if (appUserOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        AppUser appUser = appUserOptional.get();
        return User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(appUser.getAuthorities())
                .build();
    }

    public AppUser createUser(AppUser user) {
        return appUserRepository.save(user);
    }
}

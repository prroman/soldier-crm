package com.rpr.soldierscrm;

import com.rpr.soldierscrm.service.AppUserDetailsImpl;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SoldiersCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoldiersCrmApplication.class, args);
    }
    @Bean
    public ApplicationRunner runner(AppUserDetailsImpl service, PasswordEncoder passwordEncoder) {
        return (args) -> {
/*            AppUser appUser = new AppUser("admin", passwordEncoder.encode("password"), List.of("ADMIN", "SUPER_ADMIN"));
            service.createUser(appUser);
            service.createUser(new AppUser("user", passwordEncoder.encode("password"), List.of("ADMIN")));

            System.out.println(appUser.getAuthorities());
            System.out.println(appUser.getRoles());*/

            UserDetails admin = service.loadUserByUsername("admin");
            UserDetails user = service.loadUserByUsername("user");

            System.out.println(admin.getAuthorities());
            System.out.println(user.getAuthorities());
        };
    }
}

package com.rpr.soldierscrm;

import com.rpr.soldierscrm.entity.SystemRole;
import com.rpr.soldierscrm.entity.SystemUser;
import com.rpr.soldierscrm.service.SystemUserService;
import com.rpr.soldierscrm.service.SystemUserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SoldiersCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoldiersCrmApplication.class, args);
    }

/*    @Bean
    CommandLineRunner commandLineRunner(SystemUserService systemUserService) {
        return args -> {
            systemUserService.saveRole(new SystemRole(null, "ROLE_USER"));
            systemUserService.saveRole(new SystemRole(null, "ROLE_MANAGER"));
            systemUserService.saveRole(new SystemRole(null, "ROLE_ADMIN"));

            systemUserService.saveUser(new SystemUser(null, "user", "John Doe", "password1", new ArrayList<>()));
            systemUserService.saveUser(new SystemUser(null, "manager", "Anna Smith", "password2", new ArrayList<>()));
            systemUserService.saveUser(new SystemUser(null, "admin", "Roman Protas", "password3", new ArrayList<>()));

            systemUserService.addRoleToUser("user", "ROLE_USER");
            systemUserService.addRoleToUser("manager", "ROLE_MANAGER");
            systemUserService.addRoleToUser("admin", "ROLE_ADMIN");
        };
    }*/

}

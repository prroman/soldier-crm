package com.rpr.soldierscrm.controller;

import com.rpr.soldierscrm.entity.SystemUser;
import com.rpr.soldierscrm.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/system-users")
@RequiredArgsConstructor
public class SystemUserController {

    private final SystemUserService systemUserService;

    @GetMapping("/all")
    public List<SystemUser> getSystemUsers() {
        return systemUserService.getUsers();
    }

}

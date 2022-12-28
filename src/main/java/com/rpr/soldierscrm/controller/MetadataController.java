package com.rpr.soldierscrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MetadataController {


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/donate")
    public String donate() {
        return "donate";
    }
}

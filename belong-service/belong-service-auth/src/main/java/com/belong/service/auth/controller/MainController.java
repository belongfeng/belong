package com.belong.service.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: fengyu
 * @CreateDate: 2020/1/3 17:26
 */
@RestController
public class MainController {
    @RequestMapping("/user")
    public Object getUser(Authentication authentication) {
        return authentication;
    }
}

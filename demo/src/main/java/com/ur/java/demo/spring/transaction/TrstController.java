package com.ur.java.demo.spring.transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trst")
public class TrstController {
    private final UserDao userDaoImpl;

    public TrstController(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @GetMapping("/user/add")
    public String addUser() {
        userDaoImpl.add();
        return "add success";
    }

    @GetMapping("/user/del")
    public String delUser() {
        userDaoImpl.del();
        return "del success";
    }
}

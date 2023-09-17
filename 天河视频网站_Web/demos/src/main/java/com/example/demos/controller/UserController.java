package com.example.demos.controller;

import com.example.demos.pojo.User;
import com.example.demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //=Controller+ResponseBody注解
@RequestMapping("/user")
@CrossOrigin // 允许跨域
public class UserController {
    @Autowired //根据类型注入
    private UserService userService;

    // 根据用户昵称，查询个人信息
    // "http://localhost:8081/user/list?uname=" + name
    @RequestMapping("/list")
    public User userList(String uname) {
        return userService.userList(uname);
    }

    @RequestMapping("/users")
    User users(String uid){
        return userService.users(uid);
    }

    // 更改昵称
    // "http://localhost:8081/user/unUpdate"
    @RequestMapping("/unUpdate")
    public int uNameUpdate(String uname, String uid) {
        return userService.uNameUpdate(uname, uid);
    }

    // 更改个人简介
    // "http://localhost:8081/user/uiUpdate"
    @RequestMapping("/uiUpdate")
    public int uIntrosUpdate(String uint, String uid) {
        return userService.uIntrosUpdate(uint, uid);
    }

    // 更改个人信息
    // "http://localhost:8081/user/uifUpdate"
    @RequestMapping("/uifUpdate")
    public int uInfoUpdate(String uname, String upwd, String ugender, String uage, String uid) {
        return userService.uInfoUpdate(uname, upwd, ugender, uage, uid);
    }



    @RequestMapping("/regis")
    public Integer Register(String uname, String runname, String pwd){
        return userService.Register(uname, runname, pwd);
    }

    @RequestMapping("/names")
    public User Unames(String uname){
        User a = userService.Unames(uname);
        System.out.println(a);
        return a;
    }
}

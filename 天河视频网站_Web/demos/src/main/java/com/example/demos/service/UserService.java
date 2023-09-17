package com.example.demos.service;

import com.example.demos.pojo.User;
import io.swagger.models.auth.In;

import java.util.List;
public interface UserService {
    User userList(String uname);
    User users(String uid);
    Integer uNameUpdate(String uname, String uid);
    Integer uIntrosUpdate(String uintros, String uid);
    Integer uInfoUpdate(String uname, String upwd, String ugender, String uage, String uid);
    Integer Register(String uname, String runname, String pwd);
    User Unames(String uname);
}

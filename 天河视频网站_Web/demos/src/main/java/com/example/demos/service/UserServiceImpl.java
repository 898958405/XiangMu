package com.example.demos.service;

import com.example.demos.dao.UserDao;
import com.example.demos.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User userList(String uname) {
        return userDao.userList(uname);
    }

    @Override
    public User users(String uid) {
        return userDao.users(uid);
    }

    @Override
    public Integer uNameUpdate(String uname, String uid) {
        return userDao.uNameUpdate(uname, uid);
    }

    @Override
    public Integer uIntrosUpdate(String uintros, String uid) {
        return userDao.uIntrosUpdate(uintros, uid);
    }

    @Override
    public Integer uInfoUpdate(String uname, String upwd, String ugender, String uage, String uid) {
        return userDao.uInfoUpdate(uname, upwd, ugender, uage, uid);
    }

    @Override
    public Integer Register(String uname, String runname, String pwd) {
        return userDao.Register(uname, runname, pwd);
    }

    @Override
    public User Unames(String uname) {
        return userDao.Unames(uname);
    }

}

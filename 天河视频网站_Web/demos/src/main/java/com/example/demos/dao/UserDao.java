package com.example.demos.dao;

import com.example.demos.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper //标明他是一个dao接口
public interface UserDao {
    // 根据昵称，查询用户信息
    User userList(String uname);
    User users(String uid);
    // 昵称更改
    Integer uNameUpdate(@Param("uname") String uname, @Param("uid") String uid);
    // 个人简介更改
    Integer uIntrosUpdate(@Param("uintros") String uintros, @Param("uid") String uid);
    // 个人信息更改
    Integer uInfoUpdate(@Param("uname") String uname, @Param("upwd") String upwd,
            @Param("ugender") String ugender, @Param("uage") String uage, @Param("uid") String uid);

    Integer Register(String uname, String runname, String pwd);

    User Unames(String uname);
}

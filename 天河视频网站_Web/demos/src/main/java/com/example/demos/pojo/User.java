package com.example.demos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // get/set方法
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 有参构造
@ToString
public class User {
    private Integer uId;
    private String uName;
    private String uPassword;
    private String uNickName;
    private String uGender;
    private Integer uAdmin;
    private Integer uAge;
    private String uIntros;
}

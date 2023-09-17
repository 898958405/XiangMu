package com.example.demos.pojo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // get/set方法
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 有参构造
@ToString
public class Comment {
    private Integer dId;
    private Integer vId;
    private String dText;
    private String dDate;
}

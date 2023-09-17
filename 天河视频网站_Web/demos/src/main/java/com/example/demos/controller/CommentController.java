package com.example.demos.controller;

import com.example.demos.pojo.Comment;
import com.example.demos.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //=Controller+ResponseBody注解
@RequestMapping("/comment")
@CrossOrigin // 允许跨域
public class CommentController {
    @Autowired //根据类型注入
    private CommentService commentService;

    @RequestMapping("/cList")
    List<Comment> cLists(int vid) {
        return commentService.cLists(vid);
    }

    @RequestMapping("/cInsert")
    Integer cInsert(int vid, String text, String time){
        System.out.println("date"+time);
        return commentService.cInsert(vid, text, time);
    }
}

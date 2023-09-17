package com.example.demos.service;

import com.example.demos.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> cLists(int vid);
    Integer cInsert(int vid, String text, String date);

}

package com.example.demos.dao;

import com.example.demos.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
    List<Comment> cLists(int vid);

    Integer cInsert(int vid, String text, String date);
}

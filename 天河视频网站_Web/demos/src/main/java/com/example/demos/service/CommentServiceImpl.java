package com.example.demos.service;

import com.example.demos.dao.CommentDao;
import com.example.demos.pojo.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;
    @Override
    public List<Comment> cLists(int vid) {
        return commentDao.cLists(vid);
    }

    @Override
    public Integer cInsert(int vid, String text, String date) {
        return commentDao.cInsert(vid, text, date);
    }
}

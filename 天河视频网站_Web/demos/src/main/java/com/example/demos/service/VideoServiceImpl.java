package com.example.demos.service;

import com.example.demos.dao.VideoDao;
import com.example.demos.pojo.Video;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService  {
    @Resource
    private VideoDao videoDao;

    @Override
    public List<Video> List() {
        return videoDao.List();
    }

    @Override
    public List<Video> videoList(String uid) {
        return videoDao.videoList(uid);
    }

    @Override
    public Integer vInfoUpdate(String vname, String vclassify, String vIntroduction, String vid) {
        return videoDao.vInfoUpdate(vname, vclassify, vIntroduction, vid);
    }

    @Override
    public Integer vDelete(String vid) {
        return videoDao.vDelete(vid);
    }

    @Override
    public Integer vUpload(String uid, String introduction, String address, String region, String title){
        return videoDao.vUpload(uid, introduction, address, region, title);
    }

    @Override
    public Video videoItem(String vid) {
        return videoDao.videoItem(vid);
    }

    @Override
    public List<Video> vSearch(String key) {
        return videoDao.vSearch(key);
    }

    @Override
    public List<Video> PaiHang() {
        return videoDao.PaiHang();
    }
}

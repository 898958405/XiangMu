package com.example.demos.service;

import com.example.demos.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> List();
    List<Video> videoList(String uid);
    Integer vInfoUpdate(String vname, String vclassify, String vIntroduction, String vid);
    Integer vDelete(String vid);
    Integer vUpload(String uid, String introduction, String address, String region, String title);

    Video videoItem(String vid);

    List<Video> vSearch(String key);
    List<Video> PaiHang();


}

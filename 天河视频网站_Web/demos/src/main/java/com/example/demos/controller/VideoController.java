package com.example.demos.controller;

import com.example.demos.pojo.Video;
import com.example.demos.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //=Controller+ResponseBody注解
@RequestMapping("/video")
@CrossOrigin // 允许跨域
public class VideoController {
    @Autowired //根据类型注入
    private VideoService videoService;
    @RequestMapping("/listl")
    List<Video> List() {
        return videoService.List();
    }

    // 根据用户id，查询视频信息
    // "http://localhost:8081/video/list?uid=" + uid
    @RequestMapping("/list")
    public List<Video> videoList(String uid) {
        return videoService.videoList(uid);
    }

    // 更改视频信息
    // "http://localhost:8081/video/vifUpdate"
    @RequestMapping("/vifUpdate")
    public int vInfoUpdate(String vname, String vclassify, String vIntroduction, String vid) {
        return videoService.vInfoUpdate(vname, vclassify, vIntroduction, vid);
    }

    // 删除视频
    // "http://localhost:8081/video/vDelete?vid"+vid
    @RequestMapping("/vDelete")
    public int vDelete(String vid) {
        return videoService.vDelete(vid);
    }

    // 添加视频
    // "http://localhost:8081/video/vUpload”
    @RequestMapping("/vUpload")
    public int vUpload(String uid, String introduction, String address, String region, String title) {
        return videoService.vUpload(uid, introduction, address, region, title);
    }

    // 根据视频id查询信息
    // "http://localhost:8081/video/videoItem”
    @RequestMapping("/videoItem")
    Video videoItem(String vid) {
        return videoService.videoItem(vid);
    }

    // 根据视频id查询信息
    // "http://localhost:8081/video/videoItem”
    @RequestMapping("/vSearch")
    List<Video> vSearch(String key) {
        System.out.println("key: "+key);
        return videoService.vSearch(key);
    }

    @RequestMapping("/vranking")
    List<Video> PaiHang() {
        System.out.println(videoService.PaiHang());
        return videoService.PaiHang();
    }


}

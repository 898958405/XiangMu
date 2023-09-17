package com.example.demos.dao;

import com.example.demos.pojo.Video;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    List<Video> List();
    // 根据用户id，查询视频信息
    List<Video> videoList(String uid);

    Integer vInfoUpdate(@Param("vname") String vname, @Param("vclassify") String vclassify,
                        @Param("vIntroduction") String ugender, @Param("vid") String vid);

    Integer vDelete(String vid);

    Integer vUpload(String uid, String introduction, String address, String region, String title);
    Video videoItem(String vid);
    List<Video> vSearch(String keys);
    List<Video> PaiHang();
}

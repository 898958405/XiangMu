package com.example.demos;

import com.example.demos.pojo.Comment;
import com.example.demos.pojo.User;
import com.example.demos.pojo.Video;
import com.example.demos.service.CommentService;
import com.example.demos.service.UserService;
import com.example.demos.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemosApplicationTests {
    @Resource
    VideoService videoService;

    @Test
    void selectl() {
        List<Video> re = videoService.PaiHang();
        for (int i = 0; i <re.size(); i++) {
            System.out.println(re.get(i));
        }
    }
//    @Test
//    void selectlss() {
//        User re = users.getUserById(1);
//        System.out.println(re);
//    }

}

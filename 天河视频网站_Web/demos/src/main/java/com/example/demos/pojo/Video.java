package com.example.demos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // get/set方法
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 有参构造
@ToString
public class Video {
    private Integer vId; // 视频id
    private Integer uId; // 用户id
    private String vIntroduction; // 视频介绍
    private String vAddress; // 视频地址
    private String vCountsvCounts; // 计数
    private String classify; // 视频类别
    private String vName; // 视频标题

    public String getvImg() {
        return vImg;
    }

    public void setvImg(String vImg) {
        this.vImg = vImg;
    }

    private String vImg; // 视频标题

    public Integer getvId() {
        return vId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getvIntroduction() {
        return vIntroduction;
    }

    public void setvIntroduction(String vIntroduction) {
        this.vIntroduction = vIntroduction;
    }

    public String getvAddress() {
        return vAddress;
    }

    public void setvAddress(String vAddress) {
        this.vAddress = vAddress;
    }

    public String getvCountsvCounts() {
        return vCountsvCounts;
    }

    public void setvCountsvCounts(String vCountsvCounts) {
        this.vCountsvCounts = vCountsvCounts;
    }



    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }
}

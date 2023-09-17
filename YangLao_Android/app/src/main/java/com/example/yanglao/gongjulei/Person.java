package com.example.yanglao.gongjulei;

public class Person {
    private String title; // 标题
//    private String status; // 状态
    private String time; // 下单事件
    private String mony; // 价格
    public Person() {}
    public Person(String title, String time, String mony) {
        this.title = title;
//        this.status = status;
        this.time = time;
        this.mony = mony;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getStatus() {
//        return status;
//    }

//    public void setStatus(String status) {
//        this.status = status;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMony() {
        return mony;
    }

    public void setMony(String mony) {
        this.mony = mony;
    }

    @Override
    public String toString() {
        return "Person{" +
                "title='" + title + '\'' +
//                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", mony='" + mony + '\'' +
                '}';
    }
}

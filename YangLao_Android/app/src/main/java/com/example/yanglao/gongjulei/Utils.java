package com.example.yanglao.gongjulei;

import android.util.Log;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
// 》》》》》连接数据库《《《《《《《《《
public class Utils {
    private static Utils instance;

    public static Utils getInstance(){
        if (instance ==null){
            instance = new Utils();
        }
        return instance;
    }
//139.196.102.204
    public static Connection getConnection(String sjkName, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://47.98.53.251:3306/"+sjkName+"?serverTimezone=UTC&" +
                    "useSSL=false&"+"allowPublicKeyRetrieval=true&"+"serverTimezone=GMT%2b8&"+
                    "useUnicode=true&"+"characterEncoding=UTF-8&"+"zeroDateTimeBehavior=convertToNull"+
                    "&autoReconnect=true&allowMultiQueries=true";

            Log.e("Utils", "-->数据库连接成功<--" );

            return (Connection) DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            Log.e("Utils", "-->无数据库连接<--" );
            e.printStackTrace();
            return null;
        }
    }
}

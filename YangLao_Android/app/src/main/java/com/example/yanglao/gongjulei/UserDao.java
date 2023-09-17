package com.example.yanglao.gongjulei;

import static com.example.yanglao.MainActivity.MyLocationListener.jingdu;
import static com.example.yanglao.MainActivity.MyLocationListener.weidu;

import android.annotation.SuppressLint;
import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

// 》》》》实现注册，登录，找回密码等功能《《《《
public class UserDao {
    static String shujuku_mingcheng = "hx";
    Connection connection = Utils.getConnection(shujuku_mingcheng,"root","377987309hm");
    // ----------------------------------------注册过程开始-------------------------------------------
    //【注册，登录，改密码】查询账号是否已注册
    public String zc_Cha_SF_zhuce(String phone){
        String srcS = null;
        if (connection == null) {
            Log.e("UserDao_zc_Cha_SF_zhuce", "-->无数据库连接<--");
            return "sorry";
        } else {
            String sql = "select phone from user where phone = '"+phone+"'";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    srcS = (String) resultSet.getObject("phone");
                    Log.e("UserDao_zc_Cha_SF_zhuce", srcS+"(该手机号已注册)");

                }
                Log.e("UserDao_zc_Cha_SF_zhuce", "-->是否注册，查询成功<--");
                resultSet.close();
                statement.close();
                return srcS;
            } catch (SQLException e) {
                Log.e("UserDao_zc_Cha_SF_zhuce", "-->查询失败<--");
                e.printStackTrace();
                return null;
            }
        }
    }
    //【注册】
    public int ZhuCe(String phone,String pwd,String mibao){
        if(connection == null){
            Log.e("UserDao_ZhuCe", "-->无数据库连接<--" );
            return 0;
        }else {
            String sql ="INSERT INTO user(phone, pwd, mibao) " +
                    "VALUES('"+phone+"', '"+pwd+"', '"+mibao+"');";
            try {
                Statement statement = connection.createStatement();
                int number = statement.executeUpdate(sql);
                Log.e("UserDao_ZhuCe", "-->注册成功<--");
                statement.close();
                return number;
            } catch (SQLException e) {
                Log.e("UserDao_ZhuCe", "-->注册失败<--");
                e.printStackTrace();
                return 0;
            }
        }
    }
    // --------------------------------------注册过程结束---------------------------------------------

    // --------------------------------------登录过程开始---------------------------------------------
    //【退出登录】，将登录状态改为未登录状态
    public int dl_Deng_weiDeng(String phone){
        if (connection == null) {
            Log.e("UserDao_dl_Deng_weiDeng", "-->无数据库连接<--");
            return -1;
        } else {
            String sql = "UPDATE user set zhuangtai = 0 WHERE phone = '"+ phone+"'";
            try {
                Statement statement = connection.createStatement();
                int number = statement.executeUpdate(sql);
                Log.e("UserDao_dl_Deng_weiDeng", "-->已登录，已改成未登录<--");
                statement.close();
                return number;
            } catch (SQLException e) {
                Log.e("UserDao_dl_Deng_weiDeng", "-->更改失败<--");
                e.printStackTrace();
                return -1;
            }
        }
    }
    //【登录】
    public String DengLu(String phone,String pwd) {
        String srcS = null;
        if(connection == null){
            Log.e("UserDao_DengLu", "-->无数据库连接<--" );
            return "sorry";
        }else {
            String sql = "SELECT pwd FROM user WHERE phone = '"+phone+"' AND pwd = '"+pwd+"';";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    srcS = (String) resultSet.getObject("pwd");
                }
                Log.e("UserDao_DengLu", "-->账号信息配对成功<--");
                resultSet.close();
                statement.close();
                return srcS;
            } catch (SQLException e) {
                Log.e("UserDao_DengLu", "-->账号信息配对失败<--");
                e.printStackTrace();
                return null;
            }
        }
    }
    //【登录】改为登录状态
    public int dl_Deng_Dingwei(String phone){
        if (connection == null) {
            Log.e("UserDao_dl_Deng_Dingwei", "-->无数据库连接<--");
            return -1;
        } else {
            String sql  = "UPDATE user SET zhuangtai = 1, jingdu = '"+jingdu+"',  weidu = '"+weidu+"' WHERE phone in (select phone where phone = '"+phone+"')";
            try {
                Statement statement = connection.createStatement();
                int number = statement.executeUpdate(sql);
                Log.e("UserDao_dl_Deng_Dingwei", "-->修改成功<--");
                statement.close();
                return number;
            } catch (SQLException e) {
                Log.e("UserDao_dl_Deng_Dingwei", "-->修改失败<--");
                e.printStackTrace();
                return -1;
            }
        }
    }
    //【登录】查询身份证号是否为'Null'
    public String shenfenzhengCX(String phone) {
        String srcS = null;
        if(connection == null){
            Log.e("shenfenzhengCX", "-->无数据库连接<--" );
            return "sorry";
        }else {
            String sql = "SELECT * from sys_oldman WHERE phone = '"+phone+"'";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                System.out.println("ss     :"+resultSet);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    srcS = (String) resultSet.getObject("Id_card");
                }
                Log.e("shenfenzhengCX", "-->账号信息配对成功 Id_card: "+srcS+"<--");
                resultSet.close();
                statement.close();
                return srcS+"s";
            } catch (SQLException e) {
                Log.e("shenfenzhengCX", "-->账号信息配对失败<--");
                e.printStackTrace();
                return "sorrys";
            }
        }
    }

    // --------------------------------------------登录过程结束---------------------------------------

    // -------------------------------------------重置密码开始----------------------------------------
    // 【改密码】查询账号是否存在，是否匹配
    public String zm_Cha_SF_zhuce(String phone, String mibao){
        String srcS = null;
        if (connection == null) {
            Log.e("UserDao_zm_Cha_SF_zhuce", "-->无数据库连接<--");
            return "sorry";
        } else {
            String sql = "select phone, mibao from user where phone = '"+phone+"' and mibao = '"+mibao+"'";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    srcS = (String) resultSet.getObject("mibao");
                    Log.e("UserDao_zm_Cha_SF_zhuce", srcS+"--》账号信息匹配");
                }
                Log.e("UserDao_zm_Cha_SF_zhuce", "-->是否匹配，查询成功<--");
                resultSet.close();
                statement.close();
                return srcS;
            } catch (SQLException e) {
                Log.e("UserDao_zm_Cha_SF_zhuce", "-->查询失败<--");
                e.printStackTrace();
                return null;
            }
        }
    }
    //【改密码】
    public int GaiMiMa(String phone,String pwd){
        if(connection == null){
            Log.e("UserDao_GaiMiMa", "-->无数据库连接<--" );
            return 0;
        }else {
            String sql ="UPDATE user SET pwd = '"+pwd+"' WHERE phone IN (SELECT phone WHERE phone = '"+phone+"')";
            try {
                Statement statement = connection.createStatement();
                int number = statement.executeUpdate(sql);
                Log.e("UserDao_GaiMiMa", "-->密码修改成功<--");
                return number;
            } catch (SQLException e) {
                Log.e("UserDao_GaiMiMa", "-->密码修改失败<--");
                e.printStackTrace();
                return 0;
            }
        }
    }
    // -------------------------------------------重置密码结束----------------------------------------
//
//    // -------------------------------------------在线账号查询开始-------------------------------------
//    //【紧急呼救，预约】
//    public String ZaiXianZhangHao_cx(){
//        String srcS = null;
//        if (connection == null) {
//            Log.e("UserDao_zc_Cha_SF_zhuce", "-->无数据库连接<--");
//            return "sorry";
//        } else {
//            String sql = "select phone from user where zhuangtai = 1";
//            try {
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(sql);
//                //5、取出结果集的数据
//                while (resultSet.next()) {
//                    srcS = (String) resultSet.getObject("phone");
//                }
//                Log.e("UserDao_zc_Cha_SF_zhuce", srcS+ "在线");
//                resultSet.close();
//                statement.close();
//                return srcS;
//            } catch (SQLException e) {
//                Log.e("UserDao_zc_Cha_SF_zhuce", "-->在线查询失败<--");
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//    // -------------------------------------------在线账号查询结束-------------------------------------

    // -------------------------------------------用户下单开始----------------------------------------
    //【预约】
    public int YH_xiadan_cunChu(int number, String id_card, String name, String phone, String address,
                         String ordername, String paymethod, String placetime,
                         String placeremark, String money) {
        if(connection == null){
            Log.e("YH_xiadan_cunChu", "-->无数据库连接<--" );
            return 0;
        }else {
            String sql = "INSERT INTO sys_server(number, id_card, name, phone, address, ordername, " +
                    "paymethod, placetime, placeremark, money) VALUES ("+number+", " +
                    "'"+id_card+"','"+name+"','"+phone+"','"+address+"','"+ordername+"','"+paymethod+"'" +
                    ",'"+placetime+"','"+placeremark+"','"+money+"');";
            try {
                Statement statement = connection.createStatement();
                int num = statement.executeUpdate(sql);
                Log.e("YH_xiadan_cunChu", "-->下单成功<--");
                return num;
            } catch (SQLException e) {
                Log.e("YH_xiadan_cunChu", "-->下单失败<--");
                e.printStackTrace();
                return 0;
            }
        }
    }

    // 查看
    public ArrayList<Person> Chakan(String phone, int num){
        ArrayList<Person> persons=new ArrayList<Person>();
        if (connection == null) {
            Log.e("Chakan", "-->无数据库连接<--");
            return null;
        } else {
            String sql;
            if(num == 0) {
                sql = "select * from sys_server where phone = '"+phone+"'";
            } else {
                sql = "select * from sys_server where phone = '"+phone+"' and orderstate = "+num+"";
            }
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    Person person = new Person(resultSet.getString("ordername"),
                            resultSet.getString("placetime"),
                            resultSet.getString("money"));
                    persons.add(person);
                }
                Log.e("Chakan", "-->查询成功<--");
                resultSet.close();
                statement.close();
                return persons;
            } catch (SQLException e) {
                Log.e("Chakan", "-->在线查询失败<--");
                e.printStackTrace();
                return null;
            }
        }
    }

    // -------------------------------------------用户下单结束----------------------------------------


    // -------------------------------------------登录信息显示开始-------------------------------------
    //【个人信息查看】
    public HashMap<String, Object>  getInfoByName(String phone){
        HashMap<String, Object> map = new HashMap<>();
        if (connection == null) {
            Log.e("getInfoByName", "-->无数据库连接<--");
            return null;
        } else {
            String sql = "select * from user where zhuangtai = 1 and phone = '"+phone+"'";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    map.put("phone", resultSet.getString("phone"));
                    map.put("pwd", resultSet.getString("pwd"));
                    map.put("jijiu", resultSet.getString("jijiu"));
                    map.put("mibao", resultSet.getString("mibao"));
                }
                Log.e("getInfoByName", "-->在线查询成功<--");
                resultSet.close();
                statement.close();
                return map;
            } catch (SQLException e) {
                Log.e("getInfoByName", "-->在线查询失败<--");
                e.printStackTrace();
                return null;
            }
        }
    }

    public HashMap<String, Object>  getInfoByoldman(String phone){
        HashMap<String, Object> map = new HashMap<>();
        if (connection == null) {
            Log.e("getInfoByoldman", "-->无数据库连接<--");
            return null;
        } else {
            String sql = "select * from sys_oldman where phone = '"+phone+"'";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    map.put("name", resultSet.getString("name"));
                    map.put("sex", resultSet.getString("sex"));
                    map.put("age", resultSet.getString("age"));
                    map.put("Id_card", resultSet.getString("Id_card"));
                    map.put("address", resultSet.getString("address"));
                    map.put("birthday", resultSet.getString("birthday"));

                }
                Log.e("getInfoByoldman", "-->在线查询成功<--");
                resultSet.close();
                statement.close();
                return map;
            } catch (SQLException e) {
                Log.e("getInfoByoldman", "-->在线查询失败<--");
                e.printStackTrace();
                return null;
            }
        }
    }

    //【个人信息补充】
    public int XX_TianXie(String name, String sex, String age, String card, String address, String phone, String birthday){
        if(connection == null){
            Log.e("XX_TianXie", "-->无数据库连接<--" );
            return 0;
        }else {
            String sql ="insert into sys_oldman(name, sex, age , phone, Id_card, address, birthday)" +
                    " values('"+name+"', '"+sex+"', '"+age+"', '"+phone+"', '"+card+"', '"+address+"', '"+birthday+"')";
            try {
                Statement statement = connection.createStatement();
                int number = statement.executeUpdate(sql);
                Log.e("XX_TianXie", "-->添加成功  "+number+"<--");
                statement.close();
                return number;
            } catch (SQLException e) {
                Log.e("XX_TianXie", "-->添加失败<--");
                e.printStackTrace();
                return 0;
            }
        }
    }

    public int XX_JingJiLXR(String jijiu, String phone){
        if(connection == null){
            Log.e("XX_JingJiLXR", "-->无数据库连接<--" );
            return 0;
        }else {
            String sql  = "UPDATE user SET jijiu = '"+jijiu+"' WHERE phone = '"+phone+"'";
            try {
                Statement statement = connection.createStatement();
                int number = statement.executeUpdate(sql);
                Log.e("XX_JingJiLXR", "-->添加成功  "+number+"<--");
                statement.close();
                return number;
            } catch (SQLException e) {
                Log.e("XX_JingJiLXR", "-->添加失败<--");
                e.printStackTrace();
                return 0;
            }
        }
    }

    // -------------------------------------------登录信息显示结束-------------------------------------

    // -------------------------------------------一键拨号开始----------------------------------------
    //【一键拨号】
    public static String getBoHao(String phone){
        Connection connection = Utils.getConnection(shujuku_mingcheng,"root","377987309hm");
        String srcS = null;
        if (connection == null) {
            Log.e("getBoHao", "-->无数据库连接<--");
            return null;
        } else {
            String sql = "SELECT jijiu FROM user WHERE phone = '"+phone+"';";
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                //5、取出结果集的数据
                while (resultSet.next()) {
                    srcS = (String) resultSet.getObject("jijiu");
                }
                Log.e("getBoHao", srcS+ "为急救联系方式");
                resultSet.close();
                statement.close();
                return srcS;
            } catch (SQLException e) {
                Log.e("getBoHao", "-->在线查询失败<--");
                e.printStackTrace();
                return null;
            }
        }
    }
    // -------------------------------------------一键拨号结束----------------------------------------

    // -------------------------------------------紧急呼救开始----------------------------------------
    //【紧急呼救】
    public int CALL(int num, String phone){
        String srcS = null;
        if (connection == null) {
            Log.e("UserDao_zc_Cha_SF_zhuce", "-->无数据库连接<--");
            return 0;
        } else {
            String sql = null;
            String []arr = {"一般呼救", "紧急呼救", "我的身体不舒服", "我迷路了", "已拨打120，请求支援"};
            String []arr1 = {"一般呼叫", "紧急呼叫", "服务呼叫", "最高级呼救"};
            // 创建日期对象(获取当前时间部分时间值)
            Date date = new Date();
            // 创建对象(对获取的时间进行加工，便于理解)
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss"); // 2022年10月14日 15:07:45
            // 格式化：将日期格式化成日期/时间的字符串(从Date到String)
            String time = format.format(date);
            if (num == 1){ // 一般呼救
                sql ="INSERT INTO alarme(fromuser, touser, text, time, status, kind, jindu, weidu) " +
                        "VALUES('"+phone+"', '张管理员', '"+arr[0]+"', '"+time+"', '0', '"+arr1[0]+"', '"+jingdu+"', '"+weidu+"');";

            }else if (num == 2) { // 紧急呼救
                sql ="INSERT INTO alarme(fromuser, touser, text, time, status, kind, jindu, weidu) " +
                        "VALUES('"+phone+"', '张管理员', '"+arr[1]+"', '"+time+"', '0', '"+arr1[1]+"', '"+jingdu+"', '"+weidu+"');";

            }else if (num == 3) { // 身体不舒服
                sql ="INSERT INTO alarme(fromuser, touser, text, time, status, kind, jindu, weidu) " +
                        "VALUES('"+phone+"', '张管理员', '"+arr[2]+"', '"+time+"', '0', '"+arr1[2]+"', '"+jingdu+"', '"+weidu+"');";

            }else if (num == 4) { // 迷路了
                sql ="INSERT INTO alarme(fromuser, touser, text, time, status, kind, jindu, weidu) " +
                        "VALUES('"+phone+"', '张管理员', '"+arr[3]+"', '"+time+"', '0', '"+arr1[2]+"', '"+jingdu+"', '"+weidu+"');";
            }
            else if (num == 5) { // 拨120
                sql ="INSERT INTO alarme(fromuser, touser, text, time, status, kind, jindu, weidu) " +
                        "VALUES('"+phone+"', '张管理员', '"+arr[4]+"', '"+time+"', '0', '"+arr1[3]+"', '"+jingdu+"', '"+weidu+"');";
            }
            try {
                Statement statement = connection.createStatement();
                int number = statement.executeUpdate(sql);
                Log.e("UserDao_ZhuCe", "-->呼救成功<--");
                statement.close();
                return number;
            } catch (SQLException e) {
                Log.e("UserDao_ZhuCe", "-->呼救失败<--");
                e.printStackTrace();
                return 0;
            }
        }
    }
    // -------------------------------------------紧急呼救结束----------------------------------------
}

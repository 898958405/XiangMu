package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yanglao.gongjulei.UserDao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class YuyueFragment extends Fragment {
    private Button yy_tijiao;
    private EditText yy_xingming, yy_dizhi, yy_dianhua, yy_neirong;
    // 订单号
    private int number, money;
    String phone = PHONE; // 手机号
    // 老人身份证号，支付方式，下单时间，下单备注，订单金额
    private String id_card, paymethod, placetime, placeremark;
    HashMap<String, Object> map = new HashMap<>();
    private RadioButton weixin, xianjin;
    private EditText beizhu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yuyue,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化
        initView();
        Tijiao();
    }

    private void initView() {
        yy_xingming = getActivity().findViewById(R.id.yy_xingming);
        yy_dizhi = getActivity().findViewById(R.id.yy_dizhi);
        yy_dianhua = getActivity().findViewById(R.id.yy_dianhua);
        yy_neirong = getActivity().findViewById(R.id.yy_neirong);
        yy_tijiao = getActivity().findViewById(R.id.yy_tijiao);
    }
    public String Times() {
        // 创建日历对象(多态的形式)(获取当前时间段的所有时间值)
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR); // 年
        int month = c.get(Calendar.MONTH)+1; // 月
        String months = "";
        if(month < 10 ) {
            months = "0" + month;
        } else {
            months = String.valueOf(month);
        }
        int date = c.get(Calendar.DATE); // 日
        String dates = "";
        if(date < 10 ) {
            dates = "0" + date;
        } else {
            dates = String.valueOf(date);
        }
        int hour = c.get(Calendar.HOUR_OF_DAY); // 小时
        String hours = "";
        if(hour < 10 ) {
            hours = "0" + hour;
        } else {
            hours = String.valueOf(hour);
        }
        int minute = c.get(Calendar.MINUTE); // 分钟
        String minutes = "";
        if(minute < 10 ) {
            minutes = "0" + minute;
        } else {
            minutes = String.valueOf(minute);
        }
        return year + "." + months + "." + dates + " " + hours + ":" + minutes;
    }

    private void Tijiao() {
        yy_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String XingMing = yy_xingming.getText().toString().trim();
                        String DiZhi = yy_dizhi.getText().toString().trim();
                        String DianHua = yy_dianhua.getText().toString().trim();
                        String NeiRong = yy_neirong.getText().toString().trim();
                        UserDao ud = new UserDao();
                        String phone = PHONE;
                        if (phone == null) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), DengLu.class);
                            startActivity(intent);
                            Looper.loop();
                        } else if (phone.equals("sorry")) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else if (XingMing.equals("") || DiZhi.equals("") || DianHua.equals("") || NeiRong.equals("")) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), "所有内容都不能为空！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else if (DianHua.length() != 11) {
                            Looper.prepare();
                            Toast.makeText(getActivity(), "手机号长度不符合规定！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            placeremark = "无"; // 下单备注
                            paymethod = "现金支付";
                            // 支付方式和备注信息存到到数据库
                            Random r = new Random();
                            number = r.nextInt(100000)+10000;// 订单号
                            money = r.nextInt(40)+10; //价格
                            map = ud.getInfoByoldman(phone);
                            id_card = (String) map.get("Id_card"); // 身份证号码
                            placetime = Times(); // 下单时间
                            int num = ud.YH_xiadan_cunChu(number, id_card, XingMing, DianHua, DiZhi,
                                    NeiRong, paymethod, placetime, placeremark, String.valueOf(money));
                            if (num == 0) {
                                Looper.prepare();
                                Toast.makeText(getActivity(), "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(getActivity(), "下单成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    }
                }).start();
            }
        });
    }

}
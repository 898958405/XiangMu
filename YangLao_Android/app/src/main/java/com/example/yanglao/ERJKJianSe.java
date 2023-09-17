package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglao.gongjulei.UserDao;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class ERJKJianSe extends AppCompatActivity {
    private Button jkjc_fanhui, jkjc_queren;
    private TextView jkjc_jiage, jkjc_fuwuneirong;
    private TabHost jkjc_TabHost;
    private RadioButton weixin, xianjin;
    private EditText beizhu;
    // 订单号，老人身份证号，老人姓名，老人电话号码，地址，服务内容，支付方式，下单时间，下单备注，订单金额
    private int number;
    private String id_card, name, phone, address, ordername, paymethod, placetime,
            placeremark, money;
    HashMap<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int num = ShouyeFragment.FW_JiLu;
        switch (num) {
            case 11:
                setContentView(R.layout.zz_jk_jian_se);
                break;
            case 12:
                setContentView(R.layout.zz_pt_jiu_yi);
                break;
            case 13:
                setContentView(R.layout.zz_yh_ding_zhi);
                break;
            case 14:
                setContentView(R.layout.zz_zl_hu_li);
                break;
            case 15:
                setContentView(R.layout.zz_kf_liao_li);
                break;
            case 16:
                setContentView(R.layout.zz_jz_fu_wu);
                break;
            case 17:
                setContentView(R.layout.zz_xl_guan_ai);
                break;
            case 18:
                setContentView(R.layout.zz_js_zhu_san);
                break;
            case 19:
                setContentView(R.layout.zz_chang_hu_xian);
                break;
        }

        initView();

        jkjc_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ERJKJianSe.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        jkjc_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 添加下单信息存到数据库
                Random r = new Random();
                number = r.nextInt(100000)+10000;// 订单号
                phone = PHONE; // 手机号
                // 获取老人姓名，身份证号，地址
                xinxiHuoqu();
                ordername = jkjc_fuwuneirong.getText().toString().trim(); // 服务内容
                paymethod = ""; // 支付方式
                placetime = Times(); // 下单时间
                placeremark = ""; // 下单备注
                money = jkjc_jiage.getText().toString().trim(); // 订单金额
                char[] cards =  money.toCharArray();
                String birthday = "";
                for (int i = 0; i < cards.length; i++) {
                    if(i >= 1) {
                        birthday += cards[i];
                    }
                }

                // 弹出对话框，选择支付方式
                LayoutInflater layoutInflater = LayoutInflater.from(ERJKJianSe.this);
                View loginVIew = layoutInflater.inflate(R.layout.dialong_login, null);
                AlertDialog.Builder loginDialog = new AlertDialog.Builder(ERJKJianSe.this);
                loginDialog.setTitle("请选择支付方式：");
                loginDialog.setView(loginVIew); // 为对话框设置自定义View
                String finalBirthday = birthday;
                loginDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 登录按钮功能代码
                        xianjin = loginVIew.findViewById(R.id.xianjin);
                        weixin = loginVIew.findViewById(R.id.weixin);
                        beizhu = loginVIew.findViewById((R.id.beizhu));
                        // 二选一
                        if (weixin.isChecked()) {
                            paymethod = weixin.getText().toString();
                        }else  if (xianjin.isChecked()) {
                            paymethod = xianjin.getText().toString();
                        }
                        if(paymethod.equals("")) {
                            Toast.makeText(ERJKJianSe.this, "请选择支付方式", Toast.LENGTH_LONG).show();
                        } else {
                            placeremark = beizhu.getText().toString().trim();
                            if(placeremark.equals("")) {
                                placeremark = "无";
                            }
                            // 支付方式和备注信息存到到数据库
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    UserDao ud = new UserDao();
                                    int num = ud.YH_xiadan_cunChu(number, id_card, name, phone, address,
                                            ordername, paymethod, placetime, placeremark, finalBirthday);
                                    if (num == 0) {
                                        Looper.prepare();
                                        Toast.makeText(ERJKJianSe.this,"⚠️系统管理员等你反馈",Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    } else {
                                        Looper.prepare();
                                        Toast.makeText(ERJKJianSe.this,"下单成功",Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                }
                            }).start();
                            Intent intent = new Intent(ERJKJianSe.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
                AlertDialog dialog = loginDialog.create(); // 创建对话框
//                dialog.setCanceledOnTouchOutside(false); // 设置单击对话框外部时，对话框不消失
                dialog.show();// 显示对话框
            }
        });
        jkjc_TabHost.setup();
        jkjc_TabHost.addTab(jkjc_TabHost.newTabSpec("tab01").setIndicator("详情").setContent(R.id.jkjc_xiangqing));
        jkjc_TabHost.addTab(jkjc_TabHost.newTabSpec("tab01").setIndicator("评价").setContent(R.id.jkjc_pingjia));
    }

    public void xinxiHuoqu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao ud = new UserDao();
                map = ud.getInfoByoldman(phone);
                id_card = (String) map.get("Id_card"); // 身份证号码 ✓
                name = (String) map.get("name"); // 姓名 ✓
                address = (String) map.get("address"); // 地址 ✓
            }
        }).start();
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
    void Print() {
        System.out.println("手机号：" + phone);
        System.out.println("订单号：" + number);
        System.out.println("身份证号：" + id_card);
        System.out.println("姓名：" + name);
        System.out.println("地址：" + address);
        System.out.println("服务内容：" + ordername);
        System.out.println("支付方式：" + paymethod);
        System.out.println("下单时间：" + placetime);
        System.out.println("下单备注：" + placeremark);
        System.out.println("金额：" + money);
    }
    private void initView() {
        jkjc_TabHost = (TabHost) findViewById(R.id.jkjc_TabHost);
        jkjc_fanhui = findViewById(R.id.jkjc_fanhui);
        jkjc_queren = findViewById(R.id.jkjk_queren);
        jkjc_jiage = findViewById(R.id.jkjc_jiage);
        jkjc_fuwuneirong = findViewById(R.id.jkjc_fuwuneirong);


    }
}
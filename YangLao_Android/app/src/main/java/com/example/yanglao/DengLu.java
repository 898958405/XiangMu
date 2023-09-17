package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.yanglao.gongjulei.UserDao;

public class DengLu extends AppCompatActivity implements View.OnClickListener {
    private EditText dl_shoujihao, dl_mima;
    private Button dl_zhuce, dl_wangjimima, dl_denglu, dl_fanhui;
    private RadioButton dl_danxuan;
    public static String PHONE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu);
        initView();

    }
    void initView() {
        dl_shoujihao = findViewById(R.id.dl_shoujihao);
        dl_mima = findViewById(R.id.dl_mima);
        dl_danxuan = findViewById(R.id.dl_danxuankuang);
        dl_zhuce = findViewById(R.id.dl_zhuce);
        dl_zhuce.setOnClickListener(this);
        dl_denglu = findViewById(R.id.dl_denglu);
        dl_denglu.setOnClickListener(this);
        dl_wangjimima = findViewById(R.id.dl_wangjimima);
        dl_wangjimima.setOnClickListener(this);
        dl_fanhui = findViewById(R.id.dl_fanhui);
        dl_fanhui.setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        dl_shoujihao.setText(sp.getString("username", null));
        dl_mima.setText(sp.getString("password", null));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dl_denglu:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phone = dl_shoujihao.getText().toString().trim();
                        String pwd = dl_mima.getText().toString().trim();
                        if(phone.equals("")||pwd.equals("")||!dl_danxuan.isChecked()){
                            Looper.prepare();
                            Toast.makeText(DengLu.this,"内容不该为空！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        UserDao ud = new UserDao();
                        String src = ud.zc_Cha_SF_zhuce(phone);
                        if (src == null) {
                            Looper.prepare();
                            Toast.makeText(DengLu.this,"该账号未注册",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else if (src.equals("sorry")) {
                            Looper.prepare();
                            Toast.makeText(DengLu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else{
                            String result = ud.DengLu(phone,pwd); // 登录验证
                            if (result == null) {
                                Looper.prepare();
                                Toast.makeText(DengLu.this,"密码错误",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else if (result.equals("sorry")){
                                Looper.prepare();
                                Toast.makeText(DengLu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                int number1 = ud.dl_Deng_Dingwei(phone); // 该账号改成登录状态
                                if (number1 == 0) {
                                    Looper.prepare();
                                    Toast.makeText(DengLu.this,"⚠️系统管理员等你反馈",Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(DengLu.this,"登录成功",Toast.LENGTH_SHORT).show();
                                    PHONE = phone;
                                    String ldentity = ud.shenfenzhengCX(phone);
                                    if (ldentity.equals("nulls")) {
                                        Intent intent = new Intent(DengLu.this, MainActivity.class);
                                        startActivity(intent);
                                        ShouyeFragment.FW_JiLu = 100;
                                        finish();
                                    } else if (ldentity.equals("sorry")){
                                        Looper.prepare();
                                        Toast.makeText(DengLu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    } else {
                                        Intent intent = new Intent(DengLu.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                }).start();
                break;

            case R.id.dl_wangjimima:
                Intent i = new Intent(DengLu.this,ERGaiMiMa.class);
                startActivity(i);
                ShouyeFragment.FW_JiLu = 11;
                break;
            case R.id.dl_zhuce:
                Intent intent = new Intent(DengLu.this, ZhuCe.class);
                startActivity(intent);
                break;
            case R.id.dl_fanhui:
                Intent ins = new Intent(DengLu.this,MainActivity.class);
                startActivity(ins);
                finish();
                break;
        }
    }
}
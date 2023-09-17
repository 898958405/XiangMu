package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanglao.gongjulei.UserDao;

public class ERGaiMiMa extends AppCompatActivity implements View.OnClickListener {
    private EditText gm_shoujihao, gm_mibao, gm_xinmima;
    private Button gm_fanhui, gm_queren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_gai_mi_ma);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gm_queren:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phone = gm_shoujihao.getText().toString().trim();//username
                        String pwd= gm_xinmima.getText().toString().trim();
                        String mibao = gm_mibao.getText().toString().trim();
                        if(phone.equals("")||pwd.equals("")||mibao.equals("")){
                            Looper.prepare();
                            Toast toast = Toast.makeText(ERGaiMiMa.this,"所有内容都不能为空！",Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        } else if (pwd.length() < 6 || pwd.length() > 12) {
                            Looper.prepare();
                            Toast.makeText(ERGaiMiMa.this,"密码长度不符合规定！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        UserDao ud = new UserDao();
                        String src = ud.zc_Cha_SF_zhuce(phone);
                        if (src == null) {
                            Looper.prepare();
                            Toast.makeText(ERGaiMiMa.this,"该账号未注册",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else if (src.equals("sorry")) {
                            Looper.prepare();
                            Toast.makeText(ERGaiMiMa.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            String srcS = ud.zm_Cha_SF_zhuce(phone, mibao); // 配对密保答案
                            if (srcS == null) {
                                Looper.prepare();
                                Toast.makeText(ERGaiMiMa.this,"密保问题答案填写有误！",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else if (srcS.equals("sorry")) {
                                Looper.prepare();
                                Toast.makeText(ERGaiMiMa.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                int number = ud.GaiMiMa(phone,pwd); // 注册验证
                                if (number == 0) {
                                    Looper.prepare();
                                    Toast.makeText(ERGaiMiMa.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(ERGaiMiMa.this,"密码修改成功!",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ERGaiMiMa.this, ERSheZhi.class);
                                    startActivity(intent);
                                    finish();
                                    Looper.loop();
                                }

                            }
                        }
                    }
                }).start();
                break;
            case R.id.gm_fanhui:
                if (ShouyeFragment.FW_JiLu == 11) {
                    Intent intent = new Intent(ERGaiMiMa.this, DengLu.class);
                    startActivity(intent);
                    finish();
                } else if (ShouyeFragment.FW_JiLu == 22) {
                    Intent intent = new Intent(ERGaiMiMa.this, ERSheZhi.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }

    }
    void initView() {
        gm_fanhui = findViewById(R.id.gm_fanhui);
        gm_fanhui.setOnClickListener(this);
        gm_queren = findViewById(R.id.gm_queren);
        gm_queren.setOnClickListener(this);
        gm_shoujihao = findViewById(R.id.gm_shoujihao);
        gm_mibao = findViewById(R.id.gm_mibao);
        gm_xinmima = findViewById(R.id.gm_mima);

    }
}
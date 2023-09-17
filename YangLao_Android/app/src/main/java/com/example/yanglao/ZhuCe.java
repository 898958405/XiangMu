package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.yanglao.gongjulei.UserDao;

public class ZhuCe extends AppCompatActivity implements View.OnClickListener {
    private Button zc_fanhui, zc_zhuce;
    private EditText zc_shoujihao, zc_mima, zc_mibao;
    private RadioButton zc_yonghuxieyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initVIew();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zc_zhuce:
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String phone = zc_shoujihao.getText().toString().trim();//username
                        String pwd = zc_mima.getText().toString().trim();
                        String mibao = zc_mibao.getText().toString().trim();

                        if(phone.equals("")||pwd.equals("")||mibao.equals("")||!zc_yonghuxieyi.isChecked()){
                            Looper.prepare();
                            Toast toast = Toast.makeText(ZhuCe.this,"所有内容都不能为空！",Toast.LENGTH_SHORT);
                            toast.show();
                            System.out.println(phone.length());
                            Looper.loop();
                        } if (phone.length() != 11) {
                            Looper.prepare();
                            Toast.makeText(ZhuCe.this,"手机号长度不符合规定！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } if (pwd.length() < 6 || pwd.length() > 12) {
                            Looper.prepare();
                            Toast.makeText(ZhuCe.this,"密码长度不符合规定！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        UserDao ud = new UserDao();
                        String src = ud.zc_Cha_SF_zhuce(phone); // 查询注册账号是否已注册
                        if (src == null) {
                            int number = ud.ZhuCe(phone,pwd,mibao); // 注册验证
                            if (number == 0) {
                                Looper.prepare();
                                Toast.makeText(ZhuCe.this,"⚠️系统管理员等你反馈",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(ZhuCe.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ZhuCe.this, DengLu.class);
                                startActivity(intent);
                                finish();
                                Looper.loop();
                            }
                        } else if (src.equals("sorry")) {
                            Looper.prepare();
                            Toast.makeText(ZhuCe.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                                Looper.prepare();
                                Toast.makeText(ZhuCe.this,"该账号已注册",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                        }
                    }
                }).start();
                break;
            case R.id.zc_fanhui:
                Intent intent = new Intent(ZhuCe.this, DengLu.class);
                ZhuCe.this.startActivity(intent);
                finish();
                break;
        }
    }
    private void initVIew() {
            zc_fanhui = findViewById(R.id.zc_fanhui);
            zc_fanhui.setOnClickListener(this);
            zc_zhuce = findViewById(R.id.zc_zhuce);
            zc_zhuce.setOnClickListener(this);
            zc_shoujihao = findViewById(R.id.zc_shoujihao);
            zc_mima = findViewById(R.id.zc_mima);
            zc_mibao = findViewById(R.id.zc_mibao);
            zc_yonghuxieyi = findViewById(R.id.zc_yonghuxieyi);
        }
}
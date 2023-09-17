package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.yanglao.gongjulei.UserDao;

import java.util.ArrayList;
import java.util.List;

public class ERJingjiHujiu extends AppCompatActivity implements View.OnClickListener {
    private TextView jj_xinxi1, jj_xinxi2, jj_xinxi3, jj_xinxi4;
    private ImageView jj_120;
    private Button jj_fanhui;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_jingji_hujiu);
        initView();
    }

    private void initView() {
        jj_xinxi1 = findViewById(R.id.jj_xinxi1);
        jj_xinxi1.setOnClickListener(this);
        jj_xinxi2 = findViewById(R.id.jj_xinxi2);
        jj_xinxi2.setOnClickListener(this);
        jj_xinxi3 = findViewById(R.id.jj_xinxi3);
        jj_xinxi3.setOnClickListener(this);
        jj_xinxi4 = findViewById(R.id.jj_xinxi4);
        jj_xinxi4.setOnClickListener(this);
        jj_fanhui = findViewById(R.id.jj_fanhui);
        jj_fanhui.setOnClickListener(this);
        jj_120 = findViewById(R.id.jj_120);
        jj_120.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao ud = new UserDao();
                String phone = PHONE;
                String jijiu_dianhua = UserDao.getBoHao(phone);
                switch (view.getId()) {
                    case R.id.jj_fanhui:
                        Intent intent = new Intent(ERJingjiHujiu.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.jj_120:
                        if (jijiu_dianhua == null) {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            int number = ud.CALL(5, phone);
                            if (number == 0) {
                                Looper.prepare();
                                Toast.makeText(ERJingjiHujiu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + jijiu_dianhua + "")));
                                startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:120")));
                                Toast.makeText(ERJingjiHujiu.this, "已进行呼救⚠️", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                        break;
                    case R.id.jj_xinxi1:
                        int number = ud.CALL(1, phone);
                        if (number == 0) {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "已进行呼救⚠️", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        break;
                    case R.id.jj_xinxi2:
                        if (jijiu_dianhua == null) {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            int number1 = ud.CALL(2, phone);
                            if (number1 == 0) {
                                Looper.prepare();
                                Toast.makeText(ERJingjiHujiu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + jijiu_dianhua + "")));
                                Toast.makeText(ERJingjiHujiu.this, "已进行呼救⚠️", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                        break;
                    case R.id.jj_xinxi3:
                        int number2 = ud.CALL(3, phone);
                        if (number2 == 0) {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "已进行呼救⚠️", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        break;
                    case R.id.jj_xinxi4:
                        int number3 = ud.CALL(4, phone);
                        if (number3 == 0) {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(ERJingjiHujiu.this, "已进行呼救⚠️", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        break;
                }
            }
        }).start();
    }

}
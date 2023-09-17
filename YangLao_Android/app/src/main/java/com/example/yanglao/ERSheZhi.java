package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yanglao.gongjulei.UserDao;

public class ERSheZhi extends AppCompatActivity implements View.OnClickListener {
    private Button sz_fnhui, sz_tuichu;
    private ImageView sz_gaimima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);

        initView();
    }

    private void initView() {
        sz_fnhui = findViewById(R.id.sz_fanhui);
        sz_fnhui.setOnClickListener(this);
        sz_gaimima = findViewById(R.id.sz_gaimima);
        sz_gaimima.setOnClickListener(this);
        sz_tuichu = findViewById(R.id.sz_tuichu);
        sz_tuichu.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sz_fanhui:
                Intent intent = new Intent(ERSheZhi.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.sz_gaimima:
                Intent intent1 = new Intent(ERSheZhi.this, ERGaiMiMa.class);
                startActivity(intent1);
                ShouyeFragment.FW_JiLu = 22;
                break;
            case R.id.sz_tuichu:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phone = PHONE;
                        UserDao ud = new UserDao();
                        int number = ud.dl_Deng_weiDeng(phone);
                        if (number == -1) {
                            Looper.prepare();
                            Toast.makeText(ERSheZhi.this,"⚠️系统管理员等你反馈",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            PHONE = null;
                            Intent intent2 = new Intent(ERSheZhi.this, DengLu.class);
                            startActivity(intent2);
                            finish();
                        }

                    }
                }).start();
                break;
        }
    }
}
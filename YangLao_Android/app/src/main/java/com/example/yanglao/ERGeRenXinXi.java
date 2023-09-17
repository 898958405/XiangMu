package com.example.yanglao;


import static com.example.yanglao.DengLu.PHONE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yanglao.gongjulei.UserDao;

import java.util.HashMap;

public class ERGeRenXinXi extends AppCompatActivity {
    private Button gr_fanhui;
    private TextView gr_name, gr_card, gr_phone, gr_pwd, gr_sex, gr_age, gr_addres, gr_birthday, gr_jj_lianxiren, gr_mibao;
    HashMap<String, Object> map = new HashMap<>();
    HashMap<String, Object> mapp = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_ge_ren_xin_xi);
        initView();
        gr_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ERGeRenXinXi.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        xinxi_hoqu();
    }

    private void xinxi_hoqu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String phone = PHONE;
                UserDao ud = new UserDao();
                map = ud.getInfoByName(phone);
                mapp = ud.getInfoByoldman(phone);

                gr_phone.setText((String) map.get("phone"));
                gr_pwd.setText((String) map.get("pwd"));
                gr_jj_lianxiren.setText((String) map.get("jijiu"));
                gr_mibao.setText((String) map.get("mibao"));

                gr_name.setText((String)mapp.get("name"));
                gr_sex.setText((String) mapp.get("sex"));
                gr_age.setText((String) mapp.get("age"));
                gr_card.setText((String) mapp.get("Id_card"));
                gr_addres.setText((String) mapp.get("address"));
                gr_birthday.setText((String) mapp.get("birthday"));


            }
        }).start();
    }

    private void initView() {
        gr_fanhui = findViewById(R.id.gr_fanhui);
        gr_name = findViewById(R.id.gr_name);
        gr_card = findViewById(R.id.gr_card);
        gr_phone = findViewById(R.id.gr_phone);
        gr_pwd = findViewById(R.id.gr_pwd);
        gr_sex = findViewById(R.id.gr_sex);
        gr_age = findViewById(R.id.gr_age);
        gr_addres = findViewById(R.id.gr_address);
        gr_birthday = findViewById(R.id.gr_birthday);
        gr_jj_lianxiren = findViewById(R.id.gr_jj_lianxiren);
        gr_mibao = findViewById(R.id.gr_mibao);
    }

}
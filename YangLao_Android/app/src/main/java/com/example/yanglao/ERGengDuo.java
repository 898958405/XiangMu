package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ERGengDuo extends AppCompatActivity implements View.OnClickListener {
    private ImageView gd_jk_jiance, gd_pt_jiuyi, gd_yh_dingzhi, gd_zl_huli
            , gd_kf_liaoli, gd_jz_fuwu, gd_xl_guanai, gd_jiucan, gd_zhuanghuxian;
    private Button gd_fanhui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_geng_duo);
        initView();
    }

    private void initView() {
        gd_jk_jiance = findViewById(R.id.gd_jk_jianse);
        gd_jk_jiance.setOnClickListener(this);
        gd_pt_jiuyi = findViewById(R.id.gd_pt_jiuyi);
        gd_pt_jiuyi.setOnClickListener(this);
        gd_yh_dingzhi = findViewById(R.id.gd_yh_dingzhi);
        gd_yh_dingzhi.setOnClickListener(this);
        gd_zl_huli = findViewById(R.id.gd_zl_huli);
        gd_zl_huli.setOnClickListener(this);
        gd_kf_liaoli = findViewById(R.id.gd_kf_liaoli);
        gd_kf_liaoli.setOnClickListener(this);
        gd_jz_fuwu = findViewById(R.id.gd_jz_fuwu);
        gd_jz_fuwu.setOnClickListener(this);
        gd_xl_guanai = findViewById(R.id.gd_xl_guanai);
        gd_xl_guanai.setOnClickListener(this);
        gd_jiucan = findViewById(R.id.gd_jiucan);
        gd_jiucan.setOnClickListener(this);
        gd_zhuanghuxian = findViewById(R.id.gd_zhuanghuxian);
        gd_zhuanghuxian.setOnClickListener(this);
        gd_fanhui = findViewById(R.id.gd_fanhui);
        gd_fanhui.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gd_jk_jianse:
                Intent intent = new Intent(this, ERJKJianSe.class);
                startActivity(intent);
                ShouyeFragment.FW_JiLu = 11;
                break;
            case R.id.gd_pt_jiuyi:
                Intent intent1 = new Intent(this, ERJKJianSe.class);
                startActivity(intent1);
                ShouyeFragment.FW_JiLu = 12;
                break;
            case R.id.gd_yh_dingzhi:
                Intent intent2 = new Intent(this, ERJKJianSe.class);
                startActivity(intent2);
                ShouyeFragment.FW_JiLu = 13;
                break;
            case R.id.gd_zl_huli:
                Intent intent3 = new Intent(this, ERJKJianSe.class);
                startActivity(intent3);
                ShouyeFragment.FW_JiLu = 14;
                break;
            case R.id.gd_kf_liaoli:
                Intent intent4 = new Intent(this, ERJKJianSe.class);
                startActivity(intent4);
                ShouyeFragment.FW_JiLu = 15;
                break;
            case R.id.gd_jz_fuwu:
                Intent intent5 = new Intent(this, ERJKJianSe.class);
                startActivity(intent5);
                ShouyeFragment.FW_JiLu = 16;
                break;
            case R.id.gd_xl_guanai:
                Intent intent6 = new Intent(this, ERJKJianSe.class);
                startActivity(intent6);
                ShouyeFragment.FW_JiLu = 17;
                break;
            case R.id.gd_jiucan:
                Intent intent7 = new Intent(this, ERJKJianSe.class);
                startActivity(intent7);
                ShouyeFragment.FW_JiLu = 18;
                break;
            case R.id.gd_zhuanghuxian:
                Intent intent8 = new Intent(this, ERJKJianSe.class);
                startActivity(intent8);
                ShouyeFragment.FW_JiLu = 19;
                break;
            case R.id.gd_fanhui:
                Intent intent9 = new Intent(this, MainActivity.class);
                startActivity(intent9);
                finish();
                break;


        }
    }
}
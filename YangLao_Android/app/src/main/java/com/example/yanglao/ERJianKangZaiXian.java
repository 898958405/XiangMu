package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class ERJianKangZaiXian extends AppCompatActivity {
    private Button fw_fanhui;
    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_jian_kang_zai_xian);
        tabHost = (TabHost) findViewById(R.id.yangshengchangshi);
        fw_fanhui = findViewById(R.id.jkzx_fanhui);
        fw_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ERJianKangZaiXian.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("急救知识").setContent(R.id.jkzx_jijiu));
        tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("健康快车").setContent(R.id.jkzx_kuaiche));
        tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("膳食营养").setContent(R.id.jkzx_yingyang));
        int ss = ShouyeFragment.FW_JiLu;
        tabHost.setCurrentTab(ss);
    }
}
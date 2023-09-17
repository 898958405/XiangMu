package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ERSousuo extends AppCompatActivity {
    private EditText ss_suosou;
    private Button ss_fanhui;
    private TextView ss_jiuyi, ss_zhiliao, ss_zhucan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_sou_suo);
        initView();
        FanHui();
        souSuo();
        ReMenFuWu();
    }
    private void initView() {
        ss_suosou = findViewById(R.id.ss_sousuo);
        ss_fanhui = findViewById(R.id.ss_fanhui);
        ss_jiuyi = findViewById(R.id.ss_jiuyi);
        ss_zhiliao = findViewById(R.id.ss_zhiliao);
        ss_zhucan = findViewById(R.id.ss_zhucan);
    }

    private void FanHui() {
        ss_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ERSousuo.this, MainActivity.class);
                ERSousuo.this.startActivity(intent);
                finish();
            }
        });
    }

    // 搜索框事件
    private void souSuo() {
        ss_suosou.setSelectAllOnFocus(true); // 让Edit Text获得焦点时选中全部文本
        ss_suosou.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(ERSousuo.this, ss_suosou.getText().toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
//     热门服务
    private void ReMenFuWu() {
        ss_jiuyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ERSousuo.this, ss_jiuyi.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        ss_zhiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ERSousuo.this, ss_zhiliao.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        ss_zhucan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ERSousuo.this, ss_zhucan.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
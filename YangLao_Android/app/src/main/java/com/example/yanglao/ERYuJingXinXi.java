package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ERYuJingXinXi extends AppCompatActivity {
    private Button yj_fanhui;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_yu_jing_xin_xi);
        yj_fanhui = findViewById(R.id.yj_fanhui);
        yj_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent14 = new Intent(ERYuJingXinXi.this, MainActivity.class);
                startActivity(intent14);
                finish();
            }
        });
    }
}
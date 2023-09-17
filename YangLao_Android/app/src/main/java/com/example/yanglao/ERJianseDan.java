package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ERJianseDan extends AppCompatActivity {
    private Button jk_jiancedan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_jian_se_dan);

        jk_jiancedan = findViewById(R.id.jk_jiancedan);
        jk_jiancedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(ERJianseDan.this, MainActivity.class);
                ERJianseDan.this.startActivity(intent3);
                finish();
            }
        });
    }
}
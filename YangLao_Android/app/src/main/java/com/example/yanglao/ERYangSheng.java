package com.example.yanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ERYangSheng extends AppCompatActivity {
    private Button ys_fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_yang_sheng);
        ys_fanhui = findViewById(R.id.ys_fanhui);
        ys_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ERYangSheng.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
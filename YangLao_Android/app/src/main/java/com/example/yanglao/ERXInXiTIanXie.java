package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanglao.gongjulei.UserDao;

public class ERXInXiTIanXie extends AppCompatActivity {
    private Button xxtx_queren;
    private EditText xxtx_name,  xxtx_sex,  xxtx_age,  xxtx_Id_card,  xxtx_address, xxtx_jijiu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_xin_xi_tian_xie);
        initView();
        queren();
    }

    private void queren() {
        xxtx_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String card = xxtx_Id_card.getText().toString().trim();
                System.out.println(card.length());
                if(card.length() != 18) {
                    Toast.makeText(ERXInXiTIanXie.this, "身份证号为18位", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String name = xxtx_name.getText().toString().trim();
                            String sex = xxtx_sex.getText().toString().trim();
                            String age = xxtx_age.getText().toString().trim();
                            String address = xxtx_address.getText().toString().trim();
                            String jijiu = xxtx_jijiu.getText().toString().trim();
                            char[] cards =  card.toCharArray();
                            String birthday = "";
                            for (int i = 0; i < cards.length; i++) {
                                if(i == 14) {
                                    break;
                                }
                                if(i >= 6) {
                                    birthday += cards[i];
                                }
                            }
                            StringBuilder birthdays = new StringBuilder(birthday);
                            birthdays.insert(4, "-");
                            birthdays.insert(7, "-");
                            birthday = String.valueOf(birthdays);

                            String phone = PHONE;
                            UserDao ud = new UserDao();
                            int number = ud.XX_TianXie(name, sex, age, card, address, phone, birthday);
                            int som = ud.XX_JingJiLXR(jijiu, phone);
                            if (number == 0 && som == 0) {
                                Looper.prepare();
                                Toast.makeText(ERXInXiTIanXie.this, "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(ERXInXiTIanXie.this, "信息已完善", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(ERXInXiTIanXie.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                                ShouyeFragment.FW_JiLu = -1;
                                Looper.loop();
                            }
                        }
                    }).start();
                }
            }
        });
    }

    private void initView() {
        xxtx_name = findViewById(R.id.xxtx_name);
        xxtx_sex = findViewById(R.id.xxtx_sex);
        xxtx_age = findViewById(R.id.xxtx_age);
        xxtx_Id_card = findViewById(R.id.xxtx_Id_card);
        xxtx_address = findViewById(R.id.xxtx_address);
        xxtx_jijiu = findViewById(R.id.xxtx_jijiu);
        xxtx_queren = findViewById(R.id.xxtx_queren);
    }
}
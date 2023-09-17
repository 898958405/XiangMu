package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.yanglao.gongjulei.Person;
import com.example.yanglao.gongjulei.UserDao;

import java.util.ArrayList;
import java.util.HashMap;

public class ErFuwuJL extends AppCompatActivity {
    private SimpleAdapter adapter = null;
    private ListView lv;
    private Button fw_fanhui;
    private ArrayList<Person> persons;
    private ArrayList<HashMap<String , Object>> data = new ArrayList<>();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.er_fuwu_jilu);
        initView();
        fanhui();
        int ss = ShouyeFragment.FW_JiLu;
        xinxi_hoqu(ss);
    }

    private void xinxi_hoqu(int num) {
        //工作线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                /** 处理复杂的操作 */
                String phone = PHONE;
                UserDao ud = new UserDao();
                persons = ud.Chakan(phone, num);
                for (int i = 0; i < persons.size(); i++) {
                    HashMap<String, Object> item = new HashMap<>();
                    item.put("title", persons.get(i).getTitle());
                    switch (num) {
                        case 0:
                            item.put("status", "未接单");
                            break;
                        case 1:
                            item.put("status", "已派单");
                            break;
                        case 2:
                            item.put("status", "服务中");
                            break;
                        case 3:
                            item.put("status", "已评价");
                            break;
                        case 4:
                            item.put("status", "已完成");
                            break;
                    }
                    item.put("time", persons.get(i).getTime());
                    item.put("mony", "￥" + persons.get(i).getMony());
                    data.add(item);
                }
                Message message = Message.obtain();
                message.what= 1;
                message.obj=data;
                handler.sendMessage(message);
            }
        }).start();
        handler= new Handler() {
            @Override
            public void handleMessage(Message msg) {
                ArrayList<HashMap<String , Object>> datas = new ArrayList<>();
                datas = (ArrayList<HashMap<String, Object>>) msg.obj;

                String[] from = {"title", "status", "time", "mony"};
                int[] to = {R.id.title, R.id.status, R.id.time, R.id.mony};
                adapter = new SimpleAdapter(ErFuwuJL.this, datas, R.layout.dialong_login2, from, to);
                lv.setAdapter(adapter);
            }
        };
    }


    private void fanhui() {
        fw_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ErFuwuJL.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void initView() {
        fw_fanhui = findViewById(R.id.fw_fanhui);
        lv = findViewById(R.id.listView);
    }
}
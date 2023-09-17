package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglao.gongjulei.CircleImageViewDrawable;


public class WodeFragment extends Fragment implements View.OnClickListener {
    private ImageView wd_touxiang, wd_shezhi, wd_fuwujilu, wd_yujing, wd_jiankangshuju;
    private TextView wd_username, wd_gr_xinxi, wd_yifu, wd_daifu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wode, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化
        initView();
        String phone = PHONE;
        if (phone == null) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getActivity(), DengLu.class);
            WodeFragment.this.startActivity(intent);
        } else {
            wd_username.setText(phone);
        }
        // 圆圈头像设置
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a_touxiang);
        Drawable drawable = new CircleImageViewDrawable(bitmap);
        wd_touxiang.setImageDrawable(drawable);

    }
    void initView() {
        wd_touxiang = getActivity().findViewById(R.id.wd_touxiang);
        wd_username = getActivity().findViewById(R.id.wd_username);
        wd_gr_xinxi = getActivity().findViewById(R.id.wd_grxinxi);
        wd_gr_xinxi.setOnClickListener(this);
        wd_yifu = getActivity().findViewById(R.id.wd_yifu);
        wd_yifu.setOnClickListener(this);
        wd_daifu = getActivity().findViewById(R.id.wd_daifu);
        wd_daifu.setOnClickListener(this);
        wd_fuwujilu = getActivity().findViewById(R.id.wd_fuwujilu);
        wd_fuwujilu.setOnClickListener(this);
        wd_shezhi = getActivity().findViewById(R.id.wd_shezhi);
        wd_shezhi.setOnClickListener(this);
        wd_yujing = getActivity().findViewById(R.id.wd_yujing);
        wd_yujing.setOnClickListener(this);
        wd_jiankangshuju = getActivity().findViewById(R.id.wd_jiankangshuju);
        wd_jiankangshuju.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wd_grxinxi:
                Intent intent = new Intent(getActivity(), ERGeRenXinXi.class);
                WodeFragment.this.startActivity(intent);
                break;
            case R.id.wd_yifu:
                Intent intent1 = new Intent(getActivity(), ErFuwuJL.class);
                WodeFragment.this.startActivity(intent1);
                ShouyeFragment.FW_JiLu = 4;
                break;
            case R.id.wd_daifu:
                Intent intent2 = new Intent(getActivity(), ErFuwuJL.class);
                WodeFragment.this.startActivity(intent2);
                ShouyeFragment.FW_JiLu = 1;
                break;
            case R.id.wd_shezhi:
                Intent intent3 = new Intent(getActivity(), ERSheZhi.class);
                WodeFragment.this.startActivity(intent3);
                break;
            case R.id.wd_fuwujilu:
                Intent intent4 = new Intent(getActivity(), ErFuwuJL.class);
                WodeFragment.this.startActivity(intent4);
                ShouyeFragment.FW_JiLu = 0;
                break;
            case R.id.wd_yujing:
                Intent intent5 = new Intent(getActivity(), ERYuJingXinXi.class);
                WodeFragment.this.startActivity(intent5);
                break;
            case R.id.wd_jiankangshuju:
                Intent intent6 = new Intent(getActivity(), ERJianseDan.class);
                WodeFragment.this.startActivity(intent6);
                break;
        }
    }

}
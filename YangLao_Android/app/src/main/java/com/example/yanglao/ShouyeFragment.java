package com.example.yanglao;

import static com.example.yanglao.DengLu.PHONE;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import com.example.yanglao.gongjulei.LoopViewAdapter;
import com.example.yanglao.gongjulei.UserDao;

public class ShouyeFragment extends Fragment implements View.OnClickListener {
    private ViewPager sy_lunbotu;  //轮播图模块
    private Button sy_btnsousuo;
    private int[] mImg;
    private int[] mImg_id;
    private String[] mDec;
    private ArrayList<ImageView> mImgList;
    private LinearLayout sy_xiaoyuandian;
    private TextView sy_lunbotu_biaoti, sy_yangsheng;
    private int previousSelectedPosition = 0;
    boolean isRunning = false;
    private ImageView sy_daifukuan, sy_dadianhua, sy_jk_jiance, sy_pt_jiuyi, sy_yh_dingzhi, sy_zl_huli
            , sy_kf_liaoli, sy_jz_fuwu, sy_xl_guanai, sy_gengduo, sy_jijiu, sy_kuaiche, sy_yingyang, sy_yujingxinxi, sy_jinjihujiu;
    public static int FW_JiLu = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shouye,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        //实现轮播图功能
        initLoopView();

    }

    private void initView() {
        sy_lunbotu = getActivity().findViewById(R.id.sy_lunbotu);
        sy_xiaoyuandian = getActivity().findViewById(R.id.sy_xiaoyuandian);
        sy_lunbotu_biaoti = getActivity().findViewById(R.id.sy_lunbotu_biaoti);
        sy_btnsousuo = getActivity().findViewById(R.id.sy_btnsousuo);
        sy_btnsousuo.setOnClickListener(this);
        sy_yangsheng = getActivity().findViewById(R.id.sy_yangsheng);
        sy_yangsheng.setOnClickListener(this);
        sy_daifukuan = getActivity().findViewById(R.id.sy_daifukuan);
        sy_daifukuan.setOnClickListener(this);
        sy_jinjihujiu = getActivity().findViewById(R.id.sy_jinji_hujiu);
        sy_jinjihujiu.setOnClickListener(this);
        sy_dadianhua = getActivity().findViewById(R.id.sy_dadianhua);
        sy_dadianhua.setOnClickListener(this);
        sy_jk_jiance = getActivity().findViewById(R.id.sy_jk_jianse);
        sy_jk_jiance.setOnClickListener(this);
        sy_pt_jiuyi = getActivity().findViewById(R.id.sy_pt_jiuyi);
        sy_pt_jiuyi.setOnClickListener(this);
        sy_yh_dingzhi = getActivity().findViewById(R.id.sy_yh_dingzhi);
        sy_yh_dingzhi.setOnClickListener(this);
        sy_zl_huli = getActivity().findViewById(R.id.sy_zl_huli);
        sy_zl_huli.setOnClickListener(this);
        sy_kf_liaoli = getActivity().findViewById(R.id.sy_kf_liaoli);
        sy_kf_liaoli.setOnClickListener(this);
        sy_jz_fuwu = getActivity().findViewById(R.id.sy_jz_fuwu);
        sy_jz_fuwu.setOnClickListener(this);
        sy_xl_guanai = getActivity().findViewById(R.id.sy_xl_guanai);
        sy_xl_guanai.setOnClickListener(this);
        sy_gengduo = getActivity().findViewById(R.id.sy_gengduo);
        sy_gengduo.setOnClickListener(this);
        sy_jijiu = getActivity().findViewById(R.id.sy_jijiu);
        sy_jijiu.setOnClickListener(this);
        sy_kuaiche = getActivity().findViewById(R.id.sy_kuaiche);
        sy_kuaiche.setOnClickListener(this);
        sy_yingyang = getActivity().findViewById(R.id.sy_yingyang);
        sy_yingyang.setOnClickListener(this);
    }

    //实现轮播图功能
    private void initLoopView() {
        // 图片资源id数组
        mImg = new int[]{
                R.mipmap.yanglao1,
                R.mipmap.yanglao2,
                R.mipmap.yanglao3,
        };

        // 文本描述
        mDec = new String[]{
                "岁月深长，万物有期",
                "福寿绵绵，长命百岁",
                "福如东海，寿比南山"
        };

        mImg_id = new int[]{
                R.id.pager_img1,
                R.id.pager_img2,
                R.id.pager_img3
        };

        // 初始化要展示的5个ImageView
        mImgList = new ArrayList<ImageView>();
        ImageView imageView;
        View dotView;
        LinearLayout.LayoutParams layoutParams;
        for(int i=0;i<mImg.length;i++){
            //初始化要显示的图片对象
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(mImg[i]);
            imageView.setId(mImg_id[i]);
            mImgList.add(imageView);
            //加引导点
            dotView = new View(getActivity());
            dotView.setBackgroundResource(R.drawable.d_yuandianheji);
            layoutParams = new LinearLayout.LayoutParams(10,10);
            if(i!=0){
                layoutParams.leftMargin=10;
            }
            //设置默认所有都不可用
            dotView.setEnabled(false);
            sy_xiaoyuandian.addView(dotView,layoutParams);
        }

        sy_xiaoyuandian.getChildAt(0).setEnabled(true);
        sy_lunbotu_biaoti.setText(mDec[0]);
        previousSelectedPosition=0;
        //设置适配器
        sy_lunbotu.setAdapter(new LoopViewAdapter(mImgList));
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        int m = (Integer.MAX_VALUE / 2) %mImgList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        sy_lunbotu.setCurrentItem(currentPosition);

        sy_lunbotu.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int newPosition = i % mImgList.size();
                sy_lunbotu_biaoti.setText(mDec[newPosition]);
                sy_xiaoyuandian.getChildAt(previousSelectedPosition).setEnabled(false);
                sy_xiaoyuandian.getChildAt(newPosition).setEnabled(true);
                previousSelectedPosition = newPosition;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 开启轮询
        new Thread(){
        public void run(){
            isRunning = true;
            while(isRunning){
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //下一条
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sy_lunbotu.setCurrentItem(sy_lunbotu.getCurrentItem()+1);
                    }
                });
            }
        }

            private void runOnUiThread(Runnable runnable) {

            }
        }.start();
    }

    @Override
    public void onClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao ud = new UserDao();
                String phone = PHONE;
                if (phone == null) {
                    Looper.prepare();
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), DengLu.class);
                    startActivity(intent);
                    Looper.loop();
                } else {
                    switch (view.getId()) {
                        // 实现搜索框功能
                        case R.id.sy_btnsousuo:
                            Intent intent = new Intent(getActivity(), ERSousuo.class);
                            ShouyeFragment.this.startActivity(intent);
                            break;
                        case R.id.sy_daifukuan:
                            Intent intent1 = new Intent(getActivity(), ErFuwuJL.class);
                            ShouyeFragment.this.startActivity(intent1);
                            FW_JiLu = 2;
                            break;
                        case R.id.sy_jinji_hujiu:
                            Intent intent123 = new Intent(getActivity(), ERJingjiHujiu.class);
                            ShouyeFragment.this.startActivity(intent123);
                            break;
                        case R.id.sy_dadianhua:
                            String jijiu_dianhua = UserDao.getBoHao(phone);
                            if (jijiu_dianhua == null) {
                                Looper.prepare();
                                Toast.makeText(getActivity(), "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                int number = ud.CALL(2, phone);
                                if (number == 0) {
                                    Looper.prepare();
                                    Toast.makeText(getActivity(), "⚠️系统管理员等你反馈", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + jijiu_dianhua + "")));
                                    Toast.makeText(getActivity(), "已进行呼救⚠️", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            }
                            break;
                        case R.id.sy_yangsheng:
                            Intent intent3 = new Intent(getActivity(), ERYangSheng.class);
                            ShouyeFragment.this.startActivity(intent3);
                            break;
                        case R.id.sy_jk_jianse:
                            Intent intent4 = new Intent(getActivity(), ERJKJianSe.class);
                            ShouyeFragment.this.startActivity(intent4);
                            FW_JiLu = 11;
                            break;
                        case R.id.sy_pt_jiuyi:
                            Intent intent5 = new Intent(getActivity(), ERJKJianSe.class);
                            ShouyeFragment.this.startActivity(intent5);
                            FW_JiLu = 12;
                            break;
                        case R.id.sy_yh_dingzhi:
                            Intent intent6 = new Intent(getActivity(), ERJKJianSe.class);
                            ShouyeFragment.this.startActivity(intent6);
                            FW_JiLu = 13;
                            break;
                        case R.id.sy_zl_huli:
                            Intent intent7 = new Intent(getActivity(), ERJKJianSe.class);
                            ShouyeFragment.this.startActivity(intent7);
                            FW_JiLu = 14;
                            break;
                        case R.id.sy_kf_liaoli:
                            Intent intent8 = new Intent(getActivity(), ERJKJianSe.class);
                            ShouyeFragment.this.startActivity(intent8);
                            FW_JiLu = 15;
                            break;
                        case R.id.sy_jz_fuwu:
                            Intent intent9 = new Intent(getActivity(), ERJKJianSe.class);
                            ShouyeFragment.this.startActivity(intent9);
                            FW_JiLu = 16;
                            break;
                        case R.id.sy_xl_guanai:
                            Intent intent10 = new Intent(getActivity(), ERJKJianSe.class);
                            ShouyeFragment.this.startActivity(intent10);
                            FW_JiLu = 17;
                            break;
                        case R.id.sy_gengduo:
                            Intent intent11 = new Intent(getActivity(), ERGengDuo.class);
                            ShouyeFragment.this.startActivity(intent11);
                            break;
                        case R.id.sy_jijiu:
                            Intent intent12 = new Intent(getActivity(), ERJianKangZaiXian.class);
                            ShouyeFragment.this.startActivity(intent12);
                            FW_JiLu = 0;
                            break;
                        case R.id.sy_kuaiche:
                            Intent intent13 = new Intent(getActivity(), ERJianKangZaiXian.class);
                            ShouyeFragment.this.startActivity(intent13);
                            FW_JiLu = 1;
                            break;
                        case R.id.sy_yingyang:
                            Intent intent14 = new Intent(getActivity(), ERJianKangZaiXian.class);
                            ShouyeFragment.this.startActivity(intent14);
                            FW_JiLu = 2;
                            break;
                    }
                }
            }
        }).start();
    }
}
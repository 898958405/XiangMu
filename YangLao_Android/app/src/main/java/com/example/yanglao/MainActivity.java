package com.example.yanglao;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.yanglao.gongjulei.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout shouye_ll, yuyue_ll, wode_ll; // 底部按钮
    private ImageView shouye_ib, yuyue_ib, wode_ib; // 底部图片
    private TextView shouye_tv, wode_tv; //底部文字
    private Fragment tabShouye, tabYuyue, tabWode; // 初始化三个Fragment
    public LocationClient mlocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (ShouyeFragment.FW_JiLu == 100) {
            duihuakuang();
        }

        initView();

        dingWei();

        // 设置三个导航布局的单击事件
        shouye_ll.setOnClickListener(this);
        yuyue_ll.setOnClickListener(this);
        wode_ll.setOnClickListener( this);

        //设置3个ImageView的单机事件（点击底部图标也能切换）
        shouye_ib.setOnClickListener(this);
        yuyue_ib.setOnClickListener(this);
        wode_ib.setOnClickListener(this);

        shouye_ll.performClick();//模拟一次点击，既进去后选择消息界面

    }

    @Override
    public void onClick(View v) {
        // 将底部的按钮图标设置为初始状态
        shouye_ib.setImageResource(R.mipmap.shouye_bai);
        shouye_tv.setTextColor(Color.parseColor("#81868E"));
        yuyue_ib.setImageResource(R.mipmap.tianjia);
        wode_ib.setImageResource(R.mipmap.wode_bai);
        wode_tv.setTextColor(Color.parseColor("#81868E"));

        switch (v.getId()) {
            case R.id.shouye_ll:
                setSelect(0);
                break;
            case R.id.yuyue_ll:
                setSelect(1);
                break;
            case R.id.wode_ll:
                setSelect(2);
                break;
        }

        switch (v.getId()) {
            case R.id.shouye_ib:
                setSelect(0);
                break;
            case R.id.yuyue_ib:
                setSelect(1);
                break;
            case R.id.wode_ib:
                setSelect(2);
                break;
        }
    }

    // 将图片设置为亮色的， 切换显示内容
    private void setSelect(int i) {
        FragmentManager fm=getSupportFragmentManager();
        //创建一个事务
        FragmentTransaction transaction=fm.beginTransaction();
        //先隐藏所有的Fragement，然后根据单机的选项处理具体要显示的Fragement
        hideFragment(transaction);
        switch (i) {
            case 0: //单击的选项
                if (tabShouye == null) {
                    tabShouye = new ShouyeFragment();
                    transaction.add(R.id.id_contet, tabShouye);
                } else {
                    transaction.show(tabShouye);
                }
                //将图片设置为亮色
                shouye_ib.setImageResource(R.mipmap.shuoye_lv);
                shouye_tv.setTextColor(Color.parseColor("#189d43"));
                break;
            case 1: //单击的选项
                if (tabYuyue == null) {
                    tabYuyue = new YuyueFragment();
                    transaction.add(R.id.id_contet, tabYuyue);
                } else {
                    transaction.show(tabYuyue);
                }
                yuyue_ib.setImageResource(R.mipmap.tianjia);
                break;
            case 2: //单击的选项
                if (tabWode == null) {
                    tabWode = new WodeFragment();
                    transaction.add(R.id.id_contet, tabWode);
                } else {
                    transaction.show(tabWode);
                }
                //将图片设置为亮色
                wode_ib.setImageResource(R.mipmap.wode_lv);
                wode_tv.setTextColor(Color.parseColor("#189d43"));
                break;
        }
        transaction.commit();//提交事务
    }

    // 隐藏所有的Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (tabShouye != null) {
            transaction.hide(tabShouye);
        }
        if (tabYuyue != null) {
            transaction.hide(tabYuyue);
        }
        if (tabWode != null) {
            transaction.hide(tabWode);
        }
    }

    void initView() {
        shouye_ll = this.findViewById(R.id.shouye_ll);
        shouye_ib = this.findViewById(R.id.shouye_ib);
        shouye_tv = this.findViewById(R.id.shouye_tv);
        yuyue_ll = this.findViewById(R.id.yuyue_ll);
        yuyue_ib = this.findViewById(R.id.yuyue_ib);
        wode_ll = this.findViewById(R.id.wode_ll);
        wode_ib = this.findViewById(R.id.wode_ib);
        wode_tv = this.findViewById(R.id.wode_tv);
    }

    //    ---------------------------位置获取开始--------------------------------—
    public void dingWei() {
        LocationClient.setAgreePrivacy(true);
        try {
            mlocationClient = new LocationClient(getApplicationContext());
            mlocationClient.registerLocationListener(new MainActivity.MyLocationListener());
            Log.e("BaiDU2","位置信息获取成功");
            mlocationClient.start();

            List<String > permissionList = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (!permissionList.isEmpty()) {
                String [] permission = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(MainActivity.this, permission, 1);
            } else {
                requesetLocation();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "位置信息获取失败", Toast.LENGTH_LONG).show();
            Log.e("BaiDU2","位置信息获取失败");
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                    }
                    requesetLocation();
                }else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    private void requesetLocation() {
        initLocation();
//        try {
//            initLocationOption();
//        } catch (Exception e) {
//            Log.e("MainActivity","定位出问题啦");
//            e.printStackTrace();
//        }
        mlocationClient.start();
    }

//     初始化定位参数配置
//    private void initLocationOption() throws Exception {
//        //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
//        LocationClient locationClient = new LocationClient(getApplicationContext());
//        //声明LocationClient类实例并配置定位参数
//        LocationClientOption locationOption = new LocationClientOption();
//        MyLocationListener myLocationListener = new MyLocationListener();
//        //注册监听函数
//        locationClient.registerLocationListener(myLocationListener);
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
//        locationOption.setCoorType("gcj02");
//        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
//        locationOption.setScanSpan(1010);
//        //可选，设置是否需要地址信息，默认不需要
//        locationOption.setIsNeedAddress(true);
//        //可选，设置是否需要地址描述
//        locationOption.setIsNeedLocationDescribe(true);
//        //可选，设置是否需要设备方向结果
//        locationOption.setNeedDeviceDirect(false);
//        //可选，默认false，设置是否当Gnss有效时按照1S1次频率输出Gnss结果
//        locationOption.setLocationNotify(true);
//        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        locationOption.setIgnoreKillProcess(true);
//        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        locationOption.setIsNeedLocationDescribe(true);
//        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        locationOption.setIsNeedLocationPoiList(true);
//        //可选，默认false，设置是否收集CRASH信息，默认收集
//        locationOption.SetIgnoreCacheException(false);
//        //可选，默认false，设置是否开启卫星定位
////        locationOption.setOpenGnss(true);
//        locationOption.setOpenGps(true);
//        //可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
//        locationOption.setIsNeedAltitude(false);
//        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
//        locationOption.setOpenAutoNotifyMode();
//        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
//        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
//        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//        locationClient.setLocOption(locationOption);
//        //开始定位
//        locationClient.start();
//    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        // 定位模式：高精度
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 返回经纬度坐标类型：BD0911 百度经纬度坐标
        option.setCoorType("bd0911");
        // 首次定位时可选择速度或准确性有先：准确性优先
        option.setFirstLocType(LocationClientOption.FirstLocType.ACCURACY_IN_FIRST_LOC);
        // 发起定位请求的间隔，设置为0， 表示单次定位
        option.setScanSpan(1010);
        // 是否使用卫星定位
        option.setOpenGps(true);
//        option.setOpenGnss(true);
        // 是否当卫星定位有效时按照15/1次频率输出卫星定位结果
        option.setLocationNotify(true);
        // 是否在stop的时候杀死该进程
        option.setIgnoreKillProcess(true);
        // 如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        // 是否需要过滤卫星定位仿真结果
        option.setEnableSimulateGps(false);
        // 是否需要最新版本的地址信息
        option.setIsNeedAddress(true);

        mlocationClient.setLocOption(option);
    }

    public static class MyLocationListener extends BDAbstractLocationListener {

        public static String jingdu, weidu;

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            jingdu = String.valueOf(bdLocation.getLongitude()); // 获取经度信息
            weidu = String.valueOf(bdLocation.getLatitude()); // 获取纬度信息
            float radius = bdLocation.getRadius(); // 获取定位精度
            StringBuilder currentPrisetion = new StringBuilder();
            currentPrisetion.append("(").append(bdLocation.getLongitude()).append(", ");
            currentPrisetion.append(bdLocation.getLatitude()).append(")");
            Log.e("BaiDU","经纬度："+currentPrisetion);
            System.out.println("定位精准度："+radius);
        }
    }

//    ---------------------------位置获取结束--------------------------------—
//    ---------------------------信息完善开始----------------------------------
    void duihuakuang() {
        AlertDialog.Builder allertDang = new AlertDialog.Builder(MainActivity.this);// 创建
    //                                        allertDang.setTitle("课程评价调度"); //设置标题
        allertDang.setMessage("请你完善个人信息"); //设置显示信息
    //                                        allertDang.setIcon(R.drawable.a_mibao); //设置图标
        // 设置积极按钮，通常为确定
        allertDang.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this,ERXInXiTIanXie.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = allertDang.create(); // 创建对话框
        dialog.setCanceledOnTouchOutside(false); // 设置单击对话框外部时，对话框不消失
        dialog.show();// 显示对话框
    }
    //    ---------------------------信息完善结束----------------------------------
}
package activitytest.example.com.gloryscience;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    private MapView mapView;
    public LocationClient mLocationClient;
    //private TextView positionText;
    StringBuilder currentLocation;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        //注册定位监听器，当获取到定位信息的时候，将回调这个监听器
        mLocationClient.registerLocationListener(new MyLocationListener());

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.history);//默认选中历史选项
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.settings :
                        intent = new Intent(MainActivity.this, Settings.class);
                        break;
                    default:
                }
                if(intent != null) {
                    startActivity(intent);
                }
                return true;
            }
        });
        mapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();//通过mapView来获得baiduMap，它是地图的总控制器
        baiduMap.setMyLocationEnabled(true);
  //      positionText = (TextView)findViewById(R.id.position_text_view);
  //      positionText.setText(currentLocation);
        List<String> permissionList = new ArrayList<String>();
        //如果这三个权限没有获取那么放到list集合中
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                 != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //如果集合中确实有未获得的权限，那么转换成数组给requetPermissions方法，让其获取
        if(!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {//如果权限都有了，那么启动
            requestLocation();
        }
        /*ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }*/
    }

    /**
     * 当点击菜单栏的时候拉出抽屉
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    private void navigateTo(BDLocation location) {
        if(isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(19f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");// //可选，默认gcj02，设置返回的定位结果坐标系.使用百度的经度更高，国测的会有位置偏移
     //   option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);//强制使用gps
     //   option.setScanSpan(5000);//每5秒更新一下位置
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();//在活动结束的时候让该对象停止，否则后台会一直定位，严重消耗手机电量
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1 :
                if(grantResults.length > 0) {
                    for(int result : grantResults) {
                        if(result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            /*StringBuilder currentLocation = new StringBuilder();
            currentLocation.append("纬度 : ").append(bdLocation.getLatitude()).append("\n");
            currentLocation.append("经度 ：").append(bdLocation.getLongitude()).append("\n");
            currentLocation.append("国家 : ").append(bdLocation.getCountry()).append("\n");
            currentLocation.append("省 ：").append(bdLocation.getProvince()).append("\n");
            currentLocation.append("市 ：").append(bdLocation.getCity()).append("\n");
            currentLocation.append("区 ：").append(bdLocation.getDistrict()).append("\n");
            currentLocation.append("街道 ：").append(bdLocation.getStreet()).append("\n");
            currentLocation.append("定位方式：");
            if(bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                currentLocation.append("GPS");
            } else if(bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentLocation.append("网络");
            }*/
            currentLocation = new StringBuilder();
            currentLocation.append(bdLocation.getCity());
            if(bdLocation.getLocType() == BDLocation.TypeGpsLocation ||
                    bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(bdLocation);
              //  currentLocation.append("GPS");
            }

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }


    }
}

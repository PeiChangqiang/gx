package activitytest.example.com.gloryscience;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import activitytest.example.com.common.AboutApp;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView textView = (TextView) findViewById(R.id.common_title);
        textView.setText("设置");

        /**
         * 退出登录的按钮事件，跳转到登录页面
         */
        TextView quitLogin = (TextView) findViewById(R.id.quit_login);
        quitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 账号与安全的按钮事件，跳转到手机号和密码设置页面
         */
        TextView settings = (TextView) findViewById(R.id.count_and_safe);
        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, AccountAndSecurity.class);
                startActivity(intent);
            }
        });

        /**
         * 关于软件
         */
        TextView aboutApp = (TextView) findViewById(R.id.about_app);
        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到关于软件的信息页面
                Intent intent = new Intent(Settings.this, AboutApp.class);
                startActivity(intent);
            }
        });
    }
}

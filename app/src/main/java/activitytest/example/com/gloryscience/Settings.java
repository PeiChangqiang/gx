package activitytest.example.com.gloryscience;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView textView = (TextView) findViewById(R.id.common_title);
        textView.setText("设置");

        /**
         *当返回的时候销毁该活动
         */
        Button button = (Button) findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 退出登录的按钮事件，跳转到登录页面
         */
        Button quitLogin = (Button) findViewById(R.id.quit_login);
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
        Button settings = (Button) findViewById(R.id.count_and_safe);
        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, AccountAndSecurity.class);
                startActivity(intent);
            }
        });
    }
}

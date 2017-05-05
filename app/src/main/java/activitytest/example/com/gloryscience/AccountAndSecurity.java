package activitytest.example.com.gloryscience;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountAndSecurity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_and_security);
        TextView textView = (TextView) findViewById(R.id.common_title);
        textView.setText("账号与安全");
        //跳转到修改手机号的页面
        Button updateAccount = (Button) findViewById(R.id.update_account);
        updateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(AccountAndSecurity.this, ChangePhoneNumber.class);
                startActivity(intent);*/
            }
        });

        /**
         * 跳转到设置密码的页面
         */
        Button cancelAccount = (Button) findViewById(R.id.cancel_account);
        cancelAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(AccountAndSecurity.this, LoginActivity.class);
                startActivity(intent);*/
            }
        });
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
    }
}
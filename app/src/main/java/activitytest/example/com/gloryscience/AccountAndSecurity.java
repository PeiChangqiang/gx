package activitytest.example.com.gloryscience;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AccountAndSecurity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_and_security);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
        TextView textView = (TextView) findViewById(R.id.common_title);
        textView.setText("账号与安全");
        //跳转到修改账号信息的页面
        TextView updateAccount = (TextView) findViewById(R.id.update_account);
        updateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountAndSecurity.this, AccountChange.class);
                startActivity(intent);
            }
        });

        TextView tv = (TextView) findViewById(R.id.testText);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountAndSecurity.this, FaultActivity.class);
                startActivity(intent);
            }
        });


    }
}
        /*
         * 跳转到注销账号的页面
        * /
        /*TextView cancelAccount = (TextView) findViewById(R.id.cancel_account);
        cancelAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountAndSecurity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/






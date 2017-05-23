package activitytest.example.com.gloryscience;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_change);
        TextView textView = (TextView) findViewById(R.id.common_title);
        textView.setText("修改账号信息");


    }
}

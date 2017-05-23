package activitytest.example.com.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import activitytest.example.com.gloryscience.R;


public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app);
        TextView textView = (TextView) findViewById(R.id.common_title);
        textView.setText("关于软件");
    }
}

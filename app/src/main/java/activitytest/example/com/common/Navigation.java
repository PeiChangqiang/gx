package activitytest.example.com.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import activitytest.example.com.gloryscience.R;

/**
 * Created by pcq on 2017/5/17.
 */

public class Navigation extends LinearLayout {
    public Navigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.navigation, this);
        //TextView navText = (TextView) findViewById(R.id.nav_text);
        //navText.setText("账号名称：XXX");
    }
}

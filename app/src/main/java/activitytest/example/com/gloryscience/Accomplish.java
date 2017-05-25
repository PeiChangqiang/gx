package activitytest.example.com.gloryscience;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Accomplish extends Activity {


    TextView tv_door_time;
    LinearLayout layout_doortime;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish);
        ImageView back = (ImageView) findViewById(R.id.accomplish_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击提交的时候发生的事件
        TextView submit = (TextView) findViewById(R.id.task_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Accomplish.this, "you click the submit!", Toast.LENGTH_SHORT).show();
            }
        });

        //点击时间时选择时间
        tv_door_time = (TextView) findViewById(R.id.tv_door_time);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String now = sdf.format(date);
        tv_door_time.setText("上门时间  " + now);

        layout_doortime = (LinearLayout) findViewById(R.id.layout_doortime);
        layout_doortime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accomplish.this, TimeActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1 :
                if(resultCode == RESULT_OK) {
                    String date = data.getStringExtra("date");
                    String time = data.getStringExtra("time");
                    tv_door_time.setText("上门时间  " + date.replace("日期", "") + " " + time.replace("时间", ""));
                } break;
            default:
        }
    }

}

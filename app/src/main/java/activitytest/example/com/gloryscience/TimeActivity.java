package activitytest.example.com.gloryscience;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class TimeActivity extends Activity {

    Intent intent;
    TextView date;
    TextView time;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        intent = new Intent();
        TextView doorTime = (TextView) findViewById(R.id.common_title);
        doorTime.setText("上门时间");
        LinearLayout dateChoice = (LinearLayout) findViewById(R.id.date_choice);
        LinearLayout timeChoice = (LinearLayout) findViewById(R.id.time_choice);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        Date dateTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String nowDate = sdf.format(dateTime);
        date.setText("日期 " + nowDate);
        sdf = new SimpleDateFormat("hh:mm");
        String nowTime = sdf.format(dateTime);
        time.setText("时间 " + nowTime);
        timeChoice.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    showTimeLog();
                    return true;
                }
                return false;
            }
        });
        dateChoice.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatelog();
                    return true;
                }
                return false;
            }
        });
        dateChoice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    showDatelog();
                }
            }
        });
        timeChoice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    showTimeLog();
                }
            }
        });
       /* date.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatelog();
                    return true;
                }
                return false;
            }*/
        Button button = (Button) findViewById(R.id.time_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateString = date.getText() + "";
                String timeString = time.getText() + "";
                intent = new Intent();
                intent.putExtra("date", dateString);
                intent.putExtra("time", timeString);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void showDatelog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(TimeActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText("日期  " + year + "年" + ++month + "月" + dayOfMonth + "日");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected  void showTimeLog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog time_dialog = new TimePickerDialog(TimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String min = minute + "";
                String hour = hourOfDay + "";
                if(minute < 10) {
                    min = "0" + minute;
                }
                if(hourOfDay < 10) {
                    hour = "0" + hourOfDay;
                }
                time.setText("时间  " + hour + ":" + min);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        time_dialog.show();
    }

}

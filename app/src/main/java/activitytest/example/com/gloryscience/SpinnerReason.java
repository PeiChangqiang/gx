package activitytest.example.com.gloryscience;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerReason extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_reason);
        Button confirm = (Button) findViewById(R.id.confirm);

        //点击确定时，获取退单原因内容，以及备注信息
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner) findViewById(R.id.cancel_reason);
                EditText remark = (EditText) findViewById(R.id.text_remark);
                String reason = spinner.getSelectedItem().toString();
                String content = remark.getText().toString();
                Toast.makeText(SpinnerReason.this, reason + ",备注：" + content, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        //点击取消时，销毁当前活动
        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

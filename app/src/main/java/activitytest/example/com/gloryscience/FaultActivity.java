package activitytest.example.com.gloryscience;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fault_activity);
        TextView title = (TextView) findViewById(R.id.common_title);
        title.setText("机顶盒故障");

        //完成按钮事件
        Button button = (Button) findViewById(R.id.bt_accomplish);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaultActivity.this, Accomplish.class);
                startActivity(intent);
            }
        });

        //退单按钮事件
        TextView cancelTask = (TextView) findViewById(R.id.cancel_task);
        cancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaultActivity.this, SpinnerReason.class);
                startActivity(intent);
            }
        });
    }
}

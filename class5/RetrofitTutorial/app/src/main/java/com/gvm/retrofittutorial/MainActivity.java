package com.gvm.retrofittutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ControllerCallback {

    TextView tvLog;
    Button btnLog;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLog = findViewById(R.id.tv_log);
        btnLog = findViewById(R.id.btn_log);
        controller = new Controller(this);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.start();
            }
        });
    }

    @Override
    public void onSuccess(List<Change> changes) {
        StringBuilder sb = new StringBuilder();
        for(Change change : changes) {
            sb.append(change.getSubject()).append("\n");
        }
        tvLog.setText(sb.toString());
    }

    @Override
    public void onError(String errorMsg) {
        tvLog.setText(errorMsg);
    }
}

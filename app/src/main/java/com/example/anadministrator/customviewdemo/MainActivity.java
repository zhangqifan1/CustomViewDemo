package com.example.anadministrator.customviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RadioButton mRb;
    private CheckBox mCb;
    private RelativeLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mActivityMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("dsadasssssss");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    private void initView() {
        mRb = (RadioButton) findViewById(R.id.rb);
        mCb = (CheckBox) findViewById(R.id.cb);
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
    }
}

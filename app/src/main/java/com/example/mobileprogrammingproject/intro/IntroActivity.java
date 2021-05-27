package com.example.mobileprogrammingproject.intro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.ActivityIntroBinding;
import com.example.mobileprogrammingproject.view.LoginActivity;
public class IntroActivity extends AppCompatActivity {

    private ActivityIntroBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityIntroBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        init();
        IntroThread introThread = new IntroThread(handler);
        introThread.start();
    }

    private void init() {
        mBinding.tvIntroIcon.setImageResource(R.drawable.covidicon);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
    };

}

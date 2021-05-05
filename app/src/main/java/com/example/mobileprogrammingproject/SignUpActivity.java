package com.example.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mobileprogrammingproject.databinding.ActivityLoginBinding;
import com.example.mobileprogrammingproject.databinding.ActivitySignUpBinding;
import com.example.mobileprogrammingproject.model.User;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "mobile-db")
                .allowMainThreadQueries()
                .build();

        db.userDao().findAll().toString();

        mBinding.btnCompleteSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mBinding.ptSignUpEmail.getText().toString();
                db.userDao().insert(new User(email, "정우", "프로필"));
                mBinding.ptSignUpPasswordCheck.setText(db.userDao().findAll().toString());
                Toast.makeText(getApplicationContext(), "버튼누름", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
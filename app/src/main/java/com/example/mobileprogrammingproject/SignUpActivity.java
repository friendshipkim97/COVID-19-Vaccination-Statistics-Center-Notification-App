package com.example.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mobileprogrammingproject.databinding.ActivityLoginBinding;
import com.example.mobileprogrammingproject.databinding.ActivitySignUpBinding;
import com.example.mobileprogrammingproject.model.User;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    // Attributes
    private ActivitySignUpBinding mBinding;
    private AppDatabase mAppDatabase;
    private boolean emailDuplicate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        initToolbar();

        mAppDatabase = AppDatabase.getInstance(getApplicationContext());

        mBinding.btnEmailDuplicateCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailDuplicateCheck();
            }
        });

        mBinding.btnCompleteSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpCheck();
//                String email = mBinding.ptSignUpEmail.getText().toString();
//                mAppDatabase.userDao().insert(new User(mBinding.ptSignUpEmail.getText().toString(), "kimjungwoo", "null"));
//                mBinding.ptSignUpPassword.setText(mAppDatabase.userDao().findAll().toString());
//                Toast.makeText(getApplicationContext(), "버튼누름", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean emailDuplicateCheck(){
        emailDuplicate = false;
        String inputEmail = mBinding.etEmailSignUp.getText().toString();
        List<String> allEmail = mAppDatabase.userDao().findAllEmail();
        for(String email : allEmail){
            if(email.equals(inputEmail)){
                if(mAppDatabase.userDao().findTypeById(mAppDatabase.userDao().findIdByEmail(email)).equals("app")){
                    emailDuplicate = true;
                    Toast.makeText(getApplicationContext(), "이메일이 중복입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(emailDuplicate == false){
            Toast.makeText(getApplicationContext(), "사용가능한 이메일입니다.", Toast.LENGTH_SHORT).show();
        }
        return emailDuplicate;
    }

    private void signUpCheck() {
        if()
        if(emailDuplicate==false){
            Toast.makeText(getApplicationContext(), "이메일 중복체크를 해주세요.", Toast.LENGTH_SHORT).show();
        }
    }


    // ActionBar Settings
    private void initToolbar() {
        setSupportActionBar(mBinding.tbSignUp);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.signupactionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_signUpExit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
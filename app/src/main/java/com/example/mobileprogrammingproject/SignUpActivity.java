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
import com.example.mobileprogrammingproject.presenter.SignUpContract;
import com.example.mobileprogrammingproject.presenter.SignUpPresenter;

import java.util.List;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    // Attributes
    private ActivitySignUpBinding mBinding;
    private AppDatabase mAppDatabase;
    private boolean emailDuplicate = false;
    private SignUpContract.Presenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mAppDatabase = AppDatabase.getInstance(getApplicationContext());
        initToolbar();
        signUpPresenter = new SignUpPresenter(this, mBinding, mAppDatabase);

        init();

    }

    private void init(){
        mBinding.btnEmailDuplicateCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean emailDuplicateResult = signUpPresenter.emailDuplicateCheck(true);
            }
        });

        mBinding.btnCompleteSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpPresenter.signUpCheck();
            }
        });

        mBinding.cbFullAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.cbPrivacyAgreement.setChecked(true);
                mBinding.cbTermsOfServiceAgreement.setChecked(true);
            }
        });
    }

    @Override
    public void showToast(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    // ToolBar Settings
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
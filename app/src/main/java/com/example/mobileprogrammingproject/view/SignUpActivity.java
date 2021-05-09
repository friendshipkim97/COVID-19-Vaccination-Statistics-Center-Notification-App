package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mobileprogrammingproject.dao.AppDatabase;
import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.ActivitySignUpBinding;
import com.example.mobileprogrammingproject.presenter.SignUpContract;
import com.example.mobileprogrammingproject.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    // Attributes
    private ActivitySignUpBinding mBinding;
    private AppDatabase mAppDatabase;
    private SignUpContract.Presenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set Binding
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        // set Toolbar
        initToolbar();

        // set Database
        mAppDatabase = AppDatabase.getInstance(getApplicationContext());

        // set Presenter
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
        menuInflater.inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_Exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
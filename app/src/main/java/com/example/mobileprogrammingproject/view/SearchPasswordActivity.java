package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.ActivitySearchPasswordBinding;
import com.example.mobileprogrammingproject.presenter.SearchPasswordContract;
import com.example.mobileprogrammingproject.presenter.SearchPasswordPresenter;

public class SearchPasswordActivity extends AppCompatActivity implements SearchPasswordContract.View{

    // Attributes
    private ActivitySearchPasswordBinding mBinding;
    private AppDatabase mAppDatabase;
    private SearchPasswordContract.Presenter searchPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        // Set Binding
        mBinding = ActivitySearchPasswordBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mAppDatabase = AppDatabase.getInstance(getApplicationContext());
        searchPasswordPresenter = new SearchPasswordPresenter(this, mBinding, mAppDatabase);

        init();
        initToolbar();

    }

    private void init() {
        mBinding.btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findEmail = mBinding.etPasswordSearchEmail.getText().toString();
                searchPasswordPresenter.sendGmail(findEmail);
            }
        });
    }

    // ToolBar Settings
    private void initToolbar() {
        setSupportActionBar(mBinding.tbSearchPassword);
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

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
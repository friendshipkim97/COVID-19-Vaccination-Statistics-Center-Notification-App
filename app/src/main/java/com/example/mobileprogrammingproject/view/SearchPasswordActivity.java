package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.ActivitySearchEmailBinding;
import com.example.mobileprogrammingproject.databinding.ActivitySearchPasswordBinding;

public class SearchPasswordActivity extends AppCompatActivity {

    private ActivitySearchPasswordBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Binding
        mBinding = ActivitySearchPasswordBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        initToolbar();
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
}
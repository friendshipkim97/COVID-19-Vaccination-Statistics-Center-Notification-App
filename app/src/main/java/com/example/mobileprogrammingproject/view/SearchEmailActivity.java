package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mobileprogrammingproject.dao.AppDatabase;
import com.example.mobileprogrammingproject.adapter.CustomAdapter;
import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.valueObject.SearchEmailChild;
import com.example.mobileprogrammingproject.databinding.ActivitySearchEmailBinding;
import com.example.mobileprogrammingproject.databinding.SearchEmailChildListViewBinding;
import com.example.mobileprogrammingproject.presenter.SearchEmailContract;
import com.example.mobileprogrammingproject.presenter.SearchEmailPresenter;

import java.util.ArrayList;

public class SearchEmailActivity extends AppCompatActivity implements SearchEmailContract.View{

    // Name And Birth Attributes
    private RecyclerView recyclerViewNameAndBirth;
    private RecyclerView.Adapter adapterNameAndBirth;
    private RecyclerView.LayoutManager layoutManagerNameAndBirth;
    private ArrayList<SearchEmailChild> arrayListNameAndBirth;

    // Name And Phone Attributes
    private RecyclerView recyclerViewNameAndPhone;
    private RecyclerView.Adapter adapterNameAndPhone;
    private RecyclerView.LayoutManager layoutManagerNameAndPhone;
    private ArrayList<SearchEmailChild> arrayListNameAndPhone;

    // Other Attributes
    private ActivitySearchEmailBinding mBinding;
    private SearchEmailChildListViewBinding searchEmailBinding;
    private SearchEmailContract.Presenter searchEmailPresenter;
    private AppDatabase mAppDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Binding
        mBinding = ActivitySearchEmailBinding.inflate(getLayoutInflater());
        searchEmailBinding = SearchEmailChildListViewBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        // set Database
        mAppDatabase = AppDatabase.getInstance(getApplicationContext());

        // Name And Birth
        recyclerViewNameAndBirth= mBinding.rvSearchEmailByNameAndBirth;
        recyclerViewNameAndBirth.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManagerNameAndBirth = new LinearLayoutManager(this);
        recyclerViewNameAndBirth.setLayoutManager(layoutManagerNameAndBirth);
        arrayListNameAndBirth = new ArrayList<>();
        adapterNameAndBirth = new CustomAdapter(arrayListNameAndBirth, this, searchEmailBinding,this, mAppDatabase);
        recyclerViewNameAndBirth.setAdapter(adapterNameAndBirth);

        // Name And Phone
        recyclerViewNameAndPhone= mBinding.rvSearchEmailByNameAndPhone;
        recyclerViewNameAndPhone.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManagerNameAndPhone = new LinearLayoutManager(this);
        recyclerViewNameAndPhone.setLayoutManager(layoutManagerNameAndPhone);
        arrayListNameAndPhone = new ArrayList<>();
        adapterNameAndPhone = new CustomAdapter(arrayListNameAndPhone, this, searchEmailBinding, this, mAppDatabase);
        recyclerViewNameAndPhone.setAdapter(adapterNameAndPhone);


        // set Presenter
        searchEmailPresenter = new SearchEmailPresenter(this, mBinding, mAppDatabase,
                arrayListNameAndBirth, arrayListNameAndPhone, adapterNameAndBirth, adapterNameAndPhone);

        // init
        initToolbar();
        init();
    }

    private void init() {
       mBinding.btnSearchEmailByNameAndBirth.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               searchEmailPresenter.NABClick();

           }
       });
       mBinding.btnSearchEmailByNameAndPN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               searchEmailPresenter.NAPClick();
           }
       });
    }

    // ToolBar Settings
    private void initToolbar() {
        setSupportActionBar(mBinding.tbSearchEmail);
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
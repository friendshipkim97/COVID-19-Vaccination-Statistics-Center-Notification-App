package com.example.mobileprogrammingproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // main Attributes
    private ActivityMainBinding mBinding;
    private String strNick, strProfileImg, strEmail, strEmailType;

    // drawer Attributes
    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button btn_close;
    private ImageView profileImg;
    private TextView tv_name, tv_email;
    private TextView tv_menu1, tv_menu2, tv_info1, tv_info2, tv_info3, tv_info4, tv_info5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set Binding
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        // get Contents
        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strEmail = intent.getStringExtra("email");
        strProfileImg = intent.getStringExtra("profileImg");
        strEmailType = intent.getStringExtra("check");

        navigationBarInit();
        drawerInit();
        actionbarInit();
//        mainInit();

    }

    private void navigationBarInit() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_frag, HomeFragment.newInstance()).commit();
        mBinding.bottomNavi.setSelectedItemId(R.id.action_home);

        mBinding.bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_menu:
                        drawerLayout.openDrawer(drawerView);
                        break;
                    case R.id.action_search:
                        setFrag(1);
                        break;
                    case R.id.action_home:
                        setFrag(2);
                        break;
                    case R.id.action_myPage:
                        setFrag(3);
                        break;
                    case R.id.action_setting:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });
    }

    private void actionbarInit() {
    }

    private void drawerInit() {
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);
        btn_close = findViewById(R.id.btn_close);
        profileImg = findViewById(R.id.iv_profile);
        tv_name = findViewById(R.id.tv_nickName);
        tv_email = findViewById(R.id.tv_email);
        tv_menu1 = findViewById(R.id.tv_menu1);
        tv_menu2 = findViewById(R.id.tv_menu2);
        tv_info1 = findViewById(R.id.tv_info1);
        tv_info2 = findViewById(R.id.tv_info2);
        tv_info3 = findViewById(R.id.tv_info3);
        tv_info4 = findViewById(R.id.tv_info4);
        tv_info5 = findViewById(R.id.tv_info5);

        Glide.with(this).load(strProfileImg).override(300,300).into(profileImg);
        tv_name.setText(strNick);
        tv_email.setText(strEmail);

        DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) { }
            @Override
            public void onDrawerOpened(@NonNull View drawerView) { }
            @Override
            public void onDrawerClosed(@NonNull View drawerView) { }
            @Override
            public void onDrawerStateChanged(int newState) { }
        };

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        tv_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_info1.getVisibility()==View.VISIBLE){
                    tv_info1.setVisibility(View.GONE);
                    tv_info2.setVisibility(View.GONE);
                    tv_info3.setVisibility(View.GONE);
                }
                else{
                    tv_info1.setVisibility(View.VISIBLE);
                    tv_info2.setVisibility(View.VISIBLE);
                    tv_info3.setVisibility(View.VISIBLE);
                }
            }
        });

        tv_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_info4.getVisibility()==View.VISIBLE){
                    tv_info4.setVisibility(View.GONE);
                    tv_info5.setVisibility(View.GONE);
                }
                else{
                    tv_info4.setVisibility(View.VISIBLE);
                    tv_info5.setVisibility(View.VISIBLE);
                }
            }
        });

        tv_info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {drawerLayout.closeDrawers(); replaceFragment(CovidInfo1Fragment.newInstance()); }
        });
        tv_info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {drawerLayout.closeDrawers(); replaceFragment(CovidInfo2Fragment.newInstance()); }
        });
        tv_info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {drawerLayout.closeDrawers(); replaceFragment(CovidInfo3Fragment.newInstance()); }
        });
        tv_info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {drawerLayout.closeDrawers(); replaceFragment(CovidInfo4Fragment.newInstance()); }
        });
        tv_info5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {drawerLayout.closeDrawers(); replaceFragment(CovidInfo5Fragment.newInstance()); }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });
    }

    private void setFrag(int n){
        switch (n){
            case 0:
                break;
            case 1:
                SearchFragment searchFragment = new SearchFragment();
                replaceFragment(searchFragment);
                break;
            case 2:
                HomeFragment homeFragment = new HomeFragment();
                replaceFragment(homeFragment);
                break;
            case 3:
                MyPageFragment myPageFragment = new MyPageFragment();
                myPageFragment.setArguments(setPersonalBundleInfo());
                replaceFragment(myPageFragment);
                break;
            case 4:
                SettingFragment settingFragment = new SettingFragment();
                replaceFragment(settingFragment);
                break;
        }
    }

    private Bundle setPersonalBundleInfo() {
        Bundle bundle = new Bundle();
        bundle.putString("strNick", strNick);
        bundle.putString("strEmail", strEmail);
        bundle.putString("strProfileImg", strProfileImg);
        bundle.putString("strEmailType", strEmailType);
        return bundle;
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }

}
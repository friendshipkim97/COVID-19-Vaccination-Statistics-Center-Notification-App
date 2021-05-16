package com.example.mobileprogrammingproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Attributes
    private ActivityMainBinding mBinding;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private MenuFragment menuFragment;
    private SearchFragment searchFragment;
    private HomeFragment homeFragment;
    private MyPageFragment myPageFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strEmail = intent.getStringExtra("email");
        strProfileImg = intent.getStringExtra("profileImg");
        strEmailType = intent.getStringExtra("check");

        mBinding.bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_menu:
                        setFrag(0);
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
        menuFragment = new MenuFragment();
        searchFragment = new SearchFragment();
        homeFragment = new HomeFragment();
        myPageFragment = new MyPageFragment();
        settingFragment = new SettingFragment();
        setFrag(2); // 첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택
    }

    // 프래그먼트 교체가 일어나는 실행문이다, 총 5개의 fragment 교체
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.frame_frag, menuFragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame_frag, searchFragment);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.frame_frag, homeFragment);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.frame_frag, myPageFragment);
                Bundle bundle = new Bundle();
                bundle.putString("strNick", strNick);
                bundle.putString("strEmail", strEmail);
                bundle.putString("strProfileImg", strProfileImg);
                bundle.putString("strEmailType", strEmailType);
                myPageFragment.setArguments(bundle);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.frame_frag, settingFragment);
                ft.commit();
                break;
        }
    }



}
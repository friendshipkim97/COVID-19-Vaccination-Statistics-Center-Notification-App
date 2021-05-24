package com.example.mobileprogrammingproject.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.ActivityMainBinding;
import com.example.mobileprogrammingproject.databinding.FragmentMyPageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class MyPageFragment extends Fragment {

    private static boolean setInfo = false;
    private static Bundle bundle;

    private View view;
    private FragmentMyPageBinding mBinding;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private Context context;
    enum loginStatus{KAKAO, GOOGLE, APP};
    private FragmentActivity fragmentContext;

    public static MyPageFragment newInstance() {
        return new MyPageFragment();
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // fragment는 onCreateView로 생성하면 된다.

        // set Binding
        mBinding = FragmentMyPageBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();
        // set Toolbar
        init();
        initToolbar();
        initLoginInfo();
        setHasOptionsMenu(true);

        return view;
    }

    private void init() {

        mBinding.btnQnaWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QNAWriteFragment qNAWriteFragment = QNAWriteFragment.newInstance();
                qNAWriteFragment.setArguments(setPersonalBundleInfo());
                replaceFragment(qNAWriteFragment);
            }
        });

        mBinding.btnQnaCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QNACheckFragment qnaCheckFragment = QNACheckFragment.newInstance();
                qnaCheckFragment.setArguments(setPersonalBundleInfo());
                replaceFragment(qnaCheckFragment);
            }
        });
        mBinding.btnChangePersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePersonalInformationFragment changePersonalInformationFragment = new ChangePersonalInformationFragment();
                changePersonalInformationFragment.setArguments(setPersonalBundleInfo());
                replaceFragment(changePersonalInformationFragment);
            }
        });

    }

    private void initLoginInfo() {
        //if(setInfo==false) {
            Bundle bundle = getArguments();
            strNick = bundle.getString("strNick");
            strProfileImg = bundle.getString("strProfileImg");
            strEmail = bundle.getString("strEmail");
            strEmailType = bundle.getString("strEmailType");
            mBinding.tvCompletedNickName.setText(strNick);
            if (strEmailType.equals(String.valueOf(loginStatus.KAKAO))) {
                kakaoLogout();
            } else if (strEmailType.equals(String.valueOf(loginStatus.GOOGLE))) {
                googleLogout();
            } else if (strEmailType.equals(String.valueOf(loginStatus.APP))) {
                appLogout();
            }
            setInfo = true;
       // }
    }

    private void kakaoLogout() {
        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        showToast("로그아웃 했습니다.");
                        getActivity().finish();
                    }
                });
            }
        });
    }

    private void googleLogout() {
        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                showToast("로그아웃 했습니다.");
                getActivity().finish();
            }
        });
    }

    private void appLogout() {
        mBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("로그아웃 했습니다.");
                getActivity().finish();
            }
        });
    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbMyPage);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_Exit:
                goHomeFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goHomeFragment() {
        ((MainActivity)getActivity()).replaceFragment(HomeFragment.newInstance());
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }

    private Bundle setPersonalBundleInfo() {
        Bundle bundle = new Bundle();
        bundle.putString("strNick", strNick);
        bundle.putString("strEmail", strEmail);
        bundle.putString("strProfileImg", strProfileImg);
        bundle.putString("strEmailType", strEmailType);
        return bundle;
    }


}

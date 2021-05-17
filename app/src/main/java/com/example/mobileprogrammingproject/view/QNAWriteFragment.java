package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.FragmentQNAWriteBinding;

public class QNAWriteFragment extends Fragment {

    private View view;
    private FragmentQNAWriteBinding mBinding;
    private Context context;
    private String strNick, strProfileImg, strEmail, strEmailType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentQNAWriteBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        Bundle bundle = getArguments();
        strNick = bundle.getString("strNick");
        strProfileImg = bundle.getString("strProfileImg");
        strEmail = bundle.getString("strEmail");
        strEmailType = bundle.getString("strEmailType");

        initToolbar();
        setHasOptionsMenu(true);

        return view;
    }

    public static QNAWriteFragment newInstance() {
        return new QNAWriteFragment();
    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbQnaWrite);
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
        MyPageFragment myPageFragment = MyPageFragment.newInstance();
        myPageFragment.setArguments(setPersonalBundleInfo());
        ((MainActivity)getActivity()).replaceFragment(myPageFragment);
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
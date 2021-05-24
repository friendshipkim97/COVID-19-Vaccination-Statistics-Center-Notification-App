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
import android.widget.Toast;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentQNAWriteBinding;
import com.example.mobileprogrammingproject.contract.QnAWriteContract;
import com.example.mobileprogrammingproject.presenter.QnAWritePresenter;

public class QNAWriteFragment extends Fragment implements QnAWriteContract.View {

    private View view;
    private FragmentQNAWriteBinding mBinding;
    private Context context;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private QnAWriteContract.Presenter qnaPresenter;
    private AppDatabase mAppDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentQNAWriteBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        // set Database
        mAppDatabase = AppDatabase.getInstance(context.getApplicationContext());

        qnaPresenter = new QnAWritePresenter(mAppDatabase, mBinding, this);

        Bundle bundle = getArguments();
        strNick = bundle.getString("strNick");
        strProfileImg = bundle.getString("strProfileImg");
        strEmail = bundle.getString("strEmail");
        strEmailType = bundle.getString("strEmailType");

        initToolbar();
        init();
        setHasOptionsMenu(true);

        return view;
    }

    private void init() {
        mBinding.tvQnaWriteEmail.setText(strEmail);
        mBinding.btnQnaWriteSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qnaPresenter.submit(strEmail, strEmailType);
            }
        });
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

    @Override
    public void showToast(String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
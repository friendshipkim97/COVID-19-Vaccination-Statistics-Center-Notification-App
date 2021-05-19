package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.mobileprogrammingproject.adapter.QNACustomAdapter;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentQNACheckBinding;
import com.example.mobileprogrammingproject.model.QnA;
import com.example.mobileprogrammingproject.presenter.QnACheckContract;
import com.example.mobileprogrammingproject.presenter.QnACheckPresenter;
import com.example.mobileprogrammingproject.presenter.QnAWriteContract;
import com.example.mobileprogrammingproject.valueObject.VQnA;

import java.util.ArrayList;

public class QNACheckFragment extends Fragment implements QnACheckContract.View {

    private View view;
    private FragmentQNACheckBinding mBinding;
    private Context context;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private QnACheckContract.Presenter qnaPresenter;
    private AppDatabase mAppDatabase;

    // Recycler View
    private QNACustomAdapter qnaCustomAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentQNACheckBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        // set Database
        mAppDatabase = AppDatabase.getInstance(context.getApplicationContext());

        qnaPresenter = new QnACheckPresenter(mAppDatabase, mBinding, this);

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

    public void init(){
        mBinding.tvQnaCheckEmail.setText(strEmail);
        ArrayList<QnA> arrayList = qnaPresenter.getData(strEmail, strEmailType);

        // RecyclerView
        recyclerView= mBinding.rvQnaCheck;
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.qnaCustomAdapter = new QNACustomAdapter(arrayList, context, strEmail);
        recyclerView.setAdapter(qnaCustomAdapter);

    }

    public static QNACheckFragment newInstance() {
        return new QNACheckFragment();
    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbQnaCheck);
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
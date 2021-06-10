package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.adapter.QNACustomAdapter;
import com.example.mobileprogrammingproject.contract.ChangePersonalInformationContract;
import com.example.mobileprogrammingproject.contract.SignUpContract;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentChangePersonalInformationBinding;
import com.example.mobileprogrammingproject.contract.QnACheckContract;
import com.example.mobileprogrammingproject.presenter.ChangePersonalPresenter;
import com.example.mobileprogrammingproject.presenter.QnACheckPresenter;

public class ChangePersonalInformationFragment extends Fragment implements ChangePersonalInformationContract.View{

    private View view;
    private FragmentChangePersonalInformationBinding mBinding;
    private Context context;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private ChangePersonalInformationContract.Presenter presenter;
    private AppDatabase mAppDatabase;
    private FragmentActivity fragmentContext;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentChangePersonalInformationBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        // set Database
        mAppDatabase = AppDatabase.getInstance(context.getApplicationContext());

        presenter = new ChangePersonalPresenter(this, mBinding, mAppDatabase, context);

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

    public void init() {
        mBinding.tvChangePersonalEmail.setText(strEmail);

        mBinding.btnCompleteChangePersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean changePersonalInfoCheck = presenter.changePersonalInformation(strEmail, strEmailType);
                if(changePersonalInfoCheck == true){
                    replaceFragment(HomeFragment.newInstance());
                }
            }
        });

    }


    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.tbChangePersonal);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
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

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }

    public static ChangePersonalInformationFragment newInstance() {
        return new ChangePersonalInformationFragment();
    }
}
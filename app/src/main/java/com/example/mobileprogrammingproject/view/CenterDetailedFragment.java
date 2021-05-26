package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.mobileprogrammingproject.databinding.FragmentCenterDetailedBinding;
import com.example.mobileprogrammingproject.databinding.FragmentMyPageBinding;
import com.example.mobileprogrammingproject.model.Center;

import java.io.Serializable;

public class CenterDetailedFragment extends Fragment {

    private View view;
    private FragmentCenterDetailedBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;

    public static CenterDetailedFragment newInstance() {
        return new CenterDetailedFragment();
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
        mBinding = FragmentCenterDetailedBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();
        // set Toolbar
        init();
        initToolbar();
        setHasOptionsMenu(true);

        return view;
    }

    private void init() {
        Bundle bundle = getArguments();
        Center center = (Center) bundle.getSerializable("centerItem");
        mBinding.tvCenterName.setText(center.getCenterName());
        mBinding.tvCenterType.setText(center.getCenterType());
        mBinding.tvAdress.setText(center.getAddress());
        mBinding.tvFacilityName.setText(center.getFacilityName());
        mBinding.tvOrg.setText(center.getOrg());
        mBinding.tvPhoneNumber.setText(center.getPhoneNumber());

        mBinding.btnMoveSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceSetBundleFragment(WebViewFragment.newInstance(), (Center) bundle.getSerializable("centerItem"));
            }
        });
    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbGoogleMap);
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
                replaceFragment(GoogleMapFragment.newInstance());
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

    public void replaceSetBundleFragment(Fragment fragment, Center centerItem){
        Bundle bundle = new Bundle();
        bundle.putSerializable("centerItem", centerItem);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }

}
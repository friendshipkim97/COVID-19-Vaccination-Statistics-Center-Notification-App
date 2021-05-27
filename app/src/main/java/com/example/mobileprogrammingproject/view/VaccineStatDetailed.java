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
import com.example.mobileprogrammingproject.databinding.FragmentVaccineStatDetailedBinding;
import com.example.mobileprogrammingproject.model.Center;
import com.example.mobileprogrammingproject.model.VaccineStat;

public class VaccineStatDetailed extends Fragment {

    private View view;
    private FragmentVaccineStatDetailedBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;

    public static VaccineStatDetailed newInstance() {
        return new VaccineStatDetailed();
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // set Binding
        mBinding = FragmentVaccineStatDetailedBinding.inflate(getLayoutInflater());
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
        VaccineStat vaccineStat = (VaccineStat) bundle.getSerializable("vaccineStat");
        mBinding.tvBaseDateDetailed.setText(vaccineStat.getBaseDate());
        mBinding.tvSidoDetailed.setText(vaccineStat.getSido());
        mBinding.tvFirstCntDetailed.setText(String.valueOf(vaccineStat.getFirstCnt()));
        mBinding.tvSecondCntDetailed.setText(String.valueOf(vaccineStat.getSecondCnt()));
        mBinding.tvTotalFirstCntDetailed.setText(String.valueOf(vaccineStat.getTotalFirstCnt()));
        mBinding.tvTotalSecondCntDetailed.setText(String.valueOf(vaccineStat.getTotalSecondCnt()));
        mBinding.tvAccumulatedFirstCntDetailed.setText(String.valueOf(vaccineStat.getAccumulatedFirstCnt()));
        mBinding.tvAccumulatedSecondCntDetailed.setText(String.valueOf(vaccineStat.getAccumulatedSecondCnt()));

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
                replaceFragment(HomeFragment.newInstance());
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
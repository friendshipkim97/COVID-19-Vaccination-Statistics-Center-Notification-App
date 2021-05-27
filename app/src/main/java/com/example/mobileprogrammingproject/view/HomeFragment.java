package com.example.mobileprogrammingproject.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.adapter.MainVaccineStatAdapter;
import com.example.mobileprogrammingproject.api.VaccinationApi;
import com.example.mobileprogrammingproject.api.VaccineStatApi;
import com.example.mobileprogrammingproject.constants.Constants;
import com.example.mobileprogrammingproject.databinding.FragmentHomeBinding;
import com.example.mobileprogrammingproject.databinding.FragmentMyPageBinding;
import com.example.mobileprogrammingproject.model.Center;
import com.example.mobileprogrammingproject.model.Pagination;
import com.example.mobileprogrammingproject.model.VaccineStat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private View view;
    private FragmentHomeBinding mBinding;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private Context context;
    private FragmentActivity fragmentContext;
    Vector<VaccineStat> vaccineStats;
    private MainVaccineStatAdapter mainVaccineStatAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager vaccineStatLayoutManager;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { // fragment는 onCreateView로 생성하면 된다.

        // set Binding
        mBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();
        // set Toolbar
        init();
        initToolbar();
        setHasOptionsMenu(true);

        mBinding.btnGoogleMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleMapFragment googleMapFragment = GoogleMapFragment.newInstance();
                replaceFragment(googleMapFragment);
            }
        });

        return view;
    }

    private void init() {
        mBinding.tvCriteriaTime.setText(getNowTime());
        recyclerView = mBinding.rvVaccinateStat;
        vaccineStatLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(vaccineStatLayoutManager);
        mainVaccineStatAdapter = new MainVaccineStatAdapter(fragmentContext);
        recyclerView.setAdapter(mainVaccineStatAdapter);

        vaccineStats = new Vector<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.EGoogleMapFragment.baseUrl.getText())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VaccineStatApi vaccineStatApi = retrofit.create(VaccineStatApi.class);
        vaccineStatApi.getVaccineStat(getNowTime())
                .enqueue(new Callback<Pagination<List<VaccineStat>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<VaccineStat>>> call, Response<Pagination<List<VaccineStat>>> response) {
                        if (response.isSuccessful()) {
                            for (VaccineStat res : response.body().getData()) {
                                vaccineStats.add(res);
                            }
                            mainVaccineStatAdapter.addVaccineStats(vaccineStats);
                            mainVaccineStatAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFailure(Call<Pagination<List<VaccineStat>>> call, Throwable t) {
                    }
                });

    }

    private String getNowTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String datestr = sdf.format(cal.getTime());
        return datestr;
    }

    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbHome);
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

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

}

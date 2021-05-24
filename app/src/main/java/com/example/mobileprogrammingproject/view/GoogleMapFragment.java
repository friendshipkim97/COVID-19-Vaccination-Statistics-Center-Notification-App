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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.contract.QnAWriteContract;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentGoogleMapBinding;
import com.example.mobileprogrammingproject.model.Center;
import com.example.mobileprogrammingproject.model.CenterResponse;
import com.example.mobileprogrammingproject.model.PageRequest;
import com.example.mobileprogrammingproject.model.Pagination;
import com.example.mobileprogrammingproject.presenter.QnAWritePresenter;
import com.example.mobileprogrammingproject.rest.VaccinationApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentGoogleMapBinding mBinding;
    private Context context;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private QnAWriteContract.Presenter qnaPresenter;
    private AppDatabase mAppDatabase;
    private FragmentActivity fragmentContext;

    private MapView mapView;
    Vector<Center> centerItems;

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentGoogleMapBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();

        mapView = mBinding.googleMap;
        mapView.getMapAsync(this);
        centerItems = new Vector<>();

        // set Database
        mAppDatabase = AppDatabase.getInstance(context.getApplicationContext());

        // 공공데이터활용지원센터_코로나19 예방접종센터 조회서비스
        initToolbar();
        //init();
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) { // 구글 맵이 준비가되면 이 메서드가 호출이 된다. 마커란 지도 핀을 의미함
        LatLng location = new LatLng(37.485284, 126.901451); // LatLng는 위도 경도를 의미함, 위도와 경도가 구로디지털단지위치임
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("구로디지털단지역");
        markerOptions.snippet("전철역");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16)); // v는 넓게볼건지 좁게볼건지 가까이보고 싶으면 숫자를 늘리면 된다.

        fetchCenter(googleMap);
    }

    public void fetchCenter(GoogleMap googleMap){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.odcloud.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VaccinationApi vaccinationApi = retrofit.create(VaccinationApi.class);
        vaccinationApi.getCenter()
                .enqueue(new Callback<Pagination<List<Center>>> () {
            @Override
            public void onResponse(Call<Pagination<List<Center>>> call, Response<Pagination<List<Center>>> response) {
                if(response.isSuccessful()){
                    Log.e("성공", "성공");
                    for(Center res: response.body().getData()){
                        centerItems.add(res);
                    }
                    updateMapMarkers(centerItems, googleMap);
                }
            }
            @Override
            public void onFailure(Call<Pagination<List<Center>>>  call, Throwable t) {
                Log.e("실패", "실패임");
            }
        });
    }


    private void updateMapMarkers(Vector<Center> center, GoogleMap googleMap) {
        if(center !=null && center.size() >0){
            for(Center centerItem : center){
                MarkerOptions markerOptions = new MarkerOptions();
                LatLng location = new LatLng(Double.valueOf(centerItem.getLat()), Double.valueOf(centerItem.getLng()));
                markerOptions.title(centerItem.getCenterName());
                markerOptions.snippet("주소: " + centerItem.getAddress());
                markerOptions.position(location);
                googleMap.addMarker(markerOptions);
            }
        }

    }

    // ToolBar Settings
    private void initToolbar() {
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbMyPage);
//        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayHomeAsUpEnabled(false);
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

    public static GoogleMapFragment newInstance() {
        return new GoogleMapFragment();
    }
}
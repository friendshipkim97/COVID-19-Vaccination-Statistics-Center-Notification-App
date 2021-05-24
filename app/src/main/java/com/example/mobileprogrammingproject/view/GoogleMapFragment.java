package com.example.mobileprogrammingproject.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.constants.Constants;
import com.example.mobileprogrammingproject.contract.QnAWriteContract;
import com.example.mobileprogrammingproject.database.AppDatabase;
import com.example.mobileprogrammingproject.databinding.FragmentGoogleMapBinding;
import com.example.mobileprogrammingproject.model.Center;
import com.example.mobileprogrammingproject.model.Pagination;
import com.example.mobileprogrammingproject.api.VaccinationApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private FragmentGoogleMapBinding mBinding;
    private Context context;
    private String strNick, strProfileImg, strEmail, strEmailType;
    private QnAWriteContract.Presenter qnaPresenter;
    private AppDatabase mAppDatabase;
    private FragmentActivity fragmentContext;

    private MapView mapView;
    Vector<Center> centerItems;

    private GoogleMap mMap;
    private Marker currentMarker = null;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    Location mCurrentLocatiion;
    LatLng currentPosition;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;
    private View mLayout;


    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentGoogleMapBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();
        mAppDatabase = AppDatabase.getInstance(context.getApplicationContext());

        // 공공데이터활용지원센터_코로나19 예방접종센터 조회서비스
        initToolbar();
        setHasOptionsMenu(true);
        mBinding.btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hasFineLocationPermission = ContextCompat.checkSelfPermission(fragmentContext,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(fragmentContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION);
                if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                        hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
                    startLocationUpdates(); // 주기적 위치 업데이트
                }else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(fragmentContext, REQUIRED_PERMISSIONS[0])) {
                        Snackbar make = Snackbar.make(mLayout, Constants.EGoogleMapFragment.gpsAccessMessage.getText(),
                                Snackbar.LENGTH_INDEFINITE);
                        make.setAction(Constants.EGoogleMapFragment.checkMessage.getText(), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(fragmentContext, REQUIRED_PERMISSIONS,
                                        PERMISSIONS_REQUEST_CODE);
                            }
                        });
                        make.show();
                    } else {
                        ActivityCompat.requestPermissions( fragmentContext, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }

                }
            }
        });
        mBinding.btnStopMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endLocationUpdates();
            }
        });
        fragmentContext.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLayout = mBinding.layoutMain;
        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_MS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);
        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(fragmentContext);

        mapView = mBinding.googleMap;
        mapView.getMapAsync(this);
        centerItems = new Vector<>();

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
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setDefaultLocation();
        fetchCenter(googleMap);
    }

    public void fetchCenter(GoogleMap googleMap) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.EGoogleMapFragment.baseUrl.getText())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        VaccinationApi vaccinationApi = retrofit.create(VaccinationApi.class);
        vaccinationApi.getCenter()
                .enqueue(new Callback<Pagination<List<Center>>>() {
                    @Override
                    public void onResponse(Call<Pagination<List<Center>>> call, Response<Pagination<List<Center>>> response) {
                        if (response.isSuccessful()) {
                            for (Center res : response.body().getData()) {
                                centerItems.add(res);
                            }
                            updateMapMarkers(centerItems, googleMap);
                        }
                    }
                    @Override
                    public void onFailure(Call<Pagination<List<Center>>> call, Throwable t) {
                    }
                });
    }


    private void updateMapMarkers(Vector<Center> center, GoogleMap googleMap) {
        if (center != null && center.size() > 0) {
            for (Center centerItem : center) {
                MarkerOptions markerOptions = new MarkerOptions();
                LatLng location = new LatLng(Double.valueOf(centerItem.getLat()), Double.valueOf(centerItem.getLng()));
                markerOptions.title(centerItem.getCenterName());
                markerOptions.snippet(Constants.EGoogleMapFragment.address.getText() + centerItem.getAddress());
                markerOptions.position(location);
                googleMap.addMarker(markerOptions);
            }
        }

    }

    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        }else {
            int hasFineLocationPermission = ContextCompat.checkSelfPermission(fragmentContext,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(fragmentContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION);
            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED   ) {
                return;
            }
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
            if (checkPermission())
                mMap.setMyLocationEnabled(true);
        }

    }

    private void endLocationUpdates(){
        mFusedLocationClient.removeLocationUpdates(locationCallback);
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);
                currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());
                String markerTitle = getCurrentAddress(currentPosition);
                String markerSnippet = Constants.EGoogleMapFragment.latitude.getText() + String.valueOf(location.getLatitude())
                        + Constants.EGoogleMapFragment.longitude.getText() + String.valueOf(location.getLongitude());
                setCurrentLocation(location, markerTitle, markerSnippet);

                mCurrentLocatiion = location;
            }


        }

    };

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {


        if (currentMarker != null) currentMarker.remove();
        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        currentMarker = mMap.addMarker(markerOptions);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mMap.moveCamera(cameraUpdate);
    }

    public void setDefaultLocation() {

        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        String markerTitle = Constants.EGoogleMapFragment.notLocationMessage.getText();
        String markerSnippet = Constants.EGoogleMapFragment.permissionAndGPSMessage.getText();
        if (currentMarker != null) currentMarker.remove();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mMap.addMarker(markerOptions);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);

    }

    private boolean checkPermission() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(fragmentContext,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(fragmentContext,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            if ( check_result ) {
                startLocationUpdates();
            }
            else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(fragmentContext, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(fragmentContext, REQUIRED_PERMISSIONS[1])) {
                    Snackbar.make(mLayout, Constants.EGoogleMapFragment.permission1.getText(),
                            Snackbar.LENGTH_INDEFINITE).setAction(Constants.EGoogleMapFragment.checkMessage.getText(), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fragmentContext.finish();
                        }
                    }).show();

                }else {
                    Snackbar.make(mLayout, Constants.EGoogleMapFragment.permission2.getText(),
                            Snackbar.LENGTH_INDEFINITE).setAction(Constants.EGoogleMapFragment.checkMessage.getText(), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fragmentContext.finish();
                        }
                    }).show();
                }
            }

        }
    }

    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentContext);
        builder.setTitle(Constants.EGoogleMapFragment.deactivationMessage.getText());
        builder.setMessage(Constants.EGoogleMapFragment.gpsServiceMessage.getText());
        builder.setCancelable(true);
        builder.setPositiveButton(Constants.EGoogleMapFragment.setting.getText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton(Constants.EGoogleMapFragment.cancel.getText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        needRequest = true;
                        return;
                    }
                }
                break;
        }
    }

    public String getCurrentAddress(LatLng latlng) {
        Geocoder geocoder = new Geocoder(fragmentContext, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(fragmentContext, Constants.EGoogleMapFragment.geoMessage.getText(), Toast.LENGTH_LONG).show();
            return Constants.EGoogleMapFragment.geoMessage.getText();
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(fragmentContext, Constants.EGoogleMapFragment.notGPSMessage.getText(), Toast.LENGTH_LONG).show();
            return Constants.EGoogleMapFragment.notGPSMessage.getText();
        }
        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(fragmentContext, Constants.EGoogleMapFragment.notFindAddressMessage.getText(), Toast.LENGTH_LONG).show();
            return Constants.EGoogleMapFragment.notFindAddressMessage.getText();
        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }

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
        switch (item.getItemId()) {
            case R.id.btn_Exit:
                goHomeFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goHomeFragment() {
        ((MainActivity) getActivity()).replaceFragment(HomeFragment.newInstance());
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = fragmentContext.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_frag, fragment).commit();
    }

    public static GoogleMapFragment newInstance() {
        return new GoogleMapFragment();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

}

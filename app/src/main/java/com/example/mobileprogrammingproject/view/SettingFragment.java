package com.example.mobileprogrammingproject.view;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.mobileprogrammingproject.databinding.FragmentMyPageBinding;
import com.example.mobileprogrammingproject.databinding.FragmentSettingBinding;
import com.example.mobileprogrammingproject.network.NetworkReceiver;


public class SettingFragment extends Fragment {


    private View view;
    private FragmentSettingBinding mBinding;
    private Context context;
    private FragmentActivity fragmentContext;
    public static TextView broadTextView;
    private NetworkReceiver receiver;

    public static SettingFragment newInstance() {
        return new SettingFragment();
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
        mBinding = FragmentSettingBinding.inflate(getLayoutInflater());
        context = container.getContext();
        View view = mBinding.getRoot();
        // set Toolbar
        init();
        initToolbar();
        setHasOptionsMenu(true);

        return view;
    }

    private void init() {
        broadTextView = mBinding.tvState;
        mBinding.btnBroadCastCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentFilter filter = new IntentFilter(); // Intent에는 2가지가 있다 명시적(그동안썼던 Intent, 목적이 뚜렷함), 암시적(특정 구성 요소의 이름을 대지 않는다.)
                receiver = new NetworkReceiver();
                filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
                fragmentContext.registerReceiver(receiver, filter);
            }
        });
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // 브로드 캐스트 리시버 해제!
//        fragmentContext.unregisterReceiver(receiver);
//    }

    // ToolBar Settings
    private void initToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.tbSetting);
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
}

package com.example.mobileprogrammingproject.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobileprogrammingproject.R;
import com.example.mobileprogrammingproject.databinding.FragmentMyPageBinding;
import com.example.mobileprogrammingproject.databinding.FragmentSettingBinding;
import com.example.mobileprogrammingproject.network.NetworkReceiver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import lombok.SneakyThrows;


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
        fragmentContext = (FragmentActivity) activity;
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
        mBinding.btnFileAppCreator.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle("제작자 정보를 확인하시겠습니까?"); //제목
                dlg.setMessage("제작자 정보를 확인하시겠습니까?"); // 메시지
                dlg.setCancelable(false);
                dlg.setIcon(R.drawable.covidicon);

                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    @SneakyThrows
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(context,"확인을 누르셨습니다.",Toast.LENGTH_SHORT).show();
                        ReadTextFile();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    // 취소 버튼 클릭시 설정, 왼쪽 버튼입니다.
                    public void onClick(DialogInterface dialog, int whichButton){
                        Toast.makeText(context,"취소 하셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
        mBinding.btnFileAppCreatorWrite.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle("제작자 정보를 변경하시겠습니까?"); //제목
                dlg.setMessage("제작자 정보를 변경하시겠습니까?"); // 메시지
                dlg.setCancelable(false);
                dlg.setIcon(R.drawable.covidicon);

                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    @SneakyThrows
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(context,"확인을 누르셨습니다.",Toast.LENGTH_SHORT).show();
                        WriteTextFile(mBinding.tvCreatorAffiliation2.getText().toString() + "\n" + mBinding.tvCreatorName2.getText().toString() + "\n" +
                                mBinding.tvCreatorNumber2.getText().toString());
                        mBinding.tvCreatorAffiliation2.setText("");
                        mBinding.tvCreatorName2.setText("");
                        mBinding.tvCreatorNumber2.setText("");
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    // 취소 버튼 클릭시 설정, 왼쪽 버튼입니다.
                    public void onClick(DialogInterface dialog, int whichButton){
                        Toast.makeText(context,"취소 하셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
    }

    public void WriteTextFile(String contents) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("file.txt", Context.MODE_PRIVATE));
        outputStreamWriter.write(contents);
        outputStreamWriter.close();
        Toast.makeText(context, "작성을 완료했습니다.", Toast.LENGTH_SHORT).show();

    }

    public String ReadTextFile() throws IOException {
        InputStream inputStream = context.openFileInput("file.txt");
        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temp = "";
            StringBuffer strBuffer = new StringBuffer();
            int i = 0;
            while ((temp = bufferedReader.readLine()) != null) {
                getTextView(i, temp);
                strBuffer.append(temp + "\n");
                i++;
            }
            inputStream.close();
            return strBuffer.toString();
        }
        return null;
    }

    private void getTextView(int i, String temp) {
        if(i==0){
            mBinding.tvCreatorAffiliation.setText(temp);
        } else if(i==1){
            mBinding.tvCreatorName.setText(temp);
        } else if(i==2){
            mBinding.tvCreatorNumber.setText(temp);
        }
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

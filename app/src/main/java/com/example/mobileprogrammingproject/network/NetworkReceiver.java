package com.example.mobileprogrammingproject.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.example.mobileprogrammingproject.constants.Constants;
import com.example.mobileprogrammingproject.view.SettingFragment;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 네트워크 상태 값 받아오기
        if(WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())){
            NetworkInfo info = (NetworkInfo)intent.getParcelableExtra((WifiManager.EXTRA_NETWORK_INFO));
            NetworkInfo.DetailedState state = info.getDetailedState();
            if(state == NetworkInfo.DetailedState.CONNECTED){ // 네트워크 연결 상태이면...
                  SettingFragment.newInstance().broadTextView.setText(Constants.ENetworkReceiver.successNetwork.getText());
            } else if(state == NetworkInfo.DetailedState.DISCONNECTED){ // 네트워크 연결 해제이면..
                SettingFragment.newInstance().broadTextView.setText(Constants.ENetworkReceiver.failNetwork.getText());
            }
        }
    }
}

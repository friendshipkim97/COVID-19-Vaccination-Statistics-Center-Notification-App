package com.example.mobileprogrammingproject.firebasemessage;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() { // FCM 토큰은 각각 핸드폰기계에서 받아오는 난수의 값인데, 토큰을 받게 되면 토큰을 받는 디바이스들은 푸쉬를 받게 됨, ID같은 개념이다.

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, token);

        sendRegistrationToServer(token);

    }

    private void sendRegistrationToServer(String token){

    }
}

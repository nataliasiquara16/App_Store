package com.example.appstore;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Provider;

public class MyService extends Service{

    String TAG = "MyService";
    CountDownTimer countdowntimer = null;
    public static final String CONNECT = "Intent.ACTION_INTERNET_CONNECTED";
    public static final String DISCONNECT = "Intent.ACTION_INTERNET_DISCONNECTED";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service Started!");
        super.onCreate();
        startTimer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void startTimer(){
        Log.i(TAG, "Starting timer...");

        countdowntimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                if(!isConnected()){
                    Intent bi = new Intent(DISCONNECT);
                    sendBroadcast(bi);
                }else{
                    Intent bi = new Intent(CONNECT);
                    sendBroadcast(bi);
                }
                startTimer();
            }
        };

        countdowntimer.start();
    }

    boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }
}

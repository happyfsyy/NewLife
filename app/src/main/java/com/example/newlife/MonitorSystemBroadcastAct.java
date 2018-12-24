package com.example.newlife;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 监听系统状态的Activity
 */
public class MonitorSystemBroadcastAct extends Activity {

    private NetWorkChangeReceiver receiver;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        receiver=new NetWorkChangeReceiver();
        registerReceiver(receiver,intentFilter);

//        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        connectivityManager.requestNetwork(new NetworkRequest.Builder().build(),new ConnectivityManager.NetworkCallback(){
//            @Override
//            public void onAvailable(Network network) {
//                Toast.makeText(MonitorSystemBroadcastAct.this, "network is available", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onLost(Network network) {
//                Toast.makeText(MonitorSystemBroadcastAct.this,"network is unavailable",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class NetWorkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(MonitorSystemBroadcastAct.this, "network is available", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MonitorSystemBroadcastAct.this,"network is unavailable",Toast.LENGTH_SHORT).show();
            }


        }
    }
}

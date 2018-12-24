package com.example.broadcast_receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.newlife.BroadcastLoginActivity;
import com.example.utils.ActivityCollector;

public class OfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("warning");
        builder.setMessage("OFFLINE!!!!");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();
                Intent startIntent=new Intent(context,BroadcastLoginActivity.class);
                context.startActivity(startIntent);
            }
        });
        builder.show();
    }
}

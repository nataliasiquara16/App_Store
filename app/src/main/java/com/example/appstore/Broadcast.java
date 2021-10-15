package com.example.appstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

// broadcast que indica mudança na conectividade
public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Testando Broadcast","aqui");
        Toast.makeText(context.getApplicationContext(), "Instabilidade na conexão de internet", Toast.LENGTH_SHORT).show();
        context.startService(new Intent("INICIAR_SERVICE"));
    }
}

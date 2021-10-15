package com.example.appstore;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.Provider;

// testando service que incrementa de 0 até 9
public class MyService extends Service implements Runnable {
    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }
    //método que define que quando o serviço é criado, esse inicia a Thread
    @Override
    public void onCreate(){
        new Thread(this).start();

    }

    //metodo quando o service é inicializado
    @Override
    public void onStart(Intent it, int startId){
        Log.i("Teste Service","Serviço inicializado");
    }

    public void inc(){
        for(int i=0; i<10; i++ ){
            try {
                Thread.sleep(3000);
                Log.i("Teste Service", String.valueOf(i));
        } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
// metodo que executa o processo da Thread
    @Override
    public void run() {
        inc();
        stopSelf(); //para o serviço
    }
}

package com.example.myapplication;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;



public class ServicerRun extends Service
{








    private AsyncClass a;

    public void OnCreate()
    {

    }

    @Override
    public int onStartCommand (Intent intent, int flag, int idProcess) //inicia el servicio
    {
        Toast.makeText(this, "Llamando al serv", Toast.LENGTH_SHORT).show();
        a = new AsyncClass(this);
       a.setCancelar(false);
       a.ejecutar();


        return START_STICKY;
    }

    @Override
    public void onDestroy() //detine el servicio
    {
         a.setCancelar(true);
        Toast.makeText(this, "Deteniendo el serv", Toast.LENGTH_SHORT).show();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

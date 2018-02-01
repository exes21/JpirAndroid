package com.example.myapplication;


import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.presenter.Presenter;

public class Clase extends AppCompatActivity {
    private MainActivity activity;


    public Clase(MainActivity activity) {
        this.activity = activity;

    }

    public void iniciar(){
        Thread thread = new Thread(() -> {
            activity.callHttp();
            runOnUiThread(() -> activity.setProgressIndicator(true));
        });
        thread.start();
    }











}




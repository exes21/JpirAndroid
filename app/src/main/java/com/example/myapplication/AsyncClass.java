package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.myapplication.presenter.Presenter;

/**
 * Created by USER on 1/31/2018.
 */

public class AsyncClass {
    private Boolean cancelar = false;
    private Context contexto;
    private Presenter presenter;
    private Clase c;


    public AsyncClass(Context contexto) {
        this.contexto = contexto;
        c = new Clase(MainActivity.mainActivity);

    }


    public void setCancelar(Boolean cancelar) {
        this.cancelar = cancelar;
    }



    public void ejecutar(){
        Tiempo a = new Tiempo();
        proceso();
        a.execute();


    }

    public void proceso(){
        new Thread((new Runnable() {
            @Override
            public void run() {
                c.iniciar();
            }
        }));
    }

    public void hilo() {


        try {
            Thread.sleep(60000);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }


    }

    public class Tiempo extends AsyncTask<Void,Integer,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i=0;i<3;i++){
                hilo();

                if (cancelar == true) break;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(cancelar == false){

               Toast.makeText(contexto,"Midiendo",Toast.LENGTH_SHORT).show();
                ejecutar();
            }
            else{
                onCancelled();
            }

        }

        protected void onCancelled() {
            super.onCancelled();


          // Toast.makeText(MainActivity.this,"te amo",Toast.LENGTH_SHORT).show();
        }


    }





}

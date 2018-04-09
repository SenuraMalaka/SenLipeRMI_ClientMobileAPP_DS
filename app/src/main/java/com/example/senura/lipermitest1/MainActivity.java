package com.example.senura.lipermitest1;

import android.os.AsyncTask;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;

public class MainActivity extends AppCompatActivity{


    private Button btnTest;
    private static int count=0;
    private static TestService testService;
    private static Client client;
    private AsyncTask atask=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btnTest=(Button) findViewById(R.id.button2);



        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    Log.e("mainACT","inside try" );
                    Toast.makeText(MainActivity.this, "Inside conn my nigga", Toast.LENGTH_SHORT).show();
                    CallHandler callHandler = new CallHandler();
                    Log.e("mainACT","after callhandler" );
                    client = new Client("192.168.1.4", 58882, callHandler);
                    Log.e("mainACT","after new client" );
                    testService = (TestService) client.getGlobal(TestService.class);
                    Log.e("mainACT","after testservice" );
                    String msg = "Test";//testService.getResponse("qwe");
                    //Toast.makeText(MainActivity.this, testService.getResponse("abc"), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                    Log.e("mainACT","afterToast" );

                    //count++;
                    sendRegSen();
                    Log.e("mainACT","after sendregsen" );
                    Toast.makeText(MainActivity.this, "END conn my nigga", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });






    }





    private void sendRegSen(){

        Toast.makeText(MainActivity.this, testService.getResponse("ycma boys"), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "count if inside", Toast.LENGTH_SHORT).show();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }











    class Conn extends AsyncTask<Void, Void, MainActivity> {

        @Override
        protected MainActivity doInBackground(Void... params) {
            Looper.prepare();

            Log.e("mainACT","inside async task" );

            try {
                Log.e("mainACT","inside try" );
                Toast.makeText(MainActivity.this, "Inside conn my nigga", Toast.LENGTH_SHORT).show();
                CallHandler callHandler = new CallHandler();
                Log.e("mainACT","after callhandler" );
                client = new Client("192.168.1.4", 58882, callHandler);
                Log.e("mainACT","after new client" );
                testService = (TestService) client.getGlobal(TestService.class);
                Log.e("mainACT","after testservice" );
                String msg = "Test";//testService.getResponse("qwe");
                //Toast.makeText(MainActivity.this, testService.getResponse("abc"), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                Log.e("mainACT","afterToast" );

                //count++;
                sendRegSen();
                Log.e("mainACT","after sendregsen" );
                Toast.makeText(MainActivity.this, "END conn my nigga", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Looper.loop();
            return null;
        }





        private void sendRegSen(){

            Toast.makeText(MainActivity.this, testService.getResponse("ycma boys"), Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "count if inside", Toast.LENGTH_SHORT).show();
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }






    }



}

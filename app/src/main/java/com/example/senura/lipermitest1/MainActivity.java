package com.example.senura.lipermitest1;


import android.os.StrictMode;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{


    private Button btnStartCon;
    private Button sendRes;

    public static TestService remoteObject;
    private static Client client;

    private static TextView statusText=null;

    private static final String mainServerIPAddress="192.168.1.4";
    private static final int mainServerPort=58882;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btnStartCon=(Button) findViewById(R.id.buttonStartCon);
        sendRes=(Button) findViewById(R.id.buttonGetResponse);

        statusText =(TextView) findViewById(R.id.textViewStatus);



        btnStartCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TimerControllers.initGlobalVariables();
                TimerContainer.setTimerToCheckServerAvailability();



//                try {
//                    Log.e("mainACT","inside try" );
//                    setStatusText("inside try");
//
//
//                    Toast.makeText(MainActivity.this, "Inside conn my nigga", Toast.LENGTH_SHORT).show();
//                    CallHandler callHandler = new CallHandler();
//
//                    Log.e("mainACT","after callhandler" );
//                    setStatusText("after callhandler");
//
//
//                    client = new Client(mainServerIPAddress, mainServerPort, callHandler);
//
//                    Log.e("mainACT","after new client" );
//                    setStatusText("after newclient");
//
//                    remoteObject = (TestService) client.getGlobal(TestService.class);
//
//                    Log.e("mainACT","after testservice" );
//                    setStatusText("after testservice");
//
//                    sendRegSen();
//                    Log.e("mainACT","after sendregsen" );
//                    setStatusText("after sendregsen func");
//
//                    Toast.makeText(MainActivity.this, "END conn my nigga", Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    setStatusText("Client is not online");
//                }




            }
        });



        sendRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusText.setText(statusTextString);
                GETRequestHandlers.mainMenuContext=MainActivity.this;
            }
        });



    }





//    private void sendRegSen(){
//
//
//        try {
//
//            String responseText = remoteObject.getResponse("sen android");
//
//            Toast.makeText(MainActivity.this, responseText, Toast.LENGTH_SHORT).show();
//            setStatusText("Response got : "+responseText);
//
//
//        }catch (Exception ex){
//            setStatusText("\nError:\n"+ex.getMessage()+"\n");
//        }
//
//
//    }







    private void closeTheConnection(){
        try {
            client.close();
            setStatusText("client closed");
        } catch (IOException e) {
            setStatusText("client not closed");
            setStatusText("\nError:\n"+e.getMessage()+"\n");
        }
    }



    public static void setStatusText(String textStatus){
        //statusText.append(""+"\n");
        statusTextString+=textStatus+"\n";
    }

    public static String statusTextString="";

    private static void setStatusChangeTimer(){
        final Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {

                statusText.setText(statusTextString);


            }
        };

        timer.schedule(myTask, 1000, 1000);
    }






}

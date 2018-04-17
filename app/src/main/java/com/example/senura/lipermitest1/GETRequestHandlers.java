package com.example.senura.lipermitest1;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.format.Formatter;

import net.sf.lipermi.net.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by senura on 4/15/18.
 */

public class GETRequestHandlers extends TimerContainer{



    public static Context mainMenuContext;




//    public static void sendGet(String urlToRead){
//
//
//
//        try {
//            urlToRead=getHTML(urlToRead);
//        } catch (Exception ex) {
//            //
//        }
//
//        MainActivity.setStatusText(urlToRead);
//    }



//    public static boolean sendGetsToCalculateTimeElapsed(String urlToRead){
//
//        MainActivity.setStatusText("ClientConnection.isgetCountCalculated >> "+isgetCountCalculated);
//        MainActivity.setStatusText("getcount >> "+getCount);
//        MainActivity.setStatusText("\n\n");
//
//        if(!isgetCountCalculated){
//
//            try {
//                urlToRead=getHTML(urlToRead);
//            } catch (Exception ex) {
//                //
//            }
//
//            getCount++;
//
//            long timeElapsed=System.currentTimeMillis()-startMilisecs;
//
//            //measuring for 10secs
//            if((timeElapsed/1000)>10 && !isgetCountCalculated){
//                isgetCountCalculated=true;
//                MainActivity.setStatusText("get count = "+getCount+" gets per 10secs");
//
//                ClientControllerCon.setControllerVar(getCount, getIpAddressOfThis(),getHostNameOfThis());////////newnewnew
//
//                //re init
//                getCount=0; startMilisecs=0;
//
//                ClientControllerCon.start();
//
//                return true;//10 secs gone
//            }
//
//
//        }
//
//        return false;
//    }



    public static boolean sendGetsToCalculateTimeElapsed(String urlToRead){

        MainActivity.setStatusText("ClientConnection.isgetCountCalculated >> "+isgetCountCalculated);
        MainActivity.setStatusText("getcount >> "+getCount);
        MainActivity.setStatusText("\n\n");

        if(!isgetCountCalculated){

            try {
                urlToRead=getHTML(urlToRead);
            } catch (Exception ex) {
                //
            }

            getCount++;

            long timeElapsed=System.currentTimeMillis()-startMilisecs;

            //measuring for 10secs
            if((timeElapsed/1000)>10 && !isgetCountCalculated){
                isgetCountCalculated=true;
                MainActivity.setStatusText("get count = "+getCount+" gets per 10secs");

                ClientControllerCon.setControllerVar(getCount, getIpAddressOfThis(),getHostNameOfThis());////////newnewnew

                //re init
                getCount=0; startMilisecs=0;

                ClientControllerCon.start();

                return true;//10 secs gone
            }


        }

        return false;
    }





    public static boolean sendGets(String url,int amount){
        int failures=0;
        MainActivity.setStatusText("sendGets() ran");

        int successfulGetCounts=0;

        for(int i=0;i<amount;i++){
            try {
                MainActivity.setStatusText(i+" getHTML is :"+getHTML(url));
                successfulGetCounts++;

            } catch (Exception ex) {
                failures++;
            }
        }

        ClientControllerCon.sendGetLoopFinishedToControlServer(successfulGetCounts, getIpAddressOfThis(), getHostNameOfThis());

        MainActivity.setStatusText("sendGets() finished");


        return (failures<(amount/2));

    }




    static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    private static String getIpAddressOfThis(){

        String ipAdrs="unknown";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAdrs=inetAddress.getHostAddress();
            //android.os.Build.MODEL;
            ipAdrs=Settings.Secure.getString(mainMenuContext.getContentResolver(), "bluetooth_name");

        } catch (UnknownHostException ex) {
           //
        }
        return ipAdrs;
    }


    private static String getHostNameOfThis(){

        String hostName="unknown";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName=inetAddress.getHostName();
        } catch (UnknownHostException ex) {
           //
        }
        return hostName;
    }






}

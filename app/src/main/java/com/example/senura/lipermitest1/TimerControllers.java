package com.example.senura.lipermitest1;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;

import static com.example.senura.lipermitest1.MainActivity.remoteObject;

/**
 * Created by senura on 4/15/18.
 */

public class TimerControllers {

    protected static boolean isServerCameOnline=false;

    protected static String DDOS_URL;

    //to calculate the gets per sec
    static long startMilisecs;
    static int getCount;
    static boolean isgetCountCalculated;
    static boolean isCountFirstRun;

    //regarding to perCliedProposedDDOSCount
    static boolean isGETCompleted=false;
    static boolean sendGetFuncRan=false;


    protected static boolean checkURLAvailable(){

        try{


            if(remoteObject.isHavingHostURL()) {

                MainActivity.setStatusText("isCountFirstRun = "+isCountFirstRun+" isgetCountCalculated = "+isgetCountCalculated);
                if(!isgetCountCalculated && isCountFirstRun){

                    MainActivity.setStatusText("inside if");

                    DDOS_URL= remoteObject.getURL();
                    MainActivity.setStatusText(DDOS_URL);

                    countNumOfConPerSec();///////
                    isCountFirstRun=false;

                }

            }
            else
                MainActivity.setStatusText(TimeClass.getTime()+" Not having a URL");
        }catch(java.lang.reflect.UndeclaredThrowableException ex){
            MainActivity.setStatusText(TimeClass.getTime()+" Server gone Offline");
            isServerCameOnline=false;
            initGlobalVariables();
            TimerContainer.setTimerToCheckServerAvailability();
            //setClient();
            return false;
        }
        return true;
    }



    private static void countNumOfConPerSec(){

        if(startMilisecs==0)
            startMilisecs= System.currentTimeMillis();

        if(!DDOS_URL.equals(""))TimerContainer.setTimerToCountGets(DDOS_URL);


    }



     static void initGlobalVariables(){

        DDOS_URL="";
        startMilisecs=0;
        getCount=0;
        isgetCountCalculated=false;
        isCountFirstRun=true;
        isGETCompleted=false;
        sendGetFuncRan=false;

    }


    protected static void setClient(){

        CallHandler callHandler = new CallHandler();
        String remoteHost = "192.168.1.4";
        int portWasBinded = 58882;

        Client client=null;

        try {
            client = new Client(remoteHost, portWasBinded, callHandler);
            isServerCameOnline=true;
        } catch (IOException ex) {
            MainActivity.setStatusText(TimeClass.getTime()+" server is not online");
        }


        if(client!=null){
            remoteObject =
                    (TestService) client.getGlobal(TestService.class);

            MainActivity.setStatusText(remoteObject.getResponse("Hey babe.."));

            TimerContainer.setTimerToCheckURLAvailability();

        }
    }




}

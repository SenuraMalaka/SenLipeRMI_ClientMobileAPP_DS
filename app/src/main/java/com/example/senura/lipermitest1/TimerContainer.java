package com.example.senura.lipermitest1;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by senura on 4/15/18.
 */

public class TimerContainer extends TimerControllers{




    //Timers
    protected static void setTimerToCheckURLAvailability(){

        final Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                MainActivity.setStatusText(">>>>>>>>URLAvailability ran");
                if(!checkURLAvailable()) {
                    MainActivity.setStatusText("-------------------------------------------");

                    timer.cancel();
                }

            }
        };

        timer.schedule(myTask, 1000, 1000);//3000
    }


    protected static void setTimerToCountGets(final String sampleUrl){
        final Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                MainActivity.setStatusText("setTimerToCountGets ran");
                if(GETRequestHandlers.sendGetsToCalculateTimeElapsed(sampleUrl)){
                    MainActivity.setStatusText("CANCEEEEEEEEELLL");
                    timer.cancel();
                }
            }
        };

        timer.schedule(myTask, 1000, 1000);
    }









    protected static void setTimerToCheckServerAvailability(){
        final Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                MainActivity.setStatusText("setTimerToCheckServerAvailability ran");
                if(isCountFirstRun==true && isgetCountCalculated==true) isgetCountCalculated=false;
                setClient();
                if(isServerCameOnline) timer.cancel();
            }
        };

        timer.schedule(myTask, 1000, 1000);//4000
    }









}

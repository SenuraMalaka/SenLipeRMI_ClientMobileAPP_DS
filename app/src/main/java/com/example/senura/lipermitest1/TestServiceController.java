package com.example.senura.lipermitest1;

/**
 * Created by senura on 4/15/18.
 */

public interface TestServiceController {

    public String getResponse(String data);
    public void setDDOSCount(int count, String ipAddress, String hostName);
    public String getClientInfo();
    public int getNumofDDOSToBeExcecuted(String ipAddress);
    public void setProposedDDOSCount(int count);
    public boolean isProposedDDOSCountSet();
    public void sendGetDone(int count, String ipAddress, String hostName);
    public String getRewardsStatusText();

}

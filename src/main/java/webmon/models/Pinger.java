package webmon.models;

import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class Pinger
{
	private ScheduledExecutorService service;
	private static int pingTime = 1; // (Minutes)
	
    public static boolean checkConnection (String url) {
        
        try {
        	URL siteUrl = new URL(url);
        	HttpURLConnection connection = (HttpURLConnection)siteUrl.openConnection();
        	connection.setRequestMethod("GET");
        	connection.setConnectTimeout(1000 * 10);   // 10 seconds
        	
        	connection.connect();
        	
        	int code = connection.getResponseCode();
        	if (code == 200){
                return true;
        	}
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        return false;
    }
    
    public void startService(final String url, final List<ResponseInfo> responseInfo){
       
        try {      	
        	// Ping every intervalSeconds second
        	ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        	
        	this.service = exec;
        	
        	exec.scheduleAtFixedRate(new Runnable(){
        		 @Override
        		  public void run() {
        			 // Calculate response time at regular intervals
        			 long responseTime = 0;
        			 try{
        				URL siteUrl = new URL(url);
         	        	HttpURLConnection connection = (HttpURLConnection)siteUrl.openConnection();
         	        	connection.setRequestMethod("GET");
         	        	connection.setConnectTimeout(1000 * 10);   // 10 seconds
        	        	long StartTime = System.currentTimeMillis();
        	        	connection.connect();
        	        	long EndTime = System.currentTimeMillis();

        	        	int code = connection.getResponseCode();
        	        	if (code == 200){
        	     
        	                responseTime = EndTime - StartTime;
        	                
        	                // Create responseInfo object
        	                ResponseInfo now = new ResponseInfo(responseTime);
        	                responseInfo.add(now);
        	                }
        			 }
        			 catch(Exception e){
        				 e.printStackTrace();
        			 }
        		  }
        	}, 0, Pinger.pingTime, TimeUnit.MINUTES);
        	
        	
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    // Stops the service
    public void stopService(){
    	this.service.shutdown();
    }
}
package webmon.models;

import java.util.ArrayList;
import java.util.List;

public class WebsiteInfo {

	private long id;
	private String url;
//	private int pingTime; // In minutes
	private List<ResponseInfo> responseInfo;
	private Pinger pinger;
	
	WebsiteInfo(String url)
	{
		this.id = WebMonInfo.getNewWebsiteId();
		this.url = url;
	//	this.pingTime = pingTime;
		responseInfo = new ArrayList<ResponseInfo>();
	}
	
	/*public void setPingTime(int pingTime){
		this.pingTime = pingTime;
		
		this.stopMonitoring();
		this.startMonitoring();
	}
	
	public int getPingTime(){
		return this.pingTime;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ResponseInfo> getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(List<ResponseInfo> responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	public void startMonitoring(){
		this.pinger = new Pinger();
		this.pinger.startService(url, responseInfo);
	}
	
	public void stopMonitoring(){
		this.pinger.stopService();
	}
}

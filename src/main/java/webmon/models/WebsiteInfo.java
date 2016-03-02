package webmon.models;

import java.util.ArrayList;
import java.util.List;

public class WebsiteInfo {

	private long id;
	private String url;
	private List<ResponseInfo> responseInfo;
	private Pinger pinger;
	private String name;
	
	public WebsiteInfo(String url, String name)
	{
		this.id = WebMonInfo.getNewWebsiteId();
		this.url = url;
		this.name = name;
		responseInfo = new ArrayList<ResponseInfo>();
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

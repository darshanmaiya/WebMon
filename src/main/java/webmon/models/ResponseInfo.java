package webmon.models;

import java.util.Date;

public class ResponseInfo {

	public long responseTime;
	public Date timestamp;
	
	ResponseInfo(long responseTime)
	{
		this.responseTime = responseTime;
		this.timestamp = new Date();
	}
}
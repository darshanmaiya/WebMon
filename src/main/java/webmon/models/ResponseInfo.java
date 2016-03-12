package webmon.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.EmbeddedEntity;

public class ResponseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6710381441783763289L;
	public long responseTime;
	public Date timestamp;
	
	public ResponseInfo(long responseTime)
	{
		this.responseTime = responseTime;
		this.timestamp = new Date();
	}
	
	public ResponseInfo(long responseTime, Date timestamp)
	{
		this.responseTime = responseTime;
		this.timestamp = timestamp;
	}
	
	public static List<EmbeddedEntity> toEmbeddedEntity(List<ResponseInfo> responseInfos) {
		List<EmbeddedEntity> embeddedResponseInfos = new ArrayList<EmbeddedEntity>();
		for(ResponseInfo responseInfo : responseInfos) {
			EmbeddedEntity embeddedResponseInfo = new EmbeddedEntity();
			
			embeddedResponseInfo.setProperty("responseTime", responseInfo.responseTime);
			embeddedResponseInfo.setProperty("timestamp", responseInfo.timestamp);
			
			embeddedResponseInfos.add(embeddedResponseInfo);
		}

        return embeddedResponseInfos;
	}
	
	public static List<ResponseInfo> fromEmbeddedEntity(ArrayList<EmbeddedEntity> embeddedResponseInfos) {
		List<ResponseInfo> responseInfos = new ArrayList<ResponseInfo>();
		for(EmbeddedEntity embeddedResponseInfo : embeddedResponseInfos) {
			ResponseInfo responseInfo = new ResponseInfo(
					(long)embeddedResponseInfo.getProperty("responseTime"),
					(Date)embeddedResponseInfo.getProperty("timestamp")
				);
			
			responseInfos.add(responseInfo);
		}

        return responseInfos;
	}
}
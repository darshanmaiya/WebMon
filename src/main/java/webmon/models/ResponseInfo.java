package webmon.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.EmbeddedEntity;

/**
 * The Class ResponseInfo.
 */
public class ResponseInfo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6710381441783763289L;
	
	/** The response time. */
	public long responseTime;
	
	/** The timestamp. */
	public Date timestamp;
	
	/**
	 * Instantiates a new response info.
	 *
	 * @param responseTime the response time
	 */
	public ResponseInfo(long responseTime)
	{
		this.responseTime = responseTime;
		this.timestamp = new Date();
	}
	
	/**
	 * Instantiates a new response info.
	 *
	 * @param responseTime the response time
	 * @param timestamp the timestamp
	 */
	public ResponseInfo(long responseTime, Date timestamp)
	{
		this.responseTime = responseTime;
		this.timestamp = timestamp;
	}
	
	/**
	 * Convert a list of Response Infos to a list of embedded entities.
	 *
	 * @param responseInfos the response infos
	 * @return the list of embedded entities
	 */
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
	
	/**
	 * Convert a list of embedded entities to a list of response infos.
	 *
	 * @param embeddedResponseInfos the embedded entities
	 * @return the list of response infos
	 */
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
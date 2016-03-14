package webmon;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Map;
import java.util.HashMap;
 
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;

import webmon.models.User;

public class Notifier {
	
	private static final String ACCOUNT_SID = "";
	private static final String AUTH_TOKEN = "";
	private static final String TWILIO_NUMBER = "";
	private static final String WEBMON_EMAIL = "";
	private static final String WEBMON_NAME = "WebMon";
	private static final String WEBMON_SUBJECT = "WebMon Notification";
	
	public static final void notifyViaMail(User user, String message) {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(WEBMON_EMAIL, WEBMON_NAME));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(user.getEmail(), user.getName()));
            msg.setSubject(WEBMON_SUBJECT);
            msg.setText(message);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static final void notifyViaSms(User user, String message) {
		//Create a Twilio REST client
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account account = client.getAccount();
 
        //Use the API to send a text message
        SmsFactory smsFactory = account.getSmsFactory();
        Map<String, String> smsParams = new HashMap<String, String>();
        smsParams.put("To", "+1" + user.getPhone()); 
        smsParams.put("From", TWILIO_NUMBER);
        smsParams.put("Body", message);
        try {
			smsFactory.create(smsParams);
		} catch (TwilioRestException e) {
			e.printStackTrace();
		}
	}
}

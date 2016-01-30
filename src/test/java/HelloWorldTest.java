import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloWorldTest {
	private String localAddress = "http://localhost:8080/hello";
	private String remoteAddress = "http://cs263-webmon.appspot.com/hello";
	private String toTest = localAddress;
	private String expectedResponse = "Hello World!";
	@Test
	public void testReply() throws IOException {
		URL url;
		HttpURLConnection connection;
		
		url = new URL(toTest);
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int code = connection.getResponseCode();
		assertEquals(200, code);
		String response = (new BufferedReader(new InputStreamReader((connection.getInputStream())))).readLine();
		assertEquals(expectedResponse, response);
	}
}
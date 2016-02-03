package webmon;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloWorldTest {
	private String toTest = TestConstants.localAddress;
	private String expectedResponse = "Hello World!";
	
	@Test
	public void testReply() {
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL(toTest);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			assertEquals(200, code);
			String response = (new BufferedReader(new InputStreamReader((connection.getInputStream())))).readLine();
			assertEquals(expectedResponse, response);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
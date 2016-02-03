package webmon.tests;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloWorldTest {
	private String toTest = TestConstants.localAddress;
	
	@Test
	public void testHelloWorld() throws IOException {
		URL url;
		HttpURLConnection connection;

		String expectedResponse = "Hello World!";
		
		url = new URL(toTest + "hello");
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int code = connection.getResponseCode();
		assertEquals(200, code);
		String response = (new BufferedReader(new InputStreamReader((connection.getInputStream())))).readLine();
		assertEquals(expectedResponse, response);
	}
	
	@Test
	public void testJersey() throws IOException {
		URL url;
		HttpURLConnection connection;

		String expectedResponse = "Hello Jersey";
		
		url = new URL(toTest + TestConstants.restUrl + "test");
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int code = connection.getResponseCode();
		assertEquals(200, code);
		String response = (new BufferedReader(new InputStreamReader((connection.getInputStream())))).readLine();
		assertEquals(expectedResponse, response);
	}
}
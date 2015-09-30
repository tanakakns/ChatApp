package com.example.chatapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessageHttpClient {
	
	private String postUrlStr = "http://chatserver-sample.elasticbeanstalk.com/ChatServer/message?m=";
	private String getUrlStr = "http://chatserver-sample.elasticbeanstalk.com/ChatServer/messages/latest/10";
	private URL postUrl = null;
	private URL getUrl = null;
	
	public MessageHttpClient() {
		try {
			this.getUrl = new URL(getUrlStr);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean post(String body) {
		HttpURLConnection conn;
		try {
			this.postUrl = new URL(postUrlStr+body);
			conn = (HttpURLConnection) postUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.connect();
//			PrintWriter w = new PrintWriter(conn.getOutputStream());
//			w.print("m="+body);
//			w.close();
			conn.getResponseCode();
			
			return true;
		} catch (IOException e) {
			Log.d("app", "During post:" + body, e);
			return false;
		}
	}
	
	public List<String> get() {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) getUrl.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.connect();
			conn.getResponseCode();
			BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			Gson gson = new Gson();
			return gson.fromJson(r, new TypeToken<List<String>>(){}.getType());
			
		} catch (IOException e) {
			Log.d("app", "During get:" + e);
			return new ArrayList<String>();
		}
	}
}

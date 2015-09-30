package com.example.chatapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

public class MessageHttpClient {
	
	private String urlStr = "";
	private URL url = null;
	
	public MessageHttpClient() {
		try {
			this.url = new URL(urlStr);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean post(String body) {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.connect();
			PrintWriter w = new PrintWriter(conn.getOutputStream());
			w.print("m="+body);
			w.close();
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
			conn = (HttpURLConnection) url.openConnection();
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

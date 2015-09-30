package com.example.chatapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddMessageActivity extends Activity {

	TextView tv;
	Button sendButton;
	Button cancelButton;
	
	String message;
	MessageHttpClient httpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_message);

		tv = (TextView) findViewById(R.id.inputMessage);
		sendButton = (Button) findViewById(R.id.inputButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);
		
		httpClient = new MessageHttpClient();

		sendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				message = tv.getText().toString();
				sendNewMessage();
				finish();
			}
		});

		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	public void sendNewMessage() {
		//Send to Server
		MessageSyncTask mst = new MessageSyncTask();
		mst.execute();
	}
	
	protected class MessageSyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			if(httpClient.post(message)){
				//tv.setText("");
			}
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_message, menu);
		return true;
	}

}

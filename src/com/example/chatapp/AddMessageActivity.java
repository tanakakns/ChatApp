package com.example.chatapp;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_message);

		tv = (TextView) findViewById(R.id.inputMessage);
		sendButton = (Button) findViewById(R.id.inputButton);
		cancelButton = (Button) findViewById(R.id.cancelButton);

		sendButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String message = tv.getText().toString();
				sendNewMessage(message);
				finish();
			}
		});

		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv.setText("");
				finish();
			}
		});
	}

	public void sendNewMessage(String message) {
		//Send to Server
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_message, menu);
		return true;
	}

}

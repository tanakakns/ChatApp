package com.example.chatapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	
	private List messageList = null;
	private SimpleAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		messageList = new ArrayList<String>();
		adapter = new SimpleAdapter(
				getApplicationContext(),
				messageList,
				R.layout.message_in_list,
				new String[] {"message"}, // from
                new int[] {R.id.textView1} // to
				);
		ListView lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			Intent intent = new Intent(this, AddMessageActivity.class);
			startActivityForResult(intent, 0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		GetMessageSyncTask async = new GetMessageSyncTask();
		async.execute();
		messageList = async.results;

		adapter.notifyDataSetChanged();
	}

	protected class GetMessageSyncTask extends AsyncTask<Void, Void, Void> {
		
		List<String> results = null;
		
		@Override
		protected Void doInBackground(Void... arg0) {
			MessageHttpClient httpClient = new MessageHttpClient();
			
			results = httpClient.get();
			return null;
		}
	}

}

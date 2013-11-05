package com.corso.fileoutput;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class Data extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data, menu);
		return true;
	}
	
	
	public void ok(View view)
	{
		DatePicker d = (DatePicker) findViewById(R.id.calendario);
		String data = d.getDayOfMonth() + " - "+ d.getMonth() + " - "+ d.getYear();
		Intent i = new Intent();
		i.putExtra("data_selezionata", data);
		setResult(1,i);
		finish();
	}
	
	public void displayData(View v)
	{
		DatePicker d = (DatePicker) findViewById(R.id.calendario);
		Toast.makeText(this, "data selezionata " + d.getDayOfMonth() + " - "+ d.getMonth() + " - "+ d.getYear() , Toast.LENGTH_LONG).show();

	}

}

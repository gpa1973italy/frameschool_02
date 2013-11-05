package it.woodstocoasts.gpaservicecounter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	/***
	 * 
	 */
	Button btnStart = (Button) findViewById(R.id.btnStartService);
	btnStart.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startService(v);
		}
	});
	
	
	/***
	 * 
	 */
	Button btnStop = (Button) findViewById(R.id.btnStopService);
	btnStop.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stopService(v);
		}
	});	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Avvio il servizio
	 * @param v
	 */
	public void startService(View v)
	{
		Intent i = new Intent(getApplicationContext(), CounterService.class);
		startService(i);
	}
	
	/**
	 * Arresto il servizio
	 * @param v
	 */
	public void stopService(View v)
	{
		Intent i = new Intent(getApplicationContext(), CounterService.class);
		stopService(i);
	}
}

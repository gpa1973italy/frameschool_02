package it.woodstocoasts.gpaservicecounter;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class CounterService extends Service {

	private Timer ticker;
	private long counter;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Invocato alla creazione del servizio
	 */
	@Override
	public void onCreate()
	{
		super.onCreate();
		counter = 1;

		TimerTask taskToExecute = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i("GPA_Service_Counter", "Stato contatore: " + counter++);
				//Toast.makeText(getApplicationContext(), "Sono al : " + counter, Toast.LENGTH_SHORT).show();
			}
		};

		ticker = new Timer();
		ticker.scheduleAtFixedRate(taskToExecute, 0, 3000);

	}

	/**
	 * Viene invocato all'arresto del servizio
	 */
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		
		// Stoppo il timer
		ticker.cancel();
		
		// Distruggo tutto! :)
		ticker = null;
		Log.i("GPA_Service_Counter", "Servizio arrestato");
	}
	
}

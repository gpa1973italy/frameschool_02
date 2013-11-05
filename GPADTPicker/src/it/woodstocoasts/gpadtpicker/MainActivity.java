package it.woodstocoasts.gpadtpicker;

import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		final DatePicker dtpick = (DatePicker) findViewById(R.id.dtp);
		
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		
		
		/***
		 * Disabilito la funzione della settimana nelle nuove
		 * versioni di Android, va messo in testa 
		 * 	@SuppressLint("NewApi") 
		 * per non dare errore di compilazione!
		 */
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= 11){
			dtpick.setCalendarViewShown(false);
		}
		
		dtpick.updateDate(year, month, day);
		
		final Button btnGetDate = (Button) findViewById(R.id.button1);
		
		btnGetDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnGetDate.setText("Mostra data");
				
				String myRes = "";
				myRes += "Il risultato non modificato.\n";
				myRes += dtpick.getDayOfMonth() + " - ";
				myRes += dtpick.getMonth() + " - ";
				myRes += dtpick.getYear();
				
				myRes += "\n\nIl risultato CORRETTO: \n";
				myRes += dtpick.getDayOfMonth() + " - ";
				myRes += (dtpick.getMonth() + 1) + " - ";
				myRes += dtpick.getYear();
				
				
				Toast.makeText(getApplicationContext(), myRes, Toast.LENGTH_LONG).show();
								
				
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

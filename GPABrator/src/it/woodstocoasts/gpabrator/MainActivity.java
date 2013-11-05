package it.woodstocoasts.gpabrator;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		
		Button btnVib = (Button) findViewById(R.id.btnVibra);
		
		btnVib.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				

				Vibrami();
						
			}
		});
		
		
		
		
	}
	
	
	
	public void Vibrami()
	{
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		long[] vibpat = new long[] {0,800,200,1200,300,2000,400,4000};
		
		//vib.vibrate(vibpat, 5);
		vib.vibrate(2000);
		
		vib.cancel();
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

package it.woodstocoasts.gpamenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId())
		{
		case R.id.action_DTAdjust:
			Toast.makeText(getApplicationContext(), "Modifica Data",Toast.LENGTH_LONG).show();
			break;
		case R.id.action_TMAdjust:
			Toast.makeText(getApplicationContext(), "Modifica Ora",Toast.LENGTH_LONG).show();
			break;
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "Impostazioni",Toast.LENGTH_LONG).show();
			break;
		case R.id.action_Exit:
			Toast.makeText(getApplicationContext(), "Esci",Toast.LENGTH_LONG).show();
			break;

			default:
				Toast.makeText(getApplicationContext(), "-",Toast.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
		
	}

}

package it.woodsocoasts.gpacalcdialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnCalc = (Button)	findViewById(R.id.btnCalc);

		final TextView tvValoreA = (TextView) findViewById(R.id.etValoreA);
		final TextView tvValoreB = (TextView) findViewById(R.id.etValoreB);

		btnCalc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double a = Double.parseDouble(tvValoreA.getText().toString());
				double b = Double.parseDouble(tvValoreB.getText().toString());

				createDialog1(a, b);
			}
		});
		
		
		/**
		 * 
		 */
		
		Button btnMsg = (Button) findViewById(R.id.btnMessaggio);

		btnMsg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_LONG).show();	
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private Dialog createDialog1(double a, double b){
		AlertDialog.Builder dlg = new AlertDialog.Builder(this);

		double result = Math.pow(a, b);

		dlg.setTitle("Elevamento a potenza")
		.setNeutralButton("OK", null)
		.setMessage("Il valore " + a + " elevato a " + b + "\n è uguale a:" + result);
		//dlg.create();

		return dlg.show();

	}

}

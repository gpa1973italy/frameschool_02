package com.example.it.woodstocoasts.gpadlgtest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Button btn = (Button) findViewById(R.id.btn2ndChiudi);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				removeDialog(1);
				showDialog(1);
			}
		});


		Button btn2 = (Button) findViewById(R.id.btnOrologioDialog);
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				removeDialog(2);
				showDialog(2);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	protected Dialog onCreateDialog(int idDialog){
		Dialog dlgg = null;
		switch (idDialog)
		{


		case 1:

			dlgg = new AlertDialog.Builder(this).setTitle("Conferma uscita").setMessage("Sei sicuro di voler uscire")
			.setPositiveButton("Ok", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					//setTitle("TITOLO");
					finish();
				}
			}).setNegativeButton("Annulla", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Toast.makeText(getApplicationContext(), "Hai annullato", Toast.LENGTH_LONG).show();
				}
			}).create();
			break;
		case 2:
			dlgg = customClockDialog();
			break;
		}

		return dlgg;
	}



	private Dialog customClockDialog(){
		View vw = getLayoutInflater().inflate(R.layout.dlg_orologio, null);
		AlertDialog.Builder shdlg = new AlertDialog.Builder(this);

		shdlg.setTitle("orologio")
		.setMessage("guarda che ora è")
		.setView(vw)
		.setNeutralButton("OK", null);
		AlertDialog gino = shdlg.create();
		gino.setCanceledOnTouchOutside(true);

		return gino;

	}
}



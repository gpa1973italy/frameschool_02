package com.example.it.woodstocoasts.gpadlgtest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
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
		
		Button btnSimple = (Button) findViewById(R.id.btnSimpleDialog);
		
		btnSimple.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent SecondDialog = new Intent(getApplicationContext(), SecondActivity.class);

				startActivity(SecondDialog);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	public void ShowMyDialog(){
//		AlertDialog.Builder myDlg =  new AlertDialog.Builder(this);
//		
//		myDlg.setTitle("CIAO")
//		.setMessage("123 test");
//		
//	}

}

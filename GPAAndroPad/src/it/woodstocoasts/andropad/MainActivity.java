package it.woodstocoasts.andropad;

import it.woodstocoasts.andropad.R.string;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {

	private final static int SUCCES_COLOR = Color.GREEN;
	private final static int ERROR_COLOR = Color.RED;
	private static final String FILE_PATH = "mioFile";




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Spinner spinner = (Spinner) findViewById(R.id.spinModes);
		String[] mialista = getResources().getStringArray(R.array.access_modes);

		ArrayAdapter<String> mioAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mialista);

		spinner.setAdapter(mioAdapter);

		/* 
			// Create an ArrayAdapter using the string array and a default spinner layout
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
			        R.array.access_modes, android.R.layout.simple_spinner_item);

			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			// Apply the adapter to the spinner
			spinner.setAdapter(adapter);
		 */


		Button btnSaveFile = (Button) findViewById(R.id.btnSaveFile);
		btnSaveFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SaveFile();
			}
		});


		Button btnOpen = (Button) findViewById(R.id.btnOpenFile);
		btnOpen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ReadFile();
			}
		});

		Button btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DeleteFile();
			}
		});
		
		
		
		Button btnExtAct = (Button) findViewById(R.id.extAct);
		btnExtAct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PackageManager pm = getPackageManager();
				Intent intent = pm.getLaunchIntentForPackage("it.woodstocoasts.gpadtpicker");
				startActivity(intent);	
			}
		});

	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public void SaveFile (){
		EditText etDati = (EditText) findViewById(R.id.editText1);
		TextView tvError = (TextView) findViewById(R.id.textView1);
		tvError.setText("");

		String dati_da_salvare = etDati.getText().toString();
		Spinner spinner = (Spinner) findViewById(R.id.spinModes);

		long modo_accesso = spinner.getSelectedItemId();
		int i = 0;

		//int i = modo_accesso >= 0 ? (int) modo_accesso: Context.MODE_PRIVATE;

		int modo;

		switch (i){
		case 1:
			modo = Context.MODE_PRIVATE;
			break;
		case 2:
			modo = Context.MODE_WORLD_READABLE;
			break;
		case 3:
			modo = Context.MODE_WORLD_WRITEABLE;
			break;
		case 4:
			modo = Context.MODE_APPEND;
			break;
		default:
			modo = Context.MODE_PRIVATE;
		}

		try {

			FileOutputStream myStream = openFileOutput(FILE_PATH, modo);
			//DataOutputStream strOut = new DataOutputStream(myStream);

			//strOut.writeUTF(dati_da_salvare.toString());
			//strOut.close();

			DataOutputStream strOut = new DataOutputStream(myStream);
			strOut.writeUTF(dati_da_salvare.toString());
			strOut.close();

			tvError.setText("OK!");
			tvError.setBackgroundColor(SUCCES_COLOR);
			etDati.setText(null);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			tvError.setText("Errore:" + ex.getMessage());
			tvError.setBackgroundColor(ERROR_COLOR);
		}
		finally{

		}




	}




	public void ReadFile (){
		EditText etDati = (EditText) findViewById(R.id.editText1);
		TextView tvError = (TextView) findViewById(R.id.textView1);
		tvError.setText("");


		try {
			FileInputStream fis = openFileInput(FILE_PATH);
			DataInputStream dis = new DataInputStream(fis);

			CharSequence t = (CharSequence) dis.readUTF();
			etDati.setText(t);
			dis.close();
			tvError.setText("OK!");
			tvError.setBackgroundColor(SUCCES_COLOR);


		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			tvError.setText("Errore:" + ex.getMessage());
			tvError.setBackgroundColor(ERROR_COLOR);
		}
		finally{

		}


	}

	public void DeleteFile(){
		TextView tvError = (TextView) findViewById(R.id.textView1);
		tvError.setText("");
		File f = getFileStreamPath(FILE_PATH);

		boolean deleted = false;

		try {
			deleted = f.delete();
			tvError.setText("OK! :" + deleted);
			tvError.setBackgroundColor(SUCCES_COLOR);

		}
		catch (SecurityException ex){
			ex.printStackTrace();
			tvError.setText("Errore:" + ex.getMessage());
			tvError.setBackgroundColor(ERROR_COLOR);
		}
		finally{
		}
	}
}




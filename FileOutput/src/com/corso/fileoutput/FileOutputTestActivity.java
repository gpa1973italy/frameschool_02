package com.corso.fileoutput;

import java.io.File;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FileOutputTestActivity extends Activity {
	
	// definisco i Colori relativi ai messaggi di successo ed errore
	private final static int SUCCESS_COLOR = Color.GREEN;
	private final static int ERROR_COLOR = Color.RED;

	// Path del file da salvare
	private final static String FILE_PATH = "myFile";
	// Riferimento allo Spinner
	private Spinner spinnerObj;
	// TextView dei messaggi di output
	private TextView output;
	// EditText per l'input
	private EditText editText;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// legge array con i valori con le varie modalita di lettura/scrittura nel file
		String[] valori = getResources().getStringArray(R.array.mode_array);
		
		/* Definisce un ArrayAdapter e associa all'adapter i valori
		 * dell'array
		 * Un Adapter è una specie di vettore, nel nostro caso tipizzato a String, 
		 * dove le stringhe passate come parametri vengono adattate agli 
		 * elementi del componente (nel nostro caso lo Spinner)
		*/


		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, valori);

		// anche così:
		//ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		//		R.array.mode_array, android.R.layout.simple_spinner_item);		
		
		// Crea riferimento all'oggetto spinner
		spinnerObj = (Spinner) findViewById(R.id.mySpinner);
		
		// ASSOCIA L'ADAPTER ALL'OGGETTO SPINNER
		spinnerObj.setAdapter(adapter);
		
		// Crea riferimenti agli oggetti TextView di output e EditText
		output = (TextView) findViewById(R.id.output);
		editText = (EditText) findViewById(R.id.inputText);

		
	}

	// test
	public void testClasseLeggiFile(View button)
	{
		
		LeggiFileTxt f = new LeggiFileTxt(FILE_PATH, this);
		CharSequence s = f.esegui();
		TextView t = (TextView)findViewById(R.id.output);
		t.setText(s.toString());
		Toast.makeText(this, "Valore letto: " +  s , Toast.LENGTH_LONG).show();
		
	}
	/*
	 * Scrive nel file il testo inserito
	 * 
	 */
	public void saveFile(View button) {
		
		// Legge la sequenza di caratteri digitati nel componente EditText
		// CharSequence è un'interfaccia di java.lang
		// consente di leggere i dati carattere carattere senza vincolarsi a String che è di tipo final
		CharSequence inputDati = editText.getText();
		
		// Leggo il valore associato selezionato nell'oggetto Spinner
		long selectedId = spinnerObj.getSelectedItemId();
		
		int modo = Context.MODE_PRIVATE;	//default
		if (selectedId == 0) {
			/* Rende il file privato, cioè di proprietà esclusiva dell'applicazione 
			 * che lo crea. Nessun'altra applicazione potrà accedere a questo file
			 */
			modo = Context.MODE_PRIVATE;
		} else if (selectedId == 1) {
			/* le informazioni sono accessibili in lettura da 
			 * tutte le applicazioni
			 */
			modo = Context.MODE_WORLD_READABLE;
		} else if (selectedId == 2) {
			/* Rende il file accessibile in sola scrittura dalle altre applicazioni 
			 * installate nel sistema
			 */
			modo = Context.MODE_WORLD_WRITEABLE;
		} else if (selectedId == 3) {
			/* Agisce in append sul file specificato, cioè se il file già esiste 
			 *  invece di sovrascriverlo gli accoda i nuovi byte che saranno 
			 *  scritti nello stream. Utile quando si generano report e log.
			*/ 
			modo = Context.MODE_APPEND;
		}

		// Reset del messaggio di output
		output.setText("");
		
		// salva il contenuto nel file di testo
		try {
			// Apre uno StreamOut nella modalità selezionata
			/*
			FileOutputStream fos;
			if(modo != Context.MODE_APPEND)
				fos = openFileOutput(FILE_PATH, modo);
			else
				fos = new FileOutputStream(new File(FILE_PATH), true);
			*/
			FileOutputStream fos = openFileOutput(FILE_PATH, modo);
			// Crea variabile di tipo DataOutputStream 
			DataOutputStream dos = new DataOutputStream(fos);
			
			// converte il CharSequence in String e scrive nel file 
			// (utilizza il set di caratteri UTF)
			dos.writeUTF(inputDati.toString());
			
			// chiude il file
			dos.close();
			
			// imposta il colore associato a operazione corretta
			output.setTextColor(SUCCESS_COLOR);
			
			// valorizza la textView con la stringa ok_message
			output.setText(R.string.ok_message);
		} catch (IOException e) {
			e.printStackTrace();
			
			// imposta il colore associato a operazione errata
			output.setTextColor(ERROR_COLOR);
			// Visualizza l'errore nella textView
			output.setText(e.getMessage());
		}
	}

	/**
	 * Caricamento del file e visualizzazione nella EditText
	 *
	 * @param button
	 *    Riferimento al Button
	 */
	public void loadFile(View button) {
		// Legge il file
		try {
			// Apre uno Stream input del file da leggere
			FileInputStream fis = openFileInput(FILE_PATH);
			
			DataInputStream dis = new DataInputStream(fis);
			String text = dis.readUTF();
			dis.close();
			output.setTextColor(SUCCESS_COLOR);
			editText.setText(text);
			output.setText(R.string.ok_message);
			
			// invia un Toast
			// il contest non può essere this perchè si riferisc eal listener del pulsante
			Toast.makeText(FileOutputTestActivity.this, "Il File è stato caricato", Toast.LENGTH_LONG).show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			output.setTextColor(ERROR_COLOR);
			output.setText(e.getMessage());
		}
	}

	/**
	 * Cancella il file e visualizzazione nella EditText
	 * 
	 * @param button
	 *     Riferimento al Button
	 */
	public void deleteFile(View button) {
		File file = getFileStreamPath(FILE_PATH);
		
		output.setText(file.toString());
		
		boolean deleted = false;
		try {
			deleted = file.delete();

			// E' possibile anche invocare il seguente metodo
			// di Context
			// deleteFile(FILE_PATH);
			
			output.setTextColor(SUCCESS_COLOR);
			output.setText("Cancellazione:" + deleted);
			editText.setText("");
		} catch (SecurityException se) {
			se.printStackTrace();
			deleted = false;
			output.setTextColor(ERROR_COLOR);
			output.setText(se.getMessage());
		}
	}
	
	public void avviaActivity2(View view)
	{
		Intent i = new Intent(getApplicationContext(), Data.class);
		
		// lancia intent e attende codice di ritorno
		startActivityForResult(i, 1);
		//DatePicker d = (DatePicker) findViewById(R.id.calendario);
		//Toast.makeText(this, "data selezionata " + d.getDayOfMonth() + " - "+ d.getMonth() + " - "+ d.getYear() , Toast.LENGTH_LONG).show();
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent Data)
	{
		if(requestCode ==CONTEXT_INCLUDE_CODE)
		{
			if(resultCode==0)
			{
				Toast.makeText(this, "Premuto pulsante back " , Toast.LENGTH_LONG).show();
			}
			else if(resultCode==1)
			{
				// confermato
				String valore_arrivo = Data.getStringExtra("data_selezionata");
				Toast.makeText(this, "data selezionata " +  valore_arrivo , Toast.LENGTH_LONG).show();
				
			}
		}
	}
}
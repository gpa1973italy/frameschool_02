package com.corso.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private View button1;

	private View button2;

	private View button3;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		button3 = findViewById(R.id.button3);
	}

	public void onClickButton(View v)
	{
		v.setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.enable_button_1:
			button1.setEnabled(true);
			return true;
		case R.id.enable_button_2:
			button2.setEnabled(true);
			return true;
		case R.id.enable_button_3:
			button3.setEnabled(true);
			return true;
		case R.id.menu_about:
			Toast.makeText(this, R.string.about_clicked, Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	// chiamato prima di visualizzare gli items del menu
	// imposta lo stato enabled degli items del menu al contrario dello stato enabled dei bottoni
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		menu.findItem(R.id.enable_button_1).setEnabled(!button1.isEnabled());
		menu.findItem(R.id.enable_button_2).setEnabled(!button2.isEnabled());
		menu.findItem(R.id.enable_button_3).setEnabled(!button3.isEnabled());
		return true;
	}

}
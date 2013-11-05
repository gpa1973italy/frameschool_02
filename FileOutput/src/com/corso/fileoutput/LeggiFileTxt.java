package com.corso.fileoutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;


public class LeggiFileTxt{
	
	String fileName;
	Context context;
	
	LeggiFileTxt(String fileName, Context context)
	{
		this.fileName = fileName;
		this.context = context;
		
	}
	public CharSequence esegui() {
	    BufferedReader in = null;
	    try {
	            in = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
	            String line;
	            StringBuilder buffer = new StringBuilder();
	            while ((line = in.readLine()) != null) buffer.append(line).append('\n');
	            return buffer;
	    } catch (IOException e) {
	            Log.e("readTextFile", "Error readind file " + fileName, e);
	            return "";
	    } finally {
	            if (in != null) {
	                    try {
	                            in.close();
	                    } catch (IOException e) {
	                            // Ignore
	                    }
	            }
	    }
	}
	
}

package it.woodstocoasts.gpavideo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity {

	VideoView video;
	private boolean videopause = false;
	private boolean videoisplay = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		video = (VideoView) findViewById(R.id.videoView1);

		Button btnNet = (Button) findViewById(R.id.btnStartFromNet);
		btnNet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startVideoFromNet(v);
			}
		});

		
		Button btnLocal = (Button) findViewById(R.id.btnStartFromLocal);
		btnLocal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startVideoFromLocalResource();
			}
		});
		
		
		video.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				if (videoisplay)
				{
					
					
					if (videopause == false)
					{
						video.pause();
						videopause = !videopause;
					}
					else
					{
						video.resume();
						videopause = !videopause;
					}
						
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void startVideoFromNet(View v)
	{
		Uri videoUri = Uri.parse("http://www.gflash.it/android/video.mp4");
		video.setVideoURI(videoUri);
		video.start();
	}
	

	public void startVideoFromLocalResource()
	{
		video.setVideoPath("/storage/sdcard0/DCIM/Camera/20130623_125525.mp4");
		
		video.start();
		videoisplay = true;
	}
	

}

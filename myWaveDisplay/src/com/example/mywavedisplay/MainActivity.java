package com.example.mywavedisplay;

import java.util.Timer;
import java.util.TimerTask;

//import javax.security.auth.callback.Callback;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private  SurfaceHolder holder;
	Timer timer = new Timer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		final SurfaceView surface = (SurfaceView) findViewById(R.id.sv_show);
		Button sin = (Button) findViewById(R.id.btn_sin);
		Button cos = (Button) findViewById(R.id.btn_cos);	
		DisplayMetrics metrics = new DisplayMetrics();  
		Display display = this.getWindowManager().getDefaultDisplay();
		display.getMetrics(metrics);		
		final int HEIGHT = metrics.heightPixels;
		final int WIDTH=metrics.widthPixels;
		Log.d("WIN", "HEIGHT: "+HEIGHT+" "+"WIDTH: "+WIDTH);
		holder = surface.getHolder();
		final DrawWave myDraw=new DrawWave();
		sin.setOnClickListener(new OnClickListener(){

			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				myDraw.cancletask();
				myDraw.drawBack(holder, WIDTH, HEIGHT);
				myDraw.drawWave(holder, WIDTH, HEIGHT);
	
				
			}
			
		});
		
		

		
	}

	}

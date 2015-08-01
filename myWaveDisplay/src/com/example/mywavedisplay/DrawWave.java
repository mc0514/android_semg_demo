package com.example.mywavedisplay;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class DrawWave {
	
	boolean isCreated=false;
	
	//private SurfaceHolder holder;
	private Paint paint;
	private Paint p1;
	private Paint textpaint;
	
	final int X_OFFSET = 5;
	final int Y_OFFSET=5;
	
	Timer timer = new Timer();
	//Timer timer =null;
	TimerTask task = null;
	//private  SurfaceHolder holder;
	
	public void drawBack(SurfaceHolder holder,int width,int height) {
		
		//SurfaceHolder holder =surface.getHolder();
		Canvas canvas = holder.lockCanvas();
		// 绘制黑色背景
		canvas.drawColor(Color.BLACK);
		 p1 = new Paint();
		p1.setColor(Color.RED);
		p1.setStrokeWidth(5);

		// 绘制方框
		// canvas.drawLine(X_OFFSET, centerY, WIDTH, centerY, p);
		canvas.drawLine(X_OFFSET, Y_OFFSET, width-X_OFFSET, Y_OFFSET, p1);
		//canvas.drawLine(X_OFFSET, height-Y_OFFSET, width, height-Y_OFFSET, p1);
		canvas.drawLine(X_OFFSET, X_OFFSET, X_OFFSET, height-Y_OFFSET, p1);
		canvas.drawLine(width-X_OFFSET, Y_OFFSET, width-X_OFFSET, height-Y_OFFSET, p1);
		holder.unlockCanvasAndPost(canvas);
		holder.lockCanvas(new Rect(0, 0, 2, 300));
		holder.unlockCanvasAndPost(canvas);

	}
	public void cancletask()
	{
		if(task!=null)
		 {
			 task.cancel();
		 }
	}
	
	public void cleanDisplay(Canvas canvas,int color)
	{
		
			canvas.drawColor(color);
	}
	public void drawWave( final SurfaceHolder holder, final int width, final int height)
	{
		//timer=new Timer();
		//final SurfaceHolder holder = surface.getHolder();
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(1);
		textpaint=new Paint();
		textpaint.setTextSize(10);  
		textpaint.setColor( Color.WHITE);
		textpaint.setTextAlign(Paint.Align.RIGHT);
		
		//int cx=X_OFFSET;
		 
		 task=new TimerTask(){

			 int cx=2*X_OFFSET;
				int beforecx=2*X_OFFSET;
				int beforecy=2*Y_OFFSET;
				int tt=0;
			 @Override
			public void run() {
				// TODO Auto-generated method stub
				 int cy = (int) (Math.random() * (height-200	));
				 if(holder==null)
				 {
					 Log.d("holder", "null");
				 }
				

				 Log.d("height", Integer.toString(height));
				Canvas canvas = holder.lockCanvas(new Rect(beforecx,2*Y_OFFSET ,cx, height));
				 if(canvas==null)
				 {
					 Log.d("canvas", "null");
				 }
				
				if(isCreated==false)
				{
					Log.d("holder", "created failed!");
				}
				 
				 //cleanDisplay(canvas,Color.BLACK);
				
				
				 canvas.drawColor(Color.BLACK);
				 canvas.drawLine(beforecx, beforecy, cx, cy, paint);
				 canvas.drawLine(beforecx,height-200, cx, height-200, textpaint);
				 
				 String text=Integer.toString(tt);
				float textWidth = textpaint.measureText(text); 
				canvas.drawText(text,cx,height-180, textpaint);
				
				beforecx = cx;
				beforecy = cy;
				//cx = cx + 80;
				tt=tt+40;
				
				cx=tt%(width-4*X_OFFSET);
				Log.d("cx", Integer.toString(cx));
//				if (cx > (width - 2*X_OFFSET)) {
//
//					//task.cancel();
//					//task = null;
//					beforecx=cx = 2*X_OFFSET;
//
//				}
				holder.unlockCanvasAndPost(canvas);
				
				
			}
			 
		 };
		 
		 timer.schedule(task, 0, 500);
		 
		 holder.addCallback(new Callback(){

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				isCreated=true;
				
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				drawBack(holder,width,height);
				
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				timer.cancel();
			}
			 
		 });
		
		
	}

}

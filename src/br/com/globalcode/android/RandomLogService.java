package br.com.globalcode.android;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class RandomLogService extends Service {
	
	private RandonLogBinder binder;
	
	@Override
	public void onCreate() {
		Log.d("And1OnlineLab11", "OnCreate");
		super.onCreate();
		
		binder = new RandonLogBinder();
	}

	@Override
	public IBinder onBind(Intent arg0) {

		return binder;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("And1OnlineLab11", "RandomLogService.onStartCommand");
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				int randomNumber = generateRandomNumber(0, 100);
				Log.d("And1OnlineLab11", "Service Aleatory Number: " +  randomNumber);
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0, 10000);
		return 0;
	}
	
	public int generateRandomNumber(int min, int max) {
		
		Random r = new Random(System.currentTimeMillis());
		int randomNumber = r.nextInt(max - min) + min;
		return randomNumber;
	}

	public class RandonLogBinder extends Binder {
		
		public RandomLogService getService() {
			return RandomLogService.this;
		}
	}
}



package br.com.globalcode.android;

import br.com.globalcode.android.RandomLogService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RandomLogReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Intent intentRandomLogService = new Intent(context, RandomLogService.class);
		context.startService(intentRandomLogService);
	}

}

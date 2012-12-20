package br.com.globalcode.android;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import br.com.globalcode.android.R;
import br.com.globalcode.android.RandomLogService;

public class MainActivity extends Activity {
	
	private EditText minEditText;
	private EditText maxEditText;
	private TextView numberTextView;
	
	private RandomLogService service;
	private ServiceConnection serviceConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		serviceConnection = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				service = null;
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
				Log.d("And1OnlineLab11", "ServiceConnection.onServiceConnected");
				service = ((RandomLogService.RandonLogBinder) serviceBinder).getService();
			}
		};
		
		Intent serviceIntent = new Intent(this, RandomLogService.class);
		bindService(serviceIntent, serviceConnection, Service.BIND_AUTO_CREATE);
		
		minEditText = (EditText) findViewById(R.id.minEditText);
		maxEditText = (EditText) findViewById(R.id.maxEditText);
		numberTextView = (TextView) findViewById(R.id.numberTextView);
		
		findViewById(R.id.produceButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				generateRandomNumber();
			}
		});
		
	}
	
	private void generateRandomNumber() {
		
		int min = Integer.parseInt(minEditText.getText().toString());
		int max = Integer.parseInt(maxEditText.getText().toString());
		int number = service.generateRandomNumber(min, max);
		numberTextView.setText("Random Number is: " + number);
	}

}

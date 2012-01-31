package de.Adam;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SimpleNotification extends Activity {
	
	private NotificationManager mNotificationManager;
	private int SIMPLE_NOTFICATION_ID;
	private final String TAG = SimpleNotification.class.getSimpleName();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d("TAG", "haha");
        
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		final Notification notifyDetails = new Notification(R.drawable.android,"New Alert, Click Me!",System.currentTimeMillis());
		

        Button start = (Button)findViewById(R.id.bt_startservice);
        Button cancel = (Button)findViewById(R.id.bt_stopservice);
        
//        start.setOnClickListener(new OnClickListener() {
//        	
//        	@Override
//			public void onClick(View v) {
//        		Context context = getApplicationContext();
//        		CharSequence contentTitle = "Notification Details...";
//        		CharSequence contentText = "Browse Android Official Site by clicking me";
//        		Intent notifyIntent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.android.com"));
//        		PendingIntent intent = 
//        			PendingIntent.getActivity(SimpleNotification.this, 0, 
//        			notifyIntent, android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
//        		
//        		notifyDetails.setLatestEventInfo(context, contentTitle, contentText, intent);
//        		mNotificationManager.notify(SIMPLE_NOTFICATION_ID, notifyDetails);
//        	}
//        });
        
//        cancel.setOnClickListener(new OnClickListener() {
//        	
//        	@Override
//			public void onClick(View v) {
//        		
//        		mNotificationManager.cancel(SIMPLE_NOTFICATION_ID);
//        	}
//        });
    }
    
    public void onButtonClick(View v){
    	switch(v.getId()){
    	
    	case R.id.bt_startservice:
    		Log.d(TAG , "Start Service");
    		if(isMyServiceRunning()){
    			Log.d(TAG, "Yes, MySerivice is allready running!!!");
    			Toast.makeText(this, "My Service is allready running!!!", Toast.LENGTH_SHORT).show();	

    		}else{
    			Log.d(TAG,"No, my Service is not running!!! Starting him");
    			startService(new Intent(this,MyService.class));
    		}
    		break;
    		
    	case R.id.bt_stopservice:
    		Log.d(TAG, "Stop Service");
    		stopService(new Intent(this,MyService.class));
    		break;
    	}
    }
    
    private boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
        	//Log.d(TAG, "ServiceName: "+ service.service.getClassName());
            if ("de.Adam.MyService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
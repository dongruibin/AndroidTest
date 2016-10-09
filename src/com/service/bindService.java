package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class bindService extends Service {
	final static String TAG="bindService";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG,"bindService is onCreate!");
	}
//onStart方法已经不推荐使用了
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG,"bindService is  onStartCommand");
		return super.onStartCommand(intent, flags, startId);
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG,"bindService is onDestroy");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG,"bindService id onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
//        IBinder result = null;  
//        if ( null == result ) result = new MyBinder() ;
//        Toast.makeText(this, "onBind",Toast.LENGTH_LONG);
//        return result;
	}
	
	//////注意为了保证可以在activity里面获取service对象
	class serviceBinder extends Binder{
		public bindService getService(){
			return bindService.this;
		}
	}

}

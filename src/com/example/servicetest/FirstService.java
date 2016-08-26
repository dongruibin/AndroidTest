package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class FirstService extends Service {
	//必须实现的方法---dong
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	//Service被创建时回调该方法
	@Override
	public void onCreate(){
		super.onCreate();
		System.out.println("service is create");
	}
	@Override
	//Service被启动时回调该方法
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		System.out.println("Service is started");
		return START_STICKY;
		
	}
	//Service被关闭时回调该方法
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		System.out.println("中华Service is Destroy");
	}
	
}

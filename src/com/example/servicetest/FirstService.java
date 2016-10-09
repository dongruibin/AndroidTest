package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class FirstService extends Service {
	//��ӡ��־����鿴����������
	private static final String TAG="FirstService"; //һ��дһ�¸�����
	//����ʵ�ֵķ���---dong
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	//Service������ʱ�ص��÷���
	@Override
	public void onCreate(){
		super.onCreate();
		//System.out.println("service is create");
		Log.v(TAG, "Service is create");
	}
	@Override
	//Service������ʱ�ص��÷���
	public int onStartCommand(Intent intent,int flags,int startId)
	{
		//System.out.println("Service is started");
		return START_STICKY;
		
	}
	//Service���ر�ʱ�ص��÷���
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		//System.out.println("�л�Service is Destroy");
		
	}
	
}

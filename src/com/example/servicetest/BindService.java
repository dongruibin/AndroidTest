package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService extends Service {
	private int count;
	private boolean quit;
	//����onBinder���������صĶ���
	private MyBinder binder=new MyBinder();
	//ͨ���̳�Binder��ʵ��IBinder��
	public class MyBinder extends Binder{
		//ͨ���̳�Binder����ʵ��һ��IBinder�������MyBinder����
		//Service���ڲ��࣬����ڰ󶨱���Service����֮ͨ��
		public int getCount(){
			//��ȡService������״̬��count
			return count;
		}
	}
	//����ʵ�ֵķ������󶨸�Serviceʱ�ص��ĸ÷���
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
//		return null;
		System.out.println("Service is Binded");
		return binder;
		//�����ʵ��onBind()�������÷���������һ���ɷ��ʸ�Service״̬���ݣ�countֵ��
		//��IBinder���󣬸ö��󽫱�����Service�ķ�����
	}
	//Service������ʱ�ص��÷���
	public void onCreate()
	{
		super.onCreate();
		System.out.println("Service is create");
		//����һ���̣߳���̬���޸�count״ֵ̬
		new Thread()
		{
			@Override
			public void run()
			{
				while(!quit)
				{
					try{
						Thread.sleep(1000);
					}
					catch(InterruptedException e){
						
					}
					count++;
				}
			}			
		}.start();
	}
	//Service ���Ͽ�����ʱ�ص��÷���
	@Override
	public boolean onUnbind(Intent intent){
		System.out.println("Service is Unbinded");
		return true;
	}
	//Service ���ر�ʱ�ص��÷���
	@Override
	public void onDestroy(){
		super.onDestroy();
		this.quit=true;
		System.out.println("Service is Destroyed");
	}

}

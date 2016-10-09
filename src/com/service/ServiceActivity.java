package com.service;

import com.example.prefer.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ServiceActivity extends Activity implements OnClickListener{
	final static String TAG="ServiceActivity";
	bindService bindservice;
	Button startServiceButton,bindServiceButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicemain);
		
		startServiceButton=(Button) findViewById(R.id.startService);
		 bindServiceButton=(Button) findViewById(R.id.bindService);
		startServiceButton.setOnClickListener(this);
		bindServiceButton.setOnClickListener(this);
		
	
		

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.startService:
				Log.i(TAG, "使用startService");
				Toast.makeText(ServiceActivity.this, "这里不进行start操作", Toast.LENGTH_LONG).show();
				break;
			case R.id.bindService:
				Toast.makeText(ServiceActivity.this, "本Activity就可以进行相应演示", Toast.LENGTH_LONG).show();
				//绑定服务
				Intent bindServiceIntent =new Intent(ServiceActivity.this,bindService.class);
				bindService(bindServiceIntent, conn, Context.BIND_AUTO_CREATE);
				break;
			default :break;
		
		}
	}
	
	private ServiceConnection conn=new ServiceConnection(){
		
		//获取服务对象
		 public void onServiceConnected(ComponentName name, IBinder service){
			 bindservice=((bindService.serviceBinder)service).getService();
		 }
		//无法获取服务对象
		 public void onServiceDisconnected(ComponentName name){
			 bindservice=null;
		 }
	};
	
//	注意：这个地方有朋友可能会出现onServiceConnected不调用的情况。
//	这个问题当调用bindService方法后就会回调Activity的onServiceConnected，
//	在这个方法中会向Activity中传递一个IBinder的实例，Acitity需要保存这个实例
//	在Service中需要创建一个实现IBinder的内部类(这个内部类不一定在Service中实现，但必须在Service中创建它)。
//	在OnBind（）方法中需返回一个IBinder实例，不然onServiceConnected方法不会调用。
//	不过，我在这里传递null也能够调用，大家根据情况进行判定吧，如果是返回一个ibinder实例的话，可以参考binService里面的OnBind

}

package com.example.servicetest;


import com.example.prefer.R;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class servicestest extends Activity {//public整个包内外都可以使用
	//日志打印
	private static final String TAG="servicetest";
	//启动Service方法中的变量
	private Button start_sv,stop_sv;
	
	//跳转到音乐播放器的开关
	private Button mp3_start;
	//绑定Service方法中的变量
	private Button b_bind,b_unbind,get_s;
		//保持所启动的Service的IBinder对象
		BindService.MyBinder binder;
		//定义一个ServiceConnection对象
		private ServiceConnection conn=new ServiceConnection() {
			//当该Activity与Service连接成功时回调该方法

			@Override
			public void onServiceConnected(ComponentName arg0, IBinder arg1) {
				// TODO Auto-generated method stub
				System.out.println("--Service Connected--");
				//获取Service的onBind方法所返回的MyBinder对象
				binder=(BindService.MyBinder)arg1;
				//该代码binder=用于在该Activity与Service连接成功时获取Service的onBind()方法所返回的MyBinder对象
			}
			//当该Activity与Service断开连接时回调该方法
			@Override
			public void onServiceDisconnected(ComponentName arg0) {
				// TODO Auto-generated method stub
				System.out.println("--Service Disconnected--");
			}
			
		};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
    setContentView(R.layout.services_main);
    //控件初始化
     start_sv=(Button) findViewById(R.id.start_sv);
     stop_sv=(Button) findViewById(R.id.stop_sv);
     //创建启动Service的Intent
     final Intent sv_intent=new Intent();
     //为Intent设置Action属性
     sv_intent.setAction("com.service.FIRST_SERVICE");
     //下面设置setPackage---如果没有setPackage会出现implicit intents with startservice are not safe
     	sv_intent.setPackage(getPackageName());//这里你需要设置你应用的包名
     
     //绑定服务控件
     	b_bind=(Button) findViewById(R.id.b_bind);
     	b_unbind=(Button) findViewById(R.id.b_unbind);
     	get_s=(Button) findViewById(R.id.get_s);
     	//创建绑定Service的Intent
     	final Intent b_intent=new Intent();
     	b_intent.setAction("com.bindservice.BIND");
     	 b_intent.setPackage(getPackageName());
     	
     //启动按键
     start_sv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//启动指定的Service
			startService(sv_intent);
		}
	});
     //关闭按键
     stop_sv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			stopService(sv_intent);
		}
	});
     //另外一种绑定方法测试
     //启动绑定服务
     b_bind.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//绑定指定的Service
			bindService(b_intent,conn,Service.BIND_AUTO_CREATE);
		}
	});
     //解绑服务
     b_unbind.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//解除绑定的Service
			unbindService(conn);
		}
	});
     //获取Service里面的参数
     get_s.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//获取显示Service的count值
			Toast.makeText(getApplicationContext(), "Service的count值为"+binder.getCount(), 
					Toast.LENGTH_SHORT).show();
			//Toast里面的getApplicationContext 可以用services.this
		}
	});
     //基于mp3的音乐播放器
     mp3_start=(Button) findViewById(R.id.mp3_start);
     mp3_start.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent mp3intent=new Intent();
			mp3intent.setClass(getApplicationContext(), mp3test.class);
			startActivity(mp3intent);
		}
	});
	}

}

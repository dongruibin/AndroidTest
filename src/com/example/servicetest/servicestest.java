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

public class servicestest extends Activity {//public���������ⶼ����ʹ��
	//��־��ӡ
	private static final String TAG="servicetest";
	//����Service�����еı���
	private Button start_sv,stop_sv;
	
	//��ת�����ֲ������Ŀ���
	private Button mp3_start;
	//��Service�����еı���
	private Button b_bind,b_unbind,get_s;
		//������������Service��IBinder����
		BindService.MyBinder binder;
		//����һ��ServiceConnection����
		private ServiceConnection conn=new ServiceConnection() {
			//����Activity��Service���ӳɹ�ʱ�ص��÷���

			@Override
			public void onServiceConnected(ComponentName arg0, IBinder arg1) {
				// TODO Auto-generated method stub
				System.out.println("--Service Connected--");
				//��ȡService��onBind���������ص�MyBinder����
				binder=(BindService.MyBinder)arg1;
				//�ô���binder=�����ڸ�Activity��Service���ӳɹ�ʱ��ȡService��onBind()���������ص�MyBinder����
			}
			//����Activity��Service�Ͽ�����ʱ�ص��÷���
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
    //�ؼ���ʼ��
     start_sv=(Button) findViewById(R.id.start_sv);
     stop_sv=(Button) findViewById(R.id.stop_sv);
     //��������Service��Intent
     final Intent sv_intent=new Intent();
     //ΪIntent����Action����
     sv_intent.setAction("com.service.FIRST_SERVICE");
     //��������setPackage---���û��setPackage�����implicit intents with startservice are not safe
     	sv_intent.setPackage(getPackageName());//��������Ҫ������Ӧ�õİ���
     
     //�󶨷���ؼ�
     	b_bind=(Button) findViewById(R.id.b_bind);
     	b_unbind=(Button) findViewById(R.id.b_unbind);
     	get_s=(Button) findViewById(R.id.get_s);
     	//������Service��Intent
     	final Intent b_intent=new Intent();
     	b_intent.setAction("com.bindservice.BIND");
     	 b_intent.setPackage(getPackageName());
     	
     //��������
     start_sv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//����ָ����Service
			startService(sv_intent);
		}
	});
     //�رհ���
     stop_sv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			stopService(sv_intent);
		}
	});
     //����һ�ְ󶨷�������
     //�����󶨷���
     b_bind.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//��ָ����Service
			bindService(b_intent,conn,Service.BIND_AUTO_CREATE);
		}
	});
     //������
     b_unbind.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//����󶨵�Service
			unbindService(conn);
		}
	});
     //��ȡService����Ĳ���
     get_s.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//��ȡ��ʾService��countֵ
			Toast.makeText(getApplicationContext(), "Service��countֵΪ"+binder.getCount(), 
					Toast.LENGTH_SHORT).show();
			//Toast�����getApplicationContext ������services.this
		}
	});
     //����mp3�����ֲ�����
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

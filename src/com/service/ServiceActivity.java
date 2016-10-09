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
				Log.i(TAG, "ʹ��startService");
				Toast.makeText(ServiceActivity.this, "���ﲻ����start����", Toast.LENGTH_LONG).show();
				break;
			case R.id.bindService:
				Toast.makeText(ServiceActivity.this, "��Activity�Ϳ��Խ�����Ӧ��ʾ", Toast.LENGTH_LONG).show();
				//�󶨷���
				Intent bindServiceIntent =new Intent(ServiceActivity.this,bindService.class);
				bindService(bindServiceIntent, conn, Context.BIND_AUTO_CREATE);
				break;
			default :break;
		
		}
	}
	
	private ServiceConnection conn=new ServiceConnection(){
		
		//��ȡ�������
		 public void onServiceConnected(ComponentName name, IBinder service){
			 bindservice=((bindService.serviceBinder)service).getService();
		 }
		//�޷���ȡ�������
		 public void onServiceDisconnected(ComponentName name){
			 bindservice=null;
		 }
	};
	
//	ע�⣺����ط������ѿ��ܻ����onServiceConnected�����õ������
//	������⵱����bindService������ͻ�ص�Activity��onServiceConnected��
//	����������л���Activity�д���һ��IBinder��ʵ����Acitity��Ҫ�������ʵ��
//	��Service����Ҫ����һ��ʵ��IBinder���ڲ���(����ڲ��಻һ����Service��ʵ�֣���������Service�д�����)��
//	��OnBind�����������践��һ��IBinderʵ������ȻonServiceConnected����������á�
//	�������������ﴫ��nullҲ�ܹ����ã���Ҹ�����������ж��ɣ�����Ƿ���һ��ibinderʵ���Ļ������Բο�binService�����OnBind

}

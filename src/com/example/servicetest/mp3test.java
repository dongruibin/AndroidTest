package com.example.servicetest;

import com.example.prefer.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public  class mp3test extends Activity implements OnClickListener{
	//音乐控制开关
	ImageButton im_play,im_stop;//图片按钮
	//获取界面中显示的歌曲标题，以及作者文本框
	TextView title,author;
	//定义音乐名称、作者
	String[] tiltleStrs=new String[]{"心愿","约定","美丽新世界"};
	String[] authorStrs=new String[]{"未知艺术家","周慧","伍佰"};
	//音乐播放状态的设定
	int status=0x11;//0x11---没有音乐播放，0x12---正在播放，0x13---代表暂停
	//监听器(创建本Activity的广播接收对象)
	ActivityReceiver activityReceiver;
	//定义action(定义为静态，可以在Service里面调用)
	public  static final  String CTL_ACTION="com.example.servicetest.CTL_ACTION";
	public static final String UPDATE_ACTION="com.example.servicetest.UPDATE_ACTION";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3_main);
		//控件初始化
		title=(TextView) findViewById(R.id.title);
		author=(TextView) findViewById(R.id.author);
		//找到开关控件
		im_play=(ImageButton) findViewById(R.id.im_play);
		im_stop=(ImageButton) findViewById(R.id.im_stop);
		//为控件设置监听器
		im_play.setOnClickListener(this);
		im_stop.setOnClickListener(this);
		//实例化内部类
		activityReceiver=new ActivityReceiver();
		//创建一个IntentFilter(注：这里也可以使用在xml里面声明方式)
		IntentFilter filter=new IntentFilter();
		//指定BroadcastReceiver监听的Action
		filter.addAction("com.example.servicetest.UPDATE_ACTION");
		//注册BroadcastReceiver
		registerReceiver(activityReceiver, filter);//注册的是本Activity里面的Receiver接的广播
						//具备接收action为UPDATE_ACTION的广播
		//启动Musicservice
		Intent mp3_service=new Intent(getApplicationContext(), Musicservice.class);
		//mp3_service.setClass(getApplicationContext(), Musicservice.class);
		//启动后台Service
		startService(mp3_service);

	}
	//自定义一个BroadcastReceiver ,负责监听从service传回来的广播
	//重写onReceiver方法
	public class ActivityReceiver extends BroadcastReceiver{
		//重写接收方法
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			//获取Intent中的update消息，update代表播放状态
			//int update=mp3_service.getIntExtra("",-1);//出错是因为没有理解传递进入的参数是arg1
			int update=arg1.getIntExtra("update", -1);
			//获取Intent中的current消息，current代表当前正在播放的歌曲
			int current=arg1.getIntExtra("current", -1);
			//获取Intent里面的值后执行
			//获取current的值，改变文本框显示内容
			if(current>=0)
			{
				//说明状态发生了改变，于是要改换activity里面的TextView控件
				title.setText(tiltleStrs[current]);
				author.setText(authorStrs[current]);
			}
			switch(update)
			{
				//播放状态说明
			case 0x11:
				//系统没有音乐播放
				im_play.setImageResource(R.drawable.play);//im_play播放按钮设置为play---没有更新
				status=0x11;//当前界面的变量，当前状态
				break;
			case 0x12:
				//系统正在播放
				im_play.setImageResource(R.drawable.ic_launcher);//
				//im_play.setImageResource(R.drawable.play);//
				status=0x12;
				break;
			case 0x13:
				im_play.setImageResource(R.drawable.play);
				//系统暂停
				status=0x13;
				break;
			}
		}
		
	}
	//重写电点击按钮，按下这个按钮就会发送广播，action为 CTL_ACTION
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//这个点击事件主要是用来控制service的（通过发广播的形式）
		//Intent  mp_intent=new Intent("com.example.servicetest.CTL_ACTION");
		Intent  mp_intent=new Intent("com.example.servicetest.CTL_ACTION");
		//mp_intent.setAction(CTL_ACTION);
		switch(arg0.getId())
		{
		//播放/暂停按钮
		case R.id.im_play:
			Toast.makeText(getApplicationContext(), "im_play is OK", Toast.LENGTH_SHORT).show();
			mp_intent.putExtra("control", 1);//需要service停止----调试
			break;
		//停止按钮
		case R.id.im_stop:
			Toast.makeText(getApplicationContext(), "im_stop is OK", Toast.LENGTH_SHORT).show();
			mp_intent.putExtra("control", 2);
			break;		
		}
		//之前做好mp_intent,现在发送
		//发送广播，将被Service组件中的BroadcastReceiver接收到
		sendBroadcast(mp_intent);
	}

}

package com.example.servicetest;

import java.io.IOException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.widget.Toast;

public class Musicservice extends Service {
	//内部类的对象声明
	MyReceiver serviceReceiver;
	//文件资源管理
	AssetManager am;
	//音乐名称
	String[] musics=new String[]{
			"wish.mp3","promise.mp3","beautiful.mp3"
	};
	//音频播放对象声明
	MediaPlayer mPlayer;
	//当前的状态，0x11代表没有播放，0x12代表正在播放，0x13代表暂停
	int status=0x11;
	//记录当前正在播放的音乐
	int current=0;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	//重写创建方法
	@Override
	public void onCreate(){
		//获取资源
		am=getAssets();
		//创建BroadcastReceiver
		serviceReceiver=new MyReceiver();
		
		//创建IntentFilter
		IntentFilter filter=new IntentFilter();
		//设置Action属性
		filter.addAction(mp3test.CTL_ACTION);
		//CTL_ACTION前面加类名调用，因为该变量为静态变量，直接使用  类名.静态方法/变量
		
		//注册该BroadcastReceiver
		registerReceiver(serviceReceiver, filter);
		
		//创建一个MediaPlayer
		mPlayer=new MediaPlayer();
		//为该MediaPlayer播放完成绑定监听器
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			//音乐播放完了，要发广播通知前端activity更改状态
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				current++;//播放完可以播放下一首
				if(current>=3){
					current=0;
				}
				//发广播，通知activity更改文本框
				//Intent sendIntent=new Intent(mp3test.UPDATE_ACTION);
				Intent sendIntent=new Intent();
				sendIntent.setAction(mp3test.UPDATE_ACTION);
				//发送广播，将被Activity里面的BroadcastReceiver接收到
				sendIntent.putExtra("current", current);
				sendBroadcast(sendIntent);
				//准备播放音乐
				prepareAndPlay(musics[current]);
			}
		});
		super.onCreate();
	}
	//内部类使用
	public class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			int control=arg1.getIntExtra("control", -1);
			switch(control){
			//播放或者暂停
			case 1://1-2
				//原来处于没有播放状态
				if(status==0x11){
					//准备并播放音乐
					prepareAndPlay(musics[current]);
					//current=0x12;//----这里纠结了很久
					status=0x12;
				}
				//原来处于播放状态
				else if(status==0x12){
					//暂停
					mPlayer.pause();
					//改变为暂停状态
					status=0x13;
				}
				else if(status==0x13){
					//播放
					mPlayer.start();
					//改变状态
					status=0x12;
				}
				//prepareAndPlay(musics[current]);
				break;
			//停止声音
			case 2:
				//prepareAndPlay(musics[current]);//仅仅用于测试使用
				//如果以前正在播放或暂停
				if(status==0x12||status==0x13){
					//停止播放
					mPlayer.stop();
					status=0x11;
				}
			}
			//广播通知Activity更改图标、文本框
			Intent sendIntent=new Intent(mp3test.UPDATE_ACTION);
			sendIntent.putExtra("update", status);
			sendIntent.putExtra("current", current);
			//发送广播，将被Activity里面的BroadcastReceiver接收
			sendBroadcast(sendIntent);
		}
		
	}
	//音乐准备与播放
	private void prepareAndPlay(String music){
		try {
			//打开指定的音乐文件
			AssetFileDescriptor afd=am.openFd(music);
			mPlayer.reset();
			//使用MediaPlayer加载指定的音频文件
			mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			//准备声音
			mPlayer.prepare();
			//播放
			mPlayer.start();
			//只供测试使用
			//Toast.makeText(getApplicationContext(), "iii", Toast.LENGTH_SHORT).show();
		}
		catch(IOException e){
			e.printStackTrace();
			//备注：Unreachable catch block for IOException. 
			//This exception is never thrown from the try statement body
			//不可到达的 catch 块异常。try 中的语句，永远不会引发此异常
		}
	}

}

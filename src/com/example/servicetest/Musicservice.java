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
	//�ڲ���Ķ�������
	MyReceiver serviceReceiver;
	//�ļ���Դ����
	AssetManager am;
	//��������
	String[] musics=new String[]{
			"wish.mp3","promise.mp3","beautiful.mp3"
	};
	//��Ƶ���Ŷ�������
	MediaPlayer mPlayer;
	//��ǰ��״̬��0x11����û�в��ţ�0x12�������ڲ��ţ�0x13������ͣ
	int status=0x11;
	//��¼��ǰ���ڲ��ŵ�����
	int current=0;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	//��д��������
	@Override
	public void onCreate(){
		//��ȡ��Դ
		am=getAssets();
		//����BroadcastReceiver
		serviceReceiver=new MyReceiver();
		
		//����IntentFilter
		IntentFilter filter=new IntentFilter();
		//����Action����
		filter.addAction(mp3test.CTL_ACTION);
		//CTL_ACTIONǰ����������ã���Ϊ�ñ���Ϊ��̬������ֱ��ʹ��  ����.��̬����/����
		
		//ע���BroadcastReceiver
		registerReceiver(serviceReceiver, filter);
		
		//����һ��MediaPlayer
		mPlayer=new MediaPlayer();
		//Ϊ��MediaPlayer������ɰ󶨼�����
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			//���ֲ������ˣ�Ҫ���㲥֪ͨǰ��activity����״̬
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				current++;//��������Բ�����һ��
				if(current>=3){
					current=0;
				}
				//���㲥��֪ͨactivity�����ı���
				//Intent sendIntent=new Intent(mp3test.UPDATE_ACTION);
				Intent sendIntent=new Intent();
				sendIntent.setAction(mp3test.UPDATE_ACTION);
				//���͹㲥������Activity�����BroadcastReceiver���յ�
				sendIntent.putExtra("current", current);
				sendBroadcast(sendIntent);
				//׼����������
				prepareAndPlay(musics[current]);
			}
		});
		super.onCreate();
	}
	//�ڲ���ʹ��
	public class MyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			int control=arg1.getIntExtra("control", -1);
			switch(control){
			//���Ż�����ͣ
			case 1://1-2
				//ԭ������û�в���״̬
				if(status==0x11){
					//׼������������
					prepareAndPlay(musics[current]);
					//current=0x12;//----��������˺ܾ�
					status=0x12;
				}
				//ԭ�����ڲ���״̬
				else if(status==0x12){
					//��ͣ
					mPlayer.pause();
					//�ı�Ϊ��ͣ״̬
					status=0x13;
				}
				else if(status==0x13){
					//����
					mPlayer.start();
					//�ı�״̬
					status=0x12;
				}
				//prepareAndPlay(musics[current]);
				break;
			//ֹͣ����
			case 2:
				//prepareAndPlay(musics[current]);//�������ڲ���ʹ��
				//�����ǰ���ڲ��Ż���ͣ
				if(status==0x12||status==0x13){
					//ֹͣ����
					mPlayer.stop();
					status=0x11;
				}
			}
			//�㲥֪ͨActivity����ͼ�ꡢ�ı���
			Intent sendIntent=new Intent(mp3test.UPDATE_ACTION);
			sendIntent.putExtra("update", status);
			sendIntent.putExtra("current", current);
			//���͹㲥������Activity�����BroadcastReceiver����
			sendBroadcast(sendIntent);
		}
		
	}
	//����׼���벥��
	private void prepareAndPlay(String music){
		try {
			//��ָ���������ļ�
			AssetFileDescriptor afd=am.openFd(music);
			mPlayer.reset();
			//ʹ��MediaPlayer����ָ������Ƶ�ļ�
			mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			//׼������
			mPlayer.prepare();
			//����
			mPlayer.start();
			//ֻ������ʹ��
			//Toast.makeText(getApplicationContext(), "iii", Toast.LENGTH_SHORT).show();
		}
		catch(IOException e){
			e.printStackTrace();
			//��ע��Unreachable catch block for IOException. 
			//This exception is never thrown from the try statement body
			//���ɵ���� catch ���쳣��try �е���䣬��Զ�����������쳣
		}
	}

}

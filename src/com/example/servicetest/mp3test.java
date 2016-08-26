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
	//���ֿ��ƿ���
	ImageButton im_play,im_stop;//ͼƬ��ť
	//��ȡ��������ʾ�ĸ������⣬�Լ������ı���
	TextView title,author;
	//�����������ơ�����
	String[] tiltleStrs=new String[]{"��Ը","Լ��","����������"};
	String[] authorStrs=new String[]{"δ֪������","�ܻ�","���"};
	//���ֲ���״̬���趨
	int status=0x11;//0x11---û�����ֲ��ţ�0x12---���ڲ��ţ�0x13---������ͣ
	//������(������Activity�Ĺ㲥���ն���)
	ActivityReceiver activityReceiver;
	//����action(����Ϊ��̬��������Service�������)
	public  static final  String CTL_ACTION="com.example.servicetest.CTL_ACTION";
	public static final String UPDATE_ACTION="com.example.servicetest.UPDATE_ACTION";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mp3_main);
		//�ؼ���ʼ��
		title=(TextView) findViewById(R.id.title);
		author=(TextView) findViewById(R.id.author);
		//�ҵ����ؿؼ�
		im_play=(ImageButton) findViewById(R.id.im_play);
		im_stop=(ImageButton) findViewById(R.id.im_stop);
		//Ϊ�ؼ����ü�����
		im_play.setOnClickListener(this);
		im_stop.setOnClickListener(this);
		//ʵ�����ڲ���
		activityReceiver=new ActivityReceiver();
		//����һ��IntentFilter(ע������Ҳ����ʹ����xml����������ʽ)
		IntentFilter filter=new IntentFilter();
		//ָ��BroadcastReceiver������Action
		filter.addAction("com.example.servicetest.UPDATE_ACTION");
		//ע��BroadcastReceiver
		registerReceiver(activityReceiver, filter);//ע����Ǳ�Activity�����Receiver�ӵĹ㲥
						//�߱�����actionΪUPDATE_ACTION�Ĺ㲥
		//����Musicservice
		Intent mp3_service=new Intent(getApplicationContext(), Musicservice.class);
		//mp3_service.setClass(getApplicationContext(), Musicservice.class);
		//������̨Service
		startService(mp3_service);

	}
	//�Զ���һ��BroadcastReceiver ,���������service�������Ĺ㲥
	//��дonReceiver����
	public class ActivityReceiver extends BroadcastReceiver{
		//��д���շ���
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			//��ȡIntent�е�update��Ϣ��update������״̬
			//int update=mp3_service.getIntExtra("",-1);//��������Ϊû����⴫�ݽ���Ĳ�����arg1
			int update=arg1.getIntExtra("update", -1);
			//��ȡIntent�е�current��Ϣ��current����ǰ���ڲ��ŵĸ���
			int current=arg1.getIntExtra("current", -1);
			//��ȡIntent�����ֵ��ִ��
			//��ȡcurrent��ֵ���ı��ı�����ʾ����
			if(current>=0)
			{
				//˵��״̬�����˸ı䣬����Ҫ�Ļ�activity�����TextView�ؼ�
				title.setText(tiltleStrs[current]);
				author.setText(authorStrs[current]);
			}
			switch(update)
			{
				//����״̬˵��
			case 0x11:
				//ϵͳû�����ֲ���
				im_play.setImageResource(R.drawable.play);//im_play���Ű�ť����Ϊplay---û�и���
				status=0x11;//��ǰ����ı�������ǰ״̬
				break;
			case 0x12:
				//ϵͳ���ڲ���
				im_play.setImageResource(R.drawable.ic_launcher);//
				//im_play.setImageResource(R.drawable.play);//
				status=0x12;
				break;
			case 0x13:
				im_play.setImageResource(R.drawable.play);
				//ϵͳ��ͣ
				status=0x13;
				break;
			}
		}
		
	}
	//��д������ť�����������ť�ͻᷢ�͹㲥��actionΪ CTL_ACTION
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//�������¼���Ҫ����������service�ģ�ͨ�����㲥����ʽ��
		//Intent  mp_intent=new Intent("com.example.servicetest.CTL_ACTION");
		Intent  mp_intent=new Intent("com.example.servicetest.CTL_ACTION");
		//mp_intent.setAction(CTL_ACTION);
		switch(arg0.getId())
		{
		//����/��ͣ��ť
		case R.id.im_play:
			Toast.makeText(getApplicationContext(), "im_play is OK", Toast.LENGTH_SHORT).show();
			mp_intent.putExtra("control", 1);//��Ҫserviceֹͣ----����
			break;
		//ֹͣ��ť
		case R.id.im_stop:
			Toast.makeText(getApplicationContext(), "im_stop is OK", Toast.LENGTH_SHORT).show();
			mp_intent.putExtra("control", 2);
			break;		
		}
		//֮ǰ����mp_intent,���ڷ���
		//���͹㲥������Service����е�BroadcastReceiver���յ�
		sendBroadcast(mp_intent);
	}

}

package com.example.prefer;

//import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class massage extends Activity {
	
	private Button massagebutton1,massagebutton2,massagebutton3,massagebutton4,massagebutton5;
	private TextView massageText1;
	private Button mclear,msgTest;
	
	////��ע2016-8-11�������߳�������looper����
    private Handler handler=new Handler(){
    	@Override
		public void handleMessage(Message msg){//handleMessage ������Ϣ�ķ���
    		if(msg.what==3||msg.what==5){
    			massageText1.setText("what="+msg.what+"������һ������Ϣ");
    		}
    		else{
    			massageText1.setText("what="+msg.what+","+msg.obj.toString());
    		}
    	}
    };
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Ӧ���Ǿ����Ƿ��б���ѡ��
        setContentView(R.layout.massage);
        //��ʼ���ؼ�
        id_init();
        


        //1�Ű�ť����
        massagebutton1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		new Thread(new Runnable(){
        			@Override
					public void run(){
        			Message message=Message.obtain();
        			message.what=1;
        			message.obj="ʹ��Message.obtain+Handler.sendMessage������Ϣ";
        			handler.sendMessage(message);
        			}
        		}).start();
        	}
        });
        //2�Ű�ť����
        massagebutton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText("qu",,Toast.LENGTH_LONG).show();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Message message=Message.obtain(handler);
						message.what=2;
						message.obj="����ʹ��Message.sendToTarget������Ϣ";
						message.sendToTarget();
					}
				}).start();
			}
		});
        //3�Ű�ť����
        massagebutton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(3);
					}
				}).start();
				
			}
		});
        //�ĺŰ�ť
        massagebutton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
                        Message message = Message.obtain();  
                        message.what = 4;  
                        message.obj = "ʹ��Message.Obtain+Hander.sendMessage()�����ӳ���Ϣ";  
                        handler.sendMessageDelayed(message, 3000);
					}
				}).start();
				
			}
		});
        //��Ű�ť����
        massagebutton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						 handler.sendEmptyMessageAtTime(5, 3000);  
					}
				}).start();
				
			}
		});
        //�����ť����
        mclear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				massageText1.setText("");
			}
		});
        //��ť���治���߳��Ƿ�Ҳ��������������Ϣ������
        msgTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message msg=Message.obtain();//��ȡ��Ϣ
				msg.what=0x222;
				msg.obj="�Ҿ��Ƿ���Ϣ���Ե�";
				handler.sendMessage(msg);
			}
		});
	}
    //��ʼ���ؼ�
    private void id_init(){
    	massagebutton1=(Button) findViewById(R.id.massagebutton1);
    	massagebutton2=(Button) findViewById(R.id.massagebutton2);
    	massagebutton3=(Button) findViewById(R.id.massagebutton3);
    	massagebutton4=(Button) findViewById(R.id.massagebutton4);
    	massagebutton5=(Button) findViewById(R.id.massagebutton5);
    	massageText1=(TextView) findViewById(R.id.massageText1);
    	msgTest=(Button) findViewById(R.id.msgTest);
    	mclear=(Button) findViewById(R.id.mclear);
    }

}

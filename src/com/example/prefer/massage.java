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
	
	////备注2016-8-11，在主线程里面有looper对象
    private Handler handler=new Handler(){
    	@Override
		public void handleMessage(Message msg){//handleMessage 处理消息的方法
    		if(msg.what==3||msg.what==5){
    			massageText1.setText("what="+msg.what+"，这是一个空消息");
    		}
    		else{
    			massageText1.setText("what="+msg.what+","+msg.obj.toString());
    		}
    	}
    };
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//应该是决定是否有标题选项
        setContentView(R.layout.massage);
        //初始化控件
        id_init();
        


        //1号按钮处理
        massagebutton1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		new Thread(new Runnable(){
        			@Override
					public void run(){
        			Message message=Message.obtain();
        			message.what=1;
        			message.obj="使用Message.obtain+Handler.sendMessage发送消息";
        			handler.sendMessage(message);
        			}
        		}).start();
        	}
        });
        //2号按钮处理
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
						message.obj="这是使用Message.sendToTarget发送消息";
						message.sendToTarget();
					}
				}).start();
			}
		});
        //3号按钮设置
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
        //四号按钮
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
                        message.obj = "使用Message.Obtain+Hander.sendMessage()发送延迟消息";  
                        handler.sendMessageDelayed(message, 3000);
					}
				}).start();
				
			}
		});
        //五号按钮设置
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
        //清除按钮设置
        mclear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				massageText1.setText("");
			}
		});
        //按钮里面不开线程是否也可以正常发送消息？！！
        msgTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message msg=Message.obtain();//获取消息
				msg.what=0x222;
				msg.obj="我就是发消息测试的";
				handler.sendMessage(msg);
			}
		});
	}
    //初始化控件
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

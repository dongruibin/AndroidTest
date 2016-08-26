package com.example.prefer;


import com.baidu.tts.ttstest;
import com.example.enter.entersystem;//导入enter里面的类
import com.example.fragment.FragmentActivity;
import com.example.fragment.preferencetest;
import com.example.net.netmain;
import com.example.sd.sdtest;
import com.example.servicetest.servicestest;
import com.example.ui.uitest;
import com.groupware.asynctest;
import com.hardware.use.hardware;
import com.json.jsonTest;
import com.multimedia.multimediatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
//继承OnClickListener---因为后面button setOnClickListener需要这样
	EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//应该是决定是否有标题选项
        setContentView(R.layout.activity_main);
        //控件初始化
        Button button1=(Button) findViewById(R.id.button1);//收录的id为r.id.控件
        Button button2=(Button) findViewById(R.id.button2);
        Button button3=(Button) findViewById(R.id.button3);
        Button button4=(Button) findViewById(R.id.button4);
        Button button5=(Button) findViewById(R.id.button5);
        Button button6=(Button) findViewById(R.id.button6);
        Button button7=(Button) findViewById(R.id.button7);
        Button button8=(Button) findViewById(R.id.button8);
        Button button9=(Button) findViewById(R.id.button9);
        Button button10=(Button) findViewById(R.id.button10);
        Button button11=(Button) findViewById(R.id.button11);
        Button button12=(Button) findViewById(R.id.button12);
        Button JsonTest=(Button) findViewById(R.id.JsonTest);
        //添加作为测试
         edit=(EditText) findViewById(R.id.ed1);
        //控件设置
        button1.setOnClickListener(this);
        button1.setTag(1);
        button2.setOnClickListener(this);
        button2.setTag(2);
        button3.setOnClickListener(this);
        button3.setTag(3);
        button4.setOnClickListener(this);
        button4.setTag(4);
        button5.setOnClickListener(this);
        button5.setTag(5);
        button6.setOnClickListener(this);
        button6.setTag(6);
        button7.setOnClickListener(this);
        button7.setTag(7);
        button8.setOnClickListener(this);
        button8.setTag(8);
        button9.setOnClickListener(this);
        button9.setTag(9);
        button10.setOnClickListener(this);
        button10.setTag(10);
        button11.setOnClickListener(this);
        button11.setTag(11);
        button12.setOnClickListener(this);
        button12.setTag(12);
        JsonTest.setTag(13);
        JsonTest.setOnClickListener(this);
        
        
    }
    
//switch下面的break都写好，以免再发生错误
    public void onClick(View v) {
    	int tag=(Integer) v.getTag();
		switch(tag)
		{
		case 1:
			//获取当前的Intent Action
			String show=getIntent().getAction();
			edit.setText(show);
			
//			Intent massageintent=new Intent(MainActivity.this,massage.class);
//			startActivity(massageintent);//启动新的activity
			
			//返回桌面
			Intent massageinitent=new Intent(MainActivity.this,massage.class);
//			massageinitent.setAction(Intent.ACTION_MAIN);
//			massageinitent.addCategory(Intent.CATEGORY_HOME);
			startActivity(massageinitent);
			
			//Toast.makeText(MainActivity.this, "按键成功1", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Intent enterintent=new Intent();
			//enterintent.setClassName("com.example.enter","com.example.enter.entersystem");
			//SetClassName主要是因为直接setClass的话里面的entersystem.class会不可见
			enterintent.setClass(MainActivity.this, entersystem.class);
			startActivity(enterintent);
			//Toast.makeText(MainActivity.this, "按键成功2", Toast.LENGTH_SHORT).show();
			break;		
		case 3:
			Intent hdintent=new Intent();
			hdintent.setClassName("com.hardware.use","com.hardware.use.hardware");
			hdintent.setClass(MainActivity.this,hardware.class);
			startActivity(hdintent);
			
//			Intent intent=new Intent(MainActivity.this,test.class);
//			startActivity(intent);
			Toast.makeText(MainActivity.this, "按键成功3", Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Intent sdintent=new Intent();
			sdintent.setClassName("com.example.sd","com.example.sd.sdtest");
			sdintent.setClass(MainActivity.this,sdtest.class);
			startActivity(sdintent);
			break;
		case 5:
			Intent serviceintent=new Intent();
			//sdintent.setClassName("com.example.sd","com.example.sd.sdtest");
			serviceintent.setClass(MainActivity.this,servicestest.class);
			startActivity(serviceintent);
			break;
		case 6:
			Intent fragmentintent=new Intent();
			fragmentintent.setClass(MainActivity.this,FragmentActivity.class);
			startActivity(fragmentintent);
			break;
		case 7:
			Intent fragmenttestintent=new Intent();
			fragmenttestintent.setClass(MainActivity.this,preferencetest.class);
			startActivity(fragmenttestintent);
			break;
		case 8:
			Intent netintent=new Intent();
			netintent.setClass(MainActivity.this,netmain.class);
			startActivity(netintent);
			break;
		case 9:
			Intent asyncintent=new Intent();
			asyncintent.setClass(MainActivity.this,asynctest.class);
			startActivity(asyncintent);
			break;
		case 10:
			Intent uiintent=new Intent();
			uiintent.setClass(MainActivity.this,uitest.class);
			startActivity(uiintent);
			break;
		case 11:
			Intent ttsintent=new Intent();
			ttsintent.setClass(MainActivity.this,ttstest.class);
			startActivity(ttsintent);
			break;
		case 12:
			Intent mediaintent=new Intent();
			mediaintent.setClass(MainActivity.this,multimediatest.class);
			startActivity(mediaintent);
			break;
		case 13:
			Intent JsonIntent=new Intent();
			JsonIntent.setClass(getApplicationContext(), jsonTest.class);
			startActivity(JsonIntent);
			break;
			default : break;
		}
	}

}

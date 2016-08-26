package com.example.enter;


import com.example.prefer.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Register extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);//使用register布局文件
		//获取Inetent
		Intent Reintent=getIntent();
		Bundle bundle=Reintent.getExtras();//获取数据包
		
		TextView Textview1=(TextView) findViewById(R.id.Textview1);
		Textview1.setText("用户名："+bundle.getString("user"));
	}

}

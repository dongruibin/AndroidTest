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
		setContentView(R.layout.register);//ʹ��register�����ļ�
		//��ȡInetent
		Intent Reintent=getIntent();
		Bundle bundle=Reintent.getExtras();//��ȡ���ݰ�
		
		TextView Textview1=(TextView) findViewById(R.id.Textview1);
		Textview1.setText("�û�����"+bundle.getString("user"));
	}

}

package com.example.enter;



import com.example.prefer.R;
import com.example.prefer.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;//��ť�¼�����
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class entersystem extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Ӧ���Ǿ����Ƿ��б���ѡ��
		setContentView(R.layout.enter_main);//
		
		Button submit=(Button) findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//��ȡ��¼ҳ�����Ϣ
				String user=((EditText)findViewById(R.id.user)).getText().toString();
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();//��ȡ����
				String repwd=((EditText)findViewById(R.id.repwd)).getText().toString();
				String email=((EditText)findViewById(R.id.email)).getText().toString();
				
				if(!"".equals(user)&&!"".equals(pwd)&&!"".equals(repwd)&&!"".equals(email)){
					if(!pwd.equals(repwd)){//���������Ƿ���ͬ	
					Toast.makeText(entersystem.this, "�����������벻ͬ��", Toast.LENGTH_SHORT).show();
					((EditText)findViewById(R.id.user)).setText("");
					((EditText)findViewById(R.id.pwd)).setText("");//��������
					((EditText)findViewById(R.id.repwd)).setText("");
					((EditText)findViewById(R.id.email)).setText("");
					}else{
					//Ҫ���������Ϣ���ݸ���һ��activity
//					Intent intent=new Intent(entersystem.this,Register.class);
//					Bundle bundle=new Bundle();//����һ��bundle����
//					bundle.putCharSequence("user", user);//�����ݴ���bundle
//					bundle.putCharSequence("pwd", pwd);
//					bundle.putCharSequence("email", email);
//					intent.putExtras(bundle);
//					startActivity(intent);//�����µ�activity
					Toast.makeText(entersystem.this,"û��ִ�гɹ�", Toast.LENGTH_SHORT).show();
					}
				}else{//��ϢΪ�գ��ڴ˴���ʾ
			   Toast.makeText(entersystem.this, "�����Ϣ��������!", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
	}

}

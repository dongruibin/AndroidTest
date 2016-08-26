package com.example.enter;



import com.example.prefer.R;
import com.example.prefer.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;//按钮事件监听
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class entersystem extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//应该是决定是否有标题选项
		setContentView(R.layout.enter_main);//
		
		Button submit=(Button) findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//获取登录页面的信息
				String user=((EditText)findViewById(R.id.user)).getText().toString();
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();//获取密码
				String repwd=((EditText)findViewById(R.id.repwd)).getText().toString();
				String email=((EditText)findViewById(R.id.email)).getText().toString();
				
				if(!"".equals(user)&&!"".equals(pwd)&&!"".equals(repwd)&&!"".equals(email)){
					if(!pwd.equals(repwd)){//两次密码是否相同	
					Toast.makeText(entersystem.this, "两次密码输入不同！", Toast.LENGTH_SHORT).show();
					((EditText)findViewById(R.id.user)).setText("");
					((EditText)findViewById(R.id.pwd)).setText("");//清空输入框
					((EditText)findViewById(R.id.repwd)).setText("");
					((EditText)findViewById(R.id.email)).setText("");
					}else{
					//要将下面的信息传递给另一个activity
//					Intent intent=new Intent(entersystem.this,Register.class);
//					Bundle bundle=new Bundle();//创建一个bundle对象
//					bundle.putCharSequence("user", user);//将数据传到bundle
//					bundle.putCharSequence("pwd", pwd);
//					bundle.putCharSequence("email", email);
//					intent.putExtras(bundle);
//					startActivity(intent);//启动新的activity
					Toast.makeText(entersystem.this,"没有执行成功", Toast.LENGTH_SHORT).show();
					}
				}else{//信息为空，在此处提示
			   Toast.makeText(entersystem.this, "请把信息输入完整!", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
	}

}

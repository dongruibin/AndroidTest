package com.hardware.use;

import com.example.prefer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class hardware extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Ӧ���Ǿ����Ƿ��б���ѡ��
		setContentView(R.layout.hardware_main);
	}
}

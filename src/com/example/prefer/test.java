package com.example.prefer;

//import com.example.activitytest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class test extends Activity {
	@Override
	 protected  void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Ӧ���Ǿ����Ƿ��б���ѡ��
		setContentView(R.layout.test);//����main�ļ�����
	}
}

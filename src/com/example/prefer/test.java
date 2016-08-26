package com.example.prefer;

//import com.example.activitytest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class test extends Activity {
	@Override
	 protected  void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//应该是决定是否有标题选项
		setContentView(R.layout.test);//按照main文件布局
	}
}

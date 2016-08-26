package com.example.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class uitest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//应该是决定是否有标题选项
	}

}

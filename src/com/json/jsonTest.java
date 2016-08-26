package com.json;

import com.example.prefer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class jsonTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Toast.makeText(this, "ceshiok", Toast.LENGTH_LONG).show();
		setContentView(R.layout.jsontest);
	}

}

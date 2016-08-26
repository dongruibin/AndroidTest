package com.example.fragment;

import com.example.prefer.R;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class preferencetest extends Activity {
	
	@Override
	 protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragement()).commit();
	//使用Fragment时，可以通过用户交互来执行一些动作，比如增加、移除、替换等。
	//	　　所有这些改变构成一个集合，这个集合被叫做一个transaction。
    //可以调用FragmentTransaction中的方法来处理这个transaction，
	//并且可以将transaction存进由activity管理的back stack中，这样用户就可以进行fragment变化的回退操作。
	//将add(), remove(), replace()方法，把所有需要的变化加进去，然后调用commit()方法，将这些变化应用。
	}
	
	//建立一个内部类，继承于PreferenceFragment类
	public class PrefsFragement extends PreferenceFragment{
		//重写onCreate方法
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.layout.prefertest);
		}		
	}

}

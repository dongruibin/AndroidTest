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
	//ʹ��Fragmentʱ������ͨ���û�������ִ��һЩ�������������ӡ��Ƴ����滻�ȡ�
	//	����������Щ�ı乹��һ�����ϣ�������ϱ�����һ��transaction��
    //���Ե���FragmentTransaction�еķ������������transaction��
	//���ҿ��Խ�transaction�����activity�����back stack�У������û��Ϳ��Խ���fragment�仯�Ļ��˲�����
	//��add(), remove(), replace()��������������Ҫ�ı仯�ӽ�ȥ��Ȼ�����commit()����������Щ�仯Ӧ�á�
	}
	
	//����һ���ڲ��࣬�̳���PreferenceFragment��
	public class PrefsFragement extends PreferenceFragment{
		//��дonCreate����
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.layout.prefertest);
		}		
	}

}

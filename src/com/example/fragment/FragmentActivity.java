package com.example.fragment;

import java.util.List;

import com.example.prefer.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Button;
import android.widget.Toast;
//����ѡ������
public class FragmentActivity extends PreferenceActivity {
	//��Android3.0��ʼ������ʹ��preferenceAcitivity����ѡ�����ã����齫preferenceActivity��preferenceFragment
	//���ʹ�ã�����preferenceֻ�������ѡ�������б����ļ���preferenceFragment�Ÿ���ѡ�����õĲ����ļ�
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//�÷������ڸ���������һ�����ⰴť
		if(hasHeaders()){//preferenceActivity�ܹ����ݳߴ��С�����ò�ͬUI
			//����header�������������һ��button����������ƽ�廹��phone��������true
			//���豸��ƽ�壬�����preference header���ұ���PreferenceScreen����
			Button button=new Button(this);
			button.setText("���ò���");
			//���ð�ť��ӵ��ý�����
			setListFooter(button);
		}
	}
	//��д�÷������������ҳ�沼���ļ�
	// // ��PreferenceActivity�Ļص�����onBuildHeaders()�м���header
   @Override
   public void onBuildHeaders(List<Header> target){
	   //����ѡ�������б�Ĳ����ļ�
	   loadHeadersFromResource(R.layout.preference_headers,target);
	   //�Լ���Ӳ��Դ���
	   Toast.makeText(getApplicationContext(), "�����Ƿ�ص���", Toast.LENGTH_SHORT).show();
	   //���������ͻ���ã���������
   }
   public static class Prefs1Fragment extends PreferenceFragment{
	   @Override
	   public void onCreate(Bundle savedInstanceState)
	   {
		   super.onCreate(savedInstanceState);
		   addPreferencesFromResource(R.layout.preferences);
		   //���perferences.xml������˽��沼���ļ����������һ���������ý��棬�ò������ý����а�������
		   //���������飩
		   //��Σ���Fragment���������ͨ�������ڲ������ʽ�̳У��̳�PreferenceFragment
		   //������onCreate()����ص�addPrferenceFromResource()����ָ���Ľ��沼���ļ�
	   }
   }
   public static class Prefs2Fragment extends PreferenceFragment{
	   @Override
	   public void onCreate(Bundle savedInstanceState)
	   {
		   super.onCreate(savedInstanceState);
		   addPreferencesFromResource(R.layout.display_prefs);
		   //��ȡ�����Fragment�Ĳ���
		   String website=getArguments().getString("website");
		   Toast.makeText(getActivity(), "��վ�������ǣ�"+website, Toast.LENGTH_SHORT).show();
	   }
   }
   //�����Activity��Ҫ��д��PreferenceActivity��public void onBuildHeaders(List<Header> target)
   //��д�÷���ָ������ǰ�涨���perference_headers.xml���沼���ļ�
}
//�����Activity���涨��������PreferenceFragment,������Ҫ�ֱ����preferences.xml display_prefs����ѡ�����õ�
//�����ļ�

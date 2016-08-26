package com.example.fragment;

import java.util.List;

import com.example.prefer.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Button;
import android.widget.Toast;
//用于选项设置
public class FragmentActivity extends PreferenceActivity {
	//从Android3.0开始，不在使用preferenceAcitivity加载选项设置，建议将preferenceActivity与preferenceFragment
	//结合使用，其中preference只负责加载选项设置列表布局文件，preferenceFragment才负责选项设置的布局文件
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//该方法用于给界面设置一个标题按钮
		if(hasHeaders()){//preferenceActivity能够根据尺寸大小，布置不同UI
			//如有header，则在最下面加一个button。本例无论平板还是phone，都返回true
			//如设备是平板，左边是preference header，右边是PreferenceScreen对象。
			Button button=new Button(this);
			button.setText("设置操作");
			//将该按钮添加到该界面上
			setListFooter(button);
		}
	}
	//重写该方法，负责加载页面布局文件
	// // 在PreferenceActivity的回调函数onBuildHeaders()中加入header
   @Override
   public void onBuildHeaders(List<Header> target){
	   //加载选项设置列表的布局文件
	   loadHeadersFromResource(R.layout.preference_headers,target);
	   //自己添加测试代码
	   Toast.makeText(getApplicationContext(), "测试是否回调了", Toast.LENGTH_SHORT).show();
	   //创建完界面就会调用！！！！！
   }
   public static class Prefs1Fragment extends PreferenceFragment{
	   @Override
	   public void onCreate(Bundle savedInstanceState)
	   {
		   super.onCreate(savedInstanceState);
		   addPreferencesFromResource(R.layout.preferences);
		   //这个perferences.xml定义好了界面布局文件（定义好了一个参数设置界面，该参数设置界面中包括两个
		   //参数设置组）
		   //其次，让Fragment（这个里面通过建立内部类的形式继承）继承PreferenceFragment
		   //在它的onCreate()里面回调addPrferenceFromResource()加载指定的界面布局文件
	   }
   }
   public static class Prefs2Fragment extends PreferenceFragment{
	   @Override
	   public void onCreate(Bundle savedInstanceState)
	   {
		   super.onCreate(savedInstanceState);
		   addPreferencesFromResource(R.layout.display_prefs);
		   //获取传入该Fragment的参数
		   String website=getArguments().getString("website");
		   Toast.makeText(getActivity(), "网站的域名是："+website, Toast.LENGTH_SHORT).show();
	   }
   }
   //上面的Activity主要重写了PreferenceActivity的public void onBuildHeaders(List<Header> target)
   //重写该方法指定加载前面定义的perference_headers.xml界面布局文件
}
//上面的Activity里面定义了两个PreferenceFragment,它们需要分别加载preferences.xml display_prefs两个选项设置的
//布局文件

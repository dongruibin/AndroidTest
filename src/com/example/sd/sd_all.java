package com.example.sd;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import com.example.prefer.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class sd_all extends Activity {
	private static final String FILENAME = "mymldn.txt" ;	// 设置文件名称
	private static final String DIR = "mldndata" ;	// 操作文件夹的名称
	//private static final Object Enviroment = null;
	TextView sd_msg=null;
	@Override
	protected void onCreate(Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//应该是决定是否有标题选项
		setContentView(R.layout.sd_all_main);
		//初始化TextView
		sd_msg=(TextView) findViewById(R.id.sd_msg);
		
		//使用Environment进行sd卡的确认
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断SD卡是否存在
			//sd卡存在
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + DIR + File.separator + FILENAME); // 定义要操作的文件
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs(); // 创建父文件夹路径
			}
			Scanner scan = null ;
			try {
				scan = new Scanner(new FileInputStream(file)) ;
				while(scan.hasNext()) {
					this.sd_msg.append(scan.next() + "\n") ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 一定要关闭流
				if (scan != null) {
					scan.close();
				}
			}
		}
		//sd不存在
		else {
			Toast.makeText(this, "保存失败，SD卡不存在！", Toast.LENGTH_LONG).show() ;
		}
		}
	

}

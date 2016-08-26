package com.example.sd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.example.prefer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

//说明数据存储可以使用activity里面的方法进行操作，但是数据非常大时就不适合使用了，这个时候要使用java IO
//这里把两种方法都说一下
//方法一：直接利用activity提供的文件操作方法进行，此类操作的所有文件路径只能是“\data\data\<package name>\files\文件名称”
//方法二：利用java IO流进行操作，此类操作的文件可以是任意路径（包括sdcard下），但是需要操作授权
public class sdtest extends Activity {
//	final String FILE_NAME="/sdtest.bin";
	private static final String FILENAME = "/mnt/sdcard/xiaodong/mymldn.txt" ;	// 设置文件名称
	//上述文件sdcard代表手机本身内存空间---，需要验证外部sd卡是否为sdcard0
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//应该是决定是否有标题选项
		setContentView(R.layout.sd_main);
		File file = new File(FILENAME) ; 	// 定义要操作的文件
		if(!file.getParentFile().exists()){//判断文件是否存在，如果不存在
			file.getParentFile().mkdirs();//创建父类文件夹路径
			//这里课外分清楚一下mkdir和mikdirs
		}
		PrintStream out=null;
		try {
			out = new PrintStream(new FileOutputStream(file));
			out.println("北京魔乐科技软件学院（MLDN，www.MLDNJAVA.cn），讲师：dong");
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {	// 一定要关闭流
			if(out != null) {
				out.close() ;
			}
		}
		////////
		Button bt_sd=(Button) findViewById(R.id.bt_sd);
		bt_sd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent  bt_sd_intent= new Intent();
				bt_sd_intent.setClass(sdtest.this, sd_all.class);
				startActivity(bt_sd_intent);
			}
		});
	}

}

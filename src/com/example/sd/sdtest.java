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

//˵�����ݴ洢����ʹ��activity����ķ������в������������ݷǳ���ʱ�Ͳ��ʺ�ʹ���ˣ����ʱ��Ҫʹ��java IO
//��������ַ�����˵һ��
//����һ��ֱ������activity�ṩ���ļ������������У���������������ļ�·��ֻ���ǡ�\data\data\<package name>\files\�ļ����ơ�
//������������java IO�����в���������������ļ�����������·��������sdcard�£���������Ҫ������Ȩ
public class sdtest extends Activity {
//	final String FILE_NAME="/sdtest.bin";
	private static final String FILENAME = "/mnt/sdcard/xiaodong/mymldn.txt" ;	// �����ļ�����
	//�����ļ�sdcard�����ֻ������ڴ�ռ�---����Ҫ��֤�ⲿsd���Ƿ�Ϊsdcard0
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Ӧ���Ǿ����Ƿ��б���ѡ��
		setContentView(R.layout.sd_main);
		File file = new File(FILENAME) ; 	// ����Ҫ�������ļ�
		if(!file.getParentFile().exists()){//�ж��ļ��Ƿ���ڣ����������
			file.getParentFile().mkdirs();//���������ļ���·��
			//�����������һ��mkdir��mikdirs
		}
		PrintStream out=null;
		try {
			out = new PrintStream(new FileOutputStream(file));
			out.println("����ħ�ֿƼ����ѧԺ��MLDN��www.MLDNJAVA.cn������ʦ��dong");
		} catch (Exception e) {
			e.printStackTrace() ;
		} finally {	// һ��Ҫ�ر���
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

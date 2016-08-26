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
	private static final String FILENAME = "mymldn.txt" ;	// �����ļ�����
	private static final String DIR = "mldndata" ;	// �����ļ��е�����
	//private static final Object Enviroment = null;
	TextView sd_msg=null;
	@Override
	protected void onCreate(Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//Ӧ���Ǿ����Ƿ��б���ѡ��
		setContentView(R.layout.sd_all_main);
		//��ʼ��TextView
		sd_msg=(TextView) findViewById(R.id.sd_msg);
		
		//ʹ��Environment����sd����ȷ��
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//�ж�SD���Ƿ����
			//sd������
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator + DIR + File.separator + FILENAME); // ����Ҫ�������ļ�
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs(); // �������ļ���·��
			}
			Scanner scan = null ;
			try {
				scan = new Scanner(new FileInputStream(file)) ;
				while(scan.hasNext()) {
					this.sd_msg.append(scan.next() + "\n") ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // һ��Ҫ�ر���
				if (scan != null) {
					scan.close();
				}
			}
		}
		//sd������
		else {
			Toast.makeText(this, "����ʧ�ܣ�SD�������ڣ�", Toast.LENGTH_LONG).show() ;
		}
		}
	

}

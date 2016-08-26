package com.groupware;

import com.example.prefer.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class asynctest extends Activity {
	//��AndroidManifest.xml�ļ�����˵��
	private ProgressBar bar;
	private TextView info;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyncmain);
		//��һ�¿ؼ�
		bar=(ProgressBar) findViewById(R.id.bar);
		info=(TextView) findViewById(R.id.info);
		//��ʼ�첽������
		ChildUpdate child=new ChildUpdate();
		child.execute(100);
	}
	//����һ���ڲ��࣬�̳��첽
	// ÿ�δ����̨���ȵ�������Integer������֮�����ֵInteger�����Ľ�����ص����ַ���
	//AsyncTask<Params, Progress, Result>
	private class  ChildUpdate extends AsyncTask<Integer, Integer, String>{
		@Override
		protected void onPostExecute(String result) {
			asynctest.this.info.setText(result) ;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {	// ÿ�θ���֮�������
			asynctest.this.info.setText("��ǰ�Ľ���ֵ�ǣ�" + String.valueOf(values[0])) ;
		}
		//�����������Ŀ�������д----��
		@Override
		protected String doInBackground(Integer... params) { // ÿ�εĽ��ȴ������Ը���UI���
			for (int x = 0; x < 100; x++) {
				asynctest.this.bar.setProgress(x); // ���ý���
				this.publishProgress(x) ;	// ���£����ø��²���
				try {// �ӳٵĲ������ⲿ����
					Thread.sleep(params[0]);
				} catch (InterruptedException e) {
				}
			}
			return "ִ�����";
		}
	}

}

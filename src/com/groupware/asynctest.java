package com.groupware;

import com.example.prefer.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class asynctest extends Activity {
	//到AndroidManifest.xml文件里面说明
	private ProgressBar bar;
	private TextView info;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyncmain);
		//找一下控件
		bar=(ProgressBar) findViewById(R.id.bar);
		info=(TextView) findViewById(R.id.info);
		//开始异步任务处理
		ChildUpdate child=new ChildUpdate();
		child.execute(100);
	}
	//创建一个内部类，继承异步
	// 每次处理后台进度的类型是Integer、更新之后的数值Integer，最后的结果返回的是字符串
	//AsyncTask<Params, Progress, Result>
	private class  ChildUpdate extends AsyncTask<Integer, Integer, String>{
		@Override
		protected void onPostExecute(String result) {
			asynctest.this.info.setText(result) ;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {	// 每次更新之后的内容
			asynctest.this.info.setText("当前的进度值是：" + String.valueOf(values[0])) ;
		}
		//下面这个方法目测必须重写----董
		@Override
		protected String doInBackground(Integer... params) { // 每次的进度处理，可以更新UI组件
			for (int x = 0; x < 100; x++) {
				asynctest.this.bar.setProgress(x); // 设置进度
				this.publishProgress(x) ;	// 更新，调用更新操作
				try {// 延迟的操作由外部决定
					Thread.sleep(params[0]);
				} catch (InterruptedException e) {
				}
			}
			return "执行完毕";
		}
	}

}

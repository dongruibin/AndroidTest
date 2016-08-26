package com.json;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

//* AsyncTask是抽象类.AsyncTask定义了三种泛型类型 Params，Progress和Result。
//* Params 启动任务执行的输入参数，比如HTTP请求的URL。
//* Progress 后台任务执行的百分比。
//* Result 后台执行任务最终返回的结果，比如String,Integer等。


//AsyncTask<>的参数类型由用户设定，这里设为三个String
//第一个String代表输入到任务的参数类型，也即是doInBackground()的参数类型
//第二个String代表处理过程中的参数类型，也就是doInBackground()执行过程中的产出参数类型，通过publishProgress()发消息
//传递给onProgressUpdate()一般用来更新界面
//第三个String代表任务结束的产出类型，也就是doInBackground()的返回值类型，和onPostExecute()的参数类型

public class QueryTask extends AsyncTask<String, Integer, String> {
	Context context;
	TextView txview;
	private static final String  OPEN_API="http://web.juhe.cn:8080/environment/air/pm";
	public QueryTask(Context context,TextView txview){
		super();
		this.context=context;
		this.txview=txview;
	}
	@Override
	protected String doInBackground(String... arg0) {//之前的Result 变成 String
		// TODO Auto-generated method stub
	
		return null;
	}

}

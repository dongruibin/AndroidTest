package com.json;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

//* AsyncTask�ǳ�����.AsyncTask���������ַ������� Params��Progress��Result��
//* Params ��������ִ�е��������������HTTP�����URL��
//* Progress ��̨����ִ�еİٷֱȡ�
//* Result ��ִ̨���������շ��صĽ��������String,Integer�ȡ�


//AsyncTask<>�Ĳ����������û��趨��������Ϊ����String
//��һ��String�������뵽����Ĳ������ͣ�Ҳ����doInBackground()�Ĳ�������
//�ڶ���String����������еĲ������ͣ�Ҳ����doInBackground()ִ�й����еĲ����������ͣ�ͨ��publishProgress()����Ϣ
//���ݸ�onProgressUpdate()һ���������½���
//������String������������Ĳ������ͣ�Ҳ����doInBackground()�ķ���ֵ���ͣ���onPostExecute()�Ĳ�������

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
	protected String doInBackground(String... arg0) {//֮ǰ��Result ��� String
		// TODO Auto-generated method stub
	
		return null;
	}

}

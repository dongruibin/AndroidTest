package com.multimedia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	//����̳�View���࣬��Ҫ������ļ�����˵��

//	public MyView(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas){//��д��ͼ����
		canvas.drawColor(Color.WHITE);//���ñ�����ɫ
		Paint paint=new Paint();//
		paint.setColor(Color.BLUE);
		canvas.drawCircle(30, 50, 25, paint);
	}
}

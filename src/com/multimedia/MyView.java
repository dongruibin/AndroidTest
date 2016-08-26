package com.multimedia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	//这个继承View的类，需要在组件文件里面说明

//	public MyView(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//	}
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas){//覆写绘图方法
		canvas.drawColor(Color.WHITE);//设置背景颜色
		Paint paint=new Paint();//
		paint.setColor(Color.BLUE);
		canvas.drawCircle(30, 50, 25, paint);
	}
}

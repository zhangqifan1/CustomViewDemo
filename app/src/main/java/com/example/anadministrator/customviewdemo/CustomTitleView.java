package com.example.anadministrator.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by 张祺钒
 * on2017/9/4.
 */

public class CustomTitleView extends View {

    private String titleText;
    private int color;
    private Paint paint;
    private int size;
    private Rect rect;

    public CustomTitleView(Context context) {
        this(context,null);
    }
    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //首先获得我们自定义的样式属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i <indexCount ; i++) {
            int index = typedArray.getIndex(i);
            switch(index){
                case R.styleable.CustomTitleView_titleText:
                    titleText = typedArray.getString(index);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    color = typedArray.getColor(index, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    //默认设置为16sp,TypedValue也可以把sp转换为px
                    size = typedArray.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        //回收
        typedArray.recycle();
        //获得绘制文本的宽和高
        paint = new Paint();
        paint.setTextSize(size);
        paint.setColor(color);
        //建一个矩形
        rect = new Rect();
        //得到文本界限
        paint.getTextBounds(titleText,0,titleText.length(),rect);

        //在构造方法里写点击事件
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                titleText = randomText();
                postInvalidate();
            }
        });
    }
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int width;
//        int height ;
//        if (widthMode == MeasureSpec.EXACTLY)
//        {
//            width = widthSize;
//        } else
//        {
//            paint.setTextSize(size);
//            paint.getTextBounds(titleText, 0, titleText.length(), rect);
//            float textWidth = rect.width();
//            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
//            width = desired;
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY)
//        {
//            height = heightSize;
//        } else
//        {
//            paint.setTextSize(size);
//            paint.getTextBounds(titleText, 0, titleText.length(), rect);
//            float textHeight = rect.height();
//            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
//            height = desired;
//        }
//        setMeasuredDimension(width, height);
//    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint);
        paint.setColor(color);
        canvas.drawText(titleText,getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2,paint);
    }

    //随机生成4位数
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }

}
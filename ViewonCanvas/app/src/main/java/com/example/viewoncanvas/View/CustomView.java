package com.example.viewoncanvas.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    Rect mrect;
    Float circleX,circleY, circleRadius,x,y;

    Paint rectPaint, circlePaint, strokePaint, paintLine;
    int width, height;

    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public static int dpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }
    public void init(@Nullable AttributeSet set){
        setWillNotDraw(false);
        mrect=new Rect();
        paintLine=new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Colors.lighterBlue);
        strokePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(7);
        strokePaint.setColor(Colors.white);
        paintLine.setColor(Color.parseColor("#ffffff"));
        paintLine.setStrokeWidth(10);
        height=dpToPx(getContext(),400);
        width = Resources.getSystem().getDisplayMetrics().widthPixels;
        circleRadius=(float)(width/5)-50;
        circleX=(float)((width/2)+width/5);
        circleY=(float)(height/5);
        rectPaint.setColor(Colors.lighterGreen);

    }
    public void swapColor(){
        Log.i("Before", "swapColor: Rect-"+rectPaint.getColor()+", circle-"+circlePaint.getColor());
//        rectPaint.setColor(Color.RED);
        this.rectPaint.setColor(rectPaint.getColor()==Colors.lighterGreen?Colors.lightGreen:Colors.lighterGreen);
//        circlePaint.setColor(Color.BLUE);
//        paintLine.setColor(Color.BLACK);
        this.circlePaint.setColor(circlePaint.getColor()==Colors.lighterBlue?Colors.lightBlue:Colors.lighterBlue);
        Log.i("After", "swapColor: Rect-"+rectPaint.getColor()+", circle-"+circlePaint.getColor());

//requestLayout();
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean value=super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x=event.getX();
                float y=event.getY();
                double dx=Math.pow(x-circleX,2);
                double dy=Math.pow(y-circleY,2);
                if(mrect.left<x&&mrect.right>x)
                    if(mrect.top<y&&mrect.bottom>y) {
                        circleRadius += 15f;
                        postInvalidate();
                    }
                if(mrect.top>y||mrect.bottom<y)
                if(dx+dy>Math.pow(circleRadius,2)){
                    circleRadius -= 15f;
                    postInvalidate();
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                float moveX=event.getX();
                float moveY=event.getY();

                double cirx=Math.pow(moveX-circleX,2);
                double ciry=Math.pow(moveY-circleY,2);
                if(cirx+ciry<Math.pow(circleRadius,2)){
                    circleX=moveX;
                    circleY=moveY;
//                    paintLine.setStrokeWidth(20);
//                    paintLine.setColor(Color.BLACK);
//                    rectPaint.setColor(Colors.lightGreen);
                    postInvalidate();
                    return true;
                }

                break;

            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        return value;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.i("dispatchDraw", "rectPaint: "+rectPaint.getColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("onDraw", "rectPaint: "+rectPaint.getColor()+", circle x-"+circleX+", circle y-"+circleY);

        canvas.drawColor(Colors.grey);
        mrect.left=width/10;
        mrect.top=width/10;
        mrect.bottom=height/5+mrect.top;
        mrect.right= (width/2)-10;

        Log.i("onDraw", "rectPaint: "+rectPaint.getColor()+", circle x-"+circleX+", circle y-"+circleY);
        canvas.drawLine(mrect.centerX(),mrect.centerY(),circleX,circleY,paintLine);
        canvas.drawRect(mrect,rectPaint);
        canvas.drawRect(mrect,strokePaint);
        canvas.drawCircle(circleX,circleY,circleRadius,circlePaint);
        canvas.drawCircle(circleX,circleY,circleRadius,strokePaint);
    }
}

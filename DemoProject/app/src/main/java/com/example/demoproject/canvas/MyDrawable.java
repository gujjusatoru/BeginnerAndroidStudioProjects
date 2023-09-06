package com.example.demoproject.canvas;
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

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.example.demoproject.R;

public class MyDrawable extends View {
    private int strokeColor, fillColor;
    private String text;
    float textX,textY, textSize;

    Paint fillPaint, strokePaint;

    public MyDrawable(Context context) {
        super(context);
        init(null);
    }

    public void updateValues(Context context, int strokeColor, int fillColor, String text) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.text = text;
        init(null);
    }

    public MyDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MyDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public static float dpToPx(Context context, float dp) {
        return  (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }
    public void init(@Nullable AttributeSet set){
        if(text==null||text.equals("")){
            return;
        }
        else {
            Rect rect=new Rect();
            textSize = dpToPx(getContext(), 32);
            strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(2);
            strokePaint.setColor(getContext().getColor(strokeColor));
            strokePaint.setStrokeJoin(Paint.Join.ROUND);
            strokePaint.setTextSize(textSize);
            strokePaint.getTextBounds(text,0,text.length(),rect);
            fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            fillPaint.setStyle(Paint.Style.FILL);
            fillPaint.setColor(getContext().getColor(fillColor));
            fillPaint.setTextSize(textSize);
            textX = (float) 0;
            textY = textSize;
            postInvalidate();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(text==null||text.equals("")){
            Log.i("onDrawText", "X: " + textX + ", Y: " + textY + ", text: "+text );
            return;
        }
        else {
            Log.i("onDrawText", "X: " + textX + ", Y: " + textY + ", strokePaint: " + strokePaint.getColor() + ", fillPaint: " + fillPaint.getColor() + ", textSize: " + fillPaint.getTextSize()+", text:"+text);
            canvas.drawText(text, textX, textY, fillPaint);
            canvas.drawText(text, textX, textY, strokePaint);
            Log.i("onDrawText", "X: " + textX + ", Y: " + textY + ", strokePaint: " + strokePaint.getColor() + ", fillPaint: " + fillPaint.getColor() + ", textSize: " + fillPaint.getTextSize()+", text:"+text);
        }


    }

}

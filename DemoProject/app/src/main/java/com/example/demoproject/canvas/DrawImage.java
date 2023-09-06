package com.example.demoproject.canvas;

import static android.provider.MediaStore.Images.Media.getBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.demoproject.data.CanvasData;
import com.example.demoproject.gesturelistener.MultiTouchListener;

import java.io.IOException;

public class DrawImage extends View {
    CanvasData savedData;
    private int strokeColor, fillColor;
    Bitmap image, frame, sticker;
    Bitmap savedBitmap;
    String text;
    float textSize;
    Paint fillPaint, strokePaint;
    Matrix matrix;

    public DrawImage(Context context) throws IOException {
        super(context);
        init(null);
    }

    public DrawImage(Context context, @Nullable AttributeSet attrs) throws IOException {
        super(context, attrs);
        init(attrs);
    }

    public DrawImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) throws IOException {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public DrawImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) throws IOException {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public static float dpToPx(Context context, float dp) {
        return (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }

    public void getData(CanvasData savedData) throws IOException {

        this.savedData = savedData;
        Log.i("get Data", "text:" + savedData.getText());
//        if (savedData.getScaleFactorY() < 1) {
//            imageY = savedData.getImageY() * (1 / savedData.getScaleFactorY());
//        } else if (savedData.getScaleFactorY() > 1) {
//            imageY = savedData.getImageY() - savedData.getImageY() / savedData.getScaleFactorY();
//        } else if (savedData.getScaleFactorY() == 1) {
//            imageY = 0;
//        }
//        if (savedData.getScaleFactorX() < 1) {
//            imageX = savedData.getImageX() * (1 / savedData.getScaleFactorX());
//        } else if (savedData.getScaleFactorX() > 1) {
//            imageX = savedData.getImageX() - savedData.getImageX() / savedData.getScaleFactorX();
//        } else if (savedData.getScaleFactorX() == 1) {
//            imageX = 0;
//        }
//        if(savedData.getScaleFactorY()<1){
//            imageY=Math.abs(savedData.getImageY())+Math.abs(savedData.getImageY()*savedData.getScaleFactorY());
//        }
//        else if(savedData.getScaleFactorY()>1){
//            imageY =savedData.getImageY()-(savedData.getImageY()/savedData.getScaleFactorY());
//        }
//        else{
//            imageY=savedData.getImageY();
//        }
//        if(savedData.getScaleFactorX()<1){
//            imageX=Math.abs(savedData.getImageX())+Math.abs(savedData.getImageX()*savedData.getScaleFactorX());
//        }
//        else if(savedData.getScaleFactorX()>1){
//            imageX =savedData.getImageX()-(savedData.getImageX()/savedData.getScaleFactorX());
//        }
//        else{
//            imageX=savedData.getImageX();
//        }
//        imageX=savedData.getImageX();
//        imageY=savedData.getImageY();
//        imageheight = (int) (savedData.getFrameHeight() * savedData.getScaleFactorY());
//        imagewidth = (int) (savedData.getFrameWidth() * savedData.getScaleFactorX());
        init(null);
    }

    public void init(@Nullable AttributeSet set) throws IOException {
        if (savedData == null) {
            Log.i("init", " no data ");
            return;
        } else {
            matrix=new Matrix();
            matrix.setRotate(savedData.getImageRotation(),savedData.getImagePivotX(),savedData.getImagePivotY());
            matrix.preScale(savedData.getScaleFactorX(),savedData.getScaleFactorY(),savedData.getImagePivotX(),savedData.getImagePivotY());
            matrix.postTranslate(savedData.getxTranslate(),savedData.getyTranslate());
            image = Bitmap.createScaledBitmap(getBitmap(getContext().getContentResolver(), savedData.getBitmapIMG()),savedData.getImageWidth(),savedData.getImageHeight(),false);
            Log.i("Image", "bitmap: " + image);
            if (savedData.getFrameID() != 0)
                frame = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getContext().getResources(), savedData.getFrameID()), savedData.getFrameWidth(), savedData.getFrameHeight(), false);
            Log.i("Frame", "bitmap: " + frame);
            if (savedData.getStickerID() != 0)
                sticker = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getContext().getResources(), savedData.getStickerID()), (int) dpToPx(getContext(), 100), (int) dpToPx(getContext(), 100), false);
            Log.i("Sticker", "bitmap: " + sticker);
            if (savedData.getText() != null) {
                text = savedData.getText();
                strokeColor = savedData.getTextStrokeID();
                fillColor = savedData.getTextFillID();
                textSize = dpToPx(getContext(), 32);
                strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                strokePaint.setStyle(Paint.Style.STROKE);
                strokePaint.setStrokeWidth(2);
                strokePaint.setColor(getContext().getColor(strokeColor));
                strokePaint.setStrokeJoin(Paint.Join.ROUND);
                strokePaint.setTextSize(textSize);
                fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                fillPaint.setStyle(Paint.Style.FILL);
                fillPaint.setColor(getContext().getColor(fillColor));
                fillPaint.setTextSize(textSize);
            }
            invalidate();
        }

    }


    @Override
    public void addOnLayoutChangeListener(OnLayoutChangeListener listener) {
        super.addOnLayoutChangeListener(listener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (savedData != null) {
//            canvas.save();
//            canvas.translate(-savedData.getxTranslate(), -savedData.getyTranslate());
            canvas.drawBitmap(image, matrix, null);
            Log.i("checkDraw", "XTranslate " + savedData.getxTranslate()  + " YTranslate " + savedData.getyTranslate() + " imagepivotx: " + savedData.getImagePivotX() + " scalingx: " + savedData.getScaleFactorX() + " imagepivoty: " + savedData.getImagePivotY() + " scalingy: " + savedData.getScaleFactorY()+" rotation " +savedData.getImageRotation());
//            canvas.restore();
            if (savedData.getFrameID() != 0)
                canvas.drawBitmap(frame, 0, 0, null);
            if (savedData.getStickerID() != 0)
                canvas.drawBitmap(sticker, savedData.getStickerX(), savedData.getStickerY(), null);
            if (savedData.getText() != null) {
                canvas.drawText(text, savedData.getTextX(), savedData.getTextY() + textSize, fillPaint);
                canvas.drawText(text, savedData.getTextX(), savedData.getTextY() + textSize, strokePaint);
            }
        }
    }

    public Bitmap save() {
        savedBitmap = Bitmap.createBitmap(savedData.getFrameWidth(), savedData.getFrameHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(savedBitmap);
        if (savedData != null) {
//            canvas.save();
//            canvas.translate(-savedData.getxTranslate(), -savedData.getyTranslate());
            canvas.drawBitmap(image, matrix, null);
//            canvas.restore();
            if (savedData.getFrameID() != 0)
                canvas.drawBitmap(frame, 0, 0, null);
            if (savedData.getStickerID() != 0)
                canvas.drawBitmap(sticker, savedData.getStickerX(), savedData.getStickerY(), null);
            if (savedData.getText() != null) {
                canvas.drawText(text, savedData.getTextX(), savedData.getTextY() + textSize, fillPaint);
                canvas.drawText(text, savedData.getTextX(), savedData.getTextY() + textSize, strokePaint);
            }
        }
        return savedBitmap;
    }
}

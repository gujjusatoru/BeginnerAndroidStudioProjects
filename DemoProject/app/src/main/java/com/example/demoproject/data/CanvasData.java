package com.example.demoproject.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class CanvasData implements Parcelable{
    private Uri bitmapIMG;
    private int frameHeight;
    private int frameWidth;
    private int imageHeight;
    private int imageWidth;

    private float imageRotation;
    private float scaleFactorX;
    private float scaleFactorY;
    private float imagePivotX;
    private float imagePivotY;
    private float imageX;
    private float imageY;



    private float xTranslate;
    private float yTranslate;
    private int frameID;
    private int stickerID;
    private float stickerX;
    private float stickerY;
    private String text;
    private float textX;
    private float textY;
    private int textFillID;
    private int textStrokeID;


    protected CanvasData(Parcel in) {

        bitmapIMG = in.readParcelable(Uri.class.getClassLoader());
        frameHeight = in.readInt();
        frameWidth = in.readInt();
        imageHeight=in.readInt();
        imageWidth=in.readInt();
        imageRotation=in.readFloat();
        scaleFactorX=in.readFloat();
        scaleFactorY=in.readFloat();
        imagePivotX = in.readFloat();
        imagePivotY = in.readFloat();
        imageX=in.readFloat();
        imageY=in.readFloat();
        xTranslate=in.readFloat();
        yTranslate=in.readFloat();
        frameID = in.readInt();
        stickerID = in.readInt();
        stickerX = in.readFloat();
        stickerY = in.readFloat();
        text = in.readString();
        textX = in.readFloat();
        textY = in.readFloat();
        textFillID = in.readInt();
        textStrokeID = in.readInt();
    }
    public CanvasData(){

    }

    public static final Creator<CanvasData> CREATOR = new Creator<CanvasData>() {
        @Override
        public CanvasData createFromParcel(Parcel in) {
            return new CanvasData(in);
        }

        @Override
        public CanvasData[] newArray(int size) {
            return new CanvasData[size];
        }
    };

    public Uri getBitmapIMG() {
        return bitmapIMG;
    }

    public void setBitmapIMG(Uri bitmapIMG) {
        this.bitmapIMG = bitmapIMG;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }
    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }
    public void setImageRotation(float imageRotation) {
        this.imageRotation = imageRotation;
    }

    public float getImageRotation() {
        return imageRotation;
    }

    public float getScaleFactorX() {
        return scaleFactorX;
    }

    public void setScaleFactorX(float scaleFactorX) {
        this.scaleFactorX = scaleFactorX;
    }

    public float getScaleFactorY() {
        return scaleFactorY;
    }

    public void setScaleFactorY(float scaleFactorY) {
        this.scaleFactorY = scaleFactorY;
    }

    public float getImagePivotX() {
        return imagePivotX;
    }

    public void setImagePivotX(float imageX) {
        this.imagePivotX = imageX;
    }

    public float getImagePivotY() {
        return imagePivotY;
    }

    public void setImagePivotY(float imageY) {
        this.imagePivotY = imageY;
    }
    public float getImageX() {
        return imageX;
    }

    public void setImageX(float imageX) {
        this.imageX = imageX;
    }

    public float getImageY() {
        return imageY;
    }

    public void setImageY(float imageY) {
        this.imageY = imageY;
    }
    public float getxTranslate() {
        return xTranslate;
    }

    public void setxTranslate(float xTranslate) {
        this.xTranslate = xTranslate;
    }

    public float getyTranslate() {
        return yTranslate;
    }

    public void setyTranslate(float yTranslate) {
        this.yTranslate = yTranslate;
    }
    public int getFrameID() {
        return frameID;
    }

    public void setFrameID(int frameID) {
        this.frameID = frameID;
    }

    public int getStickerID() {
        return stickerID;
    }

    public void setStickerID(int stickerID) {
        this.stickerID = stickerID;
    }

    public float getStickerX() {
        return stickerX;
    }

    public void setStickerX(float stickerX) {
        this.stickerX = stickerX;
    }

    public float getStickerY() {
        return stickerY;
    }

    public void setStickerY(float stickerY) {
        this.stickerY = stickerY;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getTextX() {
        return textX;
    }

    public void setTextX(float textX) {
        this.textX = textX;
    }

    public float getTextY() {
        return textY;
    }

    public void setTextY(float textY) {
        this.textY = textY;
    }

    public int getTextFillID() {
        return textFillID;
    }

    public void setTextFillID(int textFillID) {
        this.textFillID = textFillID;
    }

    public int getTextStrokeID() {
        return textStrokeID;
    }

    public void setTextStrokeID(int textStrokeID) {
        this.textStrokeID = textStrokeID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bitmapIMG,flags);
        dest.writeInt(frameHeight);
        dest.writeInt(frameWidth);
        dest.writeInt(imageHeight);
        dest.writeInt(imageWidth);
        dest.writeFloat(imageRotation);
        dest.writeFloat(scaleFactorX);
        dest.writeFloat(scaleFactorY);
        dest.writeFloat(imagePivotX);
        dest.writeFloat(imagePivotY);
        dest.writeFloat(imageX);
        dest.writeFloat(imageY);
        dest.writeFloat(xTranslate);
        dest.writeFloat(yTranslate);
        dest.writeInt(frameID);
        dest.writeInt(stickerID);
        dest.writeFloat(stickerX);
        dest.writeFloat(stickerY);
        dest.writeString(text);
        dest.writeFloat(textX);
        dest.writeFloat(textY);
        dest.writeInt(textFillID);
        dest.writeInt(textStrokeID);
    }


}

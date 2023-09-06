package com.example.demoproject.activities;

import static android.provider.MediaStore.Images.Media.getBitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.data.CanvasData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CanvasActivity extends AppCompatActivity {
    public static final int saved = 1;
    CanvasData dataSaved;
    RelativeLayout view;
    ImageView imageCanvas;
    ViewGroup.LayoutParams params;
//    DrawImage canvas;
    Button back, save;
    Bitmap savedBitmap, image, frame, sticker;
    String savedPath;
    int i = 0, previous, strokeColor, fillColor;
    float textSize;
    String pathToSave, text;
    File file;
    Matrix matrix;
    Paint strokePaint, fillPaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        view = findViewById(R.id.view);
        imageCanvas = findViewById(R.id.canvasImage);
        Intent get = getIntent();
        dataSaved = new CanvasData();
        dataSaved = get.getParcelableExtra("canvasData");
        Log.i("activity canvas", "text : " + dataSaved.getText());
//        canvas = findViewById(R.id.canvasDraw);
        params = view.getLayoutParams();
        params.height = dataSaved.getImageHeight();
        params.width = dataSaved.getImageWidth();
        view.setLayoutParams(params);
        if (dataSaved == null) {
            Log.i("init", " no data ");
            return;
        } else {
            matrix = new Matrix();
            matrix.setRotate(dataSaved.getImageRotation(), dataSaved.getImagePivotX(), dataSaved.getImagePivotY());
            matrix.preScale(dataSaved.getScaleFactorX(), dataSaved.getScaleFactorY(), dataSaved.getImagePivotX(), dataSaved.getImagePivotY());
            matrix.postTranslate(dataSaved.getxTranslate(), dataSaved.getyTranslate());
            try {
                image = Bitmap.createScaledBitmap(getBitmap(getApplicationContext().getContentResolver(), dataSaved.getBitmapIMG()), dataSaved.getImageWidth(), dataSaved.getImageHeight(), false);

                Log.i("Image", "bitmap: " + imageCanvas);
                if (dataSaved.getFrameID() != 0)
                    frame = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(), dataSaved.getFrameID()), dataSaved.getFrameWidth(), dataSaved.getFrameHeight(), false);
                Log.i("Frame", "bitmap: " + frame);
                if (dataSaved.getStickerID() != 0)
                    sticker = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(), dataSaved.getStickerID()), (int) dpToPx(this, 100), (int) dpToPx(this, 100), false);
                Log.i("Sticker", "bitmap: " + sticker);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (dataSaved.getText() != null) {
                text = dataSaved.getText();
                strokeColor = dataSaved.getTextStrokeID();
                fillColor = dataSaved.getTextFillID();
                textSize = dpToPx(this, 32);
                strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                strokePaint.setStyle(Paint.Style.STROKE);
                strokePaint.setStrokeWidth(2);
                strokePaint.setColor(this.getColor(strokeColor));
                strokePaint.setStrokeJoin(Paint.Join.ROUND);
                strokePaint.setTextSize(textSize);
                fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                fillPaint.setStyle(Paint.Style.FILL);
                fillPaint.setColor(this.getColor(fillColor));
                fillPaint.setTextSize(textSize);
            }
            savedBitmap = drawAndSave();
            imageCanvas.setImageBitmap(savedBitmap);
//        try {
//            canvas.getData(dataSaved);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

            back = findViewById(R.id.backbtn);
            save = findViewById(R.id.savebtn);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                savedBitmap = canvas.save();
                    getPrevious();
                    pathToSave = "PhotoFrame";
                    try {
                        savedPath = saveImagetoGallery();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Intent saveImg = new Intent(getApplicationContext(), ShareImage.class);
                    saveImg.putExtra("path", savedPath);
                    startActivity(saveImg);
                }
            });

        }
    }
    public static float dpToPx(Context context, float dp) {
        return (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }

    private Bitmap drawAndSave() {
        savedBitmap = Bitmap.createBitmap(dataSaved.getFrameWidth(), dataSaved.getFrameHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(savedBitmap);
        if (dataSaved != null) {
//            canvas.save();
//            canvas.translate(-savedData.getxTranslate(), -savedData.getyTranslate());
            canvas.drawBitmap(image,matrix,null);
//            canvas.rotate(dataSaved.getImageRotation(), dataSaved.getImagePivotX(), dataSaved.getImagePivotY());
//            canvas.scale(dataSaved.getScaleFactorX(), dataSaved.getScaleFactorY(), dataSaved.getImagePivotX(), dataSaved.getImagePivotY());
//            canvas.drawBitmap(image, dataSaved.getImageX(),dataSaved.getImageY(), null);
//            canvas.restore();
            if (dataSaved.getFrameID() != 0)
                canvas.drawBitmap(frame, 0, 0, null);
            if (dataSaved.getStickerID() != 0)
                canvas.drawBitmap(sticker, dataSaved.getStickerX(), dataSaved.getStickerY(), null);
            if (dataSaved.getText() != null) {
                canvas.drawText(text, dataSaved.getTextX(), dataSaved.getTextY() + textSize, fillPaint);
                canvas.drawText(text, dataSaved.getTextX(), dataSaved.getTextY() + textSize, strokePaint);
            }
        }
        return savedBitmap;
    }

    private void getPrevious() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
        int s = sharedPreferences.getInt("data", 1000);
        previous = s;
    }

    private void displayAndSaveText(int data) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("data", data);
        editor.commit();
    }

    private String saveImagetoGallery() throws IOException {
        if (savedBitmap != null) {
            i = previous + 1;
            File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File childFolder = new File(storageLoc, pathToSave);
            if (!childFolder.exists()&&!childFolder.mkdir()) {
                    return null;
                }

                    file = new File(childFolder, "picture" + i + ".jpg");
                    if (!file.exists()) {
                        boolean fileCreated = file.createNewFile();
                        if (!fileCreated) {
                            return null;
                        }
                    }
//                File file = new File(storageLoc, "picture"+System.currentTimeMillis()+".jpg");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    savedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();

                    Context context = getApplicationContext();

                    Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    scanIntent.setData(Uri.fromFile(file));
                    context.sendBroadcast(scanIntent);
                    displayAndSaveText(i);

                    Toast.makeText(getApplicationContext(), "Your image has been saved!", Toast.LENGTH_SHORT).show();
                    return file.getPath();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                Toast.makeText(getApplicationContext(), "No image taken!", Toast.LENGTH_SHORT).show();
                return null;
            }
    }
}
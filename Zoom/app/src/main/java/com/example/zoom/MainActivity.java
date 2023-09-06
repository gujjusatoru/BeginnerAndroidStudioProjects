package com.example.zoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    ScaleGestureDetector scale;
    GestureDetector gestureDetector;
    float pivotx=0;
    float pivoty=0;
    float ydist=0;
    float xdist=0;
    ImageView img;
    TextView textView;
    float scalefact=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);
        textView=findViewById(R.id.textView);
        scale=new ScaleGestureDetector(img.getContext(),new Listener());
        gestureDetector=new GestureDetector(img.getContext(),this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        scale.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent e) {
        img.setScaleX(1);
        img.setScaleY(1);
        scalefact=1;
        img.setX(0);
        img.setY(0);
        xdist=0;
        ydist=0;
        pivotx=0;
        pivoty=0;
        Toast.makeText(this, "RESET", Toast.LENGTH_SHORT).show();
        textView.setText("Interact with Image");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {


        xdist=e2.getX()-e1.getX();
        ydist=e2.getY()-e1.getY();
        img.setX(xdist);
        img.setY(ydist);
        img.setPivotX(pivotx);
        img.setPivotY(pivoty);
        Log.i("scalescroll",img.getPivotX()+","+img.getPivotY());
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public class Listener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(@NonNull ScaleGestureDetector detector) {
            img.setPivotX(pivotx);
            img.setPivotY(pivoty);
            float factor=detector.getScaleFactor();
            scalefact*=detector.getScaleFactor();

            if(factor>1){
                textView.setText("ZOOM IN");
            }
            else{
                textView.setText("ZOOM OUT");
            }
            img.setScaleX(scalefact);
            img.setScaleY(scalefact);
            Log.i("scale2",img.getPivotX()+","+img.getPivotY()+","+detector.getFocusX()+","+detector.getFocusY());
            return true;
        }

        @Override
        public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
            pivotx=detector.getFocusX();
            pivoty=detector.getFocusY();
            Log.i("scale1",img.getPivotX()+","+img.getPivotY()+","+detector.getFocusX()+","+detector.getFocusY());
            return true;
        }

        @Override
        public void onScaleEnd(@NonNull ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
            pivotx=detector.getFocusX();
            pivoty=detector.getFocusY();
            img.setPivotX(pivotx);
            img.setPivotY(pivoty);
            Log.i("scale3",img.getPivotX()+","+img.getPivotY()+","+detector.getFocusX()+","+detector.getFocusY());

        }
    }
}
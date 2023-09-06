package com.example.touchevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    ConstraintLayout constraintLayout;
    Button btn;
    public static final String Tag="GestureEvent";
    GestureDetector gestureDetector;
    private float x1,x2,y1,y2;
    private static final int distance=150;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout=findViewById(R.id.mainlay);
        gestureDetector=new GestureDetector(this,this);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Right Swipe\nLeft Swipe\nBottom Swipe\nUp Swipe", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Click to know Gestures", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//        constraintLayout.setOnTouchListener((View.OnTouchListener) this);
//        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                gestureDetector.onTouchEvent(event);
//                int eventType=event.getActionMasked();
//                switch (eventType){
//                    case MotionEvent.ACTION_DOWN:
//                        Log.i("touchtoevent","Action down");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.i("touchtoevent","Action Move");
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        Log.i("touchtoevent","Action pointers down: "+event.getPointerCount());
//                        break;
//                    case MotionEvent.ACTION_POINTER_UP:
//                        Log.i("touchtoevent","Action pointers up: "+event.getPointerCount());
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.i("touchtoevent","Action up ");
//                        break;
//                }
//                return true;
//            }
//        });

    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        Log.i(Tag,"onSingleTapConfirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent e) {
        Log.i(Tag,"onDoubleTap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        Log.i(Tag,"onDoubleTapEvent");
        return false;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        Log.i(Tag,"onDown");
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {
        Log.i(Tag,"onShowPress");

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        Log.i(Tag,"onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        Log.i(Tag,"onScroll");
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        Log.i(Tag,"onLongPress");

    }

    @Override
    public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        Log.i(Tag,"onFling");
        return false;
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        int eventType=event.getActionMasked();
//        switch (eventType){
//            case MotionEvent.ACTION_DOWN:
//                Log.i(Tag,"Action down");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.i(Tag,"Action Move");
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.i(Tag,"Action pointers down: "+event.getPointerCount());
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.i(Tag,"Action pointers up: "+event.getPointerCount());
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.i(Tag,"Action up ");
//                break;
//        }
//        gestureDetector.onTouchEvent(event);
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1=event.getX();
                y1=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2=event.getX();
                y2=event.getY();
                float valx=x2-x1;
                float valy=y2-y1;
                if(Math.abs(valx)>distance){
                    if(x2>x1){
                        constraintLayout.setBackgroundResource(R.drawable.pinkbeige);
                        Toast.makeText(this, "Right Swipe", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        constraintLayout.setBackgroundResource(R.drawable.bluepurple);
                        Toast.makeText(this, "Left Swipe", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(Math.abs(valy)>distance){
                    if(y2>y1){
                        constraintLayout.setBackgroundResource(R.drawable.watermelon);
                        Toast.makeText(this, "Bottom Swipe", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        constraintLayout.setBackgroundResource(R.drawable.purples);
                        Toast.makeText(this, "Up Swipe", Toast.LENGTH_SHORT).show();
                    }
                }

        }
        return super.onTouchEvent(event);
    }
}

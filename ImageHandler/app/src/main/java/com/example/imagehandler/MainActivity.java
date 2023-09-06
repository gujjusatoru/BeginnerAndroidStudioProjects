package com.example.imagehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    float downx, downy, x, y;
    LinearLayout layout;
    Button up,down,left,right;
    SeekBar rotate,scale;
    float scaleValue=1,imgheight,imgwidth;
    float parentHeight;
    float parentWidth;
    private float imgX;
    private float imgY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);
        layout=findViewById(R.id.linearLayout);
        rotate=findViewById(R.id.rotate);
        scale=findViewById(R.id.scale);
        up=findViewById(R.id.up);
        down=findViewById(R.id.down);
        left=findViewById(R.id.left);
        right=findViewById(R.id.right);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((img.getY()-5)>0) {
                    img.setY(img.getY() - 10);
                }
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((img.getX()-5)>0) {
                    img.setX(img.getX() - 10);
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((img.getX()+5)<(layout.getWidth()-img.getWidth())){
                    img.setX(img.getX()+10);
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((img.getY()+5)<(layout.getHeight()-img.getHeight())){
                    img.setY(img.getY()+10);
                }
            }
        });
        rotate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<90){
                    img.setRotation(progress-90);
                }
                else img.setRotation(progress-90);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        scale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                scaleValue=(float) (progress)/10;
                img.setScaleX(scaleValue);
                img.setScaleY(scaleValue);

                Rect rect = new Rect();
                img.getHitRect(rect);

                Log.i("Rect", ""+rect.left + " top " + rect.top + " right " + rect.right + " bottom " + rect.bottom);

                imgwidth = rect.right-rect.left;
                imgheight=rect.bottom-rect.top;
                img.setX(rect.left);
                img.setY(rect.top);

                /*imgwidth = img.getWidth()*img.getScaleX();
                imgheight=img.getHeight()*img.getScaleY();

                imgX= img.getX()+(img.getX()-(img.getX()*(imgwidth/img.getWidth())));
                imgY=img.getY()+(img.getY()-(img.getY()*(imgheight/img.getHeight())));
                img.setX(imgX);
                img.setY(imgY);*/


//                img.getLayoutParams().width = (int) imgwidth;
//                img.getLayoutParams().height = (int) imgheight;
//                img.invalidate();



//                img.setX(imgX);
//                img.setY(imgY);

                Log.i("scaled", "Scale Type "+img.getScaleType() +", img X "+img.getX()+", img Y "+img.getY()+", actual X "+imgX+", actual Y "+imgY+", img height "+imgheight+", img width "+imgwidth + " newW " +img.getWidth());

//                img.setX((parentWidth -imgwidth)/2);
//                img.setY((parentHeight -imgheight)/2);

                /* if(progress<5){
                    scaleValue=1-(float) (5-progress)/10;
                    imgheight=scaleValue*img.getHeight();
                    imgwidth=scaleValue*img.getWidth();
                    img.setScaleX(1-(float) (5-progress)/10);
                    img.setScaleY(1-(float) (5-progress)/10);
                    img.setX((parentWidth -imgwidth)/2);
                    img.setY((parentHeight -imgheight)/2);
//                    img.setX((img.getX()*scaleValue)/2);
//                    img.setY((img.getY()*scaleValue)/2);
                    Log.i("scaled", "Scale Type "+img.getScaleType() +", img X "+img.getX()+", img Y "+img.getY()+", img height "+imgheight+", img width "+imgwidth);

                }
                else{
                    scaleValue=1+(float) (progress-5)/10;
                    imgheight=scaleValue*img.getHeight();
                    imgwidth=scaleValue*img.getWidth();
                    img.setX((parentWidth -imgwidth)/2);
                    img.setY((parentHeight -imgheight)/2);
                    img.setScaleX(1+(float) (progress-5)/10);
                    img.setScaleY(1+(float) (progress-5)/10);
//                    img.setX((img.getX()*scaleValue)/2);
//                    img.setY((img.getY()*scaleValue)/2);
                    Log.i("scaled", "Scale Type "+img.getScaleType() +", img X "+img.getX()+", img Y "+img.getY()+", img height "+imgheight+", img width "+imgwidth);
                }*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        img.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.i("paddingimg", "onIMGLayoutChange: left-"+left+", top-"+top+", right-"+right+", bottom-"+bottom+", img X "+img.getX()+", img Y "+img.getY());
                imgheight=img.getHeight();
                imgwidth=img.getWidth();
                imgX=img.getX();
                imgY=img.getY();
            }
        });

        layout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.i("padding", "onLayoutChange: left-"+left+", top-"+top+", right-"+right+", bottom-"+bottom+", img X "+img.getX()+", img Y "+img.getY());
                parentWidth =layout.getWidth();
                parentHeight =layout.getHeight();
            }
        });

        img.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               int action=event.getActionMasked();
               switch (action){
                   case MotionEvent.ACTION_DOWN:
                       downx=event.getX();
                       downy=event.getY();
                       break;
                   case MotionEvent.ACTION_MOVE:
                       x=event.getX();
                       y= event.getY();
                       float disx=x-downx;
                       float disy=y-downy;
                       float X=layout.getX();
                       float Y=layout.getY();
                       float changex=img.getX()+disx;
                       float changey=img.getY()+disy;
                           if(changex>0&&changex<= parentWidth -imgwidth)
                               img.setX(changex);
                           if(changey<= parentHeight -imgheight&&changey>0)
                               img.setY(changey);

                       Log.i("move", "ImgX-"+img.getX()+", ImgY-"+img.getY()+", Img width-"+imgwidth+", Img height-"+imgheight+", layoutX-"+X+", layoutY-"+Y+", ChangeX-"+changex+", ChangeY-"+changey+", layout width-"+ parentWidth +", Layout height-"+ parentHeight);
                       break;
                   case MotionEvent.ACTION_UP: break;
               }

                return true;
            }
        });

    }
}
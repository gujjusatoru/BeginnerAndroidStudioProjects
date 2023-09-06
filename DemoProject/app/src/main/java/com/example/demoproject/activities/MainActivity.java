package com.example.demoproject.activities;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
import static android.provider.MediaStore.Images.Media.getBitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.demoproject.adapter.ClickedItem;
import com.example.demoproject.adapter.CustomAdapter;
import com.example.demoproject.R;
import com.example.demoproject.canvas.DrawImage;
import com.example.demoproject.canvas.MyDrawable;
import com.example.demoproject.data.CanvasData;
import com.example.demoproject.data.DataModel;
import com.example.demoproject.data.Frames;
import com.example.demoproject.data.Stickers;
import com.example.demoproject.data.Texts;
import com.example.demoproject.gesturelistener.MultiTouchListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickedItem {
    private static final int REQUEST_CODE = 100, GALLERY_IMAGE =20, CREATION_IMAGE=50, CAMERA_REQUEST=70, TEXT_ADD=35;
    private static final String IMG_NAME="temp.jpg";
    String text_added=null;
    Button camera, gallery, creations, frames, stickers, text, back, done, check;
    File gallery_folder;
    File folderCreation;
    ConstraintLayout home;
    LinearLayout actionHandler;
    RelativeLayout resize, viewHandler;
    ViewGroup.LayoutParams params;
    RecyclerView recycler;
    Uri selectedImage;
    int[] dim;
    ImageView image, frame_image, sticker_image;
    MyDrawable myDrawableText;
    DrawImage canvasDraw;
    Bitmap bitmapimg, finalbitmap;
    MainActivity activity;
    ArrayList<DataModel> dataSet;
    CanvasData savedData;
    RecyclerView.LayoutManager layoutManagerMain;
    CustomAdapter adapter;
    MultiTouchListener listen;
    int imgHeight, imgWidth;
    DataModel frame_obj=null, sticker_obj=null;
    int text_stroke, text_fill;
    int itemType;
    float downy,downx, x,y;
    private int layoutHeight, layoutWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity=MainActivity.this;

        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED|| checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
        gallery_folder=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        folderCreation = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/PhotoFrame");
        home=findViewById(R.id.splash_home);
        viewHandler=findViewById(R.id.image_canvas);
        resize=findViewById(R.id.resizeCanvas);
        recycler=findViewById(R.id.recycler);
        camera=findViewById(R.id.camera);
        gallery=findViewById(R.id.gallery);
        creations=findViewById(R.id.mycreation);
        frames=findViewById(R.id.frame);
        stickers=findViewById(R.id.sticker);
        back=findViewById(R.id.backbtn);
        done=findViewById(R.id.donebtn);
        text=findViewById(R.id.edit);
        image=findViewById(R.id.image);
        frame_image=findViewById(R.id.frame_image);
        sticker_image=findViewById(R.id.sticker_image);
        actionHandler=findViewById(R.id.actionhandler);
        myDrawableText =findViewById(R.id.textDrawable);
        myDrawableText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action=event.getActionMasked();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        downx=event.getX();
                        downy=event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x=event.getX();
                        float y= event.getY();
                        float disx=x-downx;
                        float disy=y-downy;
                        float changex= myDrawableText.getX()+disx;
                        float changey= myDrawableText.getY()+disy;
                        if(changex>0&&changex<= imgWidth - myDrawableText.getWidth())
                            myDrawableText.setX(changex);
                        if(changey<= imgHeight - myDrawableText.getHeight()&&changey>0)
                            myDrawableText.setY(changey);

                        Log.i("move", "ImgX-"+ myDrawableText.getX()+", ImgY-"+ myDrawableText.getY()+", Img width-"+ myDrawableText.getWidth()+", Img height-"+ myDrawableText.getHeight()+", layoutX-"+image.getX()+", layoutY-"+image.getY()+", ChangeX-"+changex+", ChangeY-"+changey+", layout width-"+ imgWidth +", Layout height-"+imgHeight);
                        break;
                    case MotionEvent.ACTION_UP: break;
                }

                return true;
            }
        });
        sticker_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action=event.getActionMasked();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        downx=event.getX();
                        downy=event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x=event.getX();
                        float y= event.getY();
                        float disx=x-downx;
                        float disy=y-downy;
                        float changex=sticker_image.getX()+disx;
                        float changey=sticker_image.getY()+disy;
                        if(changex>0&&changex<= imgWidth -sticker_image.getWidth())
                            sticker_image.setX(changex);
                        if(changey<= imgHeight -sticker_image.getHeight()&&changey>0)
                            sticker_image.setY(changey);

                        Log.i("move", "ImgX-"+sticker_image.getX()+", ImgY-"+sticker_image.getY()+", Img width-"+sticker_image.getWidth()+", Img height-"+sticker_image.getHeight()+", layoutX-"+image.getX()+", layoutY-"+image.getY()+", ChangeX-"+changex+", ChangeY-"+changey+", layout width-"+ imgWidth +", Layout height-"+imgHeight);
                        break;
                    case MotionEvent.ACTION_UP: break;
                }

                return true;
            }
        });
//        myDrawable=new MyDrawable(activity);

        viewHandler.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                layoutHeight=bottom-top-20;
                layoutWidth=right-left-20;
            }
        });

        image.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                imgHeight=bottom-top-(int)dpToPx(activity,20);
                imgWidth=right-left-(int)dpToPx(activity,20) ;
            }
        });

        image.setOnTouchListener(new MultiTouchListener());
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    File cameraFile=getFilefromAppDir(activity,IMG_NAME);
                    if(cameraFile!=null&&cameraFile.exists()){
                        Uri newImgUri = FileProvider.getUriForFile(getApplicationContext(),
                                getApplicationContext().getPackageName() + ".provider", cameraFile);
                        List<ResolveInfo> resolvedIntentActivities = getPackageManager().queryIntentActivities(cameraIntent, 0);
                        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                            String packageName = resolvedIntentInfo.activityInfo.packageName;
                            activity.grantUriPermission(packageName, newImgUri, FLAG_GRANT_WRITE_URI_PERMISSION | FLAG_GRANT_READ_URI_PERMISSION);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, newImgUri);
                            cameraIntent.addFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);

                        }
                        activity.startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setDataAndType(Uri.parse("/storage/emulated/0/Pictures"),"image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Gallery"), GALLERY_IMAGE);
            }
        });
        creations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(activity,Creations.class);
                i.putExtra("path",folderCreation.getPath());
                startActivity(i);
//
//                home.setVisibility(View.GONE);
//                Log.d("path", folder.toString());
//                folder.listFiles();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog;
                progressDialog = ProgressDialog.show(activity, "","Please Wait...", true,false);
                Intent canvas= new Intent(activity, CanvasActivity.class);
                Log.i("checksent", "X "+listen.translateX+" Y "+listen.translateY+" imagex: "+image.getTranslationX()+" imagey: "+image.getTranslationY()+" absolute X "+image.getX()+" absolute Y "+image.getY());
                float translateX = image.getTranslationX();
                float translateY = image.getTranslationY();
                if(image.getScaleX()<1&&image.getScaleY()<1){
                x = (image.getLeft() + translateX);
                y = (image.getTop() + translateY);
                }
                else{
                x = (image.getLeft() + translateX);
                y = (image.getTop() + translateY);}
                savedData=new CanvasData();
                savedData.setBitmapIMG(selectedImage);
                savedData.setFrameHeight(image.getHeight());
                savedData.setFrameWidth(image.getWidth());
                savedData.setImageHeight(dim[1]);
                savedData.setImageWidth(dim[0]);
                savedData.setImageRotation(image.getRotation());
                savedData.setScaleFactorX(image.getScaleX());
                savedData.setScaleFactorY(image.getScaleY());
                savedData.setImagePivotX(image.getPivotX());
                savedData.setImagePivotY(image.getPivotY());
                savedData.setImageX(x);
                savedData.setImageY(y);
                savedData.setxTranslate(image.getTranslationX());
                savedData.setyTranslate(image.getTranslationY());
//                if(image.getTranslationX()>0&&image.getX()>0){
//                    savedData.setImageX(image.getTranslationX()+ (image.getX() - image.getTranslationX()));
//                }
//                if(image.getTranslationY()>0&&image.getY()>0){
//                    savedData.setImageY(image.getTranslationY()+(image.getY() - image.getTranslationY()));
//                }
//                if(image.getTranslationX()<0&&image.getX()>0){
//                    savedData.setImageX(image.getX() +image.getTranslationX());
//                }
//                if(image.getTranslationY()<0&&image.getY()>0){
//                    savedData.setImageY(image.getY() + image.getTranslationY());
//                }
//                if(image.getTranslationX()<0&&image.getX()<0){
//                    savedData.setImageX(image.getTranslationX()+ (Math.abs(image.getX() - image.getTranslationX())));
//                }
//                if(image.getTranslationY()<0&&image.getY()<0){
//                    savedData.setImageY(image.getTranslationY()+(Math.abs(image.getY() - image.getTranslationY())));
//                }
                if(frame_obj!=null)
                    savedData.setFrameID(frame_obj.getDrawable());
                if(sticker_obj!=null){
                savedData.setStickerID(sticker_obj.getDrawable());
                savedData.setStickerX(sticker_image.getX());
                savedData.setStickerY(sticker_image.getY());}
                if(text_added!=null){
                savedData.setText(text_added);
                savedData.setTextX(myDrawableText.getX());
                savedData.setTextY(myDrawableText.getY());
                savedData.setTextFillID(text_fill);
                savedData.setTextStrokeID(text_stroke);}
                canvas.putExtra("canvasData",savedData);
                progressDialog.dismiss();
                startActivity(canvas);

//                actionHandler.setVisibility(View.INVISIBLE);
//                imageCanvas1.setVisibility(View.GONE);
//                imageCanvas2.setVisibility(View.VISIBLE);
//                canvasDraw.getData(savedData);
            }
        });
        frames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instantiateFrames();
            }
        });
        stickers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instantiateStickers();
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(activity, TextActivity.class);
                startActivityForResult(i,TEXT_ADD);
                instantiateTexts();
            }
        });



    }
    public static float dpToPx(Context context, float dp) {
        return (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }
    public void restart() {
        home.setVisibility(View.VISIBLE);
        image.setImageResource(0);
        image.setX(16);
        image.setY(16);
        image.setScaleY(1);
        image.setScaleX(1);
        image.setRotation(0);
        frame_obj=null;
        sticker_obj=null;
        myDrawableText.setVisibility(View.GONE);
        text_added=null;
        text_fill=0;
        text_stroke=0;
        frame_image.setVisibility(View.GONE);
        sticker_image.setVisibility(View.GONE);
        if(dataSet!=null){
            dataSet.clear();
            adapter.notifyDataSetChanged();}
    }

    @Override
    public void onBackPressed() {
        if(home.getVisibility()==View.VISIBLE) {
            super.onBackPressed();
        }
        else
            restart();
    }

    private void instantiateTexts() {
        recycler.setHasFixedSize(true);
        itemType=3;
        layoutManagerMain =new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(layoutManagerMain);
        recycler.setItemAnimator(new DefaultItemAnimator());
        dataSet=new ArrayList<>();
        for(int i = 0; i<Texts.id_obj.length; i++){
            dataSet.add(new DataModel(Texts.id_obj[i],Texts.name[i],Texts.drawable[i]));
        }
        adapter=new CustomAdapter(getApplicationContext(),dataSet,itemType);
        recycler.setAdapter(adapter);
        adapter.setClickedItem(this);
    }


    private void instantiateStickers() {
        recycler.setHasFixedSize(true);
        itemType=2;
        layoutManagerMain =new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(layoutManagerMain);
        recycler.setItemAnimator(new DefaultItemAnimator());
        dataSet=new ArrayList<>();
        for(int i = 0; i< Stickers.id_obj.length; i++){
            dataSet.add(new DataModel(Stickers.id_obj[i],Stickers.name[i],Stickers.drawable[i] ));
        }
        adapter=new CustomAdapter(getApplicationContext(),dataSet,itemType);
        recycler.setAdapter(adapter);
        adapter.setClickedItem(this);
    }

    private void instantiateFrames() {
        itemType=1;
        recycler.setHasFixedSize(true);
        layoutManagerMain =new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(layoutManagerMain);
        recycler.setItemAnimator(new DefaultItemAnimator());
        dataSet=new ArrayList<>();
        for(int i = 0; i< Frames.id_obj.length; i++){
            dataSet.add(new DataModel(Frames.id_obj[i],Frames.name[i],Frames.drawable[i] ));
        }
        adapter=new CustomAdapter(getApplicationContext(),dataSet,itemType);
        recycler.setAdapter(adapter);
        adapter.setClickedItem(this);
    }

    private File getFilefromAppDir(Context context, String imgName) throws IOException {
        File photoDirectory=gallery_folder;
        File photoClicked=new File(photoDirectory.getPath()+File.separator+imgName);
        if(!photoClicked.exists()){
            boolean fileCreated= photoClicked.createNewFile();
            if(!fileCreated){
                return null;
            }
        }
        return photoClicked;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent get=getIntent();

        if(get!=null) {
            int data=get.getIntExtra("restart",0);
            if (data==1){
                restart();
                setIntent(null);
            }
        }
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        List<ResolveInfo> resolvedIntentActivities = getApplicationContext().getPackageManager().queryIntentActivities(cameraIntent, PackageManager.MATCH_ALL);
//    Log.i("ResolverSize", ""+resolvedIntentActivities.size());
//        ProgressDialog progressDialog=new ProgressDialog(activity);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("loading...");
//        progressDialog.show();
    }


    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, permissions[i] + "permission granted", Toast.LENGTH_LONG).show();
//                if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED|| checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                    requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                } else {
                    requestPermissions(permissions, requestCode);
                }
            }
        }
    }
    public static int[] getResizeDim(float required_W, float required_H, int available_W, int available_H) {
        int nw1, nh1, nw2, nh2;
        float ratio = required_W / required_H;


        nw1 = available_W;
        nh1 =(int) (available_W / ratio);

        nw2 =(int) (available_H * ratio);
        nh2 = available_H;

        if (nw1 <= available_W && nh1 <= available_H) {
            // This means image has scaled in Y direction adjust the Y accordingly
            return new int[]{nw1, nh1};

        } else if (nw2 <= available_W && nh2 <= available_H) {
            // This means the image has scaled in X direction adjust the X accordingly
            return new int[]{nw2, nh2};
        }
        return new int[]{0, 0};
    }
    public static Bitmap resizeBitmap(Bitmap bit, int width, int height) {
        try {
            if (bit == null)
                return null;

            float wd = bit.getWidth();
            float he = bit.getHeight();

            float ratio = (float) bit.getWidth() / (float) bit.getHeight();
            float nw1, nh1, nw2, nh2;

            nw1 = (float) width;
            nh1 = ((float) width) / ratio;

            nw2 = ((float) height) * ratio;
            nh2 = (float) height;

            if (nw1 <= width && nh1 <= height) {
                wd = (int) nw1;
                he = (int) nh1;

            } else if (nw2 <= width && nh2 <= height) {
                wd = (int) nw2;
                he = (int) nh2;

            }

            bit = Bitmap.createScaledBitmap(bit, (int) wd, (int) he, false);
            return bit;
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
        return bit;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK) {
            if (requestCode == GALLERY_IMAGE) {
                selectedImage = data.getData();
                try {
                    bitmapimg = Bitmap.createBitmap(getBitmap(this.getContentResolver(), selectedImage));
                    dim=getResizeDim(bitmapimg.getWidth(),bitmapimg.getHeight(),layoutWidth,layoutHeight);
                    params=resize.getLayoutParams();
                    params.width=dim[0];
                    params.height=dim[1];
                    resize.setLayoutParams(params);
                    finalbitmap=resizeBitmap(bitmapimg,(int)dim[0],(int)dim[1]);
                    if(finalbitmap!=null){
                        image.setImageBitmap(finalbitmap);
                        Log.i("Image Dimen", "onSelected: height"+image.getHeight()+" width"+image.getWidth());
                        home.setVisibility(View.GONE);
                    }
                } catch (IOException e) {
                    home.setVisibility(View.VISIBLE);
                    image.setImageResource(0);
                }


            }

            if (requestCode == CAMERA_REQUEST) {
                try {
                    File cameraFile = getFilefromAppDir(activity, IMG_NAME);
                    if (cameraFile != null && cameraFile.exists()) {
                        selectedImage = Uri.fromFile(cameraFile);
                        bitmapimg = Bitmap.createBitmap(getBitmap(this.getContentResolver(), selectedImage));
                        dim=getResizeDim(bitmapimg.getWidth(),bitmapimg.getHeight(),layoutWidth,layoutHeight);
                        params=resize.getLayoutParams();
                        params.width=dim[0];
                        params.height=dim[1];
                        resize.setLayoutParams(params);
                        finalbitmap=resizeBitmap(bitmapimg,(int)dim[0],(int)dim[1]);
                        if (finalbitmap != null) {
                            image.setImageBitmap(finalbitmap);
                            home.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    home.setVisibility(View.VISIBLE);
                    image.setImageResource(0);
                    e.printStackTrace();
                }
            }
//                home.setVisibility(View.GONE);
//                selectedImage =data.getData();
//                try {
//                    bitmapimg = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                image.setImageBitmap(bitmapimg);
            if(requestCode==TEXT_ADD){
                text_added= data.getStringExtra("text");
                text_stroke=Texts.strokeColor[0];
                text_fill=Texts.fillColor[0];
                myDrawableText.updateValues(activity,Texts.strokeColor[0],Texts.fillColor[0],text_added );
            }
        }
    }

    @Override
    public void onSelected(int type, View view, int pos) {
        if(type==1){
                if(pos==-1){
                    frame_obj=null;
                    frame_image.setVisibility(View.GONE);
                    frame_image.setImageResource(0);
                }
                else {
                    frame_obj = new DataModel(Frames.id_obj[pos], Frames.name[pos], Frames.drawable[pos]);
                    frame_image.setVisibility(View.VISIBLE);
                    frame_image.setImageResource(frame_obj.getDrawable());

                }
            Log.i("Frame Dimen", "onSelected: height"+frame_image.getHeight()+" width"+frame_image.getWidth());

        }
        else if(type==2){
            if(pos==-1){
                sticker_obj=null;
                sticker_image.setVisibility(View.GONE);
                sticker_image.setImageResource(0);
            }
            else {
                sticker_obj = new DataModel(Stickers.id_obj[pos], Stickers.name[pos], Stickers.drawable[pos]);
                sticker_image.setVisibility(View.VISIBLE);
                sticker_image.setImageResource(sticker_obj.getDrawable());

            }
        }
        else if(type==3){
            Log.i("Array bounds", "StrokeColor: "+Texts.strokeColor.length+", FillColor: "+Texts.fillColor.length);
            if(pos==-1){
                text_stroke=Texts.strokeColor[0];
                text_fill=Texts.fillColor[0];

            }
            else{
                text_stroke=Texts.strokeColor[pos];
                text_fill=Texts.fillColor[pos];
            }
            myDrawableText.updateValues(activity,text_stroke,text_fill,text_added);
        }
    }

    @Override
    public void longSelected(View view, int pos) {

    }
}
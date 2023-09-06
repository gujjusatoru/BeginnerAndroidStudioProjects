package com.example.demoproject.activities;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.demoproject.R;

import java.io.File;
import java.util.List;

public class ShareImage extends AppCompatActivity {
    String path;
    Bitmap bitmap;
    ImageView image;
    Button home, back, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_image);
        Intent i= getIntent();
        path=i.getStringExtra("path");
        image=findViewById(R.id.savedImage);
        back=findViewById(R.id.backbtn);
        home=findViewById(R.id.homebtn);
        share=findViewById(R.id.sharebtn);
        bitmap=Bitmap.createBitmap(BitmapFactory.decodeFile(path));
        image.setImageBitmap(bitmap);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent again= new Intent(getApplicationContext(),MainActivity.class);
                again.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                again.putExtra("restart",1);
                startActivity(again);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                File shareFile = new File(path);
                if (shareFile != null && shareFile.exists()) {
                    Uri newImgUri = FileProvider.getUriForFile(getApplicationContext(),
                            getApplicationContext().getPackageName() + ".provider", shareFile);
                    List<ResolveInfo> resolvedIntentActivities = getPackageManager().queryIntentActivities(share, 0);
                    for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
                        String packageName = resolvedIntentInfo.activityInfo.packageName;
                        ShareImage.this.grantUriPermission(packageName, newImgUri, FLAG_GRANT_WRITE_URI_PERMISSION | FLAG_GRANT_READ_URI_PERMISSION);
                        share.putExtra(MediaStore.EXTRA_OUTPUT, newImgUri);
                        share.addFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                    share.setType("image/*");
                    share.putExtra(Intent.EXTRA_SUBJECT, "PhotoFrame App");
                    share.putExtra(Intent.EXTRA_TEXT, "I just created this edit with the Amazing PhotoFrame App.\n Download yours now.");
                    share.putExtra(Intent.EXTRA_STREAM, newImgUri);
                    startActivity(Intent.createChooser(share, "Choose a platform"));
                }
            }
        });
    }
}
package com.example.travelanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.travelanimation.adapters.MainAdapter;
import com.example.travelanimation.adapters.SecondAdapter;
import com.example.travelanimation.data.DataModel;
import com.example.travelanimation.data.DataSender;
import com.example.travelanimation.data.DataSender2;
import com.example.travelanimation.data.DataSender3;
import com.example.travelanimation.data.DialogData;

import java.util.ArrayList;

public class ActivitySecond extends AppCompatActivity implements OnClick{
    ImageView car,smoke, dialogImg1,dialogImg2;
    Animation car_animate;
    TextView headerTxt, dialogData, dialogHeader;
    Button backbtn, homebtn, likedbtn, dialogLike, dialogClose;
    RecyclerView recycleMain,recycleSecondary;
    RecyclerView.LayoutManager layoutManagerMain,layoutManagerSecondary;
    ArrayList<DataModel> data, data2, data3;
    MainAdapter adapter;
    SecondAdapter adapter_two;
    Dialog dialog;
    int clickedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        car=findViewById(R.id.car2);
        smoke=findViewById(R.id.smoke);
        car_animate= AnimationUtils.loadAnimation(this,R.anim.car2);
        car.setAnimation(car_animate);
        smoke.setAnimation(car_animate);
        headerTxt =findViewById(R.id.header);
        backbtn=findViewById(R.id.backbtn);
        homebtn=findViewById(R.id.homebtn);
        likedbtn=findViewById(R.id.btnlike);
        dialog= new Dialog(this);
        instantiateMainCards();
        instantiateSecondaryCards();
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(headerTxt.getText().toString().equals("Explore")){
                    onBackPressed();
                }
                else {
                    headerTxt.setText("Explore");
                    instantiateSecondaryCards();
                }
            }
        });
    }



    private void instantiateMainCards() {
        recycleMain=findViewById(R.id.recyclerPrimary);
        recycleMain.setHasFixedSize(true);
        layoutManagerMain =new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycleMain.setLayoutManager(layoutManagerMain);
        recycleMain.setItemAnimator(new DefaultItemAnimator());
        data=new ArrayList<>();
        for(int i=0;i< DataSender.name.length;i++){
            data.add(new DataModel(DataSender.name[i],DataSender.city[i],DataSender.id_obj[i],DataSender.images[i] ));
        }
        adapter=new MainAdapter(getApplicationContext(),data);
        recycleMain.setAdapter(adapter);


    }
    private void instantiateSecondaryCards() {
        recycleSecondary=findViewById(R.id.recyclerSecond);
        recycleSecondary.setHasFixedSize(true);
        layoutManagerSecondary=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycleSecondary.setLayoutManager(layoutManagerSecondary);
        recycleSecondary.setItemAnimator(new DefaultItemAnimator());
        data2=new ArrayList<>();
        for(int i=0;i< DataSender2.name.length;i++){
            data2.add(new DataModel(DataSender2.name[i], null,DataSender2.id_obj[i], DataSender2.img[i] ));
        }
        adapter_two= new SecondAdapter(getApplicationContext(),data2,1);
        recycleSecondary.setAdapter(adapter_two);
        adapter_two.setClicked(this);

    }

    @Override
    public void onSelect(View v, int pos,int view) {
        if (view == 1) {

            if (headerTxt.getText().toString().equals("Explore")) {

            } else {
                data3 = new ArrayList<>();
                clickedView=pos;
                for (int k = 0; k < DataSender3.id_obj.length; k++) {
                    data3.add(new DataModel(DataSender3.city[pos][k], DataSender3.state[pos][k], DataSender3.id_obj[k], DataSender3.img[pos][k]));
                }
                adapter_two = new SecondAdapter(getApplicationContext(), data3, 2);
                recycleSecondary.setAdapter(adapter_two);
                adapter_two.setClicked(this);
            }
        }
        if(view==2){
            dialog.setContentView(R.layout.dialog);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
            dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialogHeader=dialog.findViewById(R.id.dialog_city);
            dialogImg1=dialog.findViewById(R.id.dialogImg1);
            dialogImg2=dialog.findViewById(R.id.dialogImg2);
            dialogData=dialog.findViewById(R.id.data);
            dialogLike=dialog.findViewById(R.id.btnlike);
            dialogClose=dialog.findViewById(R.id.close);
            dialogHeader.setText(DialogData.city[clickedView][pos]+"");
            dialogData.setText(DialogData.data[clickedView][pos]+"");
            Glide.with(this).load(DialogData.img[clickedView][pos][0])
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(dialogImg1);
            Glide.with(this).load(DialogData.img[clickedView][pos][1])
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(dialogImg2);
            dialog.show();
            dialogClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });



        }
    }

    @Override
    public void btnClick(String header) {
        headerTxt.setText(""+header);
    }
}
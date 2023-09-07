package com.example.json;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ExtractData {

    SQLiteDatabase db;
    Context context;
    String[] ID;
    DataBaseHelperClass help;

    public ExtractData(SQLiteDatabase db, Context context) {
        this.db = db;
        this.context=context;
        this.help=new DataBaseHelperClass(context);
    }
    public ArrayList<TemplateData> getAllData(String[] id){
        ArrayList<TemplateData> templateData=new ArrayList<>();
        ID=id;
        Cursor cursor= db.rawQuery(TemplateData.getData,id);
        if (cursor.moveToFirst()){
            do{
                TemplateData template=new TemplateData();
                template.setTEMPLATE_ID(cursor.getInt(cursor.getColumnIndexOrThrow(TemplateData.COLUMN_NAME2)));
                template.setCATEGORY(cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY")));
                template.setTEMPLATE_NAME(cursor.getString(cursor.getColumnIndexOrThrow("TEMPLATE_NAME")));
                template.setRATIO_ID(cursor.getInt(cursor.getColumnIndexOrThrow("RATIO_ID")));
                template.setTHUMB_SERVER_PATH(cursor.getString(cursor.getColumnIndexOrThrow("THUMB_SERVER_PATH")));
                template.setTHUMB_LOCAL_PATH(cursor.getString(cursor.getColumnIndexOrThrow("THUMB_LOCAL_PATH")));
                template.setTHUMB_TIME(cursor.getDouble(cursor.getColumnIndexOrThrow("THUMB_TIME")));
                template.setTOTAL_DURATION(cursor.getDouble(cursor.getColumnIndexOrThrow("TOTAL_DURATION")));
                template.setSEQUENCE(cursor.getInt(cursor.getColumnIndexOrThrow("SEQUENCE")));
                template.setIS_PREMIUM(cursor.getInt(cursor.getColumnIndexOrThrow("IS_PREMIUM")));
                template.setCATEGORY_TEMP(cursor.getString(cursor.getColumnIndexOrThrow("CATEGORY_TEMP")));
                template.setIN_ANIMATION_TEMPLATE_ID(cursor.getInt(cursor.getColumnIndexOrThrow("IN_ANIMATION_TEMPLATE_ID")));
                template.setIN_ANIMATION_DURATION(cursor.getInt(cursor.getColumnIndexOrThrow("IN_ANIMATION_DURATION")));
                template.setLOOP_ANIMATION_TEMPLATE_ID(cursor.getInt(cursor.getColumnIndexOrThrow("LOOP_ANIMATION_TEMPLATE_ID")));
                template.setLOOP_ANIMATION_DURATION(cursor.getDouble(cursor.getColumnIndexOrThrow("LOOP_ANIMATION_DURATION")));
                template.setOUT_ANIMATION_TEMPLATE_ID(cursor.getInt(cursor.getColumnIndexOrThrow("OUT_ANIMATION_TEMPLATE_ID")));
                template.setOUT_ANIMATION_DURATION(cursor.getDouble(cursor.getColumnIndexOrThrow("OUT_ANIMATION_DURATION")));
                template.setMUSIC_ID(cursor.getInt(cursor.getColumnIndexOrThrow("MUSIC_ID")));
                template.setMUSIC_TYPE(cursor.getString(cursor.getColumnIndexOrThrow("MUSIC_TYPE")));
                template.setNAME(cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
                template.setMUSIC_PATH(cursor.getString(cursor.getColumnIndexOrThrow("MUSIC_PATH")));
                template.setPARENT_ID(cursor.getInt(cursor.getColumnIndexOrThrow(TemplateData.COLUMN_NAME3)));
                template.setPARENT_TYPE(cursor.getInt(cursor.getColumnIndexOrThrow("PARENT_TYPE")));
                template.setSTART_TIME_OF_AUDIO(cursor.getDouble(cursor.getColumnIndexOrThrow("START_TIME_OF_AUDIO")));
                template.setEND_TIME_OF_AUDIO(cursor.getDouble(cursor.getColumnIndexOrThrow("END_TIME_OF_AUDIO")));
                template.setSTART_TIME(cursor.getDouble(cursor.getColumnIndexOrThrow("START_TIME")));
                template.setDURATION(cursor.getDouble(cursor.getColumnIndexOrThrow("DURATION")));
                templateData.add(template);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return templateData;

    }
    public JSONObject convertData(ArrayList<TemplateData> templateDataList){
        JSONObject obj=new JSONObject();
        TemplateData templateData= new TemplateData();
        for(int i=0;i<templateDataList.size();i++) {
            templateData = templateDataList.get(i);
            String temp_id=ID[i];
            try {
                obj.put("TEMPLATE_ID", templateData.getTEMPLATE_ID());
                obj.put("CATEGORY", templateData.getCATEGORY());
                obj.put("TEMPLATE_NAME", templateData.getTEMPLATE_NAME());
                obj.put("RATIO_ID", templateData.getRATIO_ID());
                obj.put("THUMB_SERVER_PATH", templateData.getTHUMB_SERVER_PATH());
                obj.put("THUMB_LOCAL_PATH", templateData.getTHUMB_LOCAL_PATH());
                obj.put("THUMB_TIME", templateData.getTHUMB_TIME());
                obj.put("TOTAL_DURATION", templateData.getTOTAL_DURATION());
                obj.put("SEQUENCE", templateData.getSEQUENCE());
                obj.put("IS_PREMIUM", templateData.getIS_PREMIUM());
                obj.put("CATEGORY_TEMP", templateData.getCATEGORY_TEMP());
                obj.put("IN_ANIMATION_TEMPLATE_ID", templateData.getIN_ANIMATION_TEMPLATE_ID());
                obj.put("IN_ANIMATION_DURATION", templateData.getIN_ANIMATION_DURATION());
                obj.put("LOOP_ANIMATION_TEMPLATE_ID", templateData.getLOOP_ANIMATION_TEMPLATE_ID());
                obj.put("LOOP_ANIMATION_DURATION", templateData.getLOOP_ANIMATION_DURATION());
                obj.put("OUT_ANIMATION_TEMPLATE_ID", templateData.getOUT_ANIMATION_TEMPLATE_ID());
                obj.put("OUT_ANIMATION_DURATION", templateData.getOUT_ANIMATION_DURATION());
                if(templateData.getMUSIC_ID()>0){
                    obj.accumulate("MUSIC",MusicObj(temp_id.split(",")));
                }
                obj.put("PAGES",PagesArray(temp_id.split(",")));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return obj;
    }

    private JSONArray PagesArray(String[] tempId) {
        JSONArray pages=new JSONArray();
        JSONObject pagesObj=new JSONObject();
        db=help.openDataBase();
        Cursor cursor= db.rawQuery(TemplateData.getPages,tempId);
        if (cursor.moveToFirst()){
            do{
                try{
                    pagesObj.put("MODEL_ID",cursor.getInt(cursor.getColumnIndexOrThrow("MODEL_ID")));
                    pagesObj.put("MODEL_TYPE",cursor.getString(cursor.getColumnIndexOrThrow("MODEL_TYPE")));
                    pagesObj.put("DATA_ID",cursor.getInt(cursor.getColumnIndexOrThrow("DATA_ID")));
                    pagesObj.put("POS_X",cursor.getDouble(cursor.getColumnIndexOrThrow("POS_X")));
                    pagesObj.put("POS_Y",cursor.getDouble(cursor.getColumnIndexOrThrow("POS_Y")));
                    pagesObj.put("WIDTH",cursor.getDouble(cursor.getColumnIndexOrThrow("WIDTH")));
                    pagesObj.put("HEIGHT",cursor.getDouble(cursor.getColumnIndexOrThrow("HEIGHT")));
                    pagesObj.put("PREV_AVAILABLE_WIDTH",cursor.getDouble(cursor.getColumnIndexOrThrow("PREV_AVAILABLE_WIDTH")));
                    pagesObj.put("PREV_AVAILABLE_HEIGHT",cursor.getDouble(cursor.getColumnIndexOrThrow("PREV_AVAILABLE_HEIGHT")));
                    pagesObj.put("ROTATION",cursor.getInt(cursor.getColumnIndexOrThrow("ROTATION")));
                    pagesObj.put("MODEL_OPACITY",cursor.getInt(cursor.getColumnIndexOrThrow("MODEL_OPACITY")));
                    pagesObj.put("MODEL_FLIP_HORIZONTAL",cursor.getInt(cursor.getColumnIndexOrThrow("MODEL_FLIP_HORIZONTAL")));
                    pagesObj.put("MODEL_FLIP_VERTICAL",cursor.getInt(cursor.getColumnIndexOrThrow("MODEL_FLIP_VERTICAL")));
                    pagesObj.put("LOCK_STATUS",cursor.getString(cursor.getColumnIndexOrThrow("LOCK_STATUS")));
                    pagesObj.put("PARENT_ID",cursor.getInt(cursor.getColumnIndexOrThrow("PARENT_ID")));
                    pagesObj.put("BG_BLUR_PROGRESS",cursor.getInt(cursor.getColumnIndexOrThrow("BG_BLUR_PROGRESS")));
                    pagesObj.put("OVERLAY_DATA_ID",cursor.getInt(cursor.getColumnIndexOrThrow("OVERLAY_DATA_ID")));
                    pagesObj.put("OVERLAY_OPACITY",cursor.getInt(cursor.getColumnIndexOrThrow("OVERLAY_OPACITY")));
                    pagesObj.put("START_TIME",cursor.getDouble(cursor.getColumnIndexOrThrow("START_TIME")));
                    pagesObj.put("DURATION",cursor.getDouble(cursor.getColumnIndexOrThrow("DURATION")));
                    pagesObj.put("SOFT_DELETE",cursor.getInt(cursor.getColumnIndexOrThrow("SOFT_DELETE")));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        try {
            if (pagesObj.getInt("DATA_ID") != -1) {
                pagesObj.put("IMAGE", ImgObj(pagesObj.getInt("DATA_ID")));
            }
            if (pagesObj.getInt("OVERLAY_DATA_ID" )!= -1) {
                pagesObj.put("OVERLAY", OverlayObj(pagesObj.getInt("OVERLAY_DATA_ID")));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        pages.put(pagesObj);
        return pages;
    }

    private JSONObject OverlayObj(int overlayDataId) {

        JSONObject overLay=new JSONObject();
        String[] args=Integer.toString(overlayDataId).split(",");
        db=help.openDataBase();
        Cursor cursor= db.rawQuery(TemplateData.getOverLay,args);
        if (cursor.moveToFirst()){
            do{
                try{
                    overLay.put("IMAGE_ID",cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE_ID")));
                    overLay.put("IMAGE_TYPE",cursor.getString(cursor.getColumnIndexOrThrow("IMAGE_TYPE")));
                    overLay.put("SERVER_PATH",cursor.getString(cursor.getColumnIndexOrThrow("SERVER_PATH")));
                    overLay.put("LOCAL_PATH",cursor.getString(cursor.getColumnIndexOrThrow("LOCAL_PATH")));
                    overLay.put("RES_ID",cursor.getString(cursor.getColumnIndexOrThrow("RES_ID")));
                    overLay.put("IS_ENCRYPTED",cursor.getInt(cursor.getColumnIndexOrThrow("IS_ENCRYPTED")));
                    overLay.put("CROP_X",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_X")));
                    overLay.put("CROP_Y",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_Y")));
                    overLay.put("CROP_W",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_W")));
                    overLay.put("CROP_H",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_H")));
                    overLay.put("CROP_STYLE",cursor.getInt(cursor.getColumnIndexOrThrow("CROP_STYLE")));
                    overLay.put("TILE_MULTIPLE",cursor.getDouble(cursor.getColumnIndexOrThrow("TILE_MULTIPLE")));
                    overLay.put("COLOR_INFO",cursor.getString(cursor.getColumnIndexOrThrow("COLOR_INFO")));
                    overLay.put("IMAGE_WIDTH",cursor.getDouble(cursor.getColumnIndexOrThrow("IMAGE_WIDTH")));
                    overLay.put("IMAGE_HEIGHT",cursor.getDouble(cursor.getColumnIndexOrThrow("IMAGE_HEIGHT")));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return overLay;
    }

    private JSONObject ImgObj(int dataId) {
        JSONObject imageObj=new JSONObject();
        String[] args=Integer.toString(dataId).split(",");
        db=help.openDataBase();
        Cursor cursor= db.rawQuery(TemplateData.getImage,args);
        if (cursor.moveToFirst()){
            do{
                try{
                    imageObj.put("IMAGE_ID",cursor.getInt(cursor.getColumnIndexOrThrow("IMAGE_ID")));
                    imageObj.put("IMAGE_TYPE",cursor.getString(cursor.getColumnIndexOrThrow("IMAGE_TYPE")));
                    imageObj.put("SERVER_PATH",cursor.getString(cursor.getColumnIndexOrThrow("SERVER_PATH")));
                    imageObj.put("LOCAL_PATH",cursor.getString(cursor.getColumnIndexOrThrow("LOCAL_PATH")));
                    imageObj.put("RES_ID",cursor.getString(cursor.getColumnIndexOrThrow("RES_ID")));
                    imageObj.put("IS_ENCRYPTED",cursor.getInt(cursor.getColumnIndexOrThrow("IS_ENCRYPTED")));
                    imageObj.put("CROP_X",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_X")));
                    imageObj.put("CROP_Y",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_Y")));
                    imageObj.put("CROP_W",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_W")));
                    imageObj.put("CROP_H",cursor.getDouble(cursor.getColumnIndexOrThrow("CROP_H")));
                    imageObj.put("CROP_STYLE",cursor.getInt(cursor.getColumnIndexOrThrow("CROP_STYLE")));
                    imageObj.put("TILE_MULTIPLE",cursor.getDouble(cursor.getColumnIndexOrThrow("TILE_MULTIPLE")));
                    imageObj.put("COLOR_INFO",cursor.getString(cursor.getColumnIndexOrThrow("COLOR_INFO")));
                    imageObj.put("IMAGE_WIDTH",cursor.getDouble(cursor.getColumnIndexOrThrow("IMAGE_WIDTH")));
                    imageObj.put("IMAGE_HEIGHT",cursor.getDouble(cursor.getColumnIndexOrThrow("IMAGE_HEIGHT")));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return imageObj;
    }

    private JSONObject MusicObj(String[] split) {
        JSONObject music=new JSONObject();

        db=help.openDataBase();
        Cursor cursor= db.rawQuery(TemplateData.getMusic,split);
        if (cursor.moveToFirst()){
            do{
                try{
                music.put("MUSIC_ID",cursor.getInt(cursor.getColumnIndexOrThrow("MUSIC_ID")));
                music.put("MUSIC_TYPE",cursor.getString(cursor.getColumnIndexOrThrow("MUSIC_TYPE")));
                music.put("NAME",cursor.getString(cursor.getColumnIndexOrThrow("NAME")));
                music.put("MUSIC_PATH",cursor.getString(cursor.getColumnIndexOrThrow("MUSIC_PATH")));
                music.put("PARENT_ID",cursor.getInt(cursor.getColumnIndexOrThrow(TemplateData.COLUMN_NAME3)));
                music.put("PARENT_TYPE",cursor.getInt(cursor.getColumnIndexOrThrow("PARENT_TYPE")));
                music.put("START_TIME_OF_AUDIO",cursor.getDouble(cursor.getColumnIndexOrThrow("START_TIME_OF_AUDIO")));
                music.put("END_TIME_OF_AUDIO",cursor.getDouble(cursor.getColumnIndexOrThrow("END_TIME_OF_AUDIO")));
                music.put("START_TIME",cursor.getDouble(cursor.getColumnIndexOrThrow("START_TIME")));
                music.put("DURATION",cursor.getDouble(cursor.getColumnIndexOrThrow("DURATION")));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return music;
    }
}

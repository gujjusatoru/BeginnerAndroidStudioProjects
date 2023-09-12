package com.example.json;

public class TemplateData {
    public static final String TABLE_NAME1 ="TEMPLATE";
    public static final String TABLE_NAME2 ="ANIMATION";
    public static final String TABLE_NAME3 ="MUSIC_INFO";
    public static final String COLUMN_NAME1 ="MODEL_ID";
    public static final String COLUMN_NAME2 ="TEMPLATE_ID";
    public static final String COLUMN_NAME3 ="PARENT_ID";



    private int TEMPLATE_ID;
    private String CATEGORY;
    private String TEMPLATE_NAME;
    private int RATIO_ID;
    private String THUMB_SERVER_PATH;
    private String THUMB_LOCAL_PATH;
    private double THUMB_TIME;
    private double TOTAL_DURATION;
    private int SEQUENCE;
    private int IS_PREMIUM;
    private String CATEGORY_TEMP;
    private int IN_ANIMATION_TEMPLATE_ID;
    private int IN_ANIMATION_DURATION;
    private int LOOP_ANIMATION_TEMPLATE_ID;
    private double LOOP_ANIMATION_DURATION;
    private int OUT_ANIMATION_TEMPLATE_ID;
    private double OUT_ANIMATION_DURATION;
    private int MUSIC_ID;
    private String MUSIC_TYPE;
    private String NAME;
    private String MUSIC_PATH;
    private int PARENT_ID;
    private int PARENT_TYPE;
    private double START_TIME_OF_AUDIO;
    private double END_TIME_OF_AUDIO;
    private double START_TIME;
    private double DURATION;
    public TemplateData(){

    }

    public TemplateData(int TEMPLATE_ID, String CATEGORY, String TEMPLATE_NAME, int RATIO_ID, String THUMB_SERVER_PATH, String THUMB_LOCAL_PATH, double THUMB_TIME, double TOTAL_DURATION, int SEQUENCE, int IS_PREMIUM, String CATEGORY_TEMP, int IN_ANIMATION_TEMPLATE_ID, int IN_ANIMATION_DURATION, int LOOP_ANIMATION_TEMPLATE_ID, double LOOP_ANIMATION_DURATION, int OUT_ANIMATION_TEMPLATE_ID, double OUT_ANIMATION_DURATION, int MUSIC_ID, String MUSIC_TYPE, String NAME, String MUSIC_PATH, int PARENT_ID, int PARENT_TYPE, double START_TIME_OF_AUDIO, double END_TIME_OF_AUDIO, double START_TIME, double DURATION) {
        this.TEMPLATE_ID = TEMPLATE_ID;
        this.CATEGORY = CATEGORY;
        this.TEMPLATE_NAME = TEMPLATE_NAME;
        this.RATIO_ID = RATIO_ID;
        this.THUMB_SERVER_PATH = THUMB_SERVER_PATH;
        this.THUMB_LOCAL_PATH = THUMB_LOCAL_PATH;
        this.THUMB_TIME = THUMB_TIME;
        this.TOTAL_DURATION = TOTAL_DURATION;
        this.SEQUENCE = SEQUENCE;
        this.IS_PREMIUM = IS_PREMIUM;
        this.CATEGORY_TEMP = CATEGORY_TEMP;
        this.IN_ANIMATION_TEMPLATE_ID = IN_ANIMATION_TEMPLATE_ID;
        this.IN_ANIMATION_DURATION = IN_ANIMATION_DURATION;
        this.LOOP_ANIMATION_TEMPLATE_ID = LOOP_ANIMATION_TEMPLATE_ID;
        this.LOOP_ANIMATION_DURATION = LOOP_ANIMATION_DURATION;
        this.OUT_ANIMATION_TEMPLATE_ID = OUT_ANIMATION_TEMPLATE_ID;
        this.OUT_ANIMATION_DURATION = OUT_ANIMATION_DURATION;
        this.MUSIC_ID = MUSIC_ID;
        this.MUSIC_TYPE = MUSIC_TYPE;
        this.NAME = NAME;
        this.MUSIC_PATH = MUSIC_PATH;
        this.PARENT_ID = PARENT_ID;
        this.PARENT_TYPE = PARENT_TYPE;
        this.START_TIME_OF_AUDIO = START_TIME_OF_AUDIO;
        this.END_TIME_OF_AUDIO = END_TIME_OF_AUDIO;
        this.START_TIME = START_TIME;
        this.DURATION = DURATION;
    }

    public int getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(int TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getTEMPLATE_NAME() {
        return TEMPLATE_NAME;
    }

    public void setTEMPLATE_NAME(String TEMPLATE_NAME) {
        this.TEMPLATE_NAME = TEMPLATE_NAME;
    }

    public int getRATIO_ID() {
        return RATIO_ID;
    }

    public void setRATIO_ID(int RATIO_ID) {
        this.RATIO_ID = RATIO_ID;
    }

    public String getTHUMB_SERVER_PATH() {
        return THUMB_SERVER_PATH;
    }

    public void setTHUMB_SERVER_PATH(String THUMB_SERVER_PATH) {
        this.THUMB_SERVER_PATH = THUMB_SERVER_PATH;
    }

    public String getTHUMB_LOCAL_PATH() {
        return THUMB_LOCAL_PATH;
    }

    public void setTHUMB_LOCAL_PATH(String THUMB_LOCAL_PATH) {
        this.THUMB_LOCAL_PATH = THUMB_LOCAL_PATH;
    }

    public double getTHUMB_TIME() {
        return THUMB_TIME;
    }

    public void setTHUMB_TIME(double THUMB_TIME) {
        this.THUMB_TIME = THUMB_TIME;
    }

    public double getTOTAL_DURATION() {
        return TOTAL_DURATION;
    }

    public void setTOTAL_DURATION(double TOTAL_DURATION) {
        this.TOTAL_DURATION = TOTAL_DURATION;
    }

    public int getSEQUENCE() {
        return SEQUENCE;
    }

    public void setSEQUENCE(int SEQUENCE) {
        this.SEQUENCE = SEQUENCE;
    }

    public int getIS_PREMIUM() {
        return IS_PREMIUM;
    }

    public void setIS_PREMIUM(int IS_PREMIUM) {
        this.IS_PREMIUM = IS_PREMIUM;
    }

    public String getCATEGORY_TEMP() {
        return CATEGORY_TEMP;
    }

    public void setCATEGORY_TEMP(String CATEGORY_TEMP) {
        this.CATEGORY_TEMP = CATEGORY_TEMP;
    }

    public int getIN_ANIMATION_TEMPLATE_ID() {
        return IN_ANIMATION_TEMPLATE_ID;
    }

    public void setIN_ANIMATION_TEMPLATE_ID(int IN_ANIMATION_TEMPLATE_ID) {
        this.IN_ANIMATION_TEMPLATE_ID = IN_ANIMATION_TEMPLATE_ID;
    }

    public int getIN_ANIMATION_DURATION() {
        return IN_ANIMATION_DURATION;
    }

    public void setIN_ANIMATION_DURATION(int IN_ANIMATION_DURATION) {
        this.IN_ANIMATION_DURATION = IN_ANIMATION_DURATION;
    }

    public int getLOOP_ANIMATION_TEMPLATE_ID() {
        return LOOP_ANIMATION_TEMPLATE_ID;
    }

    public void setLOOP_ANIMATION_TEMPLATE_ID(int LOOP_ANIMATION_TEMPLATE_ID) {
        this.LOOP_ANIMATION_TEMPLATE_ID = LOOP_ANIMATION_TEMPLATE_ID;
    }

    public double getLOOP_ANIMATION_DURATION() {
        return LOOP_ANIMATION_DURATION;
    }

    public void setLOOP_ANIMATION_DURATION(double LOOP_ANIMATION_DURATION) {
        this.LOOP_ANIMATION_DURATION = LOOP_ANIMATION_DURATION;
    }

    public int getOUT_ANIMATION_TEMPLATE_ID() {
        return OUT_ANIMATION_TEMPLATE_ID;
    }

    public void setOUT_ANIMATION_TEMPLATE_ID(int OUT_ANIMATION_TEMPLATE_ID) {
        this.OUT_ANIMATION_TEMPLATE_ID = OUT_ANIMATION_TEMPLATE_ID;
    }

    public double getOUT_ANIMATION_DURATION() {
        return OUT_ANIMATION_DURATION;
    }

    public void setOUT_ANIMATION_DURATION(double OUT_ANIMATION_DURATION) {
        this.OUT_ANIMATION_DURATION = OUT_ANIMATION_DURATION;
    }

    public int getMUSIC_ID() {
        return MUSIC_ID;
    }

    public void setMUSIC_ID(int MUSIC_ID) {
        this.MUSIC_ID = MUSIC_ID;
    }

    public String getMUSIC_TYPE() {
        return MUSIC_TYPE;
    }

    public void setMUSIC_TYPE(String MUSIC_TYPE) {
        this.MUSIC_TYPE = MUSIC_TYPE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getMUSIC_PATH() {
        return MUSIC_PATH;
    }

    public void setMUSIC_PATH(String MUSIC_PATH) {
        this.MUSIC_PATH = MUSIC_PATH;
    }

    public int getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(int PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    public int getPARENT_TYPE() {
        return PARENT_TYPE;
    }

    public void setPARENT_TYPE(int PARENT_TYPE) {
        this.PARENT_TYPE = PARENT_TYPE;
    }

    public double getSTART_TIME_OF_AUDIO() {
        return START_TIME_OF_AUDIO;
    }

    public void setSTART_TIME_OF_AUDIO(double START_TIME_OF_AUDIO) {
        this.START_TIME_OF_AUDIO = START_TIME_OF_AUDIO;
    }

    public double getEND_TIME_OF_AUDIO() {
        return END_TIME_OF_AUDIO;
    }

    public void setEND_TIME_OF_AUDIO(double END_TIME_OF_AUDIO) {
        this.END_TIME_OF_AUDIO = END_TIME_OF_AUDIO;
    }

    public double getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(double START_TIME) {
        this.START_TIME = START_TIME;
    }

    public double getDURATION() {
        return DURATION;
    }

    public void setDURATION(double DURATION) {
        this.DURATION = DURATION;
    }
    public static final String getData="SELECT * FROM "+TABLE_NAME1+" LEFT OUTER JOIN " +TABLE_NAME2+" ON "+TABLE_NAME2+"."+COLUMN_NAME1+ "=" +COLUMN_NAME2+" LEFT OUTER JOIN "+TABLE_NAME3+" ON "+TABLE_NAME3+"."+COLUMN_NAME3+"="+COLUMN_NAME2+" WHERE "+COLUMN_NAME2+"= ?";
    public static final String getMusic="SELECT * FROM "+TABLE_NAME3+" WHERE "+TABLE_NAME3+"."+COLUMN_NAME3+"=? AND "+ TABLE_NAME3+".PARENT_TYPE = 1";
    public static final String getPage ="SELECT * FROM MODEL WHERE "+COLUMN_NAME3+" = ? AND MODEL_TYPE = 'PAGE' AND SOFT_DELETE = 0";
    public static final String getImage="SELECT * FROM IMAGE where IMAGE.IMAGE_ID = ?";
    public static final String getOverLay="SELECT * FROM IMAGE where IMAGE.IMAGE_ID = ?";
    public static final String getPagesData="SELECT * FROM MODEL WHERE PARENT_ID = ? and MODEL_TYPE != 'PAGE' AND SOFT_DELETE = 0";
    public static final String getAnimation="SELECT * FROM ANIMATION WHERE ANIMATION.MODEL_ID = ?";
    public static final String getText="SELECT * FROM TEXT_MODEL WHERE TEXT_MODEL.TEXT_ID = ?";
    public static final String getSticker="SELECT * FROM STICKER_MODEL WHERE STICKER_MODEL.STICKER_ID = ?";
}

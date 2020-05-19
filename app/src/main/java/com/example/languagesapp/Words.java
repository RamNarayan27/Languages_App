package com.example.languagesapp;

public class Words {
    private String defaultTranslation;
    private String miwokTranslation;
    private int imageResourceId=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;
    private int mAudioResourceId;
    public Words(String defalutTrans,String miwokTrans,int audioResourceId)
    {
        defaultTranslation=defalutTrans;
        miwokTranslation=miwokTrans;
        mAudioResourceId=audioResourceId;
    }
    public Words(String defaultTranslation,String miwokTranslation,int imageResourceId,int audioResourceId)
    {
        this.defaultTranslation=defaultTranslation;
        this.miwokTranslation=miwokTranslation;
        this.imageResourceId=imageResourceId;
        mAudioResourceId=audioResourceId;
    }
    public String getDefaultTranslation()
    {
        return defaultTranslation;
    }
    public String getMiwokTranslation()
    {
        return miwokTranslation;
    }
    public int getImageResourceId(){return imageResourceId;}
    public int getmAudioResourceId(){return mAudioResourceId;}
    public boolean hasImage()
    {
        return imageResourceId!=NO_IMAGE_PROVIDED;
    }
}

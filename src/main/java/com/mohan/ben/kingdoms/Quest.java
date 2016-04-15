package com.mohan.ben.kingdoms;

import android.os.Parcel;
import android.os.Parcelable;


//Quest Object that displays all necessary information for a quest page
//Currently generated in-app for testing purposes, Retrofit and Picasso not successfully integrated yet
public class Quest implements Parcelable {
    private int questNo;
    private int img;
    private String info;
    private String giverName;
    private int giverImg;
    private String giverInfo;
    //Constructor, Quests generated in Kingdom.java for testing purposes
    public Quest(int questNo, int img, String info, String giverName, int giverImg, String giverInfo){
        this.questNo = questNo;
        this.img = img;
        this.info = info;
        this.giverName = giverName;
        this.giverImg = giverImg;
        this.giverInfo = giverInfo;
    }
    protected Quest(Parcel in) {
        questNo = in.readInt();
        img = in.readInt();
        info = in.readString();
        giverName = in.readString();
        giverImg = in.readInt();
        giverInfo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questNo);
        dest.writeInt(img);
        dest.writeString(info);
        dest.writeString(giverName);
        dest.writeInt(giverImg);
        dest.writeString(giverInfo);
    }
    public String getName(){
        return "Quest " + questNo;
    }
    public int getGiverImg(){
        return giverImg;
    }
    public int getImg(){
        return img;
    }
    public String getInfo(){
        return info;
    }
    public String getGiverName(){
        return giverName;
    }
    public String getGiverInfo(){
        return giverInfo;
    }
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Quest> CREATOR = new Parcelable.Creator<Quest>() {
        @Override
        public Quest createFromParcel(Parcel in) {
            return new Quest(in);
        }

        @Override
        public Quest[] newArray(int size) {
            return new Quest[size];
        }
    };
}


package com.mohan.ben.kingdoms;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;
//Kingdom object that has all fields needed
//Currently generated on from in-app images and strings, but could be adapted to get information from retrofit
//Retrofit and picasso not integrated yet
public class Kingdom implements Parcelable {
    private String name;
    private String pop;
    private int img;
    private int icon;
    private String climate;
    private ArrayList<Quest> quests;
    //Default constructor, generated for testing purposes
    public Kingdom(String name){
        this.name = name;
        Random r = new Random();
        int x = r.nextInt(25000);
        pop = Integer.toString(x);
        img = R.drawable.camelot;
        icon = R.drawable.king;
        climate = "Moderate";
        quests = new ArrayList<>();
        quests.add(new Quest(1, R.drawable.horse, "Ride to The Holy Grail", "God", R.drawable.god, "The almighty"));
        quests.add(new Quest(2, R.drawable.bridge, "Find the Airspeed Velocity of a Swallow", "The BridgeKeeper", R.drawable.bridgekeeper, "Creepy guy watching a creepy bridge"));
        quests.add(new Quest(3, R.drawable.ni, "Bring a Shrubbery", "The Knight Who Says Ni", R.drawable.knightni, "The Knight Who Says Ni "));



    }
    //Constructor that would integrate with retrofit, allows input of all fields
    public Kingdom(String name, String pop, int img, String climate, ArrayList<Quest> quests, int icon){
        this.name = name;
        this.pop = pop;
        this.img = img;
        this.climate = climate;
        for(int i = 0; i < quests.size(); i++){
            this.quests.set(i, quests.get(i));
        }
        this.icon = icon;
    }

    //Getter methods
    public String getName(){
        return this.name;
    }
    public int getImg(){return this.img;}
    public String getPop(){return this.pop;}
    public String getClimate(){return this.climate;}
    public int getIcon(){return this.icon;}
    public ArrayList<Quest> getQuests(){
        return quests;
    }

    //Parcel constructor to make Kingdom Object Parcelable
    protected Kingdom(Parcel in) {
        name = in.readString();
        pop = in.readString();
        img = in.readInt();
        climate = in.readString();
        if (in.readByte() == 0x01) {
            quests = new ArrayList<Quest>();
            in.readList(quests, Quest.class.getClassLoader());
        } else {
            quests = null;
        }
        icon = in.readInt();

    }

    //Parcelable Method
    @Override
    public int describeContents() {
        return 0;
    }

    //Overriden Parcelable method
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pop);
        dest.writeInt(img);
        dest.writeString(climate);
        if (quests == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(quests);
        }
        dest.writeInt(icon);
    }


    //Parcelable field
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Kingdom> CREATOR = new Parcelable.Creator<Kingdom>() {
        @Override
        public Kingdom createFromParcel(Parcel in) {
            return new Kingdom(in);
        }

        @Override
        public Kingdom[] newArray(int size) {
            return new Kingdom[size];
        }
    };
}



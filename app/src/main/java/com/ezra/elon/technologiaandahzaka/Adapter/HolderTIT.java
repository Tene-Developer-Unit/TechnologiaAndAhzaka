package com.ezra.elon.technologiaandahzaka.Adapter;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by elon on 03/09/2017.
 */

public class HolderTIT implements Parcelable {
    String maslulName;
    String UrlVideo;
    String TextPath;



    public HolderTIT(String maslulName)
    {
        this.maslulName = maslulName;
    }

    public HolderTIT(String maslulName, String UrlVideo, String TextPath)
    {
        this.maslulName = maslulName;
        this.TextPath = TextPath;
        this.UrlVideo = UrlVideo;
    }
    public HolderTIT(String maslulName, String TextPath)
    {
        this.maslulName = maslulName;
        this.TextPath = TextPath;

    }

    protected HolderTIT(Parcel in) {
        maslulName = in.readString();
        UrlVideo = in.readString();
        TextPath = in.readString();
    }

    public static final Creator<HolderTIT> CREATOR = new Creator<HolderTIT>() {
        @Override
        public HolderTIT createFromParcel(Parcel in) {
            return new HolderTIT(in);
        }

        @Override
        public HolderTIT[] newArray(int size) {
            return new HolderTIT[size];
        }
    };

    public String getMaslulName() {
        return maslulName;
    }

    public void setMaslulName(String maslulName) {
        this.maslulName = maslulName;
    }

    public void setUrlVideo(String urlVideo) {
        UrlVideo = urlVideo;
    }

    public void setTextPath(String textPath) {
        TextPath = textPath;
    }

    public String getTextPath() {
        return TextPath;
    }

    public String getUrlVideo() {
        return UrlVideo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(maslulName);
        parcel.writeString(UrlVideo);
        parcel.writeString(TextPath);
    }

}

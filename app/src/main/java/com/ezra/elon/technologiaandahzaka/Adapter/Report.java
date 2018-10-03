package com.ezra.elon.technologiaandahzaka.Adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class Report implements Parcelable {

    private String image;
    private String title;
    private String subtitle;
    private String content_report;
    private long time;

    protected Report(Parcel in) {
        image = in.readString();
        title = in.readString();
        subtitle = in.readString();
        content_report = in.readString();
        time = in.readLong();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent_report() {
        return content_report;
    }

    public Report(String image, String title, String subtitle, String content_report)
    {
        if(image == "")
        {
            this.image = "null";
        }
        else
          this.image = image;


          this.title = title;
          this.subtitle = subtitle;
         this.content_report = content_report;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeString(content_report);
    }
}

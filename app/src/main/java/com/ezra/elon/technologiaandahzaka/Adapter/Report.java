package com.ezra.elon.technologiaandahzaka.Adapter;

public class Report{

    private String image;
    private String title;
    private String subtitle;
    private String content_report;
    private long time;

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
          this.image = image;
          this.title = title;
          this.subtitle = subtitle;
         this.content_report = content_report;

    }
}

package com.ezra.elon.technologiaandahzaka.Adapter;

public class CourseButton
{
    private String image;
    private String title;
    private String path;

    public String getPath() {
        return path;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    CourseButton(String image, String title,String path)
    {
        this.image = image;
        this.path = path;
        this.title = title;
    }
}

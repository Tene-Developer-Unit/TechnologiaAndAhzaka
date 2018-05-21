package com.ezra.elon.technologiaandahzaka.Adapter;

public class CourseButton
{
    private int image;
    private String title;

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    CourseButton(int image, String title)
    {
        this.image = image;
        this.title = title;
    }
}

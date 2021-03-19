package com.example.justfollow;

public class ListViewItem {
    private int iconDrawable;
    private String contentStr;


    public void setIcon(int icon) {
        iconDrawable = icon;
    }

    public  void setContent(String content) {
        contentStr = content;
    }

    public int getIcon(){
        return this.iconDrawable;
    }
    public String getContent(){
        return this.contentStr;
    }
}
package com.example.test2;

public class VideoPicture extends Picture {
    private String pic_string; //视频路径
    public VideoPicture(String pic_name, int pic_id,String pic_string) {
        super(pic_name, pic_id);
        this.pic_string=pic_string;
    }
    String getPic_string(){
        return pic_string;
    }
}

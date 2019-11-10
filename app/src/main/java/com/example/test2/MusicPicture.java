package com.example.test2;
public class MusicPicture extends Picture {
    private int pic_resource; //音乐路径
    public MusicPicture(String pic_name, int pic_id,int pic_resource) {
        super(pic_name, pic_id);
        this.pic_resource=pic_resource;
    }
    public int getPic_resource(){
        return  pic_resource;
    }
}

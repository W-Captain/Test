package com.example.test2;

public class Picture {
    private String pic_name;
    private int pic_id;//图片位置id

    public Picture(String pic_name, int pic_id) {
        this.pic_name = pic_name;
        this.pic_id = pic_id;
    }
    public String getPic_name(){
        return pic_name;
    }
    public int getPic_id(){
        return pic_id;
    }
}

package com.example.test2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private Spinner myspinner;
    private ArrayList<Picture> pictures;
    private ArrayList<MusicPicture>musicPictures;
    private ArrayList<VideoPicture>videoPictures;
    private Button openAlbum;
    PicAdapter picAdapter;
    MusAdapter musAdapter;
    VidAdapter vidAdapter;
    PicAdapter2 picAdapter2;
    MusAdapter2 musAdapter2;
    VidAdapter2 vidAdapter2;
    StaggeredGridLayoutManager gridLayoutManager1;
    StaggeredGridLayoutManager gridLayoutManager2;
    StaggeredGridLayoutManager gridLayoutManager3;
    LinearLayoutManager layoutManager1;
    LinearLayoutManager layoutManager2;
    LinearLayoutManager layoutManager3;
    RecyclerView pic_recycle;
    RecyclerView mus_recycle;
    RecyclerView vid_recycle;
    LinearLayout item_box;
    ImageView pic_img;
    TextView pic_text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.lay2,container,false);
        initPic();
        initMus();
        initVid();

        item_box=(LinearLayout)view.findViewById(R.id.item_box);
        pic_img=(ImageView)view.findViewById(R.id.pic_image);
        pic_text=(TextView)view.findViewById(R.id.pic_text);
        pic_recycle=(RecyclerView)view.findViewById(R.id.tupian_recycle);
        mus_recycle=(RecyclerView)view.findViewById(R.id.yinyue_recycle);
        vid_recycle=(RecyclerView)view.findViewById(R.id.shipin_recycle);

        gridLayoutManager1=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager2=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager3=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        layoutManager1=new LinearLayoutManager(getActivity());
        layoutManager2=new LinearLayoutManager(getActivity());
        layoutManager3=new LinearLayoutManager(getActivity());

        picAdapter=new PicAdapter(pictures);
        musAdapter=new MusAdapter(musicPictures);
        vidAdapter=new VidAdapter(videoPictures);

        picAdapter2=new PicAdapter2(pictures);
        musAdapter2=new MusAdapter2(musicPictures);
        vidAdapter2=new VidAdapter2(videoPictures);

        myspinner=(Spinner)view.findViewById(R.id.spinner);
        openAlbum=(Button)view.findViewById(R.id.open_album);
        setNormalLay();
        openAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select_item=parent.getItemAtPosition(position).toString();
                switch (select_item){
                    case "纵向排列":
                        setNormalLay();
                        break;
                    case "瀑布流":
                        setSpecialLay();
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    private void setSpecialLay() {
        pic_recycle.setLayoutManager(gridLayoutManager1);
        mus_recycle.setLayoutManager(gridLayoutManager2);
        vid_recycle.setLayoutManager(gridLayoutManager3);
        pic_recycle.setAdapter(picAdapter2);
        mus_recycle.setAdapter(musAdapter2);
        vid_recycle.setAdapter(vidAdapter2);
    }

    private void setNormalLay() {
        pic_recycle.setLayoutManager(layoutManager1);
        mus_recycle.setLayoutManager(layoutManager2);
        vid_recycle.setLayoutManager(layoutManager3);
        pic_recycle.setAdapter(picAdapter);
        mus_recycle.setAdapter(musAdapter);
        vid_recycle.setAdapter(vidAdapter);
    }

    private void initVid() {
        videoPictures=new ArrayList<VideoPicture>();
        VideoPicture videoPicture1=new VideoPicture("告白气球-周杰伦.mp4",R.drawable.gaobaiqiqiu,"gaobaiqiqiu");
        videoPictures.add(videoPicture1);
        VideoPicture videoPicture2=new VideoPicture("听爸爸的话-周杰伦.mp4",R.drawable.tingbabadehua,"tingbabadehua");
        videoPictures.add(videoPicture2);
    }

    private void initMus() {
        musicPictures=new ArrayList<MusicPicture>();
        MusicPicture musicPicture1=new MusicPicture("阴天快乐-陈奕迅.mp3",R.drawable.yintiankuaile,R.raw.yintiankuaile);
        musicPictures.add(musicPicture1);
        MusicPicture musicPicture2=new MusicPicture("好久不见-陈奕迅.mp3",R.drawable.haojiubujian,R.raw.haojiubujian);
        musicPictures.add(musicPicture2);
        MusicPicture musicPicture3=new MusicPicture("富士山下-陈奕迅.mp3",R.drawable.fushishanxia,R.raw.fushishanxia);
        musicPictures.add(musicPicture3);
        MusicPicture musicPicture4=new MusicPicture("红玫瑰-陈奕迅.mp3",R.drawable.hongmeigui,R.raw.hongmeigui);
        musicPictures.add(musicPicture4);
    }

    private void initPic() {
        pictures=new ArrayList<Picture>();
        Picture apple=new Picture("apple.png",R.drawable.apple);
        pictures.add(apple);
        Picture pear=new Picture("pear.png",R.drawable.pear);
        pictures.add(pear);
        Picture orange=new Picture("orange.png",R.drawable.orange);
        pictures.add(orange);
        Picture pineapple=new Picture("pineapple.png",R.drawable.pineapple);
        pictures.add(pineapple);
        Picture mango=new Picture("mango.png",R.drawable.mango);
        pictures.add(mango);
        Picture watermelon=new Picture("watermelon.png",R.drawable.watermelon);
        pictures.add(watermelon);
        Picture strawberry=new Picture("strawberry.png",R.drawable.strawberry);
        pictures.add(strawberry);
    }
}

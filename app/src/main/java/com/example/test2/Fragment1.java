package com.example.test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements ViewPager.OnPageChangeListener{
    private ViewPager vp;
    private  int[] imas;
    private LinearLayout ll;
    private ArrayList<ImageView> imaList;
    private Boolean flag;
    private Boolean ad;
    private LinearLayout icon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.lay1,container,false);
        initView(view);
        initAdapter();
        icon=(LinearLayout)view.findViewById(R.id.tu_biao);
        ad=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vp.setCurrentItem(vp.getCurrentItem()+1);
                        }
                    });
                }
            }
        }).start();
        ImageView img_btn=(ImageView)view.findViewById(R.id.gengduo_img);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ad){
                    Toast.makeText(getActivity(),"更多项已展示",Toast.LENGTH_SHORT).show();
                    addIcon();
                    ImageView img_map=(ImageView)view.findViewById(R.id.img_map);
                    img_map.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(),MapActivity.class));
                        }
                    });
                }
                else{
                    Toast.makeText(getActivity(),"更多项已隐藏",Toast.LENGTH_SHORT).show();
                    removeIcon();
                }
            }
        });
        ImageView img_map=(ImageView)view.findViewById(R.id.img_map);
        return view;
    }
    private void removeIcon(){
        for(int i=0;i<icon.getChildCount();i++){
            icon.removeViewAt(i);
            i=-1;
        }
        ad=true;
    }
    private void addIcon(){
        icon.setWeightSum(5);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight=1;
        int img[]=new int[]{R.drawable.yikatong,R.drawable.wenjuan,R.drawable.shiwu,R.drawable.ditu,R.drawable.xiaoli};
        String text[]=new String[]{"一卡通","问卷","失物招领","地图","校历"};
        for(int i=0;i<5;i++){
            IconText iconText=new IconText(getContext());
            iconText.init(img[i],text[i],i);
            icon.addView(iconText,params);
        }
        ad=false;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        flag=false;
    }

    private void initView(View view){
        flag=true;
        vp=(ViewPager)view.findViewById(R.id.lunbo_tu);
        ll=(LinearLayout)view.findViewById(R.id.point_container);
        imas=new int[]{R.drawable.amanecer,R.drawable.blackcat,R.drawable.zhanlang,R.drawable.liulang,R.drawable.gou};
        ImageView iv;
        View pointview;
        imaList=new ArrayList<ImageView>();
        for(int i=0;i<imas.length;i++){
            iv=new ImageView(getActivity());
            iv.setImageResource(imas[i]);
            imaList.add(iv);
            pointview=new View(getActivity());
            pointview.setEnabled(false);
            pointview.setBackgroundResource(R.drawable.circle);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(15,15);
            if(i!=0){
                params.leftMargin=10;
            }
            ll.addView(pointview,params);
        }
    }
    private void initAdapter(){
        ll.getChildAt(0).setEnabled(true);
        vp.setAdapter(new MyAdapter());
        vp.setCurrentItem(5); //从0开始
        vp.addOnPageChangeListener(this);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<ll.getChildCount();i++){
            ll.getChildAt(i).setEnabled(false);
        }
        ll.getChildAt(position%5).setEnabled(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE; //轮播效果就是通过向里面添加很多很多张图片形成的
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int newposition =position%5;
            ImageView imageView=imaList.get(newposition);
            container.addView(imageView);
            return imageView;
        }
    }
}

package com.example.test2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.VideoView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener,ViewPager.OnPageChangeListener {
    private RadioButton radioButton1,radioButton2,radioButton3;
    private List<Fragment> fragments=new ArrayList<Fragment>();
    private FragAdapter fragAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        radioButton1=(RadioButton)findViewById(R.id.r1);
        radioButton2=(RadioButton)findViewById(R.id.r2);
        radioButton3=(RadioButton)findViewById(R.id.r3);
        viewPager=(ViewPager)findViewById(R.id.view_page);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragAdapter=new FragAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragAdapter);
        init();
    }

    public void init(){
        viewPager.setCurrentItem(0);
        resetColor();
        radioButton1.setTextColor(Color.parseColor("#1255DB"));
        Drawable nav_up1=getResources().getDrawable(R.drawable.jinri); //获取图片资源
        nav_up1.setBounds(0, 0, nav_up1.getMinimumWidth(), nav_up1.getMinimumHeight()); //设置绘制区域
        radioButton1.setCompoundDrawables(null, nav_up1,null, null); //将图片设置在按钮上
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r1:
                resetColor();
                radioButton1.setChecked(true);
                radioButton1.setTextColor(Color.parseColor("#1255DB"));
                Drawable nav_up1=getResources().getDrawable(R.drawable.jinri);
                nav_up1.setBounds(0, 0, nav_up1.getMinimumWidth(), nav_up1.getMinimumHeight());
                radioButton1.setCompoundDrawables(null, nav_up1,null, null);
                viewPager.setCurrentItem(0);
                break;
            case R.id.r2:
                resetColor();
                radioButton2.setChecked(true);
                radioButton2.setTextColor(Color.parseColor("#1255DB"));
                Drawable nav_up2=getResources().getDrawable(R.drawable.meiti);
                nav_up2.setBounds(0, 0, nav_up2.getMinimumWidth(), nav_up2.getMinimumHeight());
                radioButton2.setCompoundDrawables(null, nav_up2,null, null);
                viewPager.setCurrentItem(1);
                break;
            case R.id.r3:
                resetColor();
                radioButton3.setChecked(true);
                radioButton3.setTextColor(Color.parseColor("#1255DB"));
                Drawable nav_up3=getResources().getDrawable(R.drawable.shuju);
                nav_up3.setBounds(0, 0, nav_up3.getMinimumWidth(), nav_up3.getMinimumHeight());
                radioButton3.setCompoundDrawables(null, nav_up3,null, null);
                viewPager.setCurrentItem(2);
                break;
                default:
                    break;
        }
    }
    public void resetColor(){
        radioButton1.setTextColor(Color.BLACK);
        radioButton2.setTextColor(Color.BLACK);
        radioButton3.setTextColor(Color.BLACK);
        Drawable nav_up1=getResources().getDrawable(R.drawable.jinri1);
        nav_up1.setBounds(0, 0, nav_up1.getMinimumWidth(), nav_up1.getMinimumHeight());
        radioButton1.setCompoundDrawables(null, nav_up1,null, null);
        Drawable nav_up2=getResources().getDrawable(R.drawable.meiti1);
        nav_up2.setBounds(0, 0, nav_up2.getMinimumWidth(), nav_up2.getMinimumHeight());
        radioButton2.setCompoundDrawables(null, nav_up2,null, null);
        Drawable nav_up3=getResources().getDrawable(R.drawable.shuju1);
        nav_up3.setBounds(0, 0, nav_up3.getMinimumWidth(), nav_up3.getMinimumHeight());
        radioButton3.setCompoundDrawables(null, nav_up3,null, null);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            resetColor();
            radioButton1.setChecked(true);
            radioButton1.setTextColor(Color.parseColor("#1255DB"));
            Drawable nav_up1=getResources().getDrawable(R.drawable.jinri);
            nav_up1.setBounds(0, 0, nav_up1.getMinimumWidth(), nav_up1.getMinimumHeight());
            radioButton1.setCompoundDrawables(null, nav_up1,null, null);
        }
        else if(position==1){
            resetColor();
            radioButton2.setChecked(true);
            radioButton2.setTextColor(Color.parseColor("#1255DB"));
            Drawable nav_up2=getResources().getDrawable(R.drawable.meiti);
            nav_up2.setBounds(0, 0, nav_up2.getMinimumWidth(), nav_up2.getMinimumHeight());
            radioButton2.setCompoundDrawables(null, nav_up2,null, null);
        }
        else if(position==2){
            resetColor();
            radioButton3.setChecked(true);
            radioButton3.setTextColor(Color.parseColor("#1255DB"));
            Drawable nav_up3=getResources().getDrawable(R.drawable.shuju);
            nav_up3.setBounds(0, 0, nav_up3.getMinimumWidth(), nav_up3.getMinimumHeight());
            radioButton3.setCompoundDrawables(null, nav_up3,null, null);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

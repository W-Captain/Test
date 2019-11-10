package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity  {
    private ImageView imageView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_layout);
        getSupportActionBar().hide();
        imageView=(ImageView)this.findViewById(R.id.open_img);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.3f,1.0f);
        alphaAnimation.setDuration(2000);
        imageView.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageView.setBackgroundResource(R.drawable.welcome);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(Main2Activity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

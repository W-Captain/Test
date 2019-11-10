package com.example.test2;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class IconText extends LinearLayout {
    private ImageView imageView;
    private TextView textView;

    public IconText(Context context) {
        super(context);
    }

    public IconText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IconText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(int source, String text, int i){
        this.setOrientation(VERTICAL);
        imageView=new ImageView(getContext());
        LayoutParams iconparams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iconparams.gravity=Gravity.CENTER;
        imageView.setImageResource(source);
        imageView.setLayoutParams(iconparams);
        imageView.setId(source);

        textView=new TextView(getContext());
        LayoutParams textparams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textparams.gravity=Gravity.CENTER;
        textView.setText(text);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(14);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(textparams);

        switch (i){
            case 0:
                imageView.setId(R.id.img_card);
                break;
            case 1:
                imageView.setId(R.id.img_question);
                break;
            case 2:
                imageView.setId(R.id.img_lost);
                break;
            case 3:
                imageView.setId(R.id.img_map);
                break;
            case 4:
                imageView.setId(R.id.img_calender);
                break;
                default:
                    break;
        }

        addView(imageView);
        addView(textView);
    }
}

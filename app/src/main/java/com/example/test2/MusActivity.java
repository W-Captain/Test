package com.example.test2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MusActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Button start;
    private Button pause;
    private Button stop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView textView;
    private Timer timer;
    private String hms="00:00:00";
    private SimpleDateFormat format;
    int img_id;
    int ziyuan_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mus_layout);
        //隐藏标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        Intent intent=getIntent();
        img_id=intent.getIntExtra("tupian",1);
        ziyuan_id=intent.getIntExtra("ziyuan",1);
        imageView=(ImageView)findViewById(R.id.fengmian);
        start=(Button)findViewById(R.id.mus_start);
        pause=(Button)findViewById(R.id.mus_pause);
        stop=(Button)findViewById(R.id.mus_stop);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        imageView.setImageResource(img_id);
        textView=(TextView)findViewById(R.id.mus_text);
        seekBar=(SeekBar)findViewById(R.id.mus_seekbar);
        timer=new Timer();
        format=new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));

        init();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void play(){
        mediaPlayer.start();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                hms=format.format(mediaPlayer.getCurrentPosition());
                textView.setText(hms);
            }
        },0,1000);
    }
    private void init(){
        mediaPlayer=MediaPlayer.create(this,ziyuan_id);
        seekBar.setMax(mediaPlayer.getDuration()); //获取歌曲长度，设置进度条最大值
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mus_start:
                if(!mediaPlayer.isPlaying()){
                    play();
                }
                break;
            case R.id.mus_pause:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    timer.purge();
                }
                break;
            case R.id.mus_stop:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    init();
                }
                break;
            default:
                break;
        }
    }
}

package com.example.test2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class VidActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button start;
    private Button pause;
    private Button replay;
    private SeekBar seekBar;
    private Timer timer;
    private TextView textView;
    private String hms="00:00:00";
    private SimpleDateFormat format;
    String data;
    int duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vid_layout);
        //隐藏标题栏
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        Intent intent=getIntent();
        data=intent.getStringExtra("shipin");
        videoView=(VideoView)findViewById(R.id.video_view);
        start=(Button)findViewById(R.id.vid_start);
        pause=(Button)findViewById(R.id.vid_pause);
        replay=(Button)findViewById(R.id.vid_replay);
        textView=(TextView)findViewById(R.id.vid_text);
        seekBar=(SeekBar)findViewById(R.id.vid_seekbar);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        format=new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        timer=new Timer();
        init();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    videoView.seekTo(seekBar.getProgress());
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

    private void init(){
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw/"+data));
    }
    private void play(){
        videoView.start();
        duration=videoView.getDuration();
        seekBar.setMax(duration);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(videoView.getCurrentPosition());
                hms=format.format(videoView.getCurrentPosition());
                textView.setText(hms);
            }
        },0,1000);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vid_start:
                if(!videoView.isPlaying()){
                    play();
                }
                break;
            case R.id.vid_pause:
                if(videoView.isPlaying()){
                    videoView.pause();
                    timer.purge();
                }
                break;
            case R.id.vid_replay:
                if(videoView.isPlaying()){
                    videoView.resume();
                    seekBar.setMax(duration);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            seekBar.setProgress(videoView.getCurrentPosition());
                            hms=format.format(videoView.getCurrentPosition());
                            textView.setText(hms);
                        }
                    },0,1000);
                }
                break;
                default:break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        videoView.suspend();
    }
}

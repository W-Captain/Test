package com.example.test2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment3 extends Fragment implements View.OnClickListener {
    private Button send,clear;
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.lay3,container,false);
        send=(Button)view.findViewById(R.id.send_request);
        clear=(Button)view.findViewById(R.id.clear_data);
        textView=(TextView)view.findViewById(R.id.receive_data);
        send.setOnClickListener(this);
        clear.setOnClickListener(this);
        return view;
    }

    private void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    private void showResponse(final String response){
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_request:
                sendRequest();
                break;
            case R.id.clear_data:
                Toast.makeText(getActivity(),"Text was cleared",Toast.LENGTH_SHORT).show();
                textView.setText("");
                break;
                default:
                    break;
        }
    }
}

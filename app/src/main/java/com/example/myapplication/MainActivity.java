package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
        TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         tv = findViewById(R.id.textview1);

     //   System.out.println(jsonGetRequest("https://familymart.online/wp-json/wc/v2/orders?consumer_key=ck_627c877c5455417321b31ad87162ac619c969634&consumer_secret=cs_7121081777baf7989bbd38fc4c87d8a02b049663"));
        try {
            getHttpResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getHttpResponse() throws IOException {

        String url = "https://familymart.online/wp-json/wc/v2/orders?consumer_key=ck_627c877c5455417321b31ad87162ac619c969634&consumer_secret=cs_7121081777baf7989bbd38fc4c87d8a02b049663";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

//        Response response = client.newCall(request).execute();
//        Log.e(TAG, response.body().string());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String mMessage = response.body().string();
                  setText(tv,mMessage );
              //  tv.setText(mMessage);
                Log.e("TAG", mMessage);
            }
        });
    }
    private void setText(final TextView text,final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

}
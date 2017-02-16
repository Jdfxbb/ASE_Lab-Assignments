package com.example.joshua.myapplication;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//yandex api trnsl.1.1.20170215T201824Z.0a3a0b4b920cfc41.63bb3008e7031c54c5b19eff0fec413341527288

public class APIActivity extends AppCompatActivity {
    EditText toTranslateView;
    String toTranslate;
    TextView translated;
    Button translateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("here", "here");
        final String apiKey = "trnsl.1.1.20170215T201824Z.0a3a0b4b920cfc41.63bb3008e7031c54c5b19eff0fec413341527288";
        translated = (TextView) findViewById(R.id.results);
        Button translateBtn = (Button) findViewById(R.id.translateBtn);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        translateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                toTranslateView = (EditText) findViewById(R.id.toTranslate);
                String toTranslate = toTranslateView.getText().toString();

                String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?"
                        + "key=" + apiKey
                        + "&text=" + toTranslate
                        + "&lang=en-es&callback=translate";

                OkHttpClient client = new OkHttpClient();
                try{
                    Request request = new Request.Builder().url(getURL).build();
                    client.newCall(request).enqueue(new Callback(){
                        @Override
                        public void onFailure(Call call, IOException e){
                            Log.d("failure", e.getMessage());
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException{
                            final JSONObject jsonResult;
                            final String result = response.body().string();
                            try{
                                jsonResult = new JSONObject(result);
                                JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                                final String convertedText = convertedTextArray.get(0).toString();
                                Log.d("okHttp", jsonResult.toString());
                                runOnUiThread(new Runnable(){
                                    @Override
                                    public void run(){
                                        translated.setText(convertedText);
                                    }

                                });
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }catch (Exception ex) {
                    translated.setText(ex.getMessage());
            }
        }
        });
    }
}
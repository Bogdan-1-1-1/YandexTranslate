package com.example.yandextranslate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        shouldShowRequestPermissionRationale(Manifest.permission.INTERNET);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner fromSpin = findViewById(R.id.from_lang);
        fromSpin.setAdapter(adapter);
        Spinner toSpin = findViewById(R.id.to_lang);
        toSpin.setAdapter(adapter);

        Button translateButton = findViewById(R.id.translate_button);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromLang = ((Spinner) findViewById(R.id.from_lang)).getSelectedItem().toString();
                fromLang = "" + fromLang.charAt(fromLang.length() - 3) + fromLang.charAt(fromLang.length() - 2);
                String toLang = ((Spinner) findViewById(R.id.to_lang)).getSelectedItem().toString();
                toLang = "" + toLang.charAt(toLang.length() - 3) + toLang.charAt(toLang.length() - 2);
                TextView text = findViewById(R.id.from_lang_text);

                if(fromLang != "" && fromLang != null && toLang != "" && toLang != null) {
                    String lang = fromLang + "-" + toLang;
                    Log.d("info", lang +" "+ text.getText().toString());
                    Request req = new Request(text.getText().toString(), lang);

                    TranslateTask task = new TranslateTask(MainActivity.this);
                    task.execute(req);
                }
            }
        });
    }

    public void showTranslation(String text) {
        TextView showVIew = findViewById(R.id.translated_text);
        showVIew.setText(text);
    }
}

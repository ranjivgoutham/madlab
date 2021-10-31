package com.example.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class read extends AppCompatActivity {
    String file;
    String   filedir="newfiledir";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        TextView maintext = findViewById(R.id.textdata);
        TextView title= findViewById(R.id.title);
        Bundle extra =getIntent().getExtras();
        if(extra!=null){
            file=extra.getString("filename");
        }
        FileReader fr=null;
        File exfile = new File(getExternalFilesDir(filedir),file);
        StringBuilder stringBuilder=new StringBuilder();
        try {
            fr=new FileReader(exfile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String line = bufferedReader.readLine();
            while (line!=null){
                stringBuilder.append(line).append("\n");
                line=bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            String fc = "File contents\n"+stringBuilder.toString();
            maintext.setText(fc);
            title.setText("File name : "+file);
        }
    }
}

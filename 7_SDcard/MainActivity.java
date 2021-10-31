package com.example.sdcard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText filename,writings;
    String file,content,filedir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button write = findViewById(R.id.write);
        filename=findViewById(R.id.filename);
        writings=findViewById(R.id.contents);
        file="tempfile.txt";
        filedir="newfiledir";
        if(!check()){
            write.setEnabled(false);
        }
        write.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,rite.class);
//                startActivity(intent);
                file=filename.getText().toString().trim();
                content=writings.getText().toString().trim();
                writings.setText("");
                File exfile = new File(getExternalFilesDir(filedir),file);
                FileOutputStream fos=null;
                try {
                    fos=new FileOutputStream(exfile);
                    fos.write(content.getBytes(StandardCharsets.UTF_8));
                    Toast.makeText(MainActivity.this,"Completed",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        Button read = findViewById(R.id.read);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file=filename.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this,read.class);
                intent.putExtra("filename",file);
                startActivity(intent);
            }
        });
    }

    private boolean check(){
        String external = Environment.getExternalStorageState();
        if(external.equals(Environment.MEDIA_MOUNTED)){
            return  true;
        }
        return false;}
}

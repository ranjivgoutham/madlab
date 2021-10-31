package com.example.graphicalprimitives2;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_fade=findViewById(R.id.bt_fade);
        Button bt_rotate=findViewById(R.id.bt_rotate);
        Button bt_animate=findViewById(R.id.bt_animate);
        Button bt_car=findViewById(R.id.bt_car);
        Button bt_forward=findViewById(R.id.bt_forward);
        Button bt_backward=findViewById(R.id.bt_backward);
        final ImageView iv_animate=findViewById(R.id.iv_animate);
        bt_fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in));
            }
        });
        bt_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_in));
            }
        });
        bt_animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in));
            }
        });
        bt_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap b=Bitmap.createBitmap(720,1280, Bitmap.Config.ARGB_8888);
                iv_animate.setBackgroundDrawable(new BitmapDrawable(b));
                Canvas canvas=new Canvas(b);
                Paint p=new Paint();
                p.setStrokeWidth(10);
                p.setColor(Color.RED);
                RectF r=new RectF(100,100,400,300);
                canvas.drawRect(r,p);
            }
        });
        bt_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(300f).setDuration(600);
            }
        });
        bt_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_animate.animate().translationXBy(-300f).setDuration(600);
            }
        });
    }
}

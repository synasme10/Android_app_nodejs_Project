package com.e.contracterandworkerfinder;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {


    private ProgressBar progressbar;
    private int progressStatus=0;
    private TextView textView;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressbar=findViewById(R.id.progressbar);
        textView=findViewById(R.id.textviewid);
//        Handler handler= new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(SplashScreenActivity.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },2500);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100){
                   progressStatus +=4;
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           progressbar.setProgress(progressStatus);
                           textView.setText(progressStatus+"/ "+progressbar.getMax());
                       }
                   });
                   try {
                       Thread.sleep(100);
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }
                }
                Intent intent=new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();


    }
}

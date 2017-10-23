package vn.nks.sunny.phieucongtac;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageView imglogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imglogo=findViewById(R.id.imglogo);
        TranslateAnimation anim = new TranslateAnimation( 0, 0 ,700 , 0 );
        anim.setDuration(3000);
        anim.setFillAfter( true );
        imglogo.startAnimation(anim);
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(r, 4000);
    }
}

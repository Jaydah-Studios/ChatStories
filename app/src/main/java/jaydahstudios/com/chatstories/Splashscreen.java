package jaydahstudios.com.chatstories;

/**
 * Created by Jaystiqs on 7/18/2017.
 */

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import jaydahstudios.com.chatstories.MainActivity;

import java.util.Calendar;
import java.util.Random;


public class Splashscreen extends Activity {

    Thread splashTread;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int[] ids = new int[]{R.drawable.s1,R.drawable.s2, R.drawable.s3};
        Random randomGenerator = new Random();
        int r= randomGenerator.nextInt(ids.length);
        this.imageView.setImageDrawable(getResources().getDrawable(ids[r]));

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splashscreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        TextView jsCopy = (TextView) findViewById(R.id.jsCopyright);
        jsCopy.setText("Jaydah Studios. Copyright © "+ year +". \n All Rights Reserved");
    }

}
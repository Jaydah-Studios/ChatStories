package jaydahstudios.com.chatstories;

//import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jaystiqs on 7/18/2017.
 */

public class PromoOptionsActivity extends AppCompatActivity {

    private static final String TAG = "PromoOptionsActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promooptions);

        Button shareBtn, watchAdBtn;
//        ImageView exit;

        Log.d(TAG, "onCreate:  Started");

        watchAdBtn = (Button) findViewById(R.id.watchAdBtnPromo);
        shareBtn = (Button) findViewById(R.id.shareBtnPromo);

        watchAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PromoOptionsActivity.this, "No ads to display!", Toast.LENGTH_SHORT).show();;
            }
        });

//        exit = (ImageView) findViewById(R.id.exitImgPromo) ;
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PromoOptionsActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });


        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Epic Chat Stories");
                    String sAux = "\nTry this app. It has the coolest stories\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

    }

}

package jaydahstudios.com.chatstories;

//import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jaystiqs on 7/18/2017.
 */

public class PromoOptionsActivity extends AppCompatActivity {

    private static final String TAG = "PromoOptionsActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promooptions);

//        Button back;
        Log.d(TAG, "onCreate:  Started");

//        back = (Button) findViewById(R.id.optionsBkBtn);
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PromoOptionsActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }

}

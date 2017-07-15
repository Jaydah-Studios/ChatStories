package jaydahstudios.com.chatstories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jaydahstudios.com.chatstories.adapter.CustomAdapter;
import jaydahstudios.com.chatstories.database.DatabaseHelper;
import jaydahstudios.com.chatstories.model.ChatModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private int count = 1; //Count for query with respect to row retreival
    private CustomAdapter adapter;
    private DatabaseHelper mDBHelper;
    private List<ChatModel> lstChat = new ArrayList<>();
    private List<ChatModel> newChat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);

        //Checks if DB exists
        File database =  getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(!database.exists()){
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)){
                Toast.makeText(this, "All ready. Press Next to start!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Copy error", Toast.LENGTH_LONG).show();
            }
        }

        // Initialize shared preferences
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        checkSharedPreferences();
        
        if(count != 1 ){
            int sPCount = count;
            for( int i= 1; i<sPCount; i++){
                count = i;
                getProgress(null);
            }
            count++;
        }
    }

    private boolean copyDatabase(Context context){
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0){
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied successfully");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private ChatModel setUpMessage(){
        Log.d(TAG, "setUpMessage: Exec");
        return mDBHelper.getListChat(count);

    }

    public void nextClicked(View view){
        Log.d(TAG, "nextClicked: Is Clicked");

        ChatModel chat = setUpMessage();
        lstChat.add(chat);

        final ListView lstView = (ListView)findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(lstChat,this);
        lstView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Log.i(TAG, "Counter is: "+count);

        count++;

        lstView.post(new Runnable(){
            public void run() {
                lstView.setSelection(lstView.getCount() - 1);
            }});
    }
    
    public void getProgress(View view){
        Log.d(TAG, "getProgress: Executed");

        ChatModel chat = setUpMessage();
        lstChat.add(chat);

        final ListView lstView = (ListView)findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(lstChat,this);
        lstView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lstView.post(new Runnable(){
            public void run() {
                lstView.setSelection(lstView.getCount() - 1);
            }});
    }

    private void checkSharedPreferences(){
        //Check if user has read to a point and has been saved from previous
//        int counter = mPreferences.getInt("CounterSP", 1);
        int counter = mPreferences.getInt(getString(R.string.story_progress_count), 1);

        if(counter != 1){
            count = counter;
            Log.i(TAG, "PCounter is: "+counter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

//        mEditor.putInt("CounterSP", count);
        mEditor.putInt(getString(R.string.story_progress_count), count);
        mEditor.commit();
    }
}

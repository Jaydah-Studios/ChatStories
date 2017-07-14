package jaydahstudios.com.chatstories;

import android.content.Context;
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

//    private ListView lvProduct;
//    private List<ChatModel> lstChat;

    private CustomAdapter adapter;
    private DatabaseHelper mDBHelper;
    private List<ChatModel> lstChat = new ArrayList<>();

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        lvProduct = (ListView) findViewById(R.id.listview_product);
//        mDBHelper = new DatabaseHelper(this);
//
//        //Checks if DB exists
//        File database =  getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
//        if(!database.exists()){
//            mDBHelper.getReadableDatabase();
//            //Copy db
//            if(copyDatabase(this)){
//                Toast.makeText(this, "Database copy success", Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(this, "Copy error", Toast.LENGTH_LONG).show();
//            }
//        }

////        Get product list in db when db exists
//        chatModelList = mDBHelper.getListProduct();
////        Init adapter
//        adapter = new ListProductAdapter(this, chatModelList);
////        set adapter for listview
//        lvProduct.setAdapter(adapter);
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
            Log.w("MainActivity", "DB copied");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void setUpMessage(){
        Log.d(TAG, "setUpMessage: Exec");
        mDBHelper = new DatabaseHelper(this);

        //Checks if DB exists
        File database =  getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(!database.exists()){
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)){
                Toast.makeText(this, "Database copy success", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Copy error", Toast.LENGTH_LONG).show();
            }
        }

        lstChat = mDBHelper.getListChat();
//        Get product list in db when db exists
//        lstChat.add(new ChatModel("Eric: Hello!", true));

    }

    public void nextClicked(View view){

        Log.d(TAG, "nextClicked: Is Clicked");
        setUpMessage();

        final ListView lstView = (ListView)findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(lstChat,this);
        lstView.setAdapter(adapter);

        lstView.post(new Runnable(){
            public void run() {
                lstView.setSelection(lstView.getCount() - 1);
            }});
    }
}

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
import java.util.Iterator;
import java.util.List;

import jaydahstudios.com.chatstories.adapter.CustomAdapter;
import jaydahstudios.com.chatstories.database.DatabaseHelper;
import jaydahstudios.com.chatstories.model.ChatModel;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

//    private ListView lvProduct;
//    private List<ChatModel> lstChat;
    private int count = 1;

    private CustomAdapter adapter;
    private DatabaseHelper mDBHelper;
    private List<ChatModel> lstChat = new ArrayList<>();
    private List<ChatModel> newChat = new ArrayList<>();

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        lvProduct = (ListView) findViewById(R.id.listview_product);
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


        lstChat = mDBHelper.getListChat(count);
    }

    public void nextClicked(View view){

        Log.d(TAG, "nextClicked: Is Clicked");
        setUpMessage();

//        for(ChatModel chat : lstChat)
//        {
//            newChat.add(chat);
//            break;
//        }
//
//        lstChat.remove(count);
//        count++;

//        for(ChatModel chat : lstChat) { newChat.add(chat); break; }
//        Iterator<ChatModel> it = lstChat.iterator();
//        while(it.hasNext()){
//            newChat.add(it.next());
//            it.remove();
//            break;
//        }

//        Log.i(TAG, "nextClicked: "+lstChat.size());
//        lstChat.size();
//        System.out.println(lstChat.size());

        final ListView lstView = (ListView)findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(lstChat,this);
        lstView.setAdapter(adapter);

        count++;
        adapter.notifyDataSetChanged();

        lstView.post(new Runnable(){
            public void run() {
                lstView.setSelection(lstView.getCount() - 1);
            }});
    }
}

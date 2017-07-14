package jaydahstudios.com.chatstories.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import jaydahstudios.com.chatstories.model.ChatModel;

/**
 * Created by Jaystiqs on 7/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DBNAME = "storydb.sqlite";
    public static final String DBLOCATION = "/data/data/jaydahstudios.com.chatstories/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public void closeDatabase(){
        if(mDatabase != null){
            mDatabase.close();
        }
    }

    public ChatModel getListChat(int id) {

        ChatModel chat = null;
//        List<ChatModel> chatModel = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT b.id, a.actorName, b.dialogueContent ,a.actorPlacement FROM actors a LEFT JOIN dialogue b ON a.id = b.actorId WHERE b.id ="+id+" ORDER BY b.id", null);

        cursor.moveToFirst();
//        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            System.out.println(cursor.getInt(3));

            chat = new ChatModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),  cursor.getInt(3));
            //        lstChat.add(new ChatModel("id","actor","message","true for left | false for right"));
//            chatModel.add(chat);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return chat;
//        return chatModel;
    }

    public ChatModel getChat(int id){
        ChatModel chat = null;
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT b.id, a.actorName, b.dialogueContent ,a.actorPlacement FROM actors a LEFT JOIN dialogue b ON a.id = b.actorId WHERE b.id = 2 ORDER BY b.id ASC", null);

        while(!cursor.moveToNext()){
            chat = new ChatModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        }
        cursor.close();
        closeDatabase();

        return chat;
    }

    }





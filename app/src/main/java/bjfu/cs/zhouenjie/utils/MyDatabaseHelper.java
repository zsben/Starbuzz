package bjfu.cs.zhouenjie.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import bjfu.cs.zhouenjie.home.fragment.HomeFragment;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    public static final String CREATE_COFFEE = "create table Coffee(\n" +
            "        id integer primary key,\n" +
            "        price real,\n" +
            "        name text,\n" +
            "        descri text\n" +
            ")";
    public static final String CREATE_FOOD = "create table food(\n" +
            "        id integer primary key,\n" +
            "        price real,\n" +
            "        name text,\n" +
            "        descri text\n" +
            ")";
    public static final String CREATE_GOOD = "create table good(\n" +
            "        id integer primary key,\n" +
            "        price real,\n" +
            "        name text,\n" +
            "        descri text\n" +
            ")";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COFFEE);
        db.execSQL(CREATE_FOOD);
        db.execSQL(CREATE_GOOD);
        Toast.makeText(mContext, "Create success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Coffee");
        onCreate(db);
    }
}

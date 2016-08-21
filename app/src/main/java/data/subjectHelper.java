package data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by OLAYIWOLA on 26/07/2016.
 */
public class subjectHelper extends SQLiteOpenHelper{

    //table is defined
    public static final String TABLE_NAME = "subjects";
    //columns are defined
    public static final String _SubjectID = "_ID";
    public static final String SubjectName = "Name";
    static final String DB_NAME = "tnotes.db";

    //database version
    static final int DB_VERSION = 1;

    //create table query
    private static final String Create_table = "create table " + TABLE_NAME + "(" + _SubjectID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SubjectName + "TEXT NOT NULL);";
    //constructor
    subjectHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}

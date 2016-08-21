package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by OLAYIWOLA on 03/08/2016.
 */
public class subjectController {
    private subjectHelper sHelper;
    private Context subContext;
    private SQLiteDatabase db;

    public subjectController(Context c){
        subContext = c;
    }
    public subjectController open() throws SQLException{
        sHelper = new subjectHelper(subContext);
        db = sHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        sHelper.close();
    }
    public void insert(String title){
        //contentValues act like a map
        ContentValues contentValues = new ContentValues();
        contentValues.put(subjectHelper.SubjectName,title);
        db.insert(subjectHelper.TABLE_NAME, null, contentValues);
    }
    public Cursor fetch(){
        String[]columns  = new String[] {subjectHelper._SubjectID, subjectHelper.SubjectName};
        Cursor cursor = db.query(subjectHelper.TABLE_NAME, columns, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(int id, String title){
        ContentValues contentValues = new ContentValues();
        contentValues.put(subjectHelper.SubjectName,title);
        int i = db.update(subjectHelper.TABLE_NAME, contentValues, subjectHelper._SubjectID + " = " + id, null);
        return i;
    }
    public void delete(int id) {
        db.delete(subjectHelper.TABLE_NAME, subjectHelper._SubjectID + " = " + id, null);
    }

}

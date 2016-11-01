package com.example.mnrr.whattodolist;

/**
 * Created by megha on 01-11-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskManager";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESC= "description";
    private static final String TABLE_TASKS = "tasks";



    public TaskDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Calling for creating database for all the movies..
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks(title TEXT PRIMARY KEY,description TEXT )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);
    }

    //Calling for deleting Tasks table
    public void onReset(SQLiteDatabase db) {
        db.execSQL("DELETE FROM tasks");
    }

    //Calling for adding tasks and their description
    public int addTask(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT title FROM tasks WHERE title = \"" + task.getTitle() + "\"", null);
        if (!cursor.moveToFirst()) {
            System.out.println("Inside else if");
            values.put(KEY_TITLE, task.getTitle());
            values.put(KEY_DESC, task.getDetails());
            result = 2;
            db.insert(TABLE_TASKS, null, values);
        } else{
            System.out.println("Task already exists");
            String strSQL = "UPDATE  tasks SET description = \"" + task.getDetails() + "\" WHERE title =  \"" + task.getTitle() + "\"";
            result = DATABASE_VERSION;
            db.execSQL(strSQL);
        }
        db.close();
        return result;
    }

    //Calling for retreiving task titles from table Tasks...
    public List<String> getAllTasksTitles() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT  * FROM tasks", null);
        //String taskList = BuildConfig.FLAVOR;
        List<String> tasksList = new ArrayList<>();
       // tasksList = BuildConfig.FLAVOR;
        if (cursor.moveToFirst()) {
            do {
                tasksList.add(cursor.getString(0));
                //taskList = taskList + cursor.getString(0) + "\n";
            } while (cursor.moveToNext());
        }
        return tasksList;
    }

//    //Calling for retreiving all data from table Movies...
//    public String getMoviesDetails() {
//        Cursor cursor = getReadableDatabase().rawQuery("SELECT  * FROM movies WHERE status = \"Watched\" ORDER BY category", null);
//        String movieList = BuildConfig.FLAVOR;
//        if (cursor.moveToFirst()) {
//            do {
//                movieList = movieList + cursor.getString(0) + "----" + cursor.getString(2) + "\n";
//            } while (cursor.moveToNext());
//        }
//        return movieList;
//    }
//
//    public void movieDelete(String movie)
//    {
//        getWritableDatabase().execSQL("Delete from movies where name =\"" + movie+"\"");
//
//    }
}


package com.kmema.android.projectdagger.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

import com.kmema.android.projectdagger.Annotation.ApplicationContext;
import com.kmema.android.projectdagger.Annotation.DatabaseInfo;
import com.kmema.android.projectdagger.Model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by kmema on 8/9/2017.
 */

/*@Singleton ensure a single instance of a class globally.
So, there will be only one DbHelper class instance for the app and whenever a class asks for DbHelper as a dependency,
it will be provided with the same instance that is maintained in the Dagger’s dependency graph.*/

@Singleton
public class DbHelper extends SQLiteOpenHelper{

    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "usr_name";
    public static final String USER_COLUMN_USER_ADDRESS = "usr_add";
    public static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    public static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";



/*   @Inject on the constructor instructs the Dagger to accumulate all the parameter dependencies when the class is being constructed.*/
/*@ApplicationContext Qualifier facilitates DbHelper to get the context object of the application from dagger’s dependency graph*/
/*@DatabaseInfo qualifier helps the dagger to distinguish between String and Integer Dependencies from existing same types in the dependency graph.*/
    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dBname,
                    @DatabaseInfo Integer version){
        super(context,dBname,null,version);
    }


    private void createTableStatement(SQLiteDatabase sqLiteDatabase)
    {
        try {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(Long userId)
    {
        Cursor cursor = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            cursor = sqLiteDatabase.rawQuery(
                    "SELECT * FROM " + USER_TABLE_NAME
                                    + " WHERE "
                                    + USER_COLUMN_USER_ID
                                    + " = ? ",
                                    new String[]{userId+""}
            );

            if(cursor.getCount() > 0 && cursor.moveToFirst())
            {
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)));
                return user;
            }
            else {
                throw new Resources.NotFoundException("User with id " + userId + " does not exists");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally {
            if(cursor != null)
            {
                cursor.close();
            }
        }
    }

    protected Long insertUser(User user) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, user.getName());
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.getAddress());
            return db.insert(USER_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getCurrentTimeStamp()
    {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableStatement(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE_NAME);
        onCreate(db);
    }
}

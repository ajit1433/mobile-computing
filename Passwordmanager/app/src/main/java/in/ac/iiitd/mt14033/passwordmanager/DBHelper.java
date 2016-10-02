package in.ac.iiitd.mt14033.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarvisx on 10/2/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "PasswordManager.sqlitedb";

    // Contacts table name
    private static final String TABLE_PASSWORDS = "passwords";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERID = "userid";
    private static final String KEY_URL = "url";
    private static final String KEY_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PASSWORDS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERID + " TEXT," + KEY_URL + " TEXT," + KEY_PASSWORD + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORDS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new password
    void addPassword(PasswordManager pm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERID, pm.getUserId());
        values.put(KEY_URL, pm.getUrl());
        values.put(KEY_PASSWORD, pm.getPassword());

        // Inserting Row
        db.insert(TABLE_PASSWORDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single password
    PasswordManager getPassword(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PASSWORDS, new String[] { KEY_ID,
                        KEY_USERID, KEY_URL, KEY_PASSWORD }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PasswordManager pm = new PasswordManager(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return password
        return pm;
    }

    // Getting All passwords
    public List<PasswordManager> getAllContacts() {
        List<PasswordManager> pmList = new ArrayList<PasswordManager>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PASSWORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PasswordManager pm = new PasswordManager();
                pm.setID(Integer.parseInt(cursor.getString(0)));
                pm.setUserId(cursor.getString(1));
                pm.setUrl(cursor.getString(2));
                pm.setPassword(cursor.getString(3));
                // Adding password to list
                pmList.add(pm);
            } while (cursor.moveToNext());
        }

        // return password list
        return pmList;
    }

    // Updating single password
    public int updateContact(PasswordManager pm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERID, pm.getUserId());
        values.put(KEY_URL, pm.getUrl());
        values.put(KEY_PASSWORD, pm.getUrl());

        // updating row
        return db.update(TABLE_PASSWORDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(pm.getID()) });
    }

    // Deleting single password
    public void deletePassword(PasswordManager pm) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PASSWORDS, KEY_ID + " = ?",
                new String[] { String.valueOf(pm.getID()) });
        db.close();
    }


    // Getting password Count
    public int getPasswordsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PASSWORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}

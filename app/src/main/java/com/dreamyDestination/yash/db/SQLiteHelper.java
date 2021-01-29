package com.dreamyDestination.yash.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "boxcourier.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * @param context
     * @desc initialize application sqlite database
     */
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @return
     * @desc get database writable object
     */
    public SQLiteDatabase getWritableDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db;
    }

    /**
     * @return
     * @desc get database readable object
     */
    public SQLiteDatabase getReadableDB() {
        return this.getReadableDatabase();
    }

    /**
     * @param db - application database object
     * @desc method which creates tables when database initialized
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERMASTER);
    }

    /**
     * @param db         - application database object
     * @param oldVersion - previous version of database
     * @param newVersion - newer version of database
     * @desc on database upgrade we recreates tables drop existing
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USERMASTER);
        onCreate(db);
    }

    /**
     * @param db         - application database object
     * @param oldVersion - previous version of database
     * @param newVersion - newer version of database
     * @desc on database downgrade we recreates tables drop existing
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USERMASTER);
        onCreate(db);
    }

    public static final String TABLE_USERMASTER = "UserMaster";
    public static final String USERMASTER_ID = "_id";
    public static final String USERMASTER_UserId = "UserId";
    public static final String USERMASTER_UserType = "UserType";
    public static final String USERMASTER_Email = "Email";
    public static final String USERMASTER_FullName = "FullName";
    public static final String USERMASTER_MobileNumber = "MobileNumber";

    private static final String CREATE_USERMASTER = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USERMASTER + "(" + USERMASTER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERMASTER_UserId
            + " text," + USERMASTER_UserType
            + " text," + USERMASTER_Email
            + " text," + USERMASTER_FullName
            + " text," + USERMASTER_MobileNumber
            + " text);";

    private static final String DROP_USERMASTER = "DROP TABLE IF EXISTS " + TABLE_USERMASTER;

}



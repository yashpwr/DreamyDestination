package com.dreamyDestination.yash.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dreamyDestination.yash.entity.ELUserInfo;


public class SQLiteFetcher {

    private String TAG = SQLiteFetcher.class.getSimpleName();

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public SQLiteFetcher(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    private String[] UserColumns = {SQLiteHelper.USERMASTER_ID, SQLiteHelper.USERMASTER_UserId, SQLiteHelper.USERMASTER_UserType,
            SQLiteHelper.USERMASTER_Email, SQLiteHelper.USERMASTER_FullName, SQLiteHelper.USERMASTER_MobileNumber};

    /**
     * @param TableName - name of table which content should be truncated
     * @desc truncate data of table which name pass as argument
     */
    public void truncateTable(String TableName) {
        database = dbHelper.getWritableDB();
        database.delete(TableName, null, null);
        database.close();
    }

    /**
     * @desc deletes all data related to current logged in user
     */
    public void deleteUserInfo() {
        database = dbHelper.getWritableDB();
        database.delete(SQLiteHelper.TABLE_USERMASTER, null, null);
        database.close();
    }

    /**
     * @param user - user object which need to insert in database
     * @return
     * @desc creates new user in usermaster table when user logged in application
     */
    public long InsertUser(ELUserInfo user) {

        ContentValues values = new ContentValues();

        //values.put(SQLiteHelper.USERMASTER_UserId, user.getUserId());
        values.put(SQLiteHelper.USERMASTER_UserType, user.getUser_unique_id());
        values.put(SQLiteHelper.USERMASTER_Email, user.getEmail());
        values.put(SQLiteHelper.USERMASTER_FullName, user.getFullName());

        database = dbHelper.getWritableDB();

        long Id = database.insert(SQLiteHelper.TABLE_USERMASTER, null, values);

        database.close();

        return Id;
    }

//    public long InsertUser(StudentProfileInfo user) {
//
//        ContentValues values = new ContentValues();
//
//        //values.put(SQLiteHelper.USERMASTER_UserId, user.getUserId());
//        values.put(SQLiteHelper.USERMASTER_UserType, user.getUser_unique_id());
//        values.put(SQLiteHelper.USERMASTER_Email, user.getUser_email_id());
//        values.put(SQLiteHelper.USERMASTER_FullName, user.getFull_name());
//
//        database = dbHelper.getWritableDB();
//
//        long Id = database.insert(SQLiteHelper.TABLE_USERMASTER, null, values);
//
//        database.close();
//
//        return Id;
//    }

    /**
     * @param user - user object which need to update in database
     * @return
     * @desc updates logged in user data in database
     */


//    public long InsertDoctor(ELDoctorInfo doctor) {
//
//        ContentValues values = new ContentValues();
//
//        //values.put(SQLiteHelper.USERMASTER_UserId, user.getUserId());
//        values.put(SQLiteHelper.USERMASTER_UserType, doctor.getUserType());
//        values.put(SQLiteHelper.USERMASTER_FullName, doctor.getDoctorFullname());
//
//        database = dbHelper.getWritableDB();
//
//        long Id = database.insert(SQLiteHelper.TABLE_USERMASTER, null, values);
//
//        database.close();
//
//        return Id;
//    }


    public long UpdateUser(ELUserInfo user) {

        long result;

        ContentValues values = new ContentValues();

        //values.put(SQLiteHelper.USERMASTER_UserId, user.getUserId());
        values.put(SQLiteHelper.USERMASTER_UserType, user.getUser_unique_id());
        values.put(SQLiteHelper.USERMASTER_Email, user.getEmail());
        values.put(SQLiteHelper.USERMASTER_FullName, user.getFullName());

        database = dbHelper.getWritableDB();

        result = database.update(SQLiteHelper.TABLE_USERMASTER, values, null, null);

        database.close();

        return result;

    }

    /**
     * @param cursor - database cursor object
     * @return
     * @desc converts database cursor to user object
     */
    private ELUserInfo cursorToUser(Cursor cursor) {

        ELUserInfo user = new ELUserInfo();

        int iUserId = cursor.getColumnIndex(SQLiteHelper.USERMASTER_UserId);
        int iUserType = cursor.getColumnIndex(SQLiteHelper.USERMASTER_UserType);
        int iEmail = cursor.getColumnIndex(SQLiteHelper.USERMASTER_Email);
        int iFullName = cursor.getColumnIndex(SQLiteHelper.USERMASTER_FullName);
        int iMobileNumber = cursor.getColumnIndex(SQLiteHelper.USERMASTER_MobileNumber);

        //user.setUserId(cursor.getString(iUserId));
        user.setUser_unique_id(cursor.getString(iUserType));
        user.setEmail(cursor.getString(iEmail));
        user.setFullName(cursor.getString(iFullName));

        return user;
    }

    /**
     * @return
     * @desc check whether user is present in database
     */
    public Boolean isUserPresent() {

        Boolean userPresent = false;

        database = dbHelper.getWritableDB();
        Cursor cursor = database.query(SQLiteHelper.TABLE_USERMASTER, UserColumns, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                userPresent = true;
            }
        }

        cursor.close();
        database.close();

        return userPresent;
    }

    /**
     * @return
     * @desc get user details from database
     */
    public ELUserInfo getUserInfo() {

        ELUserInfo user = null;

        database = dbHelper.getWritableDB();
        Cursor cursor = database.query(SQLiteHelper.TABLE_USERMASTER, UserColumns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                user = cursorToUser(cursor);

                cursor.moveToNext();
            }
        }

        cursor.close();
        database.close();

        return user;
    }

    /**
     * @param TableName - name of table from database
     * @return
     * @desc returns number of rows from table which pass as parameter
     */
    public int getTableCount(String TableName) {

        int count = 0;

        database = dbHelper.getWritableDB();
        String query = "select count(*) as CNT from " + TableName;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int CNT = cursor.getColumnIndex("CNT");
            count = cursor.getInt(CNT);
            cursor.close();
        }

        database.close();

        return count;
    }

    public void truncateDB() {
        truncateTable(SQLiteHelper.TABLE_USERMASTER);
    }

}

package assignment.coding.com.networkdataviewer.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.data.model.RecordsModel;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "networkInfo";

    private static final String TABLE_NETWORK = "network";

    private static final String KEY_QUARTER = "quarter";

    private static final String KEY_VOLUME_OF_DATA = "volumeofdata";

    private static final String KEY_ID = "id";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NETWORK + "("
                + KEY_QUARTER + " TEXT PRIMARY KEY," + KEY_VOLUME_OF_DATA + " DOUBLE,"
                + KEY_ID + " INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK);
        // Creating tables again
        onCreate(db);
    }

    public void addRecordsModel(RecordsModel recordsModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VOLUME_OF_DATA, recordsModel.getVolumeOfMobileData());
        values.put(KEY_QUARTER, recordsModel.getQuarter());
        values.put(KEY_ID, recordsModel.getId());

        // Inserting Row
        db.insert(TABLE_NETWORK, null, values);
        db.close(); // Closing database connection
    }

    public RecordsModel getRecordsModel(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NETWORK, new String[]{KEY_QUARTER,
                        KEY_VOLUME_OF_DATA, KEY_QUARTER}, KEY_QUARTER + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            return new RecordsModel(cursor.getString(0),
                    cursor.getDouble(1), cursor.getInt(2));
        }
        return null;
    }

    public ArrayList<RecordsModel> getAllRecords() {
        ArrayList<RecordsModel> recordsModelArrayList = new ArrayList<RecordsModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NETWORK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecordsModel recordsModel = new RecordsModel();
                recordsModel.setQuarter(cursor.getString(0));
                recordsModel.setVolumeOfMobileData(cursor.getDouble(1));
                recordsModel.setId(cursor.getInt(2));
                recordsModelArrayList.add(recordsModel);
            } while (cursor.moveToNext());
        }

        return recordsModelArrayList;
    }

    public int getRecordsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NETWORK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }


    public int updateRecord(RecordsModel recordsModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUARTER, recordsModel.getQuarter());
        values.put(KEY_VOLUME_OF_DATA, recordsModel.getVolumeOfMobileData());
        values.put(KEY_ID, recordsModel.getId());

        // updating row
        return db.update(TABLE_NETWORK, values, KEY_QUARTER + " = ?",
                new String[]{String.valueOf(recordsModel.getId())});
    }

    public void deleteRecord(RecordsModel recordsModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NETWORK, KEY_QUARTER + " = ?",
                new String[]{String.valueOf(recordsModel.getId())});
        db.close();
    }
}
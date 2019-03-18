package assignment.coding.com.networkdataviewer.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import assignment.coding.com.networkdataviewer.data.model.RecordsModel;

/**
 * This class is responsible for storing network data to local storage for caching.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    /**
     * Database version.
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Database name.
     */
    private static final String DATABASE_NAME = "networkInfo";
    /**
     * Table name in sqlite.
     */
    private static final String TABLE_NETWORK = "network";
    /**
     * Quarter data.
     */
    private static final String KEY_QUARTER = "quarter";
    /**
     * Quarterly absorbed data.
     */
    private static final String KEY_VOLUME_OF_DATA = "volumeofdata";
    /**
     * ID for quarter.
     */
    private static final String KEY_ID = "id";
    /**
     * Total mobile data consumed in a year.
     */
    private static final String KEY_TOTAL_VOLUME = "totalvolume";
    /**
     * Flag which shows decrement in mobile data in a year.
     */
    private static final String KEY_DECREMENT_FLAG = "decrementflag";
    /**
     * Year.
     */
    private static final String KEY_YEAR = "year";

    /**
     * Constructor.
     *
     * @param context context.
     */
    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NETWORK + "("
                + KEY_QUARTER + " TEXT,"
                + KEY_VOLUME_OF_DATA + " DOUBLE,"
                + KEY_ID + " INT,"
                + KEY_TOTAL_VOLUME + " DOUBLE,"
                + KEY_DECREMENT_FLAG + " TEXT,"
                + KEY_YEAR + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK);
        // Creating tables again
        onCreate(db);
    }

    /**
     * Add {@link RecordsModel} to database.
     *
     * @param recordsModel model used for UI.
     */
    public void addRecordsModel(RecordsModel recordsModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUARTER, recordsModel.getQuarter());
        values.put(KEY_VOLUME_OF_DATA, recordsModel.getVolumeOfMobileData());
        values.put(KEY_ID, recordsModel.getId());
        values.put(KEY_TOTAL_VOLUME, recordsModel.getTotalVolumeOfMobileData());
        values.put(KEY_DECREMENT_FLAG, recordsModel.getIsDecreaseInVolume());
        values.put(KEY_YEAR, recordsModel.getYear());

        // Inserting Row
        db.insert(TABLE_NETWORK, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Get all the records from database.
     *
     * @return list of records.
     */
    public ArrayList<RecordsModel> getAllRecords() {
        ArrayList<RecordsModel> recordsModelArrayList = new ArrayList<>();
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
                recordsModel.setTotalVolumeOfMobileData(cursor.getDouble(3));
                recordsModel.setIsDecreaseInVolume(cursor.getString(4));
                recordsModel.setYear(cursor.getString(5));
                recordsModelArrayList.add(recordsModel);
            } while (cursor.moveToNext());
        }

        return recordsModelArrayList;
    }

    /**
     * Get count of total records.
     *
     * @return total count of records.
     */
    public int getRecordsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NETWORK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    /**
     * Update record.
     *
     * @param recordsModel {@link RecordsModel}.
     * @return updated data in db.
     */
    public int updateRecord(RecordsModel recordsModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUARTER, recordsModel.getQuarter());
        values.put(KEY_VOLUME_OF_DATA, recordsModel.getVolumeOfMobileData());
        values.put(KEY_ID, recordsModel.getId());
        values.put(KEY_TOTAL_VOLUME, recordsModel.getTotalVolumeOfMobileData());
        values.put(KEY_DECREMENT_FLAG, recordsModel.getIsDecreaseInVolume());
        values.put(KEY_YEAR, recordsModel.getYear());

        // updating row
        return db.update(TABLE_NETWORK, values, KEY_QUARTER + " = ?",
                new String[]{String.valueOf(recordsModel.getId())});
    }

    /**
     * Delete record from database.
     *
     * @param recordsModel {@link RecordsModel}.
     */
    public void deleteRecord(RecordsModel recordsModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NETWORK, KEY_QUARTER + " = ?",
                new String[]{String.valueOf(recordsModel.getId())});
        db.close();
    }
}

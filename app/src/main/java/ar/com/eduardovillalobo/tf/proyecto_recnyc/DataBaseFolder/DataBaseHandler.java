package ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 27/04/2015.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "proyectofinal";
    private static final String TABLE_DEPTOS = "Departamento";
    private static final String KEY_ID = "i";
    private static final String KEY_NAME = "name";
    Context mContext=null;

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* String CREATE_DEPTOS = "CREATE TABLE " + TABLE_DEPTOS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_DEPTOS);*/
        try {
            SQLiteDBDeploy.deploy(db, this.mContext, "proyectofinal.zip");

            // if you like to download file from remote site comment above line and uncomment below line.
            //SQLiteDBDeploy.deploy(sqlLiteDb,"http://ingenious-camel.googlecode.com/svn/trunk/SQLiteDBDeployer/assets/northwind.zip");
        } catch (IOException e) {
            Log.e("Poryectofinal", e.getMessage(), e);
            throw new Error(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_DEPTOS);

        onCreate(db);
    }

    /**
     * Operaciones CRUD (Create, Read, Update, Delete)
     */

    public void addDepto(DeptoInfo deptoInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, deptoInfo.getName());

        db.insert(TABLE_DEPTOS, null, values);
        db.close();
    }

    public DeptoInfo getDepto(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DEPTOS, new String[]{KEY_ID, KEY_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        DeptoInfo deptoInfo = new DeptoInfo(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return deptoInfo;
    }

    public List<DeptoInfo> getAllDeptos() {
        List<DeptoInfo> deptoInfoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_DEPTOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DeptoInfo deptoInfo = new DeptoInfo();
                deptoInfo.setId(Integer.parseInt(cursor.getString(0)));
                deptoInfo.setName(cursor.getString(1));
                deptoInfo.setImageID(cursor.getString(2));
                deptoInfoList.add(deptoInfo);
            } while (cursor.moveToNext());
        }
        System.out.println("En la base" + deptoInfoList.size());
        return deptoInfoList;
    }
}

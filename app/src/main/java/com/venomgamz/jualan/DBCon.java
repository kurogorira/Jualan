package com.venomgamz.jualan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by VENOM on 12/28/2017.
 */

public class DBCon extends SQLiteOpenHelper {
    static final private String Db_NAME = "Ayamlunak";
    static final private String ID = "_id";
    static final private int Db_VER = 6;

    ////deklarasi nama tabel
    static final private String TB_MENU = "Menu";//tabel menu
    static final private String CREATE_TB_MENU = "create table " + TB_MENU + "(_id integer primary key autoincrement,nama text,harga integer);";
    static final private String TB_PEMBELIAN= "Pesanan";//tabel pembelian
    static final private String CREATE_TB_PEMBELIAN = "create table " + TB_PEMBELIAN+ "(_id integer primary key autoincrement,nama text,total integer);";//tabel penyajian
    static final private String TB_BARANG= "Penjualan";
    static final private String CREATE_TB_BARANG = "create table " + TB_BARANG+ "(_id integer primary key autoincrement,nama_barang text,harga integer,jumlah integer,total integer);";
    Context mycontext;
    SQLiteDatabase myDb;

    public DBCon(Context context) {
        super(context, Db_NAME, null, Db_VER);
        mycontext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_MENU);
        db.execSQL(CREATE_TB_PEMBELIAN);
        db.execSQL(CREATE_TB_BARANG);
        Log.i("Database", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TB_MENU);
        db.execSQL("drop table if exists " + TB_PEMBELIAN);
        db.execSQL("drop table if exists " + TB_BARANG);
        onCreate(db);
    }

    public void insertDataMenu(String p1, int p2) {
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + TB_MENU + " (nama,harga) values('" + p1 + "','" + p2 + "');");
        Toast.makeText(mycontext, "Data Saved", Toast.LENGTH_LONG).show();
    }

    public Cursor readAllMenu() {
        myDb = getWritableDatabase();
        String[] columns = new String[]{"_id", "nama", "harga"};
        Cursor c = myDb.query(TB_MENU, columns, null, null, null, null, ID + " asc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectedMenu(long id) {
        myDb=getWritableDatabase();
        String[] columns = new String[]{"_id","nama", "harga"};
        Cursor c = myDb.query(TB_MENU, columns, ID + "=" + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    public void deleteMenu(long id) {
        myDb=getWritableDatabase();
        myDb.delete(TB_MENU, ID + "=" + id, null);
        myDb.close();
    }

    public void update(long id, String p1,int p2) {
        myDb=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", p1);
        values.put("harga",p2);
        myDb.update(TB_MENU, values, ID + "=" + id, null);
        close();
    }

    public void insertDataPesanan(String p1, int p2, int p3, int p4) {
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + TB_BARANG+ " (nama_barang,harga,jumlah,total) values('" + p1 + "','" + p2 + "','" + p3 + "','" + p4 + "');");
        Toast.makeText(mycontext, "Data Saved", Toast.LENGTH_LONG).show();
    }

    public Cursor readAllPesanan() {
        myDb = getWritableDatabase();
        String[] columns = new String[]{"_id", "nama_barang", "harga", "jumlah", "total"};
        Cursor c = myDb.query(TB_BARANG, columns, null, null, null, null, ID + " asc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}

package com.example.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;
import com.example.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
  public DatabaseHandler(Context context) {
    super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACT_TABLE = "CREATE  TABLE " + Util.TABLE_NAME + "("
        + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT, "
        + Util.KEY_NUMBER + " TEXT" + ")";

    db.execSQL(CREATE_CONTACT_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String DROP_TABLE = String.valueOf(R.string.db_drop);
    db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

    onCreate(db);
  }

  public void addContact(Contact contact){
    SQLiteDatabase db =this.getWritableDatabase();
    ContentValues values = new ContentValues();

    values.put(Util.KEY_NAME, contact.getName());
    values.put(Util.KEY_NUMBER,contact.getPhoneNumber());

    db.insert(Util.TABLE_NAME,null,values);
    Log.d("db", "addContact:  " + " Item Added");
    db.close();
  }

  public Contact getContact(int id){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(Util.TABLE_NAME,
        new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_NUMBER},
        Util.KEY_ID +"=?",new String[]{String.valueOf(id)},
        null,null,null);
    if (cursor != null){
      cursor.moveToFirst();
    }
    Contact contact = new Contact();
    contact.setId(Integer.parseInt(cursor.getString(0)));
    contact.setName(cursor.getString(1));
    contact.setPhoneNumber(cursor.getString(2));
    return contact;
  }

  public List<Contact> getAllContact(){
    List<Contact> contactList = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();

    String selectAll="SELECT * FROM " + Util.TABLE_NAME;
    Cursor cursor = db.rawQuery(selectAll,null);

    if (cursor.moveToFirst()){
      do {
        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        contactList.add(contact);
      }while (cursor.moveToNext());
    }
    return contactList;
  }

  public int updateContact(Contact contact){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(Util.KEY_NAME, contact.getName());
    values.put(Util.KEY_NUMBER, contact.getPhoneNumber());
    return db.update(Util.TABLE_NAME,values,Util.KEY_ID + "=?",
        new String[]{String.valueOf(contact.getId())});
  }

  public void deleteContact(Contact contact){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(Util.TABLE_NAME,Util.KEY_ID + "=?",
        new String[]{String.valueOf(contact.getId())});
    db.close();
  }

  public int getCount(){
    String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery,null);
    return cursor.getCount();
  }

}

package com.example.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contactmanager.data.entity.Contact;

import java.util.ArrayList;

public class DataBaseHelp extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =2;
    public static final String DATABASE_NAME ="contacts_db";

    public DataBaseHelp(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contact.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Contact.TABLE_NAME);
        onCreate(db);
    }

    //Insert data in db
    public long insertContact(String name, String email,String number){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Contact.COLUMN_NAME,name);
        values.put(Contact.COLUMN_EMAIL,email);
        values.put(Contact.COLUMN_NUMBER,number);
        long id=db.insert(Contact.TABLE_NAME,null,values);
        db.close();
        return id;
    }

    //Getting data from db
    public Contact getData(long id){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.query(Contact.TABLE_NAME,new String[]{
                Contact.COLUMN_ID,
                Contact.COLUMN_NAME,

                Contact.COLUMN_EMAIL,
                Contact.COLUMN_NUMBER
        },Contact.COLUMN_ID +"=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor!=null)
            cursor.moveToFirst();
        Contact contact=new Contact(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NUMBER)),
                cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)));
        cursor.close();
        return contact;
    }

    //getting all data
    public ArrayList<Contact> getAllData(){
        ArrayList<Contact> contactsList= new ArrayList<>();

        String selectQuery=" SELECT * FROM "+Contact.TABLE_NAME+" ORDER BY "+Contact.COLUMN_ID+" DESC";
        SQLiteDatabase db= getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                Contact contact=new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)));
                contact.setNumber(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NUMBER)));

                contactsList.add(contact);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return contactsList;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Contact.COLUMN_NAME,contact.getName());
        values.put(Contact.COLUMN_EMAIL,contact.getEmail());
        values.put(Contact.COLUMN_NUMBER,contact.getNumber());
        return db.update(Contact.TABLE_NAME,values,Contact.COLUMN_ID+" =? ",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Contact.TABLE_NAME,Contact.COLUMN_ID+" =? ",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }
}

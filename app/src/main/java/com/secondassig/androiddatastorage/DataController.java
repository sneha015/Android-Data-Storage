package com.secondassig.androiddatastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataController
{
	public static final String MESSAGE="Message";
	public static String BOOK_NAME="Name";
	public static String BOOK_AUTHOR="Author";
	public static String DESCRIPTION="Description";
	public static final String TABLE_NAME="B_Table";
	public static final String DATABASE_NAME="Assignment2.db";
	public static final int DATABASE_VERSION=7;
	//public String TABLE_CREATE="";
	public static String BOOK_ID ="Id";
	private static final String[] COLUMNS = {BOOK_ID,BOOK_NAME,BOOK_AUTHOR,DESCRIPTION};
	DataBaseHelper dbHelper;
	Context context;
	SQLiteDatabase db;

	public DataController(Context context)
	{
		this.context=context;
		dbHelper=new DataBaseHelper(context);
	}
	/*public DataController(String BOOK_NAME, String BOOK_AUTHOR, String DESCRIPTION)
	{
		this.BOOK_NAME=BOOK_NAME;
		this.BOOK_AUTHOR=BOOK_AUTHOR;
		this.DESCRIPTION=DESCRIPTION;
		dbHelper=new DataBaseHelper(context);
	}*/



	public DataController open()
	{
		db=dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		dbHelper.close();
	}

	public long insert(String Name, String Author, String Description )
	{
		ContentValues content=new ContentValues();
		content.put(BOOK_NAME, Name);
		content.put(BOOK_AUTHOR, Author);
		content.put(DESCRIPTION, Description);
		return db.insertOrThrow(TABLE_NAME, null, content);
	}
	
	public Cursor retrieve()
	{
		return db.query(TABLE_NAME, COLUMNS, BOOK_ID, null, null, null, null);

	}
	
	private static class DataBaseHelper extends SQLiteOpenHelper
	{

		public DataBaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			String TABLE_CREATE = "";
		/*TABLE_CREATE = "CREATE TABLE "+ TABLE_NAME +"( " +
					ID +"INTEGER PRIMARY KEY AUTOINCREMENT, " +
					BOOK_NAME+"TEXT, "+
					BOOK_AUTHOR+"TEXT, "+ DESCRIPTION+"TEXT )";*/
			TABLE_CREATE = "CREATE TABLE B_Table"+"( " +
					"Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"Name TEXT, "+ "Author TEXT, "+ "Description TEXT )";
			try
			{
				db.execSQL(TABLE_CREATE);
			}
			catch(SQLiteException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS B_Table");
			onCreate(db);
		}
		
	}
	
}
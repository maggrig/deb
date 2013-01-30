/**
 * 
 */
package mag.grig.deb;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author grigoriy
 *
 */
class DB {

	  private final Context mCtx;

	  private DBHelper mDBHelper;
	  private SQLiteDatabase mDB;

	  public DB(Context ctx) {
	    mCtx = ctx;
	  }

	  // открываем подключение
	  public SQLiteDatabase open() {
	    mDBHelper = new DBHelper(mCtx);
	    mDB = mDBHelper.getWritableDatabase();
	    return mDB;
	  }

	  // закрываем подключение
	  public void close() {
	    if (mDBHelper != null)
	      mDBHelper.close();
	  }

	  // данные по играм
	  public Cursor getGameData() {
	     return mDB.query("main_info", null, null, null, null, null, null);
	  }

	  // данные по партиям
	  public Cursor getPartyData(long dataID) {
		   return mDB.query("mytable", null, "_id_data = "
			        + dataID, null, null, null, null);
	  }
	
private class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context) {
		super(context, "myDB", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	String query="create table main_info (_id integer primary key, _count integer,  _date text,  _time text," +
	" _Name1 text," +
	" _Name2 text," +
	" _Name3 text," +
	" _Name4 text)"; 

		try {
			db.execSQL("create table mytable (_id integer primary key ,_id_data  integer, _sum1 text, _sum2 text, _sum3 text,_bide NUMERIC);");// _bide 1-байт, 2 - без взяток, 3 и байт и без взяток			
			db.execSQL(query);
			}
		catch(SQLException e) {  } 			
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS + mytable); ");
	    	    onCreate(db);
	}
}
}
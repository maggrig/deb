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
	  private static final String DB_NAME = "mydb";
	  private static final int DB_VERSION = 1;

	  // им€ таблицы компаний, пол€ и запрос создани€ 
	  private static final String MAIN_TABLE = "main_info";
	  public static final String MAIN_COLUMN_ID = "_id";
//	  public static final String MAIN_COLUMN_NAME1 = "name1";
//	  public static final String MAIN_COLUMN_NAME2 = "name2";
//	  public static final String MAIN_COLUMN_NAME3 = "name3";
//	  public static final String MAIN_COLUMN_NAME4 = "name4";
//	  public static final String MAIN_COLUMN_DATE = "date";
//	  public static final String MAIN_COLUMN_TIME = "time";
//	  public static final String MAIN_COLUMN_COUNT = "count";
	  
	  // им€ таблицы телефонов, пол€ и запрос создани€
	  private static final String DATA_TABLE = "mytable";
	  public static final String DATA_COLUMN_ID = "_id_data";
//	  public static final String DATA_COLUMN_NAME = "name";
//	  public static final String DATA_COLUMN_COMPANY = "company";
	  
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
	     Cursor my= mDB.query(MAIN_TABLE, null, null, null, null, null, null);
	     return my ;
	  }

	  // данные по парти€м
	  public Cursor getPartyData(long dataID) {
		  Cursor my= mDB.query(DATA_TABLE, null, "_id_data = "
			        + dataID, null, null, null, null);
	    return my;
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
			db.execSQL("create table mytable (_id integer primary key ,_id_data  integer, _sum1 text, _sum2 text, _sum3 text,_bide NUMERIC);");// _bide 1-байт, 2 - без вз€ток, 3 и байт и без вз€ток			
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
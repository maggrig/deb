/**
 * 
 */
package mag.grig.deb;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author grigoriy
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context) {
		super(context, "myDB", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	String query="create table main_info ( id integer primary key, _count integer,  _data text,  _time text," +
	" _Name1 text," +
	" _Name2 text," +
	" _Name3 text," +
	" _Name4 text)"; 
		db.execSQL("create table mytable (id integer primary key ,_id_data  integer, _sum1 text, _sum2 text, _sum3 text,_bide NUMERIC);");// _bide 1-байт, 2 - без взяток, 3 и байт и без взяток
		try {
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
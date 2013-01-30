package mag.grig.deb;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Tabform extends Activity implements OnClickListener {

	TextView textView3, textView2, textView1, textView4, textView5, textView6,
			textView7;
	EditText editText1, editText2, editText3, editText4;

	DB mydb;
	Button btnext1;
	int count;
	int mHour, mMinute, mDate, mYear, mMonth;
	Calendar cal = Calendar.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabform);
		// инициализаци€
		btnext1 = (Button) findViewById(R.id.btnext1);
		btnext1.setOnClickListener(this);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView5 = (TextView) findViewById(R.id.textView5);
		textView6 = (TextView) findViewById(R.id.textView6);

		mydb = new DB(this);
		Intent intent = getIntent();
		count = intent.getIntExtra("count", 0);
		// кол-во игроков
		if (count == 2) {
			textView3.setVisibility(View.INVISIBLE);
			textView4.setVisibility(View.INVISIBLE);
			editText3.setVisibility(View.INVISIBLE);
			editText4.setVisibility(View.INVISIBLE);
			textView5.setVisibility(View.INVISIBLE);
			textView6.setVisibility(View.INVISIBLE);
		} else if (count == 3) {
			textView4.setVisibility(View.INVISIBLE);
			editText4.setVisibility(View.INVISIBLE);
			textView5.setVisibility(View.INVISIBLE);
			textView6.setVisibility(View.INVISIBLE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * ѕолучение имЄн, сохрание в таблицу main_info и перадача их в Archiv
	 */
	@Override
	public void onClick(View v) {

		ContentValues cv = new ContentValues();
		SQLiteDatabase db = mydb.open();
		// db.execSQL("DROP TABLE IF EXISTS mytable;");
		// db.execSQL("DROP TABLE IF EXISTS main_info;");
		String name1 = editText1.getText().toString();
		String name2 = editText2.getText().toString();
		String name3 = editText3.getText().toString();
		String name4 = editText4.getText().toString();
		// ¬ызываем активити дл€ записи партии
		Intent intent1 = new Intent(this, Dyn_data.class);

		if (count == 2) {
			cv.put("_Name1", name1);
			cv.put("_Name2", name2);
			cv.put("_count", count);
			intent1.putExtra("name1", name1);
			intent1.putExtra("name2", name2);
			intent1.putExtra("count", count);

		} else if (count == 3) {
			cv.put("_Name1", name1);
			cv.put("_Name2", name2);
			cv.put("_Name3", name3);
			cv.put("_count", count);
			intent1.putExtra("name1", name1);
			intent1.putExtra("name2", name2);
			intent1.putExtra("name3", name3);
			intent1.putExtra("count", count);

		} else if (count == 4) {
			cv.put("_Name1", name1);
			cv.put("_Name2", name2);
			cv.put("_Name3", name3);
			cv.put("_Name4", name4);
			cv.put("_count", count);
			intent1.putExtra("count", count);
			intent1.putExtra("name1", name1);
			intent1.putExtra("name2", name2);
			intent1.putExtra("name3", name3);
			intent1.putExtra("name4", name4);

		}
		mHour = cal.get(Calendar.HOUR_OF_DAY);
		mMinute = cal.get(Calendar.MINUTE);
		mDate = cal.get(Calendar.DATE);
		mMonth = cal.get(Calendar.MONTH);
		mYear = cal.get(Calendar.YEAR);
		cv.put("_date", mDate + "/" + (mMonth + 1) + "/" + mYear);
		cv.put("_time", mHour + ":" + mMinute);
		// вставл€ем запись и получаем ее ID
		long rowID = db.insert("main_info", null, cv);
		intent1.putExtra("rowid", rowID);
		// Cursor c = db.query("main_info", null, null, null, null, null, null);
		// c.moveToLast();
		// textView7.setText(c.getString(0));
		// c.close();
		mydb.close();
		startActivity(intent1);
	}

}
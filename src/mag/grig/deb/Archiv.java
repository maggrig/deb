package mag.grig.deb;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Archiv extends Activity implements OnClickListener {

	TextView textView1;
	TextView textView2;
	TextView textView3, textView4, textView5, textView6, textView7;
	EditText editText1, editText2, editText3;
	Button button1;
	int count;
	long row_id;
	DBHelper dbHelper = new DBHelper(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archiv);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		
		if (count == 2) {
		} else if (count == 3) {
		} else if (count == 4) {
		}
	}

	@Override
	public void onClick(View v) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor c = db.query("main_info", null, null, null, null, null, null);
		if (c != null) {
			if (c.moveToFirst()) {
				String str;
				do {
					str = "";
//					setText(c.getString(0));

				} while (c.moveToNext());
			}
			c.close();
		} else

			dbHelper.close();
	}

}

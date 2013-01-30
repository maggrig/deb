
package mag.grig.deb;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



/**
 * @author grigoriy
 * 
 */
public class Dyn_data extends Activity implements OnClickListener {
	int count;
	long row_id;
	DB mydb = new DB(this);
	TextView name1, name2, name3, name4, sum1, sum2, sum3;
	EditText res1, res2, res3;
	TableRow nameLabels, resLabels, sumLabes, btnLabels, rowTitle;
	ScrollView scrol;
	TableLayout table_head,table_data;
	DigitalClock clock;
	TableRow.LayoutParams param;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		scrol = new ScrollView(this);
		table_head = new TableLayout(this); // С€Р°РїРєР°
//		table_data = new TableLayout(this);// РґР°РЅРЅС‹Рµ РёРіСЂС‹
		 LayoutInflater ltInflater = getLayoutInflater();
        View view = ltInflater.inflate(R.layout.head, null, false);
//        LayoutParams lp = view.getLayoutParams();
		clock = new DigitalClock(this);
		nameLabels = new TableRow(this); // Р�РјРµРЅР° РёРіСЂРѕРєРѕРІ
		resLabels = new TableRow(this);// СЂРµР·СѓР»СЊС‚Р°С‚
		sumLabes = new TableRow(this);// РёС‚РѕРіРѕ
		btnLabels = new TableRow(this);//РљРЅРѕРїРєРё
		rowTitle = new TableRow(this); // С‡Р°СЃС‹
		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		nameLabels.setGravity(Gravity.CENTER_HORIZONTAL);
		resLabels.setGravity(Gravity.CENTER_HORIZONTAL);
		sumLabes.setGravity(Gravity.CENTER_HORIZONTAL);
		btnLabels.setGravity(Gravity.CENTER_HORIZONTAL);
		param = new TableRow.LayoutParams();
		
		param.setMargins(0, 0, 0, 1);
		// TableRow.LayoutParams params = new TableRow.LayoutParams();
		// params.span = 3;
		table_head.setStretchAllColumns(true);
		table_head.setShrinkAllColumns(true);
		scrol.addView(table_head);
		// --------------------------------------
		name1 = new TextView(this);
		name2 = new TextView(this);
		name3 = new TextView(this);
		name4 = new TextView(this);
		sum1 = new TextView(this);
		sum2 = new TextView(this);
		sum3 = new TextView(this);
		res1 = new EditText(this);
		res2 = new EditText(this);
		res3 = new EditText(this);
		res1.setInputType(0x00001002);
		res2.setInputType(0x00001002);
		res3.setInputType(0x00001002);
		Button button1 = new Button(this);
		Button button2 = new Button(this);
		button1.setId(1);
		button2.setId(2);
		// ----------------------------------------------------

		button1.setText("Р—Р°РїРёСЃР°С‚СЊ");
		button2.setText("РћР±РЅРѕРІРёС‚СЊ");

		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		rowTitle.addView(clock);

		btnLabels.addView(button1);
		btnLabels.addView(button2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);

		Intent intent = getIntent();
		count = intent.getIntExtra("count", 0);
		row_id = intent.getLongExtra("rowid", 0);

		if (count == 2) {
			name1.setText(intent.getStringExtra("name1"));
			name2.setText(intent.getStringExtra("name2"));
			nameLabels.addView(name1);
			nameLabels.addView(name2);
			resLabels.addView(res1);
			resLabels.addView(res2);
			sumLabes.addView(sum1);
			sumLabes.addView(sum2);

		} else if (count == 3) {
			name1.setText(intent.getStringExtra("name1"));
			name2.setText(intent.getStringExtra("name2"));
			name3.setText(intent.getStringExtra("name3"));
			nameLabels.addView(name1);
			nameLabels.addView(name2);
			nameLabels.addView(name3);
			resLabels.addView(res1);
			resLabels.addView(res2);
			resLabels.addView(res3);
			sumLabes.addView(sum1);
			sumLabes.addView(sum2);
			sumLabes.addView(sum3);
		} else if (count == 4) {
			name1.setText(intent.getStringExtra("name1") + ":"
					+ intent.getStringExtra("name2"));
			name2.setText(intent.getStringExtra("name3") + ":"
					+ intent.getStringExtra("name4"));
			nameLabels.addView(name1);
			nameLabels.addView(name2);
			resLabels.addView(res1);
			resLabels.addView(res2);
			sumLabes.addView(sum1);
			sumLabes.addView(sum2);
		}

		table_head.addView(rowTitle);
		table_head.addView(nameLabels);
		table_head.addView(resLabels);
		table_head.addView(sumLabes);
		table_head.addView(btnLabels);
		onUpdate();
		setContentView(scrol);

	}

	/*
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

		ContentValues cv = new ContentValues();
		SQLiteDatabase db = mydb.open();


		switch (v.getId()) {
		case 1: {
			cv.put("_id_data", row_id);
			cv.put("_sum1", res1.getText().toString());
			cv.put("_sum2", res2.getText().toString());
			cv.put("_sum3", res3.getText().toString());
			// cv.put("_bide",);
			long rowID = db.insert("mytable", null, cv);
			Toast.makeText(this, "Записал", Toast.LENGTH_SHORT).show();
		}
			break;
		case 2: {
			onUpdate();
			Toast.makeText(this, "Обновил", Toast.LENGTH_SHORT).show();
		}
			break;
		}
		db.close();
	}

	public void onUpdate() {
//		tr=null;
//		table.removeViews(0, 5);
		SQLiteDatabase db = mydb.open();
		Cursor c = db.query("mytable", null, "_id_data="+row_id , null,
				null, null, null);
		if (c != null) {
			int current = 0;
			if (c.moveToFirst()) {
				do {
					TableRow tr = new TableRow(this);
					tr.setId(100 + current++);
					tr.setLayoutParams(new LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
					TextView num_result = new TextView(this);
					num_result.setId(300 + current);
					num_result.setText("пїЅ" + current + "-");
					num_result.setWidth(5);
					TextView result2 = new TextView(this);
					result2.setLayoutParams(param);
					num_result.setLayoutParams(param);
					result2.setId(200 + current);
					result2.setText(c.getString(2));
					tr.addView(num_result);
					tr.addView(result2);
					TextView result3 = new TextView(this);
					result3.setId(current);
					result3.setText(c.getString(3));
					if (current % 2 == 0) {
						result2.setTextColor(Color.RED);
						result3.setTextColor(Color.RED);
						num_result.setTextColor(Color.RED);
					} else {
						result2.setTextColor(Color.BLACK);
						result3.setTextColor(Color.BLACK);
						num_result.setTextColor(Color.BLACK);
					}
					tr.addView(result3);
					table_head.addView(tr);
				} while (c.moveToNext());
			}
			c.close();
		} else
			mydb.close();

	}

}

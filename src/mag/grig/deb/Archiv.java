package mag.grig.deb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;



public class Archiv extends Activity implements OnClickListener {

	 ExpandableListView elvMain;
	  DB mydb;
	  


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.archiv);
	    // ������������ � ��
	    mydb = new DB(this);
	    mydb.open();

	    // ������� ������ �� ������� ��� ��������
	    Cursor cursor = mydb.getGameData();
	    startManagingCursor(cursor);
	    // ������������� ������ � View ��� �����
//	    String[] groupFrom = { "_id","_Name1","_Name2","_Name3","_Name4" };
	    String[] groupFrom = { "_id","_Name1","_Name2","_date","_time" };	    
	    int[] groupTo =  { R.id.grText,R.id.grText1,R.id.grText2,R.id.grText3};
	    // ������������� ������ � View ��� ���������
	    String[] childFrom = { "_id_data","_sum1","_sum2","_sum3" };
	    int[] childTo = { R.id.tvText, R.id.tvText1,R.id.tvText2,R.id.tvText3};

	    // ������� ������� � ����������� ������
	    SimpleCursorTreeAdapter sctAdapter = new MyAdapter(this, cursor,
	        /*android.R.layout.simple_expandable_list_item_2*/R.layout.group_item, groupFrom,
	        groupTo, R.layout.item, childFrom,
	        childTo);
	    elvMain = (ExpandableListView) findViewById(R.id.elvMain);
	    elvMain.setAdapter(sctAdapter);		
		
	}

	@Override
	public void onClick(View v) {
		SQLiteDatabase db = mydb.open();
		Cursor c = db.query("main_info", null, null, null, null, null, null);
		if (c != null) {
			if (c.moveToFirst()) {
				do {

//					setText(c.getString(0));

				} while (c.moveToNext());
			}
			c.close();
		} else

			db.close();
	}
	class MyAdapter extends SimpleCursorTreeAdapter {

	    public MyAdapter(Context context, Cursor cursor, int groupLayout,
	        String[] groupFrom, int[] groupTo, int childLayout,
	        String[] childFrom, int[] childTo) {
	      super(context, cursor, groupLayout, groupFrom, groupTo,
	          childLayout, childFrom, childTo);
	    }

	    protected Cursor getChildrenCursor(Cursor groupCursor) {
	      // �������� ������ �� ��������� ��� ���������� ������
	      int idColumn = groupCursor.getColumnIndex("_id");
	      Cursor my=mydb.getPartyData(groupCursor.getInt(idColumn));
	      return my;
	    }
	  }
}

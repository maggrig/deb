package mag.grig.deb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;



public class Archiv extends Activity implements OnClickListener, OnItemClickListener {

	 ExpandableListView elvMain;
	  DB mydb;
	  private static final int CM_DELETE_ID = 1;
	  final String LOG_TAG = "myLogs";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.archiv);
	    // подключаемся к БД
	    mydb = new DB(this);
	    mydb.open();

	    // готовим данные по группам для адаптера
	    Cursor cursor = mydb.getGameData();
	    startManagingCursor(cursor);
	    // сопоставление данных и View для групп
//	    String[] groupFrom = { "_id","_Name1","_Name2","_Name3","_Name4" };
	    String[] groupFrom = { "_id","_Name1","_Name2","_Name3","_Name4","_date","_time" };	    
	    int[] groupTo =  { R.id.grText,R.id.grText1,R.id.grText2,R.id.grText3,R.id.grText4,R.id.grDate,R.id.grTime};
	    // сопоставление данных и View для элементов
	    String[] childFrom = { "_id","_sum1","_sum2","_sum3" };
	    int[] childTo = { R.id.tvText, R.id.tvText1,R.id.tvText2,R.id.tvText3};

	    // создаем адаптер и настраиваем список
	    SimpleCursorTreeAdapter sctAdapter = new MyAdapter(this, cursor,
	        /*android.R.layout.simple_expandable_list_item_2*/
	    		R.layout.group_item, groupFrom,
	        groupTo, R.layout.item, childFrom,
	        childTo);
	    
	    elvMain = (ExpandableListView) findViewById(R.id.elvMain);
	    elvMain.setAdapter(sctAdapter);
	    
	    elvMain.setOnChildClickListener(new OnChildClickListener()
	    {
	        public boolean onChildClick(ExpandableListView parent, View v, int group_position, int child_position, long id)
	        {
	        	//v.
	        	  if(group_position==0 && child_position==0){
	        		  int my=group_position;
	        		  int my1=child_position;
	        		  long my2=id;
	              } else if(group_position==2 && child_position==2){
	        		  int my=group_position;
	        		  int my1=child_position;
	        		  long my2=id;
	            	  
	              }
	              return false;	        	
	        }
	    });

	}
	
	  @Override
	  public void onCreateContextMenu(ContextMenu menu, View v,
	      ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
	  }

	  @Override
	  public boolean onContextItemSelected(MenuItem item) {
	    if (item.getItemId() == CM_DELETE_ID) {
	      // получаем инфу о пункте списка
	       item.getMenuInfo();
	    	 
	      // уведомляем, что данные изменились
	  
	      return true;
	    }
	    return super.onContextItemSelected(item);
	  }
	class MyAdapter extends SimpleCursorTreeAdapter {

	    public MyAdapter(Context context, Cursor cursor, int groupLayout,
	        String[] groupFrom, int[] groupTo, int childLayout,
	        String[] childFrom, int[] childTo) {
	      super(context, cursor, groupLayout, groupFrom, groupTo,
	          childLayout, childFrom, childTo);
	    }

	    protected Cursor getChildrenCursor(Cursor groupCursor) {
	      // получаем курсор по элементам для конкретной партии
//	      int idColumn = groupCursor.getColumnIndex("_id");
	      return mydb.getPartyData(groupCursor.getInt(0));
	    }
	  }

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		Toast.makeText(this, "Записал", Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "Записал", Toast.LENGTH_SHORT).show();		
		// TODO Auto-generated method stub
		
	}
}

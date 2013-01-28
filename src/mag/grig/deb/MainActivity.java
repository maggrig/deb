package mag.grig.deb;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends TabActivity implements OnClickListener,
		OnTabChangeListener {

	Button button1,button2;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
		{			Intent intent = new Intent(this, Settings.class);
			startActivity(intent);		
		}	
			break;
		case  R.id.button2:
		{			Intent intent = new Intent(this, Archiv.class);
		startActivity(intent);		
	}	
		break;
		default:
			break;
		}
		//openOptionsMenu();	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
  	    button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
  	    button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);	  

		Intent intent = new Intent(this, Tabform.class);
		Intent intent1 = new Intent(this, Tabform.class);
		Intent intent2 = new Intent(this, Tabform.class);
		TabHost tabHost = getTabHost();
		TabHost.TabSpec tabSpec;
		// название вкладки
		tabSpec = tabHost.newTabSpec("tag1");
		tabSpec.setIndicator("2 игрока",
				getResources().getDrawable(R.drawable.tab_icon_selector));

		// создаем интент и передаем туда ков-во игроков
		intent.putExtra("count", 2);
		tabSpec.setContent(intent);
		// добавляем в корневой элемент
		tabHost.addTab(tabSpec);
		
		tabSpec = tabHost.newTabSpec("tag2");
		tabSpec.setIndicator("3 игрока",
				getResources().getDrawable(R.drawable.tab_icon_selector));

		// Intent intent = new Intent(this, Mainform.class);
		intent1.putExtra("count", 3);
		tabSpec.setContent(intent1);
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec("tag3");
		tabSpec.setIndicator("4  игрока",
				getResources().getDrawable(R.drawable.tab_icon_selector));

		intent2.putExtra("count", 4);
		tabSpec.setContent(intent2);
		tabHost.addTab(tabSpec);
		tabHost.setCurrentTabByTag("tag1");
		tabHost.setOnTabChangedListener(this);
	}	

	  @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		    getMenuInflater().inflate(R.menu.activity_main, menu);
		    return super.onCreateOptionsMenu(menu);
		  }
	  // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
            return super.onPrepareOptionsMenu(menu);
    }

    @Override
	public void onTabChanged(String tabId) {

	}

}

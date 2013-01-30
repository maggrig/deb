package mag.grig.deb;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;



public class Settings extends Activity implements OnClickListener {
	SharedPreferences sPref;
	CheckBox checkBox1;
	Button button1;
	RadioGroup radioGroup;

		  @Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.settings);
		  	  button1 = (Button) findViewById(R.id.button1);
			  button1.setOnClickListener(this);	  
			  checkBox1=(CheckBox) findViewById(R.id.checkBox1);
			  radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);			  
			  loadText("bez_vzat");
			  loadText("vis");
		}

		  @Override
			public void onClick(View v) {
				  int checkedRadioButton = radioGroup.getCheckedRadioButtonId();
				  switch (checkedRadioButton) {
				  case R.id.radio0 : saveText("vis","0");;
				                   	              break;
				  case R.id.radio1 :saveText("vis","1"); ;
						                      break;
				}
			    if (checkBox1.isChecked()==true)
			    	saveText("bez_vzat","1");
			    else 		    	saveText("bez_vzat","0");
			}

		void saveText(String _key,String _value) {
			    sPref = getPreferences(MODE_PRIVATE);
			    Editor ed = sPref.edit();
			    ed.putString(_key, _value);
			    ed.commit();
			    Toast.makeText(this, "Настройки сохранены", Toast.LENGTH_SHORT).show();
			  }
		void loadText(String _key) {
		    sPref = getPreferences(MODE_PRIVATE);
		    String savedText = sPref.getString(_key, "");
		    if (_key.equals("vis"))
		    {
			    if (savedText.equals("1")) 		    radioGroup.check(R.id.radio1);
			    else 		    radioGroup.check(R.id.radio0);
		    }
		    else if (_key.equals("bez_vzat"))
		    {
		    if (savedText.equals("1")) checkBox1.setChecked(true);
		    else checkBox1.setChecked(false);
		    }
		    Toast.makeText(this, "Настройки загружены", Toast.LENGTH_SHORT).show();
		  }
		
}

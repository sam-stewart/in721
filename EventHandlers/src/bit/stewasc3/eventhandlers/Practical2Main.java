package bit.stewasc3.eventhandlers;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Practical2Main extends Activity {
	
	Button bttn1;
	EditText editTxtAt;
	EditText editTxtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical2_main);
        
        // Get references to views
        bttn1 = (Button)findViewById(R.id.bttn1);
        editTxtAt = (EditText)findViewById(R.id.editTxtAt);
        editTxtUserName = (EditText)findViewById(R.id.editTxtUserName);
        
        // Button 1 OnClick and OnLongClick - First handler uses named inner class //              
        bttn1.setOnClickListener(new ShortClickHandler());    
        bttn1.setOnLongClickListener(new OnLongClickListener() {		
			@Override
			public boolean onLongClick(View v) {
				Toast.makeText(Practical2Main.this, "OnLongClick listener pressed", Toast.LENGTH_LONG).show();
				return false;
			}
		});
        
        // EditText Key Event @ Sign //
        editTxtAt.setOnKeyListener(new OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent e) {
				if(keyCode == KeyEvent.KEYCODE_AT && e.getAction() == KeyEvent.ACTION_DOWN)
					Toast.makeText(Practical2Main.this, "Don't press @", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
        
        // EditText KeyEvent + Not returning 'enter' //
        editTxtUserName.setOnKeyListener(new OnKeyListener() {		
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				String input = ((EditText)v).getText().toString();
				if (keyCode == KeyEvent.KEYCODE_ENTER && 
						input.length() == 8)
					Toast.makeText(Practical2Main.this, "Thank you " + input, Toast.LENGTH_SHORT).show();
				else if (keyCode == KeyEvent.KEYCODE_ENTER)
					Toast.makeText(Practical2Main.this, "Username not 8 characters, " + input, Toast.LENGTH_SHORT).show();
				else
					return false;
				return true;
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical2_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    class ShortClickHandler implements OnClickListener {
		@Override
		public void onClick(View v) {
			Toast.makeText(Practical2Main.this, "OnClick listener pressed", Toast.LENGTH_LONG).show();
		}
    }
}

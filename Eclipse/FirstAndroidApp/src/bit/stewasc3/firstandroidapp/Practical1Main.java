package bit.stewasc3.firstandroidapp;

import java.util.Random;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Practical1Main extends Activity {
	
	TextView txtViewRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical1_main);
        chooseBreed();
        showFridays();
    }
        


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical1_main, menu);
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
    
    private void chooseBreed()
    { 
        txtViewRandom = (TextView)findViewById(R.id.textView2);
        String dogBreed = "";
        Random rand = new Random();
        int dogValue = rand.nextInt(4);
        
        switch (dogValue)
        {
        case 0:
        	dogBreed = "Rottweiler";
        	break;
        case 1:
        	dogBreed = "Staffy";
        	break;
        case 2:
        	dogBreed = "Labrador";
        	break;
        case 3:
        	dogBreed = "Border Collie";
        	break;
        }
        
        txtViewRandom.setText(dogBreed);
        
    }
    
    private void showFridays()
    {
    	Resources resolver = getResources();
    	int dateArray[] = resolver.getIntArray(R.array.FebFridays);
    	TextView febFridaysText =  (TextView)findViewById(R.id.txtViewFridays);
    	febFridaysText.append("Feb Fridays On: ");
    	for(int i = 0; i < dateArray.length; i++)
    	{
    		febFridaysText.append(dateArray[i] + " ");
    	}
    }
    
    }

   

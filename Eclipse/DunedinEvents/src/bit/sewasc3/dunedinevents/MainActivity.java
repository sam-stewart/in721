package bit.sewasc3.dunedinevents;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

// This implementation would run to to trouble if we had events of the same name
// (they're used for keys in the hashmap). Fortunately the event names are unique
// in this case.

public class MainActivity extends Activity
{
	private String mJsonFilename = "dunedin_events.json";
	HashMap<String, String> mEventMap;
	private RelativeLayout mLayout;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLayout = (RelativeLayout)findViewById(R.id.root_layout);
		
		String json = readJsonAsset(mJsonFilename);
		mEventMap = parseJson(json);
		
		ListView lv = new ListView(this);
		lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		lv.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mEventMap.keySet().toArray()));
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id)
			{
				TextView tv = (TextView)view;
				String key = (String)tv.getText();
				Toast.makeText(MainActivity.this, mEventMap.get(key), Toast.LENGTH_SHORT).show();
			}
		});
		mLayout.addView(lv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public String readJsonAsset(String filename)
	{
		String json = "";
		try
		{
			InputStream is = getAssets().open(filename);
			int size = is.available();
			byte[] jsonArray = new byte[size];
			is.read(jsonArray);
			is.close();
			json = new String(jsonArray);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return json;
	}

	// Very rigid to this specific implementation
	public HashMap<String, String> parseJson(String json)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		try
		{
			JSONObject jsonObjRoot = new JSONObject(json);
			JSONObject jsonObjEvents = jsonObjRoot.getJSONObject("events");
			JSONArray jsonArrEvents = jsonObjEvents.getJSONArray("event");
			
			for(int i = 0; i < jsonArrEvents.length(); i++)
			{
				JSONObject o = jsonArrEvents.getJSONObject(i);
				String title = o.getString("title");
				String desc = o.getString("description");
				map.put(title, desc);
			}
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return map;
	}
}

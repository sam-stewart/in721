package bit.stewasc3.teleportgame;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    Button mBttnTeleport;
    Random mRand;
    TextView mTvLat;
    TextView mTvLong;
    TextView mTvCity;
    private double mLatitude;
    private double mLongitude;
    DecimalFormat mThreePlaces = new DecimalFormat("0.000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRand = new Random();
        mBttnTeleport = (Button) findViewById(R.id.bttnTeleport);
        mBttnTeleport.setOnClickListener(new TeleportHandler());
        mTvLat = (TextView) findViewById(R.id.tvLatitude);
        mTvLong = (TextView) findViewById(R.id.tvLong);
        mTvCity = (TextView) findViewById(R.id.tvCity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class TeleportHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            double lat = mRand.nextDouble() * 90;
            double longitude = mRand.nextDouble() * 180;
            mLatitude = (mRand.nextInt() %  2 == 0) ? lat : lat * -1;
            mLongitude = (mRand.nextInt() % 2 == 0) ? longitude : longitude * -1;
            mTvLat.setText("Latitude: " + mThreePlaces.format(mLatitude));
            mTvLong.setText("Longitude: " + mThreePlaces.format(mLongitude));
            new FetchTask().execute();
        }
    }

    private class FetchTask extends AsyncTask<Double, Void, String>
    {
        @Override
        protected String doInBackground(Double... params)
        {
            return new CityFetcher().fetchCity(Double.toString(mLatitude), Double.toString(mLongitude));
        }

        @Override
        protected void onPostExecute(String result)
        {
            mTvCity.setText(result);
        }
    }
}

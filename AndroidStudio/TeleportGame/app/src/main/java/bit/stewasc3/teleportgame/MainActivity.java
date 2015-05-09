package bit.stewasc3.teleportgame;

import android.app.FragmentManager;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

    Random mRand;
    TextView mTvLat;
    TextView mTvLong;
    TextView mTvCity;
    private double mLatitude;
    private double mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRand = new Random();
        mTvLat = (TextView) findViewById(R.id.tvLatitude);
        mTvLong = (TextView) findViewById(R.id.tvLong);
        mTvCity = (TextView) findViewById(R.id.tvCity);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, new CustomLocationListener());
    }

    private class CustomLocationListener implements LocationListener
    {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }

        @Override
        public void onLocationChanged(Location location)
        {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            mTvLat.setText("Lat: " + Double.toString(mLatitude));
            mTvLong.setText("Long: " + Double.toString(mLongitude));
            new FetchTask().execute();
        }

        @Override
        public void onProviderEnabled(String provider)
        {

        }

        @Override
        public void onProviderDisabled(String provider)
        {

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
            mTvCity.setText("City: " + result);
        }
    }
}

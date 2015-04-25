package bit.stewasc3.teleportgame;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samuel on 22/04/15.
 */
public class CityFetcher
{
    private static final String ROOT = "http://www.geoplugin.net/extras/location.gp?";

    public String fetchCity(String latitude, String longitude)
    {
        String returnString = "";
        try
        {
            String url = Uri.parse(ROOT).buildUpon()
                    .appendQueryParameter("lat", latitude)
                    .appendQueryParameter("long", longitude)
                    .appendQueryParameter("format", "json")
                    .build().toString();
            Log.i("URL", url);
            String jsonString = new String(getByteArray(url));
            returnString = parseJson(jsonString);
        }
        catch(IOException|JSONException e)
        {
            e.printStackTrace();
        }
        return returnString;
    }

    public String parseJson(String jsonString) throws JSONException
    {
        String cityCountryCode;
        if(jsonString.compareTo("[[]]") == 0)
            cityCountryCode = "Nothing close";
        else
        {
            JSONObject o = new JSONObject(jsonString);
            cityCountryCode = o.getString("geoplugin_place");
            cityCountryCode += ", " + o.getString("geoplugin_countryCode");
        }
        return cityCountryCode;
    }

    public byte[] getByteArray(String urlString) throws IOException
    {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = conn.getInputStream();

            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];

            while ((bytesRead = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }
        finally
        {
            conn.disconnect();
        }
    }
}

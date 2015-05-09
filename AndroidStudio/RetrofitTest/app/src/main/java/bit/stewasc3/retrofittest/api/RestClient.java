package bit.stewasc3.retrofittest.api;


import retrofit.RestAdapter;

/**
 * Created by samuel on 8/05/15.
 * RetroFit API: http://square.github.io/retrofit/
 */
public class RestClient
{
    private static FakeApi REST_CLIENT;
    private static String ROOT = "http://jsonplaceholder.typicode.com/";

    private RestClient()
    {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(FakeApi.class);
    }

    public static FakeApi get()
    {
        if(REST_CLIENT == null)
        {
            new RestClient();
        }
        return REST_CLIENT;
    }
}

package bit.stewasc3.retrofittest.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by samuel on 9/05/15.
 */
public interface FakeApi
{
    @GET("/posts/{id}")
    public void getPost(@Path("id") int postId, Callback<Post> cb);
}

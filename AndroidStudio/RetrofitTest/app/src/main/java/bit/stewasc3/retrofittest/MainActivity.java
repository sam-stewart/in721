package bit.stewasc3.retrofittest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.skyfishjy.library.RippleBackground;

import java.util.Random;

import bit.stewasc3.retrofittest.api.Post;
import bit.stewasc3.retrofittest.api.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    Button bttnGetPost;
    TextView tvPostUserId;
    TextView tvPostId;
    TextView tvPostTitle;
    TextView tvPostBody;
    RippleBackground rippleBg;
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttnGetPost = (Button) findViewById(R.id.bttnWeather);
        tvPostUserId = (TextView) findViewById(R.id.textView);
        tvPostId = (TextView) findViewById(R.id.textView2);
        tvPostTitle = (TextView) findViewById(R.id.textView3);
        tvPostBody = (TextView) findViewById(R.id.textView4);
        rippleBg = (RippleBackground) findViewById(R.id.ripple);
        rand = new Random();

        bttnGetPost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(rippleBg.isRippleAnimationRunning())
                    rippleBg.stopRippleAnimation();
                else
                    rippleBg.startRippleAnimation();
                getPost();

                YoYo.with(Techniques.StandUp).duration(1000)
                        .playOn(findViewById(R.id.imageView));
            }
        });
    }

    public void getPost()
    {
        // API has a total of 100 posts
        RestClient.get().getPost(rand.nextInt(100), new Callback<Post>()
        {
            @Override
            public void success(Post postResponse, Response response)
            {
                tvPostUserId.setText("UserID: " + Integer.toString(postResponse.getUserId()));
                tvPostId.setText("Post Id: " + Integer.toString(postResponse.getId()));
                tvPostTitle.setText("Post Title: " + postResponse.getTitle());
                tvPostBody.setText("Post Body: " + postResponse.getBody());
            }

            @Override
            public void failure(RetrofitError error)
            {
                error.printStackTrace();
            }
        });
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
}

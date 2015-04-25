package bit.stewasc3.implicitcamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity
{

    private static final int IMG_REQUEST_CODE = 0;
    private static final String IMG_INDEX = "image";
    private ImageView mImageView;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private Button mBttnStartIntent;
    private String mPhotoFileName;
    private String mSavedImgPath;
    private File mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get image view refs
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView3= (ImageView) findViewById(R.id.imageView3);
        mImageView4 = (ImageView) findViewById(R.id.imageView4);
        mBttnStartIntent = (Button) findViewById(R.id.bttnStartIntent);

        // For saving over rotation, check for savedState and if it has an IMG_INDEX key.
        // If it does, set savedImgPath, set the ImageViews and initialise mImage.
        // Initialising mImage is important, if user takes an image, rotates, then rotates
        // again without taking a new image, no path would be saved to the bundle. savedImgPath
        // is used if user starts camera, then returns without taking an image.
        if(savedInstanceState != null && savedInstanceState.containsKey(IMG_INDEX))
        {
            mSavedImgPath = savedInstanceState.getString(IMG_INDEX);
            setImageViews(mSavedImgPath);
            mImage = new File(mSavedImgPath);
        }

        mBttnStartIntent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File folder = new File(Environment.getExternalStorageDirectory(), "ImplicitCameraImages");
                folder.mkdirs();
                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                Date currentTime = new Date();
                String timeStamp = timeStampFormat.format(currentTime);
                mPhotoFileName = "IMG_" + timeStamp + ".jpg";
                mImage = new File(folder.getPath() + File.separator + mPhotoFileName);
                Uri uriImage = Uri.fromFile(mImage);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                startActivityForResult(i, IMG_REQUEST_CODE);
            }
        });
    }

    // Save image path to bundle if picture has been taken.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);

        // Only save image path onto bundle if it isn't null or empty. (longer than 0)
        if(mImage != null && mImage.length() > 0)
        {
            savedInstanceState.putString(IMG_INDEX, mImage.getPath());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == IMG_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "Image saved to: " + mImage.getPath(), Toast.LENGTH_LONG).show();
                setImageViews(mImage.getPath());
            }
            else
            {
                // If view was displaying saved image, set mImage back to old image path.
                if (mSavedImgPath != null)
                    mImage = new File(mSavedImgPath);
                Toast.makeText(this, "Cancelled or something went wrong..", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setImageViews(String imagePath)
    {
        Bitmap photoBm = BitmapFactory.decodeFile(imagePath);
        mImageView.setImageBitmap(photoBm);
        mImageView2.setImageBitmap(photoBm);
        mImageView3.setImageBitmap(photoBm);
        mImageView4.setImageBitmap(photoBm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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

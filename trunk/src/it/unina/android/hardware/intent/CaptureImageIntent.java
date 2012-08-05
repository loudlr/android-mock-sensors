package it.unina.android.hardware.intent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images;

//NOTA: getInstrumentation().getContext() per assets
public class CaptureImageIntent extends Activity
{
	public static int MY_RESULT = RESULT_OK;
	
	Uri saveUri = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	if ( MY_RESULT == RESULT_OK )
    	{
    		if (this.getIntent().getExtras() != null)
	    		saveUri = this.getIntent().getExtras().getParcelable(it.unina.android.provider.MediaStore.EXTRA_OUTPUT);
	    
	    	if (saveUri != null)
	    	{
	    		Bitmap photo = this.loadImageFromAsset(getApplicationContext(), "photo.jpg");
	    		
	    		OutputStream outputStream = null;
	    		try
	    		{
	    			outputStream = this.getContentResolver().openOutputStream(saveUri);
		    	    photo.compress(Bitmap.CompressFormat.JPEG, 75, outputStream);
		    	    outputStream.close();		    	    
		    	    
		    	    /*
		    	    ContentValues values = new ContentValues(9);
		    		values.put(Images.Media.TITLE, saveUri.getLastPathSegment());
		    		values.put(Images.Media.DISPLAY_NAME, saveUri.getLastPathSegment());
		    		values.put(Images.Media.DESCRIPTION, "");
		    		values.put(Images.Media.DATE_TAKEN, new java.util.Date().toLocaleString());
		    		values.put(Images.Media.MIME_TYPE, "image/jpeg");
		    		values.put(Images.Media.DATA, saveUri.toString());	    		
		    		values.put(Images.Media.ORIENTATION, ExifInterface.ORIENTATION_ROTATE_90);
		    		values.put(Images.Media.LATITUDE, 40.9258774);
		    		values.put(Images.Media.LONGITUDE, 14.5285639);
		    		
		    		Uri outputUri = getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, values);
		    		
		    		Intent intent = new Intent();
		    		intent.putExtra(it.unina.android.provider.MediaStore.EXTRA_OUTPUT, outputUri);
		    		this.setResult(RESULT_OK, intent);
		    		*/
		    		Intent intent = new Intent();
		    		intent.putExtra(it.unina.android.provider.MediaStore.EXTRA_OUTPUT, saveUri);
		    		this.setResult(RESULT_OK, intent);

	    		}
	    		catch (Exception ex)
	    		{
	    			this.setResult(RESULT_CANCELED);
	    			this.finish();
	    		}
	    		finally
	    		{
					try { if (outputStream != null) outputStream.close(); } catch (IOException ex) {}
	    		}
	    	}
	    	else
	    	{
	    		Bitmap preview = this.loadImageFromAsset(getApplicationContext(), "preview.jpg");
	    		setResult(RESULT_OK, new Intent("inline-data").putExtra("data", preview));
	    	}
    	}
    	else
    	{
    		this.setResult(MY_RESULT);
    	}
    	
    	this.finish();
    }
    
    public Bitmap loadImageFromAsset(Context ctx, String imageName) {
    	try {
	    	InputStream in = ctx.getAssets().open(imageName);
	    	return BitmapFactory.decodeStream(in,null,null);
    	}
    	catch(IOException ex) {
    		
    	}
    	return null;
    }
/*    
    private void startMediaScanner()
    {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addDataScheme("file");
        registerReceiver(mReceiver, intentFilter);        
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                + Environment.getExternalStorageDirectory())));
    }
*/
    /** Called when the activity going into the background or being destroyed. */
/*
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
    
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	if (intent.getAction().equals(Intent.ACTION_MEDIA_SCANNER_FINISHED))
        		finish();
        }
    };
*/
}

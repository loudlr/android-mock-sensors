package it.unina.android.hardware.intent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

//NOTA: getInstrumentation().getContext() per assets
public class CaptureImageIntent extends Activity {
	public static int MY_RESULT = RESULT_OK;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	if ( MY_RESULT == RESULT_OK )
    	{
        	//dati ritornati
        	Intent i = new Intent();
        		    	
        	Uri saveUri = null;        	
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
		    	    
		    	    this.setResult(RESULT_OK);
	    		}
	    		catch (Exception ex)
	    		{
	    			this.setResult(RESULT_CANCELED);
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
    
}

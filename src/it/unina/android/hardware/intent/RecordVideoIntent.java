package it.unina.android.hardware.intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class RecordVideoIntent extends Activity {
	public static int MY_RESULT = RESULT_OK;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	if ( MY_RESULT == RESULT_OK )
    	{
        	Uri saveUri = null;        	
	    	if (this.getIntent().getExtras() != null)
	    		saveUri = this.getIntent().getExtras().getParcelable(it.unina.android.provider.MediaStore.EXTRA_OUTPUT);
	    
	    	if (saveUri != null)
	    	{
	    		copyAssetToURI(this.getApplicationContext(), "video.mp4", saveUri);
	    	}
	    	else
	    	{	    		
	    		copyAssetToSD(this.getApplicationContext(), "video.mp4");
	    		saveUri = Uri.fromFile(new File("/sdcard/video.mp4"));
	    	}
	    	
	    	//Log.v("SaveURI", "saveuri=" + saveUri.toString());
	    	
	    	Intent intent = new Intent("inline-data");
	    	intent.putExtra(it.unina.android.provider.MediaStore.EXTRA_OUTPUT, saveUri);
	    	intent.putExtra("data", saveUri);
	    	intent.setData(saveUri);
	    	setResult(RESULT_OK, intent);	    	
    	}
    	else
    	{
    		this.setResult(MY_RESULT);
    	}
    	
    	this.finish();
    }
    
    private void copyAssetToSD(Context ctx, String filename) {
    	copyAssetToSD(ctx, filename, filename);
    }
    
    private void copyAssetToSD(Context ctx, String filename, String newFilename) {
        InputStream in = null;
        OutputStream out = null;
        try {
          in = ctx.getAssets().open(filename);
          out = new FileOutputStream("/sdcard/" + newFilename);
          copyFile(in, out);
          in.close();
          in = null;
          out.flush();
          out.close();
          out = null;
        } catch(Exception e) {
            Log.e("tag", e.getMessage());
        }       
    }
    
    private void copyAssetToURI(Context ctx, String filename, Uri dest) {
        InputStream in = null;
        OutputStream out = null;
        try {
          in = ctx.getAssets().open(filename);
          out = ctx.getContentResolver().openOutputStream(dest);
          copyFile(in, out);
          in.close();
          in = null;
          out.flush();
          out.close();
          out = null;
        } catch(Exception e) {
            Log.e("tag", e.getMessage());
        }       
    }
    
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
          out.write(buffer, 0, read);
        }
    }
}

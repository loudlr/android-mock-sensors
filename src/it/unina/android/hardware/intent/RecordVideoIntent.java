package it.unina.android.hardware.intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Video;
import android.text.format.DateFormat;
import android.util.Log;

public class RecordVideoIntent extends Activity {
	public static int MY_RESULT = RESULT_OK;

	public Uri saveUri = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	if ( MY_RESULT == RESULT_OK )
    	{
        	//Uri saveUri = null;        	
	    	if (this.getIntent().getExtras() != null)
	    		saveUri = this.getIntent().getExtras().getParcelable(it.unina.android.provider.MediaStore.EXTRA_OUTPUT);
	    	
	    	if (saveUri != null)
	    	{
	    		ContentValues values = new ContentValues(7);
	    		values.put(Video.Media.TITLE, saveUri.getLastPathSegment());
	    		values.put(Video.Media.DISPLAY_NAME, saveUri.getLastPathSegment());
	    		values.put(Video.Media.DESCRIPTION, "");
	    		values.put(Video.Media.DATE_TAKEN, new java.util.Date().toLocaleString());
	    		values.put(Video.Media.MIME_TYPE, "video/3gpp");
	    		values.put(Video.Media.DATA, saveUri.toString());	    		

	    		copyAssetToURI(this.getApplicationContext(), "video.3gp", saveUri);
	    		saveUri = getContentResolver().insert(saveUri, values);
	    	}
	    	else
	    	{	
	    		String cameraDirPath = "/sdcard/DCIM/Camera";
	    		File cameraDir  = new File(cameraDirPath);
	    		cameraDir.mkdirs();
	    		
	    		long dateTaken = System.currentTimeMillis();	    		
	    		String title = DateFormat.format("yyyy-MM-dd_kk.mm.ss", dateTaken).toString();
	    		String displayName = "VID_" + title + ".3gp";	    		
	    		String filename = cameraDirPath + "/" + displayName;
	    		
	    		ContentValues values = new ContentValues(7);
	    		values.put(Video.Media.TITLE, title);
	    		values.put(Video.Media.DISPLAY_NAME, displayName);
	    		values.put(Video.Media.DESCRIPTION, "");
	    		values.put(Video.Media.DATE_TAKEN, dateTaken);
	    		values.put(Video.Media.MIME_TYPE, "video/3gpp");
	    		values.put(Video.Media.DATA, filename);	    		
	    		
	    		copyAssetToSD(this.getApplicationContext(), "video.3gp");
	    		new File("/sdcard/video.3gp").renameTo( new File(filename) );
	    		
	    		//saveUri = Uri.fromFile(new File(filename));
	    		Uri videoTable = Uri.parse("content://media/external/video/media");
	    		saveUri = getContentResolver().insert(videoTable, values);
	    	}
	    	
	    	Intent intent = new Intent();
	    	intent.putExtra(it.unina.android.provider.MediaStore.EXTRA_OUTPUT, saveUri);
	    	intent.putExtra("data", saveUri);
	    	intent.setData(saveUri);
	    	setResult(RESULT_OK, intent);	    	
	    	this.finish();
    	}
    	else
    	{
    		this.setResult(MY_RESULT);    		
    	}
    	
    	this.finish();
    }

    private void returnIntent()
    {
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

    private void startMediaScanner()
    {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addDataScheme("file");
        registerReceiver(mReceiver, intentFilter);
        
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                + Environment.getExternalStorageDirectory())));
    }
    
    /** Called when the activity going into the background or being destroyed. */
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
    
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	if (intent.getAction().equals(Intent.ACTION_MEDIA_SCANNER_FINISHED))
        		returnIntent();
        }
    };
    
}

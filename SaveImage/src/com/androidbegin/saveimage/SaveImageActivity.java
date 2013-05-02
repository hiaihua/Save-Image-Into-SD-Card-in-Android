package com.androidbegin.saveimage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SaveImageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Save Image Tutorial");
        setContentView(R.layout.save_image);
        
        // Find the Button in save_image.xml
        Button SaveImageButton = (Button)findViewById(R.id.button);
        
        // Find the ImageView in save_image.xml
        ImageView ImagePreview = (ImageView)findViewById(R.id.image);

        // Attached the image into save_image.xml 
        ImagePreview.setImageResource(R.drawable.sample_wallpaper);
       
        // Listening to Button click
        SaveImageButton.setOnClickListener(new Button.OnClickListener(){

        	public void onClick(View arg0) {
        		// TODO Auto-generated method stub
        		
        		Bitmap bitmap;
        		OutputStream output;
        		
        		// Decoding the image
        		bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.sample_wallpaper);
        	    
        		// Find the SD Card path
        		File filepath = Environment.getExternalStorageDirectory();
        		
        		// Create a new folder AndroidBegin in SD Card
        	    File dir = new File (filepath.getAbsolutePath() + "/AndroidBegin/");
    			dir.mkdirs();
    			
    			// Create a name for the saved image
        	    File file = new File(dir, "sample_wallpaper.png");
        	    
        	    // Notify the user on successful save
        	    Toast.makeText(SaveImageActivity.this, "Image Saved to SD Card", Toast.LENGTH_SHORT).show();
        	    try {
        	    	
        	    	// Image starts saving
        	    	output = new FileOutputStream(file);
        	     
        	    	// Compress into png format image from 0% - 100%, using 100% for this tutorial
        	    	bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);    	     
        	    	output.flush();
        	    	output.close();
        	    }
        	    
        	    // Catch exceptions
        	    catch(Exception e){
        	    	// TODO Auto-generated catch block
        	    	e.printStackTrace();  
        	    }
        	}
        	});
        }

    // Not using options menu for this tutorial
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_save_image, menu);
        return true;
    }
}

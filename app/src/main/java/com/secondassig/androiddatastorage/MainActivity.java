package com.secondassig.androiddatastorage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	try
    	{
    		InputStream in=openFileInput(SetPreferencesActivity.STORE_PREFERENCES);
    		if(in!=null)
    		{
    			InputStreamReader tmp=new InputStreamReader(in);
    			BufferedReader reader=new BufferedReader(tmp);
    			String str;
    			StringBuilder buf=new StringBuilder();
    			while((str=reader.readLine())!=null)
    			{
    				buf.append(str +"\n");
    			}
    			in.close();
    			TextView savedPref=(TextView)findViewById(R.id.saved_data);
    			savedPref.setText(buf.toString());
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    public void openPreference(View view)
    {
    	Intent intent=new Intent(this,SetPreferencesActivity.class);
    	startActivity(intent);
    }
    
    public void saveInDatabase(View view)
    {
    	Intent intent =new Intent(this,SQLiteActivity.class);
    	startActivity(intent);
    }
    
    public void exitApp(View view)
    {
    	finish();
    	System.exit(0);
    }
}

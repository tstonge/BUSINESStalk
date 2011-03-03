package businesstalk.btalk;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class BUSINESStalk extends Activity {
    private static final int PICTURE_RESULT = 0;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        
        Button idCustomer = (Button)findViewById(R.id.idCustomer);
        
        idCustomer.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		doTakePhotoAction();
        	}
        });
        
        
        Button newID = (Button)findViewById(R.id.newID);
        
        newID.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent newid = new Intent(BUSINESStalk.this, NewID.class);
        		startActivity(newid);
        	}
        });
    }
    
    private void doTakePhotoAction() {
    	Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);   
        this.startActivityForResult(camera, PICTURE_RESULT);
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    }
}
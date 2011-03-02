package businesstalk.btalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BUSINESStalk extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button takephoto = (Button)findViewById(R.id.takephoto);
        
        takephoto.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent newid = new Intent(BUSINESStalk.this, NewID.class);
        		startActivity(newid);
        	}
        });
    }
}
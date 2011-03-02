package businesstalk.btalk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

public class NewID extends Activity {
	int PICTURE_RESULT;
	Bitmap mPhoto;
	Boolean mPhotoChanged;
	ImageView pictureHolder;
	Uri mImageCaptureUri;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newid);
        
        doTakePhotoAction();
    }
    private void doTakePhotoAction() {
    	Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);   
        this.startActivityForResult(camera, PICTURE_RESULT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == PICTURE_RESULT) //
            if (resultCode == Activity.RESULT_OK) {
               // Display image received on the view
                Bundle b = data.getExtras(); // Kept as a Bundle to check for other things in my actual code
                Bitmap pic = (Bitmap) b.get("data");
                
                if (pic != null) { // Display your image in an ImageView in your layout (if you want to test it)
                    pictureHolder = (ImageView) this.findViewById(R.id.IMAGE);
                    pictureHolder.setImageBitmap(pic);
                    pictureHolder.invalidate();
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
            	this.finish();
            }
    }
}


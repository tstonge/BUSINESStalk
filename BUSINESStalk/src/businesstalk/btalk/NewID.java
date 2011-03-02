package businesstalk.btalk;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class NewID extends Activity {
	int PICTURE_RESULT;
	ImageView pictureHolder;
	private static final int faceNum = 1;
	private static final int cropRadius = 100;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                	FaceDetector.Face[] faceArray = new FaceDetector.Face[faceNum];
                    FaceDetector faceDetector = new FaceDetector(pic.getWidth(), pic.getHeight(), faceNum);
                    int numFaceDetected = faceDetector.findFaces(pic, faceArray);
                	PointF faceMidPoint = new PointF(pic.getWidth()/2f, pic.getHeight()/2f);
                    
                    if (numFaceDetected > 0) {
                    	Toast.makeText(this, "Face detected!", Toast.LENGTH_LONG).show();
                    	//Face face = faceArray[0];
                    	//face.getMidPoint(faceMidPoint);
                    }
                    
                    pic = Bitmap.createBitmap(pic, (int) faceMidPoint.x - cropRadius, 
                    		(int) faceMidPoint.y - cropRadius, 2 * cropRadius, 2 * cropRadius);
                    
                    pictureHolder = (ImageView) this.findViewById(R.id.IMAGE);
                    pictureHolder.setImageBitmap(pic);
                    pictureHolder.invalidate();
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
            	this.finish();
            }
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    }
}


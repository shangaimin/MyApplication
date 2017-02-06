package liangpin.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import liangpin.myapplication.ocr.CameraSourcePreview;
import liangpin.myapplication.ocr.GraphicOverlay;
import liangpin.myapplication.ocr.OcrGraphic;
import liangpin.myapplication.ocr.OcrProcessor;
import liangpin.myapplication.ui.WaterView;

/**
 * Created by Admin on 2017/1/16.
 */

public class WaveActivity extends Activity {
WaterView waterView;
    private CameraSource mCameraSource;
    private CameraSourcePreview mPreview;
    private GraphicOverlay<OcrGraphic> mGraphicOverlay;
    TextView tv_result;
    public static final String AutoFocus = "AutoFocus";
    public static final String UseFlash = "UseFlash";
    public static final String TextBlockObject = "String";
    boolean autoFocus = true;
    boolean useFlash = false;
    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wave_activity);
        waterView= (WaterView) findViewById(R.id.wave_view);
        tv_result= (TextView) findViewById(R.id.tv_result);
        waterView.startAnimation();
        TextRecognizer textRecognizer= new TextRecognizer.Builder(this).build();
        textRecognizer.setProcessor(new OcrProcessor(mGraphicOverlay, tv_result));
        if (textRecognizer.isOperational()) {
            mCameraSource =
                    new CameraSource.Builder(getApplicationContext(), textRecognizer)
                            .setFacing(CameraSource.CAMERA_FACING_BACK)
                            .setRequestedPreviewSize(1280, 1024)
                            .setRequestedFps(2.0f)
//                            .setFlashMode(useFlash ? Camera.Parameters.FLASH_MODE_TORCH : null)
//                            .setFocusMode(autoFocus ? Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE : null)
                            .build();
        }
    }

}

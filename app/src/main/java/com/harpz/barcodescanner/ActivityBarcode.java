package com.harpz.barcodescanner;

import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.harpz.barcodescanner.util.utility;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityBarcode extends AppCompatActivity {

    @Bind(R.id.imagecontainer) ImageView imageView;
    @Bind(R.id.cameraview) SurfaceView cameraview;
    SurfaceHolder surfaceHolder;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    Context context;
    //utility class object
    //Camera camera;
    utility ut = new utility();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        ButterKnife.bind(this);

        context = this;

        //image show in picasso
        Picasso.with(this).load(getString(R.string.Picurl)).placeholder(R.drawable.loading).fit().into(imageView);



        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this,barcodeDetector).setFacing(CameraSource.CAMERA_FACING_BACK).setRequestedPreviewSize(640, 480).build();



        //Camera Preview Show
        cameraview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(cameraview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                    cameraSource.release();
                    Log.i("msg", e.getLocalizedMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });


    }
            @OnClick(R.id.scanbtn)
            public void scanbarcode() {
             barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
                 @Override
                 public void release() {

                 }

                 @Override
                 public void receiveDetections(Detector.Detections<Barcode> detections) {
                     final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                     if (barcodes.size() != 0) {
                         new MyCustomAsyncTask(context, barcodes.valueAt(0)).execute();

                     } else {
                         Log.i("msg", "no tag found");
                     }
                 }
             });
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        if(cameraview == null){

            try {
                cameraSource.start(cameraview.getHolder());
            } catch (IOException e) {
                new utility().showdialog(this);
                e.printStackTrace();
            }
        }
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.stop();

    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraSource.stop();
    }



}






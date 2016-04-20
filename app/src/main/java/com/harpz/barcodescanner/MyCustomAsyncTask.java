package com.harpz.barcodescanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by Harpz on 4/19/2016.
 */
public class MyCustomAsyncTask  extends AsyncTask<Void,Void,Void> {

    private Context context;
    private Barcode barcode;

    public MyCustomAsyncTask(Context context,Barcode barcode ){
        this.context=context;
        this.barcode = barcode;
    }


    @Override
    protected void onPreExecute() {
        // write show progress Dialog code here
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        // write service code here
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent i = new Intent();
        i.putExtra("displayValue", barcode.displayValue);
        i.putExtra("rawValue", barcode.url.toString());
        ((Activity)context).setResult(MainActivity.REQUEST_CODE_BARCODESCANNER, i);
        ((Activity)context).finish();
    }

}

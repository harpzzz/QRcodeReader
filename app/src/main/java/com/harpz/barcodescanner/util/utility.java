package com.harpz.barcodescanner.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;

import com.harpz.barcodescanner.R;

/**
 * Created by Harpz on 3/19/2016.
 */
public class utility {


//Dialog box function
    public void showdialog(final Activity context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.show();
        Button btn = (Button) dialog.findViewById(R.id.dialogclosebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                context.finish();
            }
        });

    }
}
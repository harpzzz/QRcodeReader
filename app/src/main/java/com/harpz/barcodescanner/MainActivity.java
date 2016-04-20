package com.harpz.barcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    static int REQUEST_CODE_BARCODESCANNER = 1;

    @Bind(R.id.emailTextview)
    TextView etv;
    @Bind(R.id.urltextview)
    TextView utv;
    @Bind(R.id.msgtextview)
    TextView mtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    //going to Scan screen
    @OnClick(R.id.btn_barcodescanner)
    public void openbarcodeactivity() {

        startActivityForResult(new Intent(this, ActivityBarcode.class), REQUEST_CODE_BARCODESCANNER);
    }


    //GET RESULT FROM BARCODE SCANNER ACTIVITY
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == REQUEST_CODE_BARCODESCANNER) {

                etv.setText("Display Value :" + data.getStringExtra("displayValue"));
                utv.setText("Raw Value :" + data.getStringExtra("rawValue"));
                etv.setVisibility(View.VISIBLE);
                utv.setVisibility(View.VISIBLE);
                mtv.setVisibility(View.GONE);

                String result = data.getStringExtra("result");
            }
        }
    }
}
package com.example.fireauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class MainActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText t1;
    Button b1;
    private int PERMISSION_CODE = 21;
    private String recordPermission = Manifest.permission.READ_SMS;
    private String recordPermission2 = Manifest.permission.RECEIVE_SMS;

    private boolean checkPermissions(){

        if(ActivityCompat.checkSelfPermission(getApplicationContext(), recordPermission) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), recordPermission2) == PackageManager.PERMISSION_GRANTED){
            return true;

        }

        else {
            ActivityCompat.requestPermissions(this, new String[]{recordPermission}, PERMISSION_CODE);
            ActivityCompat.requestPermissions(this, new String[]{recordPermission2}, PERMISSION_CODE);
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(EditText)findViewById(R.id.t1);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1=(Button)findViewById(R.id.b1);



        boolean perm = checkPermissions();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,manageotp.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });
    }
}
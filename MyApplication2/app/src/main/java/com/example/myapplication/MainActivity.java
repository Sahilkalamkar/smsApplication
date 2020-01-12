package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText txt_phone_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_phone_number = (EditText)findViewById(R.id.txt_phone_number);

    }

    public void btn_send(View view) {

    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

    if(permissionCheck== PackageManager.PERMISSION_GRANTED)
    {

    }
    else {

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);


    }

    }

    private void myMessage()
    {

        //There is a central mobile number
        String phoneNumber = "";
        //This is a fixed message corresponding to the only button present right now.
        String message = "This is a dummy message which is being sent just because i clicked a button";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,message,null,null);

        Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode)
        {
            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    myMessage();
                }
                else
                {
                    Toast.makeText(this,"You don't have the required Permission",Toast.LENGTH_SHORT).show();
                }
        }

    }
}

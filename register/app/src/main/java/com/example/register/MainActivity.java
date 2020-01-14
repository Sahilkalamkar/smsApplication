package com.example.register;

import android.Manifest;
import android.content.Intent;
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

    EditText txt_name;
    EditText txt_phone;
    EditText txt_address;
    private static final int MY_PERMISSION_REQUEST_RECEIVE_SMS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_name = (EditText)findViewById(R.id.txt_name);
        txt_phone = (EditText)findViewById(R.id.txt_phone);
        txt_address = (EditText)findViewById(R.id.txt_address);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS))
            {

                //Do nothing as the user has denied.
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSION_REQUEST_RECEIVE_SMS);

            }
        }


    }

    public void btn_send(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED)
        {
            myMessage();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void myMessage()
    {
        //There is a central mobile number
        String name = txt_name.getText().toString().trim();
        String phNo = txt_phone.getText().toString().trim();
        String address = txt_address.getText().toString().trim();
        String finalMessage = "Name: "+name+" "+"Phone_Number: "+phNo+" "+"Address: "+address;
        //A central mobile number.
        String phoneNumber = "9599220024";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,finalMessage,null,null);

        Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myMessage();
                } else {
                    Toast.makeText(this, "You don't have the required Permission", Toast.LENGTH_SHORT).show();
                }
            case MY_PERMISSION_REQUEST_RECEIVE_SMS:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"Thankyou for permitting",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"Bhak bkl",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

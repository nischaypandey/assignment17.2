package com.example.user.assignment172;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//MainActivity Class extending AppCompatActivity
public class MainActivity extends AppCompatActivity {

   //making objects of Button,TextView,BoundServices
    private Button startBtn;
    TextView textView;
    BoundServices boundServices;
    boolean isBound=false;

    //onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    //setting content View

        //setting objects with there Id's
        startBtn=findViewById(R.id.button);
        textView=findViewById(R.id.text);

        //making service Connection
        ServiceConnection serviceConnection=new ServiceConnection() {

           //onServiceConnected Method
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                //making Object of Local Binder
                BoundServices.MyLocalBinder binder= (BoundServices.MyLocalBinder) iBinder;

               //calling method getServices
                boundServices=binder.getService();
                isBound=true;

            }


         //onServiceDisconnected Method
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isBound=false;

            }
        };
        //making Intent to  Start Service
        Intent intent=new Intent(MainActivity.this,BoundServices.class);
       //bindService to intent call
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);


        //start Button Click Listener
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getApplicationContext(),"hii",Toast.LENGTH_SHORT).show();

                //setting current Date and Time for text View
                String currentTime=boundServices.getCurrentTime();
                textView.setText(currentTime);
            }
        });
    }

}

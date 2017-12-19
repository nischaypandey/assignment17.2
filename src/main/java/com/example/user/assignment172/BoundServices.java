package com.example.user.assignment172;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.SerializablePermission;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 15-12-2017.
 */
//BoundService Class Extending Service
public class BoundServices extends Service {


    //making object of IBinder
    private IBinder myBinder=new MyLocalBinder();

    //IBinder constructor
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

   //getCureentTime Method to Fetch Date and Time


    public String getCurrentTime() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat(" MM/dd/yyyy HH:mm:ss");
        return (dateformat.format(new Date()));
    }
    //MyLocalBinder class
    public class MyLocalBinder extends Binder {
        BoundServices getService() {
            return BoundServices.this;
        }
    }

}

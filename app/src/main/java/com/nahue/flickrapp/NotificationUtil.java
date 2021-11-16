package com.nahue.flickrapp;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NotificationUtil {
    public static void notifyNoInternet(){

    }
    public static int verifyConection(Activity activity){

            ConnectivityManager connectivityManager=(ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=connectivityManager.getActiveNetworkInfo();
            if(info!=null && info.isConnected()){
                //Cambiar esto por una notifiacion
                Toast.makeText(activity,"HAY INTERNET",Toast.LENGTH_SHORT).show();
                return 1;
            }
            else{
                Toast.makeText(activity, "No hay internet", Toast.LENGTH_SHORT).show();
                notifyNoInternet();
                return 0;
            }


    }
}

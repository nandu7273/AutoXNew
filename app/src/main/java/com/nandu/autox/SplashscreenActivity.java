package com.nandu.autox;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */


public class SplashscreenActivity extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    ImageView Imgview;
    Boolean StartUp= false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splashscreen);

        Imgview =(ImageView)findViewById(R.id.ImgViewSplscrn);

        //checkAndRequestPermissions();

      //  getGrantedPermissions();
       final boolean Permission_yes=(checkAndRequestPermissions())? true:false;

        Boolean Internet_yes=(isNetworkConnected())? true:false;



        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "AutoX");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            Toast.makeText(this,"Error Creating Folder",Toast.LENGTH_LONG).show();
        }


        if (Internet_yes!=true) {

            Imgview.setMinimumWidth(400);
            Imgview.setMinimumHeight(400);
            Drawable myDrawable = getResources().getDrawable(R.drawable.ic_signal_wifi_off_black_240dp);
            Imgview.setImageDrawable(myDrawable);



        }else {


//            Imgview.setMinimumWidth(600);
//            Imgview.setMinimumHeight(600);
            Drawable myDrawable = getResources().getDrawable(R.mipmap.logogrand_lay);
            Imgview.setImageDrawable(myDrawable);

            final int SPLASH_DISPLAY_LENGTH = 1200;

            final Intent intent = new Intent(this, MainActivity.class);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    if (checkAndRequestPermissions()) {
//                    }

                    startActivity(intent);
                    SplashscreenActivity.this.finish();
                    // overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }, SPLASH_DISPLAY_LENGTH);

        }

        Imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 0);

             Imgview.setVisibility(View.GONE);
                StartUp=true;




            }
        });



    }


    @Override
    protected void onResume() {


        if (StartUp) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Boolean Internet_yes=(isNetworkConnected())? true:false;

            if (Internet_yes != true) {

                Imgview.setVisibility(View.VISIBLE);
                Imgview.setMinimumWidth(400);
                Imgview.setMinimumHeight(400);
                Drawable myDrawable = getResources().getDrawable(R.drawable.ic_signal_wifi_off_black_24dp);
                Imgview.setImageDrawable(myDrawable);
            } else {
                StartUp = false;
                Intent i = getBaseContext().getPackageManager().
                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }


        }
            super.onResume();

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private  boolean checkAndRequestPermissions() {
//        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.SEND_SMS);
//        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int StroragePermission =ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        int StroragePermissionRead =ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
//
//
//        List<String> listPermissionsNeeded = new ArrayList<>();
//
//        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
//        }
//
//        if (StroragePermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        }
//
//        if (StroragePermissionRead != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//        }
//
//
//        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
//        }
//
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
//            return false;
//        }













        return true;
    }

    List<String> getGrantedPermissions(final String appPackage) {
        List<String> granted = new ArrayList<String>();
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(appPackage, PackageManager.GET_PERMISSIONS);
            for (int i = 0; i < pi.requestedPermissions.length; i++) {
                if ((pi.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
                    granted.add(pi.requestedPermissions[i]);
                }
            }
        } catch (Exception e) {
        }
        return granted;
    }










}

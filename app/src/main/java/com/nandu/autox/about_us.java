package com.nandu.autox;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

public class about_us extends AppCompatActivity {


    PublicShared publicshared;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        final Context context;
        context = getApplicationContext();
        publicshared = (PublicShared) getApplicationContext();
        String versioin = "";

        try {

            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            Log.d("Version", version);
            int verCode = pInfo.versionCode;
            Log.d("Version", String.valueOf(verCode));
            versioin = version;
            Log.d("Versionss", publicshared.getServer_Version());

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d("Versionss", publicshared.getServer_Version());
        }

        if ("ServerDown".equals(publicshared.getServer_Version())) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Server Under Maintenance")
                    .setMessage("Server Under Maintenance Please Wait !")
                    .show();

        }


        if (!versioin.equals(publicshared.getServer_Version())) {
            Log.d("Versionss", publicshared.getServer_Version());





            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

            try{

            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }

            } catch (Exception e){

            }





//
//
//
//
//
//            new AlertDialog.Builder(this)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setTitle("Update Available")
//                    .setMessage("Update Available Please Update and Continue")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            String url = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/download.php?file=appgrand.apk";
//
//                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                            startActivity(browserIntent);
////                            int pid = android.os.Process.myPid();
////                            android.os.Process.killProcess(pid);
////                            System.exit(0);
//
//
////                            MyAsyncTasks asyncTasks = new MyAsyncTasks();
////                            asyncTasks.execute(url);
//
//
////                            Intent returnIntent = new Intent();
////                            returnIntent.putExtra("result","1");
////                            setResult(Activity.RESULT_OK,returnIntent);
////                            finish();
//
//                        }
//
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                            int pid = android.os.Process.myPid();
////                            android.os.Process.killProcess(pid);
////                            System.exit(0);
//
//
//                            Intent returnIntent = new Intent();
//                            setResult(Activity.RESULT_CANCELED, returnIntent);
//                            finish();
//
//                        }
//                    })
//                    .show();


        } else {

            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "1");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }


    }




    class MyAsyncTasks extends AsyncTask<String, String, String> {

        File sdCardRoot;
        String Total = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(about_us.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings)  {

            try {


                //get destination to update file and set Uri
                //TODO: First I wanted to store my update .apk file on internal storage for my app but apparently android does not allow you to open and install
                //aplication with existing package from there. So for me, alternative solution is Download directory in external storage. If there is better
                //solution, please inform us in comment
                String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                String fileName = "AppName.apk";
                destination += fileName;
                final Uri uri = Uri.parse("file://" + destination);
                File file = new File(destination);





                //Delete update file if exists
                if (file.exists())
                    //file.delete() - test this, I think sometimes it doesnt work
                    file.delete();

                //get url of app on server
                String url = about_us.this.getString(R.string.update_app_url);

                //set downloadmanager
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDescription(about_us.this.getString(R.string.notification_description));
                request.setTitle(about_us.this.getString(R.string.app_name));

                //set destination
                request.setDestinationUri(uri);

                final Uri uris = FileProvider.getUriForFile(about_us.this, about_us.this.getApplicationContext().getPackageName() + ".provider", file);


                //Delete update file if exists
                // get download service and enqueue file
                final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                final long downloadId = manager.enqueue(request);

                //set BroadcastReceiver to install app when .apk is downloaded
                BroadcastReceiver onComplete = new BroadcastReceiver() {
                    public void onReceive(Context ctxt, Intent intent) {


                        Intent install = new Intent(Intent.ACTION_VIEW);
                        install.setDataAndType(uris,"application/vnd.android.package-archive");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        install.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
                        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(install);


                        unregisterReceiver(this);



                        finish();


//                        promptInstall.setDataAndType(Uri.fromFile(file),
//                                "application/vnd.android.package-archive");
//                        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(promptInstall);


                    }
                };

                //register receiver for when .apk download is compete
                registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


//
//                HttpURLConnection urlConnection = null;
//
//            String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
//            String fileName = "AppName.apk";
//            destination += fileName;
//
//                URL url = new URL(strings[0]);
//
//                urlConnection = (HttpURLConnection) url.openConnection();
//
//            urlConnection.setRequestMethod("GET");
//                urlConnection.setDoOutput(true);
//                urlConnection.connect();
//
//
//                sdCardRoot = new File(Environment.getExternalStorageDirectory(), "AutoX");
//
//                if (!sdCardRoot.exists()) {
//                    sdCardRoot.mkdirs();
//                }
//
//                Log.e("check_path", "" + sdCardRoot.getAbsolutePath());
//
//
//                        //strings[0].substring(strings[0].lastIndexOf('/') + 1, strings[0].length());
//                Log.e("dfsdsjhgdjh", "" + fileName);
//                File imgFile =
//                        new File(sdCardRoot, fileName);
//                if (!sdCardRoot.exists()) {
//                    imgFile.createNewFile();
//                }
//                InputStream inputStream = urlConnection.getInputStream();
//                int totalSize = urlConnection.getContentLength();
//                FileOutputStream outPut = new FileOutputStream(imgFile);
//                int downloadedSize = 0;
//                byte[] buffer = new byte[2024];
//                int bufferLength = 0;
//                publishProgress(String.valueOf(totalSize));
//                while ((bufferLength = inputStream.read(buffer)) > 0) {
//                    outPut.write(buffer, 0, bufferLength);
//                    downloadedSize += bufferLength;
//                    Log.e("Progress:", "downloadedSize:" + Math.abs(downloadedSize * 100 / totalSize));
//                    publishProgress(String.valueOf(Math.abs(downloadedSize * 100 / totalSize)));
//                }
//                Log.e("Progress:", "imgFile.getAbsolutePath():" + imgFile.getAbsolutePath());
//
//                Log.e("TAG", "check image path 2" + imgFile.getAbsolutePath());
//
//                //mImageArray.add(imgFile.getAbsolutePath());
//                outPut.close();


//

            }catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            if (Total.equals("")) {
                Total = values[0];
            }

            pDialog.setMessage(values[0] + "/" + Total);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // imagecount++;
            //Log.e("check_count", "" + totalimagecount + "==" + imagecount);
            //if (totalimagecount == imagecount) {
            pDialog.dismiss();
            //  imagecount = 0;
            //}
            //Log.e("ffgnjkhjdh", "checkvalue checkvalue" + checkvalue);



            }



        }


    }

package com.nandu.autox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Home_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG_SUCCESS = "success";
    private static String url_prospectus = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_prospectus.php";
    private static final String TAG_Prospects = "Prospect_No";

    private static String url_Profile_info = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_sc_info.php";
    private static String temp = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/temp.php";

    private static final String TAG_Info = "sc_info";
    ArrayList<String> strsc_Info ;

    PublicShared publicshared;
    private ProgressDialog pDialog;
     TextView TxtViewBkCount ;
     TextView TxtViewEnqCount ;
     TextView TvName,TvBranch;
     ImageView imgViewProPic;
    ImageView addEnq,Search_Eq,add_Fp,Edit_Eq;
    ImageView imgviewnv;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  FloatingActionButton fab = findViewById(R.id.fab);


        publicshared = (PublicShared) getApplicationContext();


        TxtViewBkCount = (TextView) findViewById(R.id.TxtViewHpBkngCount);
        TxtViewEnqCount = (TextView) findViewById(R.id.TxtViewHpEnqCount);
        imgViewProPic = findViewById(R.id.ImgViewPropic);
        addEnq = findViewById(R.id.Img_Add);
        Search_Eq = findViewById(R.id.Img_Search);
        add_Fp = findViewById(R.id.Img_Add_fp);
        Edit_Eq = findViewById(R.id.Img_Edit_Eq);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
            new Home_activity.Get_Sc_info().execute();
        } else {
            new Home_activity.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }




        Edit_Eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Home_activity.this, Enquiry_Search.class);
                intent.putExtra("MODE", "SE");
                intent.putExtra("Sc_Id", publicshared.getScId());
                startActivity(intent);
            }
        });


        add_Fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastCalledNumber = "";
                try {
                    lastCalledNumber = CallLog.Calls.getLastOutgoingCall(Home_activity.this);
                } catch (Exception e) {
                }
                if (lastCalledNumber != "") {
                    lastCalledNumber = lastCalledNumber.replace("+91", "");
                    addEnq_Fp(lastCalledNumber);
                } else {
                    Intent intent;
                    intent = new Intent(Home_activity.this, Enquiry_Search.class);
                    intent.putExtra("MODE", "AF");
                    intent.putExtra("Sc_Id", publicshared.getScId());
                    startActivity(intent);
                }


            }
        });


        Search_Eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent = new Intent(Home_activity.this, Enquiry_Search.class);
                intent.putExtra("MODE", "SE");
                intent.putExtra("Sc_Id", publicshared.getScId());
                startActivity(intent);

            }
        });


        addEnq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new Home_activity.ProspectusNo().execute();
                } else {
                    new Home_activity.ProspectusNo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }


            }
        });





//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//}

        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(this);

        View Headview =navigationView.getHeaderView(0);

        imgviewnv = Headview.findViewById(R.id.imageView);
        TvBranch=Headview.findViewById(R.id.textView);
        TvName=Headview.findViewById(R.id.TVname);







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                finish();
            }
        } else {
            finish();
        }
    }//onActi


    public boolean update_bio ()  {

        this.setTitle(publicshared.getScName());
        TxtViewBkCount.setText(publicshared.getBookingCount());
        TxtViewEnqCount.setText(publicshared.getEnqCount());
        TvName.setText(publicshared.getScName());
        TvBranch.setText(publicshared.getBranchName());
try {
    Bitmap b;
    b = Bitmap.createBitmap(imgViewProPic.getWidth(), imgViewProPic.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas c = new Canvas(b);


    Paint textPaint = new Paint();
    textPaint.setARGB(255, 255, 255, 255);
    textPaint.setTextAlign(Paint.Align.CENTER);
    textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC));
    textPaint.setTextSize(150);
    int xPos = (c.getWidth() / 2);
    int yPos = (int) ((c.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
    c.drawText(String.valueOf(publicshared.getScName().charAt(0)), xPos, yPos, textPaint);
    imgViewProPic.setImageBitmap(b);
} catch (Exception e){

}
        if (TxtViewEnqCount.getText().toString().equals("null")){
            return false;
        }else {
            return true;
        }

//        try {
//            URL url = new URL("http://logicxnet.com/AUTOX_GRAND/ENQ/users/default.png");
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            imgViewProPic.setImageBitmap(bmp);
//        } catch (IOException e) {
//        }





    }

    private void addEnq_Fp(final String s){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Choose Action");
        String[] pictureDialogItems = {
                "Do You Want To Search For "+s+"",
                "Add Another One" };

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    Intent intent;
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                            case 0:

                                intent = new Intent(Home_activity.this, Enquiry_Search.class);
                                intent.putExtra("Mob",s);
                                intent.putExtra("MODE", "AF");
                                intent.putExtra("Sc_Id",publicshared.getScId());
                                startActivity(intent);
                                break;
                            case 1:

                                intent = new Intent(Home_activity.this, Enquiry_Search.class);
                                intent.putExtra("MODE", "AF");
                                intent.putExtra("Sc_Id",publicshared.getScId());
                                startActivity(intent);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.camera) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setType("image/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {
            onClickWhatsApp(null);
        } else if (id == R.id.nav_send) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/download.php?file=appgrand.apk"));
            startActivity(browserIntent);

        }else if (id == R.id.nav_help) {

            Intent help ;
            //= new Intent(Home_activity.this, help.Class);

            help = new Intent(Home_activity.this, com.nandu.autox.help.class);

            startActivity(help);


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickWhatsApp(View view) {

        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/download.php?file=appgrand.apk";

            try{

                PackageInfo info=pm.getPackageInfo("com.whatsapp.w4b", PackageManager.GET_META_DATA);
                //Check if package exists or not. If not then code
                //in catch block will be called
                waIntent.setPackage("com.whatsapp.w4b");

                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(waIntent, "Share with"));


            }catch ( PackageManager.NameNotFoundException e ) {


                PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                //Check if package exists or not. If not then code
                //in catch block will be called
                waIntent.setPackage("com.whatsapp");

                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(waIntent, "Share with"));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }


    class Get_Sc_info extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray Sc_Info = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Home_activity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("Sc_Id",publicshared.getScId()));

            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_Profile_info,"GET", params);

                // getting JSON Object
                // Note that create product url accepts POST method

                if(json==null){


                    success = false;

                }else{

                    Log.d("Create Response", json.toString());

                    try {
                        success = json.getBoolean(TAG_SUCCESS);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (success) {

                      //  Sc_Info = json.getJSONArray(TAG_Info);
                        publicshared.setBookingCount(json.getString("Book_Count"));
                        publicshared.setEnqCount(json.getString("Enq_Count"));
                        publicshared.setServer_Version(json.getString("Server_version"));
                        Log.d("Versions", publicshared.getServer_Version());



                        try{
                            bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://logicxnet.com/AUTOX_GRAND/ENQ/users/"+publicshared.getSc_Desig_Code()+"_"+publicshared.getScId()+".jpg").getContent());
                           // imgViewProPic.setImageBitmap(bitmap);
                                // ImageView i = (ImageView)findViewById(R.id.image);

                            if (bitmap==null) {

                              Bitmap bitmapdefault = BitmapFactory.decodeStream((InputStream) new URL("http://logicxnet.com/AUTOX_GRAND/ENQ/users/default.jpg").getContent());
                              bitmap=bitmapdefault;



                            }

                            if (bitmap!=null) {
                                imgViewProPic.setImageBitmap(bitmap);

                            }


                            //imgViewProPic.

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();

                            }


                        Bitmap bit;
                        try{
                            bit = BitmapFactory.decodeStream((InputStream)new URL("http://logicxnet.com/AUTOX_GRAND/ENQ/users/"+publicshared.getSc_Desig_Code()+"_"+publicshared.getScId()+".jpg").getContent());
                            // imgViewProPic.setImageBitmap(bitmap);
                            // ImageView i = (ImageView)findViewById(R.id.image);

                            if (bit==null) {

                                Bitmap bitmapdefaults = BitmapFactory.decodeStream((InputStream) new URL("http://logicxnet.com/AUTOX_GRAND/ENQ/users/default.jpg").getContent());
                                bit=bitmapdefaults;

                            }

                            imgviewnv.setImageBitmap(bit);


                            //imgViewProPic.

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                       //                        for (int i = 0; i < Sc_Info.length(); i++) {
//
//                            try {
//
//
//                                JSONObject obj = Sc_Info.getJSONObject(i);
//                                strsc_Info.add((String) obj.getString("Book_Count"));
//                                strsc_Info.add((String) obj.getString("Enq_Count"));
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }

                      //  publicshared.setCust_City(strcustCity);


                    } else {
                        // failed to create product
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            if (success){


              if(update_bio()){

                  Toast.makeText(getApplicationContext(), "Profile Updated...",Toast.LENGTH_LONG).show();

              }else{
                  Intent i = getBaseContext().getPackageManager().
                          getLaunchIntentForPackage(getBaseContext().getPackageName());
                  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  startActivity(i);

              }

                Intent about = new Intent(Home_activity.this, about_us.class);
                //startActivity(about);

                int resultcode =0;
                startActivityForResult(about,resultcode);

            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();

        }

    }



    class ProspectusNo extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray Prospects_Num = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Home_activity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("BranchID",publicshared.getBranch_Id()));

            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_prospectus,"GET", params);

                // getting JSON Object
                // Note that create product url accepts POST method

                if(json==null){


                    success = false;

                }else{

                    Log.d("Create Response", json.toString());

                    try {
                        success = json.getBoolean(TAG_SUCCESS);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (success) {

                       String Prospects_Number;
                        int Pros_num;
                        Prospects_Number = json.getString(TAG_Prospects);

                      //  strProspects.clear();
//                        for (int i = 0; i < Prospects_Num.length(); i++) {
//
//                            try {
//
//                                JSONObject obj = Prospects_Num.getJSONObject(i);
//                                strProspects.add((String) obj.getString("Pros_Num"));
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }


                        Pros_num   =Integer.parseInt(Prospects_Number);
                        Prospects_Number=String.format("%06d", Pros_num);
                        publicshared.setEq_ProsNo(publicshared.getBranch_Code()+Prospects_Number);

                    } else {
                        // failed to create product
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            if (success){

//                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
//                    new Home_Activity.Get_Veh_Model().execute();
//                } else {
//                    new Home_Activity.Get_Veh_Model().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                }



              /*  Intent intent_BkList = new Intent(getApplicationContext(), BkList.class);
                finish();
                startActivity(intent_BkList);*/

//                if (Prospects_Number.equals("0")){}else{
//                    Pros_num   =Integer.parseInt(Prospects_Number);
//                    Prosp_num=Branch+String.format("%06d", Pros_num);
//                    txtProspectNo.setText(Prosp_num);
//
//                }
                Intent intent;
                intent = new Intent(Home_activity.this, Enquiry_add.class);
                intent.putExtra("Mode","ADD");
                startActivity(intent);


            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();

        }

    }




    @Override
    public void onResume() {
        super.onResume();


//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
//            new Home_activity.Get_Sc_info().execute();
//        } else {
//            new Home_activity.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        }

    }





}

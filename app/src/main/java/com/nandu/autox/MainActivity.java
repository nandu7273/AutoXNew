package com.nandu.autox;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static String url_login="http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/sc_login.php";
    private static String url_updatelogin="http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/update_sc_login.php";
    private static final String TAG_SUCCESS = "success";
    public static final String TAG_SC_DETAILS = "sc_details";
    private static String url_Occupation="http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_occupation.php";


    private static String url_city = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_city.php";
    private static String url_vehModel = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_vehModel.php";
    private static String url_enqSource = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_enqSource.php";




    private static final String TAG_Occupation = "custOccupation";
    private static final String TAG_City = "customerCity";
    private static final String TAG_VehModel = "vehModel";
    private static final String TAG_EnqSource = "enqSource";
    private static final String TAG_EnqStatus = "enqStatus";

    private String androidId="" ;
    ArrayList<String> strcustOccupation ;
    ArrayList<String> strcustCity = new ArrayList<>();
    ArrayList<String> strvehModel =new ArrayList<>();
    HashMap<Integer, String> vehModel = new HashMap<Integer, String>();
    ArrayList<String> strenqSource =new ArrayList<>();
    ArrayList<String> strenqStatus =new ArrayList<>();
    ArrayList<String> BrokName =new ArrayList<>();

    String Messages="";
    EditText usrname;
    EditText psw;
    PublicShared publicshared;


    String Sc_Id="0";
    DB_Helper mydb;
    private ProgressDialog pDialog;


    String Manufacturer_value= Build.MANUFACTURER;;
    String Brand_value = Build.BRAND;
    String Model_value = Build.MODEL;
    String Board_value = Build.BOARD;
    String Hardware_value = Build.HARDWARE;
    String Serial_nO_value = Build.SERIAL;

    String  BootLoader_value = Build.BOOTLOADER;
    String User_value = Build.USER;
    String Host_value = Build.HOST;
    String Version = Build.VERSION.RELEASE;
    String API_level = Build.VERSION.SDK_INT + "";
    String Build_ID = Build.ID;
    String Build_Time = Build.TIME + "";
    String Fingerprint = Build.FINGERPRINT;

   // Manufacturer_value,Brand_value,Model_value,Model_value,Board_value,Hardware_value,Serial_nO_value,android_id,BootLoader_value,User_value,Host_value,Version,API_level,Build_ID,Build_Time,Fingerprint


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mydb = new DB_Helper(this);

        publicshared = (PublicShared) getApplicationContext();

//        Intent intent;
//        intent = new Intent(MainActivity.this, Home_activity.class);
//
//        startActivity(intent);
//        MainActivity.this.finish();
//
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            //TO do here if permission is granted by user
        }
        else
        {
            //ask for permission if user didnot given
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }


        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG},1);


        }

         androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        if (!Check_Sc_Details()){

//
//            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
//                new VarifyUser().execute();
//            } else {
//                new VarifyUser().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            }
        }
        else {
//            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
//                new UpdateSc().execute();
//            } else {
//                new UpdateSc().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            }

        }






     //   String Manufacturer_value = Build.MANUFACTURER;
//        String Brand_value = Build.BRAND;
//        String Model_value = Build.MODEL;
//        String   Board_value = Build.BOARD;
//        String Hardware_value = Build.HARDWARE;
//        String Serial_nO_value = Build.SERIAL;
//        String android_id =
//                Settings.Secure.getString(this.getContentResolver(),
//                        Settings.Secure.ANDROID_ID);
//        String  BootLoader_value = Build.BOOTLOADER;
//        String User_value = Build.USER;
//        String Host_value = Build.HOST;
//        String Version = Build.VERSION.RELEASE;
//        String API_level = Build.VERSION.SDK_INT + "";
//        String Build_ID = Build.ID;
//        String Build_Time = Build.TIME + "";
//        String Fingerprint = Build.FINGERPRINT;






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ImgView = findViewById(R.id.ImgViewLogin);


        usrname= findViewById(R.id.EditTxtUsername);
        psw= findViewById(R.id.EditTxtpassword);

        if (publicshared.getScId().length()>0){usrname.setText(publicshared.getMobileNum());}

        usrname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (usrname.getText().toString().length() ==10) {
//                    ViewDialog alert = new ViewDialog();
//                    alert.showDialog(MainActivity.this);
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ImgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (usrname.getText().toString().length() ==10 && psw.getText().toString().length()>0) {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(MainActivity.this);
                }
                 else{
                        Toast.makeText(getApplicationContext(),"Mobile Number and Password required ",Toast.LENGTH_LONG).show();
                }
            }
        });




        }





    public class ViewDialog {

        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.newcustom_layout);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            FrameLayout mDialogNo = dialog.findViewById(R.id.frmNo);
            CardView cardview =dialog.findViewById(R.id.cardviewpop);
            TextView TxtNum = dialog.findViewById(R.id.TxtViewNumber);
            String mobile_num =usrname.getText().toString();
            TxtNum.setText("Confirm Login As "+ "\n" +"\n" +  mobile_num);

            mDialogNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            FrameLayout mDialogOk = dialog.findViewById(R.id.frmOk);
            mDialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
//                    dialog.cancel();

                    String Mob=usrname.getText().toString();
                    String Psw =psw.getText().toString();
                    publicshared.setMobileNum(Mob);
                    publicshared.setPassword(Psw);

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                        new VarifyUser().execute();
                    } else {
                        new VarifyUser().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }

                }
            });


            FloatingActionButton fab = dialog.findViewById(R.id.float_bt_ok);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
//                    dialog.cancel();

                    String Mob=usrname.getText().toString();
                    String Psw =psw.getText().toString();
                    publicshared.setMobileNum(Mob);
                    publicshared.setPassword(Psw);

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                        new VarifyUser().execute();
                    } else {
                        new VarifyUser().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }

                }
            });

            FloatingActionButton fabno = dialog.findViewById(R.id.float_bt_no);
            fabno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
//                    dialog.cancel();

                    dialog.dismiss();
                }
            });


            dialog.show();
        }
    }

    private boolean Check_Sc_Details(){

        boolean is_sc_ok= false;

        mydb= new DB_Helper(this);
        mydb.getWritableDatabase();
        SQLiteDatabase db;
        db = mydb.getReadableDatabase();

        String selectQuery = "SELECT Sc_Id,Sc_Name,Sc_Desig_Code,Branch_Code,BranchId,Branch_Name,Sc_Desig_Name,Mobile FROM sc_details";

        Cursor c;
        try {
              c= db.rawQuery(selectQuery, null);

            }catch (Exception e){

                return is_sc_ok;

            }

        // looping through all rows and adding to list
        try{
            if (c.moveToFirst()) {
                do {

                    if(c.getInt(0)>=0){
                        is_sc_ok = true;
                        //Toast.makeText(this,  c.getString(1), Toast.LENGTH_SHORT).show();
                        publicshared.setScId(String.valueOf(c.getInt(0)));
                        publicshared.setScName(c.getString(1));
                        publicshared.setSc_Desig_Code(c.getString(2));
                        publicshared.setBranch_Code(c.getString(3));
                        publicshared.setBranch_Id(c.getString(4));
                        publicshared.setBranchName(c.getString(5));
                        publicshared.setSc_Desig_Name(c.getString(6));
                        publicshared.setMobileNum(c.getString(7));

                    }else{
                        //Toast.makeText(this, "SC" + c.getInt(0), Toast.LENGTH_SHORT).show();
                    }

                } while (c.moveToNext());
                //is_sc_ok=true;
            }
        }finally{
            c.close();
        }
        db.close();

        return is_sc_ok;
    }


    class VarifyUser extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray sc_details = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Verifying details.." + "\n" + "Please wait...");
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
            //Enq_HeaderId,Req_Type,Req_DateTime,Req_ContactNo,Req_Name,Req_Remarks,CreatedOn

            params.add(new BasicNameValuePair("Sc_Mno",publicshared.getMobileNum()));
            params.add(new BasicNameValuePair("Psw",publicshared.getPassword()));
            params.add(new BasicNameValuePair("androidId",publicshared.getMobileNum()+ "_"+androidId ));

            try{
                if (publicshared.getPassword().toString().length()==0){

                    cancel(true);
                    pDialog.dismiss();

                }


                if (publicshared.getScName().toString().length()>0){

                    params.add(new BasicNameValuePair("AppOK","1"));

                }else{
                    params.add(new BasicNameValuePair("AppOK","0"));
//                    String Dev=  "("+Manufacturer_value+")("+Brand_value+")("+Model_value+")("+Model_value+")("+Board_value+")("+Hardware_value+")("+Version+")("+API_level+")";
//                    params.add(new BasicNameValuePair("DeviceDetails",Dev));


                }

                String Dev=  "("+Manufacturer_value+")("+Brand_value+")("+Model_value+")("+Model_value+")("+Board_value+")("+Hardware_value+")("+Version+")("+API_level+")";
                params.add(new BasicNameValuePair("DeviceDetails",Dev));


            }catch (Exception v){

            }


            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_login, "GET", params);

                // getting JSON Object
                // Note that create product url accepts POST method

                if (json == null) {
                    success = false;

                    SQLiteDatabase db = mydb.getWritableDatabase();
                    try { db.delete("sc_details","Mobile='"+publicshared.getMobileNum()+"'", null); }
                    catch (Exception e){e.printStackTrace();}

                } else {

                    // check log cat fro response
                    Log.d("Create Response", json.toString());

                    try {
                        success = json.getBoolean(TAG_SUCCESS);

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (success) {
                        sc_details = json.getJSONArray(TAG_SC_DETAILS);
                        // successfully created product

                        // closing this screen
                        //finish();
                    } else {
                        // failed to create product
                         Messages =json.getString("message");

                    }
                }


            } catch (JSONException e) {
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
                //Toast.makeText(getApplicationContext(), "Verified Successfully",Toast.LENGTH_LONG).show();

                for (int i = 0; i < sc_details.length(); i++) {
                    JSONObject c;
                    try {

                        c = sc_details.getJSONObject(i);
                        Insert_Sc_Details(c);

                        //Toast.makeText(getApplicationContext(), id+ " " + name,Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getApplicationContext(), "Error Reading" + e.getMessage().toString(),Toast.LENGTH_LONG).show();

                    }

                }

	        		 /*setContentView(R.layout.info);
	        		 TextView tvinfo = (TextView) findViewById(R.id.tvInfo );
	        		 tvinfo.setTypeface(tf);
	        		 tvinfo.setText("Dear customer," + "\n" + "Thank you for booking the vehicle.Our customer representative will contact you soon.");*/


            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), Messages,Toast.LENGTH_LONG).show();

            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), Messages,Toast.LENGTH_LONG).show();

            }
            pDialog.dismiss();

            if (success){

//                Intent i = getBaseContext().getPackageManager().
//                        getLaunchIntentForPackage(getBaseContext().getPackageName());
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);



                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                     new UpdateSc().execute();
                } else {
                    new UpdateSc().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }

        }



    }

    private boolean Insert_Sc_Details(JSONObject c){

        boolean is_sc_ok= false;
        SQLiteDatabase db = mydb.getWritableDatabase();
        ContentValues values = new ContentValues();
        long newRowId=-1;
        String Sc_Details;

        try {
            values.put("Sc_Id", c.getString("Sc_Id"));
            values.put("Sc_Code", c.getString("Sc_Code"));
            values.put("Sc_Name", c.getString("Sc_Name"));
            values.put("Sc_Desig_Code", c.getString("Sc_Desig_Code"));
            values.put("Branch_Code",c.getString("Branch_Code"));
            values.put("BranchId",c.getString("BranchId"));
            values.put("Branch_Name",c.getString("Branch_Name"));
            values.put("Sc_Desig_Name",c.getString("Sc_Desig_Name"));
            values.put("Mobile",publicshared.getMobileNum());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        db.delete("sc_details","Mobile='"+publicshared.getMobileNum()+"'", null);


        newRowId = db.insert(TAG_SC_DETAILS,null, values);

        if (newRowId>=0){

            is_sc_ok = true;
            Sc_Details = "Id : " + values.get("Sc_Id") + "\n" +
                    "Code : " + values.get("Sc_Code") + "\n" +
                    "Branch_Code : "+ values.get("Branch_Code")+"\n"+
                    "Name : " + values.get("Sc_Name");

//            usrName.setVisibility(View.VISIBLE);
//            //.setVisibility(View.VISIBLE);
//            usrName.setText(Sc_Details);

        }

        db.close();

        return is_sc_ok;
    }


    /**
     * Background Async Task to Create new TD
     * */
    class UpdateSc extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray sc_details = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Updating details.." + "\n" + "Please wait...");
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
            //Enq_HeaderId,Req_Type,Req_DateTime,Req_ContactNo,Req_Name,Req_Remarks,CreatedOn

            params.add(new BasicNameValuePair("Sc_MNo",publicshared.getMobileNum()));
           // params.add(new BasicNameValuePair("psw",password.getText().toString()));











            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_updatelogin,"GET", params);

                // getting JSON Object
                // Note that create product url accepts POST method

                if(json==null){
                    success = false;
                }else{

                    // check log cat fro response
                    Log.d("Create Response", json.toString());

                    try {
                        success = json.getBoolean(TAG_SUCCESS);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
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

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                     new Get_Cust_Occupt().execute();
                } else {
                    new Get_Cust_Occupt().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                SQLiteDatabase db = mydb.getWritableDatabase();
                db.delete("sc_details","Mobile='"+publicshared.getMobileNum()+"'", null);

                Toast.makeText(getApplicationContext(), "Sorry,failed to update details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();

        }



    }




    class Get_Cust_Occupt extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray Cust_Occupt = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
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

            params.add(new BasicNameValuePair("FId","0"));

            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_Occupation,"GET", params);

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


                        JSONArray Cust_Occupt = null;
                        JSONArray Enq_Status = null;

                        Enq_Status = json.getJSONArray("Ex_BrokerName");

                        BrokName.clear();

                        // HashMap<Integer, String> vehModel = new HashMap<Integer, String>() {{
                        for (int i = 0; i < Enq_Status.length(); i++) {
                            try {
                                JSONObject obj = Enq_Status.getJSONObject(i);

                                BrokName.add(obj.getString("Ex_BrokerName1"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        publicshared.setBrokeName(BrokName);



                        Cust_Occupt = json.getJSONArray(TAG_Occupation);
                        strcustOccupation= new ArrayList<>();
                        for (int i = 0; i < Cust_Occupt.length(); i++) {

                            try {

                                JSONObject obj = Cust_Occupt.getJSONObject(i);
                                strcustOccupation.add((String) obj.getString("Cust_Occupt"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        publicshared.setCust_Occupt(strcustOccupation);


//                        JSONArray Cust_City = null;
//                        Cust_City = json.getJSONArray(TAG_City);
//                        strcustCity.clear();
//                        for (int i = 0; i < Cust_City.length(); i++) {
//
//                            try {
//
//                                JSONObject obj = Cust_City.getJSONObject(i);
//                                strcustCity.add((String) obj.getString("Cust_City"));
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                        publicshared.setCust_City(strcustCity);
//
//                        JSONArray Veh_Model = null;
//                        Veh_Model = json.getJSONArray(TAG_VehModel);
//
//                        strvehModel.clear();
//
//                        // HashMap<Integer, String> vehModel = new HashMap<Integer, String>() {{
//                        for (int i = 0; i < Veh_Model.length(); i++) {
//                            try {
//                                JSONObject obj = Veh_Model.getJSONObject(i);
//                                vehModel. put(obj.getInt("veh_model"), obj.getString("Veh_Model_Name"));
//
//                                strvehModel.add(obj.getString("Veh_Model_Name"));
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        publicshared.setVeh_Model(vehModel);
//                        publicshared.setVehName(strvehModel);
//
//
//                        JSONArray Enq_Source = null;
//                        Enq_Source = json.getJSONArray(TAG_EnqSource);
//
//                        strenqSource.clear();
//
//                        // HashMap<Integer, String> vehModel = new HashMap<Integer, String>() {{
//                        for (int i = 0; i < Enq_Source.length(); i++) {
//                            try {
//                                JSONObject obj = Enq_Source.getJSONObject(i);
//
//                                strenqSource.add(obj.getString("Enq_Source"));
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        publicshared.setEnq_Source(strenqSource);
//





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

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new Get_Cust_City().execute();
                } else {
                    new Get_Cust_City().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }


//                Intent intent_BkList = new Intent(getApplicationContext(), BkList.class);
//                finish();
//                startActivity(intent_BkList);


            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();




        }



    }


    class Get_Cust_City extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray Cust_City = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
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

            // params.add(new BasicNameValuePair("FId","0"));

            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_city,"GET", params);

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

                        Cust_City = json.getJSONArray(TAG_City);
                        strcustCity.clear();
                        for (int i = 0; i < Cust_City.length(); i++) {

                            try {

                                JSONObject obj = Cust_City.getJSONObject(i);
                                strcustCity.add((String) obj.getString("Cust_City"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        publicshared.setCust_City(strcustCity);


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

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new Get_Veh_Model().execute();
                } else {
                    new Get_Veh_Model().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }



              /*  Intent intent_BkList = new Intent(getApplicationContext(), BkList.class);
                finish();
                startActivity(intent_BkList);*/


            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();

        }

    }



    class Get_Veh_Model extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray Veh_Model = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
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

            // params.add(new BasicNameValuePair("FId","0"));

            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_vehModel,"GET", params);

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

                        Veh_Model = json.getJSONArray(TAG_VehModel);

                        strvehModel.clear();

                        // HashMap<Integer, String> vehModel = new HashMap<Integer, String>() {{
                        for (int i = 0; i < Veh_Model.length(); i++) {
                            try {
                                JSONObject obj = Veh_Model.getJSONObject(i);
                                vehModel. put(obj.getInt("veh_model"), obj.getString("Veh_Model_Name"));

                                strvehModel.add(obj.getString("Veh_Model_Name"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        publicshared.setVeh_Model(vehModel);
                        publicshared.setVehName(strvehModel);


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

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new Get_Enq_Source().execute();
                } else {
                    new Get_Enq_Source().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

               /* Intent intent_BkList = new Intent(getApplicationContext(), BkList.class);
                finish();
                startActivity(intent_BkList);
*/

            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();




        }



    }



    class Get_Enq_Source extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray Enq_Source = null;
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
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

            // params.add(new BasicNameValuePair("FId","0"));

            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_enqSource,"GET", params);

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

                        Enq_Source = json.getJSONArray(TAG_EnqSource);

                        strenqSource.clear();

                        // HashMap<Integer, String> vehModel = new HashMap<Integer, String>() {{
                        for (int i = 0; i < Enq_Source.length(); i++) {
                            try {
                                JSONObject obj = Enq_Source.getJSONObject(i);

                                strenqSource.add(obj.getString("Enq_Source"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        publicshared.setEnq_Source(strenqSource);


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

//                if(Mode.equals("ADD")){
//                    Intent intent_BkUpdate = new Intent(getApplicationContext(), Bkupdate.class);
//                    // finish();
////                    intent_BkUpdate.putExtra("Sc_Id",Sc_Id);
//
//                    // intent_BkUpdate.putExtra("Mode",Mode );
//                    startActivity(intent_BkUpdate);
//
//
//                }else if (Mode.equals("SER")){
//
//                    Intent intent_BkList = new Intent(getApplicationContext(), BkList.class);
//                    PendingIntent pendingIntent =
//                            TaskStackBuilder.create(Home_Activity.this)
//                                    // add all of DetailsActivity's parents to the stack,
//                                    // followed by DetailsActivity itself
//                                    .addNextIntentWithParentStack(intent_BkList)
//                                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Home_Activity.this);
//                    builder.setContentIntent(pendingIntent);
//                    intent_BkList.putExtra("Sc_Id",Sc_Id);
//                    //finish();
//                    startActivity(intent_BkList);
//                }
                Intent intent;
                intent = new Intent(MainActivity.this, new_dashboard.class);

                startActivity(intent);
                MainActivity.this.finish();


            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();




        }



    }




}

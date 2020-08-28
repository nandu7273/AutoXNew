package com.nandu.autox;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class dashboard extends AppCompatActivity {


    private ProgressDialog pDialog;
    PublicShared publicshared;
    private static String url_Profile_info = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_sc_info.php";
    private static String url_prospectus = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_prospectus.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Prospects = "Prospect_No";
    Bitmap bitmap;
    private  int mYear;
    private  int mMonth;
    private  int mDay;

    ImageView  img_eqcount ;
    ImageView  img_bkcount ;
    ImageView  img_invcount ;
    ImageView  img_delcount ;
    ImageView  img_pencount ;
    ImageView  img_tdcount;
    ImageView  img_pro_pic ;
    ImageView  img_dash0 ;
    ImageView  img_dash1 ;
    ImageView  img_dash2 ;
    ImageView  img_dash3 ;
    ImageView  img_search;


    TextView tv_ScName;
    TextView tv_Sc_Design;
    TextView tv_Sc_Branch;
    TextView tv_Fromdate;
    TextView tv_Todate;
    TextView tv_totalEq;
    TextView tv_booking;
    TextView tv_invoiced;
    TextView tv_delivered;
    TextView tv_pendingtsk;
    TextView tv_testdrive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        publicshared = (PublicShared) getApplicationContext();

        img_eqcount =findViewById(R.id.img_view_Eq_count);
        img_bkcount =findViewById(R.id.img_view_book_count);
        img_delcount =findViewById(R.id.img_view_delivered_count);
        img_invcount =findViewById(R.id.img_view_Invoice_count);
        img_pencount =findViewById(R.id.img_view_pending_count);
        img_tdcount=findViewById(R.id.img_view_TestDrive_count);
        img_pro_pic=findViewById(R.id.img_pro_Pic);
        img_dash0 =findViewById(R.id.img_view_dash_0 );
        img_dash1 =findViewById(R.id.img_view_dash_1);
        img_dash2 =findViewById(R.id.img_view_dash_2);
        img_dash3 =findViewById(R.id.img_view_dash_3);
        img_search = findViewById(R.id.imageView_Search);

        tv_Sc_Design =findViewById(R.id.text_view_Positon);
        tv_ScName=findViewById(R.id.text_view_Name);
        tv_Sc_Branch=findViewById(R.id.text_view_branch);
        tv_Fromdate=findViewById(R.id.text_view_from_date);
        tv_Todate=findViewById(R.id.text_view_to_date);
        tv_totalEq=findViewById(R.id.text_view_total_eq);
        tv_booking=findViewById(R.id.text_view_booking_count);
        tv_invoiced=findViewById(R.id.text_view_invoice_count);
        tv_delivered=findViewById(R.id.text_view_delivery_count);
        tv_pendingtsk=findViewById(R.id.text_view_pending_count);
        tv_testdrive=findViewById(R.id.text_view_test_drive_count);


        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
            new dashboard.Get_Sc_info().execute();
        } else {
            new dashboard.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

       // setDash_Head_Bitmaps("img_dash3");

        img_dash0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new dashboard.ProspectusNo().execute();
                } else {
                    new dashboard.ProspectusNo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

            }
        });


        img_dash1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent = new Intent(dashboard.this, Enquiry_Search.class);
                intent.putExtra("MODE", "SE");
                intent.putExtra("Sc_Id", publicshared.getScId());
                startActivity(intent);
            }
        });


        img_dash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastCalledNumber = "";
                try {
                    lastCalledNumber = CallLog.Calls.getLastOutgoingCall(dashboard.this);
                } catch (Exception e) {
                    Log.e("callException",e.getMessage());
                }
                if (lastCalledNumber != "") {
                    lastCalledNumber = lastCalledNumber.replace("+91", "");
                    addEnq_Fp(lastCalledNumber);
                } else {
                    Intent intent;
                    intent = new Intent(dashboard.this, Enquiry_Search.class);
                    intent.putExtra("MODE", "AF");
                    intent.putExtra("Sc_Id", publicshared.getScId());
                    startActivity(intent);
                }

            }
        });


        tv_Fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    hideKeyboard(dashboard.this);
                    // Process to get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    // Launch Date Picker Dialog
                    DatePickerDialog dpd = new DatePickerDialog(dashboard.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // Display Selected date in textbox
                                    tv_Fromdate.setText(dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    dpd.show();


            }
        });

        tv_Todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard(dashboard.this);
                // Process to get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(dashboard.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                tv_Todate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();


            }
        });



        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new dashboard.Get_Sc_info().execute();
                } else {
                    new dashboard.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

                if ("From Date".equals(tv_Fromdate.getText()) || "To Date".equals(tv_Todate.getText())){}else {
                    tv_booking.setText("Total Booking");
                    tv_invoiced.setText("Total Invoiced");
                    tv_delivered.setText("Total Delivery");
                    tv_testdrive.setText("Total TestDrive");
                }

            }
        });

        img_search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                CheckBox checkBox_all = findViewById(R.id.check_box_all);
                checkBox_all.setChecked(false);
                tv_Fromdate.setText("From Date");
                tv_Todate.setText("To Date");
                tv_booking.setText("Booking On This Month");
                tv_invoiced.setText("Invoiced On This Month");
                tv_delivered.setText("Delivery On This Month");
                tv_testdrive.setText("TestDrive On This Month");

//                if ("From Date".equals(tv_Fromdate.getText().toString())) {
//
//                }else if ("To Date".equals(tv_Todate.getText().toString())) {

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new dashboard.Get_Sc_info().execute();
                } else {
                    new dashboard.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

                    return false;
            }
        });


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

                                intent = new Intent(dashboard.this, Enquiry_Search.class);
                                intent.putExtra("Mob",s);
                                intent.putExtra("MODE", "AF");
                                intent.putExtra("Sc_Id",publicshared.getScId());
                                startActivity(intent);
                                break;
                            case 1:

                                intent = new Intent(dashboard.this, Enquiry_Search.class);
                                intent.putExtra("MODE", "AF");
                                intent.putExtra("Sc_Id",publicshared.getScId());
                                startActivity(intent);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }






    public boolean update_bio ()  {


        try {
            Bitmap d;
            d = Bitmap.createBitmap(img_eqcount.getWidth(), img_eqcount.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas e = new Canvas(d);
            Paint textPaints = new Paint();
            textPaints.setARGB(255, 255, 255, 255);
            textPaints.setTextAlign(Paint.Align.CENTER);
            textPaints.setTypeface(Typeface.create(Typeface.SANS_SERIF , Typeface.BOLD_ITALIC));
            textPaints.setTextSize(150);
            setTextSizeForWidth(textPaints,img_eqcount.getWidth(),"MM");
            int xPoss = (e.getWidth() / 2);
            int yPoss = (int) ((e.getHeight() / 2) - ((textPaints.descent() + textPaints.ascent()) / 2));
            e.drawText(String.valueOf(publicshared.getScName().charAt(0)), xPoss, yPoss, textPaints);
            img_pro_pic.setImageBitmap(d);
        }catch (Exception w){
            Log.d("Excebitmapcreate",w.getMessage());
        }


        tv_ScName.setText(publicshared.getScName());
        tv_Sc_Design.setText(publicshared.getSc_Desig_Name());
        tv_Sc_Branch.setText(publicshared.getBranchName());

        setBitmaps("img_bkcount");
        setBitmaps("img_invcount");
        setBitmaps("img_pencount");
        setBitmaps("img_delcount");
        setBitmaps("img_eqcount");
        setBitmaps("img_tdcount");

        setDash_Head_Bitmaps("img_dash0");
        setDash_Head_Bitmaps("img_dash1");
        setDash_Head_Bitmaps("img_dash2");
        setDash_Head_Bitmaps("img_dash3");



//        this.setTitle(publicshared.getScName());
//        TxtViewBkCount.setText(publicshared.getBookingCount());
//        TxtViewEnqCount.setText(publicshared.getEnqCount());
//        TvName.setText(publicshared.getScName());
//        TvBranch.setText(publicshared.getBranchName());

    return  true;

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    public void setBitmaps(String Imgname ){

        try{

            Bitmap b;
            b = Bitmap.createBitmap(img_eqcount.getWidth(),img_eqcount.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            Paint textPaint = new Paint();
            textPaint.setARGB(255, 255, 255, 255);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.BOLD_ITALIC));
            textPaint.setTextSize(75);
            int xPos = (c.getWidth() / 2);
            int yPos = (int) ((c.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;

            setTextSizeForWidth(textPaint,img_eqcount.getWidth(),"123456789");

            if (Imgname.equals("img_eqcount")){

                c.drawText(String.valueOf(publicshared.geteqcount()),xPos,yPos,textPaint);

                img_eqcount.setImageBitmap(b);
            }else if(Imgname.equals("img_bkcount")){
                c.drawText(String.valueOf(publicshared.getBkcount()),xPos,yPos,textPaint);


                img_bkcount.setImageBitmap(b);
            }else if(Imgname.equals("img_invcount")){
                c.drawText(String.valueOf(publicshared.getinvcount()),xPos,yPos,textPaint);

                img_invcount.setImageBitmap(b);
            }else if(Imgname.equals("img_pencount")){
                c.drawText(String.valueOf(publicshared.getpencount()),xPos,yPos,textPaint);


                img_pencount.setImageBitmap(b);
            }else if(Imgname.equals("img_delcount")){
                c.drawText(String.valueOf(publicshared.getdelcount()),xPos,yPos,textPaint);


                img_delcount.setImageBitmap(b);
            }else if(Imgname.equals("img_tdcount")){
                c.drawText(String.valueOf(publicshared.gettestdrive_count()),xPos,yPos,textPaint);


                img_tdcount.setImageBitmap(b);
            }

        }catch (Exception s){
            Log.d("Excebitmapcreate",s.getMessage());
        }

       // img.setImageBitmap(b);
    }


    public void setDash_Head_Bitmaps(String Img_name ){

        try{

            Bitmap b;
            b = Bitmap.createBitmap(img_dash0 .getWidth(),img_dash0.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            Paint textPaint = new Paint();
            textPaint.setARGB(255, 255, 255, 255);
            textPaint.setTextAlign(Paint.Align.CENTER);

            textPaint.setTypeface(Typeface.create(Typeface.SERIF , Typeface.BOLD));

            textPaint.setTextSize(75);




            int xPos = (c.getWidth() / 2);
            int yPos = (int) ((c.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;

            if (Img_name.equals("img_dash0")){
                setTextSizeForWidth(textPaint,img_dash0.getWidth()/2,"Search Enquiry");
                c.drawText("Add Enquiry",xPos,yPos,textPaint);

                img_dash0.setImageBitmap(b);
            }else if(Img_name.equals("img_dash1")){
                setTextSizeForWidth(textPaint,img_dash0.getWidth()/2,"Search Enquiry");
                c.drawText("Search Enquiry",xPos,yPos,textPaint);

                img_dash1.setImageBitmap(b);
            }else if(Img_name.equals("img_dash2")){
                setTextSizeForWidth(textPaint,img_dash0.getWidth()/2,"Search Enquiry");
                c.drawText("Pending Task",xPos,yPos,textPaint);

                img_dash2.setImageBitmap(b);
            }else if(Img_name.equals("img_dash3")){
                setTextSizeForWidth(textPaint,img_dash0.getWidth()/2,"Search Enquiry");
               c.drawText("Utilities",xPos,yPos,textPaint);
                img_dash3.setImageBitmap(b);
            }
//            else if(Imgname.equals("img_delcount")){
//                c.drawText(String.valueOf(publicshared.getdelcount()),xPos,yPos,textPaint);
//
//
//                img_delcount.setImageBitmap(b);
//            }

        }catch (Exception s){
            Log.d("Excebitmapcreate",s.getMessage());
        }

        // img.setImageBitmap(b);
    }



    private static void setTextSizeForWidth(Paint paint, float desiredWidth,
                                            String text) {

        // Pick a reasonably large value for the test. Larger values produce
        // more accurate results, but may cause problems with hardware
        // acceleration. But there are workarounds for that, too; refer to
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        final float testTextSize = 48f;

        // Get the bounds of the text, using our testTextSize.
        paint.setTextSize(testTextSize);
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        // Calculate the desired size as a proportion of our testTextSize.
        float desiredTextSize = testTextSize * desiredWidth / bounds.width();

        // Set the paint for that size.
        paint.setTextSize(desiredTextSize);
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
            pDialog = new ProgressDialog(dashboard.this);
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

            if ("From Date".equals(tv_Fromdate.getText().toString())) {

            }else if ("To Date".equals(tv_Todate.getText().toString())) {

            }else{
                String fromdate=changeDateFormat("dd/MM/yyyy","yyyy-MM-dd",tv_Fromdate.getText().toString());
                String todate=changeDateFormat("dd/MM/yyyy","yyyy-MM-dd",tv_Todate.getText().toString());

                Log.e("date",fromdate + todate);

                params.add(new BasicNameValuePair("from_date",fromdate));
                params.add(new BasicNameValuePair("to_date",todate));
               // params.add(new BasicNameValuePair("ALL","1"));
            }

            CheckBox checkBox_all = findViewById(R.id.check_box_all);
            if(checkBox_all.isChecked()){
                params.add(new BasicNameValuePair("ALL","1"));
            }else{
                params.add(new BasicNameValuePair("ALL","0"));
            }


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


                      //  publicshared.setBookingCount(json.getString("Book_Count"));
                        publicshared.setEnqCount(json.getString("Enq_Count"));
                        publicshared.setServer_Version(json.getString("Server_version"));
                        publicshared.setBkcount(json.getString("BookingCount"));
                        publicshared.setinvcount(json.getString("InvoicedCount"));
                        publicshared.setpencount(json.getString("PendingCount"));
                        publicshared.setdelcount(json.getString("DeliveredCount"));
                        publicshared.seteqcount(json.getString("Enq_Count"));
                        publicshared.setTestdrive_count(json.getString("TestDriveCount"));


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
                              //  imgViewProPic.setImageBitmap(bitmap);

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

                           // imgviewnv.setImageBitmap(bit);


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

                Intent about = new Intent(dashboard.this, about_us.class);
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
            pDialog = new ProgressDialog(dashboard.this);
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
                intent = new Intent(dashboard .this, Enquiry_add.class);
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


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
            new dashboard.Get_Sc_info().execute();
        } else {
            new dashboard.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private String changeDateFormat(String currentFormat,String requiredFormat,String dateString){
        String result="";
        if (dateString==""){
            return result;
        }
        SimpleDateFormat formatterOld = new SimpleDateFormat(currentFormat, Locale.getDefault());
        SimpleDateFormat formatterNew = new SimpleDateFormat(requiredFormat, Locale.getDefault());
        Date date=null;
        try {
            date = formatterOld.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            result = formatterNew.format(date);
        }
        return result;
    }

}

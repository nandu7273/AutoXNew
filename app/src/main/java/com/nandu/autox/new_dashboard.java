package com.nandu.autox;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class new_dashboard extends AppCompatActivity {

    RecyclerView recyclerView;

    ImageView ProPic;
    TextView ScName,About,EnqCount;
    String userName,about,enqCount;
    TextView Txt_FP_pend,Txt_delivery_pend,Txt_enqGp,Txt_retGp,Txt_CF_enq,Txt_retCF,Txt_ret_target,Txt_enq_target,Txt_live_booking,Txt_total_ret;
    String FP_pend,delivery_pend,enqGp,retGp,CF_enq,retCF,ret_target,enq_target,live_booking,total_ret;
    EditText Edit_text_from,Edit_text_to;
    FloatingActionButton Fab;
    boolean applyFilter =false;

    private static String url_Profile_info = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_scinfo.php";
   // private static String url_Profile_info = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_sc_info.php";
    private static String url_prospectus = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_prospectus.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Prospects = "Prospect_No";
    private ProgressDialog pDialog;
    PublicShared publicshared;
    private String m_Text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dashboard);


        ProPic=findViewById(R.id.profile_image );

        publicshared = (PublicShared) getApplicationContext();

        ScName=findViewById(R.id.username);
        About=findViewById(R.id.about );
        EnqCount=findViewById(R.id.txt_total_enq);
        Txt_FP_pend=findViewById(R.id.txt_FP_pend);
        Txt_delivery_pend=findViewById(R.id.txt_delivery_pend);
        Txt_enqGp=findViewById(R.id.txt_enqGp);
        Txt_retGp=findViewById(R.id.txt_retGp);
        Txt_CF_enq=findViewById(R.id.txt_CF_enq);
        Txt_retCF=findViewById(R.id.txt_retCF);
        Txt_ret_target=findViewById(R.id.txt_ret_target);
        Txt_enq_target=findViewById(R.id.txt_enq_target);
        Txt_live_booking=findViewById(R.id.txt_live_booking);
        Txt_total_ret=findViewById(R.id.txt_total_ret);
        Edit_text_from =findViewById(R.id.edit_text_from);
        Edit_text_to =findViewById(R.id.edit_text_to);
        Fab=findViewById(R.id.fab);

        Edit_text_from.setVisibility(View.GONE);
        Edit_text_to.setVisibility(View.GONE);



        Txt_enq_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new new_dashboard.ProspectusNo().execute();
                } else {
                    new new_dashboard.ProspectusNo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        });

        Txt_FP_pend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(new_dashboard.this, Enquiry_Search.class);
                intent.putExtra("MODE", "AF");
                intent.putExtra("Sc_Id", publicshared.getScId());
                startActivity(intent);
            }
        });


        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(new_dashboard.this, Enquiry_Search.class);
                intent.putExtra("MODE", "SE");
                intent.putExtra("Sc_Id", publicshared.getScId());
                startActivity(intent);
            }
        });

        ProPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(applyFilter){
                    applyFilter=false;
                    Edit_text_from.setVisibility(View.GONE);
                    Edit_text_to.setVisibility(View.GONE);
                }else {
                   // openDialog();
                    applyFilter=true;
                    Edit_text_from.setText("");
                    Edit_text_to.setText("");
                    Edit_text_from.setVisibility(View.VISIBLE);
                    Edit_text_to.setVisibility(View.VISIBLE);
                }
            }
        });







        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
            new new_dashboard.Get_Sc_info().execute();
        } else {
            new new_dashboard.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void openDialog() {



        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Sales Consultant ID");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask15 the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new new_dashboard.Get_Sc_info().execute();
                } else {
                    new new_dashboard.Get_Sc_info().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public boolean update_bio () {

        ScName.setText(userName);
        About.setText(about);
        EnqCount.setText(enqCount);
        Txt_live_booking.setText(live_booking);
        Txt_total_ret .setText(total_ret);

        Txt_ret_target.setText(ret_target+"\n"+Txt_ret_target.getHint());
        Txt_enq_target.setText(enq_target+"\n"+Txt_enq_target.getHint());

        Txt_delivery_pend.setText(delivery_pend+"\n"+Txt_delivery_pend.getHint());
        Txt_FP_pend.setText(FP_pend+"\n"+Txt_FP_pend.getHint());

        Txt_retGp.setText(retGp+"\n"+Txt_retGp.getHint());
        Txt_enqGp.setText(enqGp+"\n"+Txt_enqGp.getHint());

        Txt_CF_enq.setText(CF_enq+"\n"+Txt_CF_enq.getHint());
        Txt_retCF.setText(retCF+"\n"+Txt_retCF.getHint());


        return true;
    }



    class Get_Sc_info extends AsyncTask<String, String, String> {

        JSONParser jsonParser = new JSONParser();
        boolean success = false;
        JSONArray Sc_Info = null;
        JSONObject json = null;

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(new_dashboard.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("Sc_Id", publicshared.getScId()));

//            if ("From Date".equals(tv_Fromdate.getText().toString())) {
//
//            } else if ("To Date".equals(tv_Todate.getText().toString())) {
//
//            } else {
//                String fromdate = changeDateFormat("dd/MM/yyyy", "yyyy-MM-dd", tv_Fromdate.getText().toString());
//                String todate = changeDateFormat("dd/MM/yyyy", "yyyy-MM-dd", tv_Todate.getText().toString());

            // Log.e("date", fromdate + todate);

            //params.add(new BasicNameValuePair("from_date", fromdate));
            //params.add(new BasicNameValuePair("to_date", todate));
            params.add(new BasicNameValuePair("ALL","1"));
            // }

//            CheckBox checkBox_all = findViewById(R.id.check_box_all);
//            if (checkBox_all.isChecked()) {
//                params.add(new BasicNameValuePair("ALL", "1"));
//            } else {
//                params.add(new BasicNameValuePair("ALL", "0"));
//            }


            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_Profile_info, "GET", params);

                // getting JSON Object
                // Note that create product url accepts POST method


                if (json == null) {


                    success = false;

                } else {

                    Log.d("Create Response", json.toString());

                    try {
                        success = json.getBoolean("success");
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (success) {

                        Sc_Info= json.getJSONArray("sc_details");
                        //  Sc_Info = json.getJSONArray(TAG_Info);


                        //  publicshared.setBookingCount(json.getString("Book_Count"));
//                        publicshared.setEnqCount(json.getString("Enq_Count"));
//                        publicshared.setServer_Version(json.getString("Server_version"));
//                        publicshared.setBkcount(json.getString("BookingCount"));
//                        publicshared.setinvcount(json.getString("InvoicedCount"));
//                        publicshared.setpencount(json.getString("PendingCount"));
//                        publicshared.setdelcount(json.getString("DeliveredCount"));
//                        publicshared.seteqcount(json.getString("Enq_Count"));
//                        publicshared.setTestdrive_count(json.getString("TestDriveCount"));
//
//
//                        Log.d("Versions", publicshared.getServer_Version());


                        userName=Sc_Info.getJSONObject(0).getString("Sc_Name");
                        about=Sc_Info.getJSONObject(0).getString("Branch_Name");
                        enqCount=json.getString("Enq_Count");
                        FP_pend=json.getString("PendingCount");
                        enq_target=json.getString("Enq_Counts");
                        ret_target=json.getString("DeliveredCount");
                        delivery_pend=json.getString("InvoicedCount");
                        live_booking=json.getString("BookingCount");

                        total_ret=json.getString("RetailCount");

                        retCF=json.getString("RetailCFCount");
                        CF_enq=json.getString("EnqCFCount");

                        enqGp =json.getString("EnqGapCount");
                        retGp =json.getString("RetailGapCount");

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
                success=false;
            }


            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            if (success) {


                update_bio();
//                if (update_bio()) {
//
//                    Toast.makeText(getApplicationContext(), "Profile Updated...", Toast.LENGTH_LONG).show();
//
//                } else {
//                    Intent i = getBaseContext().getPackageManager().
//                            getLaunchIntentForPackage(getBaseContext().getPackageName());
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//
//                }
//
//                Intent about = new Intent(dashboard.this, about_us.class);
//                //startActivity(about);
//
//                int resultcode = 0;
//                startActivityForResult(about, resultcode);

            } else if (json == null) {
                Toast.makeText(getApplicationContext(), "Please check your internet connection...", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Sorry,failed to check details", Toast.LENGTH_LONG).show();
                //openDialog();
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
            pDialog = new ProgressDialog(new_dashboard.this);
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
                intent = new Intent(new_dashboard .this, Enquiry_add.class);
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
}

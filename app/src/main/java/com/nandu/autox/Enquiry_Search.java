package com.nandu.autox;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Enquiry_Search extends AppCompatActivity {



    // Progress Dialog
    private ProgressDialog pDialog;
    private static final int REQUEST_CALL = 1;

    boolean success = false;

    Typeface tf;

    String FrmDate = "";
    String ToDate = "";
    String SearchFor ="";
    String SearchBy="";

    // url to create new product
    private static final String url_get_bookings = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/get_enquiries.php";
    private static final String url_update_Fp= "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/update_fp.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    String TAG_TABLE = "Enquiry";

    private List<Enquiry_Search_List> BkList = new ArrayList<Enquiry_Search_List>();
    private ListView listView;
    private Enquiry_Search_List_Adapter adapter;
    private Activity currentActivity;
    Spinner spnSearchBy;
    EditText txtSearchFor;
    EditText txtFromDate,txtToDate ;
    Button btnSearch;
    Button btnAdd;
    FloatingActionButton Filter;
    String Sc_Id,Mob;
    PublicShared publicshared ;
    private Date sdate,date1,date2;
    private String PreviousDate;
    Dialog myDialog;
    private  int mYear;
    private  int mMonth;
    private  int mDay;
    private  int mHrs;
    private  int mMin;
    private  int mSec;
    private  int dHH;
    private  int dMM;
    private  int dSS;

    private String MODE="NULL";
    Boolean SearchFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_search);

        publicshared =(PublicShared)getApplicationContext();

        Bundle b = getIntent().getExtras();
        myDialog = new Dialog(this);

        Sc_Id=b.getString("Sc_Id");
        try{
            Mob=b.getString("Mob");
            MODE=b.getString("MOE");
        }catch (Exception e ){}
        /*strfincompany = new ArrayList<String>() ;
        strfincompany = b.getStringArrayList("FinCompany");*/

        currentActivity= this;

        MODE=getIntent().getStringExtra("MODE");

        Filter =(FloatingActionButton) findViewById(R.id.FabBtn);
        txtFromDate = (EditText) findViewById(R.id.txtfrmDate);
        txtToDate = (EditText) findViewById(R.id.txtToDate);
        listView = (ListView) findViewById(R.id.list_vw);
        spnSearchBy = (Spinner) findViewById(R.id.spnSearchBy);
        txtSearchFor = (EditText) findViewById(R.id.txtSearchFor);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        // txtSearchFor.setText(spnSearchBy.getSelectedItem().toString());
        // Sc_Id =publicshared.getScId().toString();
        //Toast.makeText(getApplicationContext(),Sc_Id,Toast.LENGTH_LONG).show();




        // txtFromDate.setText(now_date());
//        myDialog.txtToDate.setText(now_date());

//        Enquiry_Search.Viewimg Viewimge= new Viewimg();
//        Viewimge.showimgDialog(getApplicationContext());
        //  Load_SearchBy();

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
            new SearchBK().execute();
        } else {
            new SearchBK().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

//        spnSearchBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                txtSearchFor.setHint(spnSearchBy.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(getApplicationContext(), Enquiry_add.class));
//
//              /*  Intent i_TD = new Intent(getApplicationContext(), Bkupdate.class);
//                startActivity(i_TD);
//                SearchFlag = true;*/
//            }
//
//        });

        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enquiry_Search.Viewimg Viewimge= new Viewimg();
                Viewimge.showimgDialog(Enquiry_Search.this);
            }
        });

//        btnSearch.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // creating new product in background thread
//
//                hideKeyboard(currentActivity);
//
//                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
//                    new SearchBK().execute();
//                } else {
//                    new SearchBK().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                }
//
//
//            }
//        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id){


                showEnquiryDialog( parent,  view,  position);



                //i_TD.putExtra("FinCompany", strfincompany);
                //startActivity(i_TD);
                SearchFlag = true;
                //finish();
                // }
            }

        });






//        txtFromDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Process to get Current Date
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//                // Launch Date Picker Dialog
//                DatePickerDialog dpd = new DatePickerDialog(Enquiry_Search.this,
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//                                // Display Selected date in textbox
//                                txtFromDate.setText(dayOfMonth + "/"
//                                        + (monthOfYear + 1) + "/" + year);
//
//                            }
//                        }, mYear, mMonth, mDay);
//                dpd.show();
//            }
//
//        });
//
//        txtToDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Process to get Current Date
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//                // Launch Date Picker Dialog
//                DatePickerDialog dpd = new DatePickerDialog(Enquiry_Search.this,
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//                                // Display Selected date in textbox
//                                txtToDate.setText(dayOfMonth + "/"
//                                        + (monthOfYear + 1) + "/" + year);
//
//                            }
//                        }, mYear, mMonth, mDay);
//                dpd.show();
//            }
//
//        });
//



    }


    private  void Call_enq(AdapterView parent, final View view, int position){

        final Intent i_TD = new Intent(getApplicationContext(),Enquiry_add.class);
        TextView desc1 = (TextView) view.findViewById(R.id.desc1);
        TextView desc2 = (TextView) view.findViewById(R.id.desc2);
        // ImageButton imgCall = (ImageButton)view.findViewById(R.id.ImgCall);
        LinearLayout callLayout=(LinearLayout)view.findViewById(R.id.callLayout);
        i_TD.putExtra("Enq_HeaderId", desc1.getTag().toString());
        i_TD.putExtra("Enq_Details", desc1.getText().toString());
        //i_TD.putExtra("Bk_FinId", desc2.getTag().toString());

        final  Object obj = parent.getItemAtPosition(position);

//        i_TD.putExtra("Mode","EDIT");
//
//        i_TD.putExtra("Cust_Name", ((Enquiry_Search_List) obj).getCust_Name());
//        i_TD.putExtra("Cust_Place", ((Enquiry_Search_List) obj).getCust_Place());
//        i_TD.putExtra("Prospect_No", ((Enquiry_Search_List) obj).getProspect_No());
//        i_TD.putExtra("Enq_Date", ((Enquiry_Search_List) obj).getEnq_Date());
//        i_TD.putExtra("Cust_Tel_Mob", ((Enquiry_Search_List) obj).getCust_Tel_Mob());
//
//        startActivity(i_TD);


        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+((Enquiry_Search_List) obj).getCust_Tel_Mob()));




        startActivity(intent);

    }



    private void CallDialog(final AdapterView parent, final View view, final int position){
        AlertDialog.Builder EnquiryDialog = new AlertDialog.Builder(this);
        EnquiryDialog.setTitle("Choose Action");
        String[] pictureDialogItems = {
                "Add FollowUp",
                "Edit Enquiry",
                "Call" };
        EnquiryDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                add_fp(parent,view,position);
                                break;
                            case 1:
                                edit_enq(parent,view,position);
                                break;
                            case 2:
                                Call_enq(parent,view,position);
                                break;
                        }
                    }
                });

        EnquiryDialog.show();
    }







    private  void edit_enq(AdapterView parent, final View view, int position){

        final Intent i_TD = new Intent(getApplicationContext(),Enquiry_add.class);
        TextView desc1 = (TextView) view.findViewById(R.id.desc1);
        TextView desc2 = (TextView) view.findViewById(R.id.desc2);
        // ImageButton imgCall = (ImageButton)view.findViewById(R.id.ImgCall);
        LinearLayout callLayout=(LinearLayout)view.findViewById(R.id.callLayout);
        i_TD.putExtra("Enq_HeaderId", desc1.getTag().toString());
        i_TD.putExtra("Enq_Details", desc1.getText().toString());
        //i_TD.putExtra("Bk_FinId", desc2.getTag().toString());

        final  Object obj = parent.getItemAtPosition(position);

        i_TD.putExtra("Mode","EDIT");

        i_TD.putExtra("Cust_Name", ((Enquiry_Search_List) obj).getCust_Name());
        i_TD.putExtra("Cust_Place", ((Enquiry_Search_List) obj).getCust_Place());
        i_TD.putExtra("Prospect_No", ((Enquiry_Search_List) obj).getProspect_No());
        i_TD.putExtra("Enq_Date", ((Enquiry_Search_List) obj).getEnq_Date());
        i_TD.putExtra("Cust_Tel_Mob", ((Enquiry_Search_List) obj).getCust_Tel_Mob());
        i_TD.putExtra("Cust_Place", ((Enquiry_Search_List) obj).getCust_Place());
        i_TD.putExtra("Cust_Tel_Office", ((Enquiry_Search_List) obj).getTel_Off());
        i_TD.putExtra("Cust_Email", ((Enquiry_Search_List) obj).getCust_Email());
        i_TD.putExtra("Cust_Occupt", ((Enquiry_Search_List) obj).getCust_Occupt());
        i_TD.putExtra("Cust_City", ((Enquiry_Search_List) obj).getCust_City());
        i_TD.putExtra("Cust_Pin", ((Enquiry_Search_List) obj).getCust_Pin());
        i_TD.putExtra("Enq_Pref1_Name", ((Enquiry_Search_List) obj).getVeh_Model());
        i_TD.putExtra("Enq_Source", ((Enquiry_Search_List) obj).getEnq_Source());
        i_TD.putExtra("Enq_Status", ((Enquiry_Search_List) obj).getEnq_Status());
        i_TD.putExtra("Enq_Pref1", ((Enquiry_Search_List) obj).getEnq_Pref());
        i_TD.putExtra("PMode_Cash", ((Enquiry_Search_List) obj).getPMode_Cash());
        i_TD.putExtra("PMode_Finance", ((Enquiry_Search_List) obj).getPMode_Finance());
        i_TD.putExtra("PMode_Exchange", ((Enquiry_Search_List) obj).getPMode_Exchange());
        i_TD.putExtra("Exchange_Vehicle", ((Enquiry_Search_List) obj).getExchange_Vehicle());
        i_TD.putExtra("Test_Drive", ((Enquiry_Search_List) obj).getTest_Drive());
        i_TD.putExtra("Test_Drive_Date", ((Enquiry_Search_List) obj).getTestDrive_Date());
        i_TD.putExtra("TestDriveFB", ((Enquiry_Search_List) obj).getTestDrive_FB());
        i_TD.putExtra("Remarks", ((Enquiry_Search_List) obj).getRemarks());
        i_TD.putExtra("SourceRemarks", ((Enquiry_Search_List) obj).getSource_Remarks());
        i_TD.putExtra("Cust_FB", ((Enquiry_Search_List) obj).getCust_FB());
        i_TD.putExtra("Test_Drive_Satisf", ((Enquiry_Search_List) obj).getTest_Drive_Satisf());
        i_TD.putExtra("Ex_BrokerName1", ((Enquiry_Search_List) obj).getEqExBroName());
        i_TD.putExtra("Ex_BrokerPrice1", ((Enquiry_Search_List) obj).getEqExBroRate());
        i_TD.putExtra("Ex_CustPrice", ((Enquiry_Search_List) obj).getEqExCustRate());
        //ShowPopup(view, obj, i_TD);

        startActivity(i_TD);

    }


    private void add_fp(AdapterView parent, final View view, int position){

        final Intent i_TD = new Intent(getApplicationContext(),Enquiry_add.class);

        TextView desc1 = (TextView) view.findViewById(R.id.desc1);
        TextView desc2 = (TextView) view.findViewById(R.id.desc2);
        TextView desc3 = (TextView) view.findViewById(R.id.desc3);

        // ImageButton imgCall = (ImageButton)view.findViewById(R.id.ImgCall);
        LinearLayout callLayout=(LinearLayout)view.findViewById(R.id.callLayout);
        i_TD.putExtra("Enq_HeaderId", desc1.getTag().toString());
        i_TD.putExtra("Enq_Details", desc1.getText().toString());
        //i_TD.putExtra("Bk_FinId", desc2.getTag().toString());

        publicshared.setEq_headerID(desc1.getTag().toString());
        publicshared.setEnq_Fp_Id(desc2.getTag().toString());

        String Details =desc3.getTag().toString();

        final  Object obj = parent.getItemAtPosition(position);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.fragment_enquiry_add_tab5);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
//        LinearLayout LinLayExe = dialog.findViewById(R.id.linearLayoutExeTask);
//        LinLayExe.setVisibility(View.GONE);

        TextView txtviewDe =dialog.findViewById(R.id.txtviewcustD);
        txtviewDe.setText(Details);
        Button BtnFpSave = dialog.findViewById(R.id.BtnFp_Save);
        Button BtnFpCancel = dialog.findViewById(R.id.BtnFp_Cancel);
        final EditText EditTxtFpDate = dialog.findViewById(R.id.EditTxt_NextActionPlanDate);
        final EditText EditTxtFp = dialog.findViewById(R.id.EditTxt_NextActionPlan);
        final EditText EditTxtExeFpDate = dialog.findViewById(R.id.EditTxt_ExeDate);
        final EditText EditTxtExeFp = dialog.findViewById(R.id.EditTxt_ExeActionPlan);
        String Items[] = {"Call","Field Visit","Showroom Visit","Other Actions"};

// Selection of the spinner
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spnNxtFollowup);



// Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Items);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

        final Spinner spnExefp = (Spinner) dialog.findViewById(R.id.spnExeFollowup);

// Application of the Array to the Spinner
//        ArrayAdapter<String> spinnerArrayAdapterExefp = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, Items);
//        spinnerArrayAdapterExefp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        spinner.setAdapter(spinnerArrayAdapterExefp);
        spnExefp.setAdapter(spinnerArrayAdapter);




        EditTxtExeFpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(Enquiry_Search.this);
               Fun_Calender(EditTxtExeFpDate);

            }
        });




        EditTxtFpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(Enquiry_Search.this);
               Fun_Calender(EditTxtFpDate);

            }
        });

        EditTxtFpDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(EditTxtFpDate.getText().toString().length()>=8 && EditTxtExeFpDate.getText().toString().length()>=8) {

                    PreviousDate = EditTxtFpDate.getText().toString();
                    if (PreviousDate.length() >= 10) {
                        PreviousDate = PreviousDate.substring(0, 10);
                    }
//                Date c = Calendar.getInstance().getTime();

                    date1 = getdateformated(now_date());
                    date2 = getdateformated(PreviousDate);

//                if (date1.compareTo(date2) <= 0) {
//                    System.out.println("earlier");
//                }


                    if (date1.compareTo(date2) == 0) {


                    } else if (date1.compareTo(date2) > 0) {

                        Toast.makeText(Enquiry_Search.this, "Invalid Date", Toast.LENGTH_SHORT).show();
                        EditTxtFpDate.setText(now_date());
                        // Toast.makeText(context," Less Than Today",Toast.LENGTH_SHORT).show();
                    } else if (date1.compareTo(date2) < 0) {
                        // publicshared.setEnquiry_Tab_Id(1);


                    }

                    if (validateDate(EditTxtFpDate.getText().toString())) {
                        publicshared.setEq_NextPlanDate(EditTxtFpDate.getText().toString());
                    } else {
                        Toast.makeText(Enquiry_Search.this, "Invalid Date", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    // Toast.makeText(Enquiry_Search.this, "Invalid Next FollowUp Date", Toast.LENGTH_SHORT).show();
                }

            }
        });


        EditTxtExeFpDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                EditTxtFpDate.setText("");


                PreviousDate=EditTxtExeFpDate.getText().toString();
                if(PreviousDate.length()>=10){PreviousDate = PreviousDate.substring(0, 10);}
//                Date c = Calendar.getInstance().getTime();

                date1=getdateformated(now_date());
                date2=getdateformated(PreviousDate);

//                if (date1.compareTo(date2) <= 0) {
//                    System.out.println("earlier");
//                }

                if(date1.compareTo(date2)==0){




                }else if(date1.compareTo(date2)>0)
                {


                    // Toast.makeText(context," Less Than Today",Toast.LENGTH_SHORT).show();
                }else if(date1.compareTo(date2)<0)
                {
                    // publicshared.setEnquiry_Tab_Id(1);
                    Toast.makeText(Enquiry_Search.this,"Invalid Date",Toast.LENGTH_SHORT).show();
                    EditTxtExeFpDate.setText(now_date());
                }

                if (validateDate(EditTxtExeFpDate.getText().toString())) {
                    publicshared.setFp_ExePlanDate(EditTxtExeFpDate.getText().toString());
                }else{ Toast.makeText(Enquiry_Search.this,"Invalid Date",Toast.LENGTH_SHORT).show();}

            }
        });


        BtnFpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation=true;
                if(EditTxtFp.getText().toString().length()==0){validation=false;}
                else if (EditTxtFpDate.getText().toString().length()==0){validation=false;}
                else if (spinner.getSelectedItem().toString().length()==0){validation=false;}
                else if (spnExefp.getSelectedItem().toString().length()==0){validation=false;}

                if(validation) {

                    publicshared.setEq_NextAction(spinner.getSelectedItem().toString());
                    publicshared.setEq_NextActionPlan(EditTxtFp.getText().toString());
                    publicshared.setFp_ExeAction(spnExefp.getSelectedItem().toString());
                    publicshared.setFp_ExeActionPlan(EditTxtFp.getText().toString());

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                        new Enquiry_Search.Update_FollowUp().execute();

                    } else {
                        new Enquiry_Search.Update_FollowUp().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }


                }else{}
            }
        });



        BtnFpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
//            FrameLayout mDialogNo = dialog.findViewById(R.id.frmNo);
//            CardView cardview =dialog.findViewById(R.id.cardviewpop);
//            TextView TxtNum = dialog.findViewById(R.id.TxtViewNumber);
        //String mobile_num =usrname.getText().toString();



        dialog.show();

    }


    private void showEnquiryDialog(final AdapterView parent, final View view, final int position){
        AlertDialog.Builder EnquiryDialog = new AlertDialog.Builder(this);
        EnquiryDialog.setTitle("Choose Action");
        String[] pictureDialogItems = {
                "Add FollowUp",
                "Edit Enquiry",
                "Call" };
        EnquiryDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                add_fp(parent,view,position);
                                break;
                            case 1:
                                edit_enq(parent,view,position);
                                break;
                            case 2:
                                Call_enq(parent,view,position);
                                break;
                        }
                    }
                });

        EnquiryDialog.show();
    }


    public void Add_FpPopup(View v,final Object obj,final Intent i_TD) {

    }


    public class Viewimg {

        public void showimgDialog(Activity activity) {
//            final Dialog dialog = new Dialog(activity);
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setCancelable(false);
//            dialog.setContentView(R.layout.search_option);
//
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.search_option);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            LinearLayout frameLinear =(LinearLayout) dialog.findViewById(R.id.frameLinear);
            txtFromDate = (EditText) dialog.findViewById(R.id.txtfrmDate);
            txtToDate = (EditText) dialog.findViewById(R.id.txtToDate);
            spnSearchBy = (Spinner) dialog.findViewById(R.id.spnSearchBy);
            txtSearchFor = (EditText) dialog.findViewById(R.id.txtSearchFor);
            btnSearch = (Button) dialog.findViewById(R.id.btnSearch);
            btnAdd = (Button) dialog.findViewById(R.id.btnAdd);

            ArrayList<String> values = new ArrayList<String>();

            values.add("Customer");
            values.add("ContactNo");
            values.add("Vehicle");

            ArrayAdapter<String> ObjAdapter = new ArrayAdapter<String>
                    (activity, android.R.layout.simple_list_item_activated_1, android.R.id.text1, values);

            spnSearchBy.setAdapter(ObjAdapter);
            FrmDate="";
            ToDate="";
            SearchFor="";
            txtToDate.setText(now_date());

            txtFromDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fun_Calender(txtFromDate);

                }

            });

            txtToDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Fun_Calender(txtToDate);

                }

            });

            txtFromDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    FrmDate =txtFromDate.getText().toString();
                }
            });

            txtToDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    ToDate =txtToDate.getText().toString();

                }
            });


            txtSearchFor.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    SearchFor =txtSearchFor.getText().toString();

                }
            });


            btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread

                hideKeyboard(currentActivity);
                Log.d("FrmDate",FrmDate);
                Log.d("FrmDate",ToDate);
                if (ToDate.equals("")){
                    ToDate=now_date();
                }
                SearchBy=spnSearchBy.getSelectedItem().toString();

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    new SearchBK().execute();
                } else {
                    new SearchBK().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

                dialog.dismiss();

            }
        });


            frameLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FrmDate="";
                    ToDate="";
                    SearchFor="";
                    dialog.dismiss();
                }
            });

            dialog.show();
        }


    }




    public String now_time(){

        final Calendar c = Calendar.getInstance();
        dHH = c.get(Calendar.HOUR_OF_DAY);
        dMM = c.get(Calendar.MINUTE);
        dSS = c.get(Calendar.SECOND);

        String now=dHH+":"+dMM+":"+dSS;

        return now;
    }


    public void ShowPopup(View v,final Object obj,final Intent i_TD) {
        TextView txtclose;
//        Button BtnEdit,BtnOk;
//        TextView txtProspectNo,txtenqDate,txtCustomer,txtPlace,txtMob,txtveh,txtStatus;
//        myDialog.setContentView(R.layout.custompopup);
//        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("X");
//        BtnOk = (Button) myDialog.findViewById(R.id.btnOK);
//        BtnEdit=(Button)myDialog.findViewById(R.id.BtnEdit);
//
//        txtCustomer=(TextView)myDialog.findViewById(R.id.txtCustomer) ;
//        txtProspectNo=(TextView)myDialog.findViewById(R.id.txtProspectNo) ;
//        txtenqDate=(TextView)myDialog.findViewById(R.id.txtenqDate) ;
//
//        txtPlace=(TextView)myDialog.findViewById(R.id.txtPlace) ;
//        txtMob=(TextView)myDialog.findViewById(R.id.txtMob) ;
//        txtveh=(TextView)myDialog.findViewById(R.id.txtveh) ;
//        txtStatus=(TextView)myDialog.findViewById(R.id.txtStatus) ;

//        txtMob.setText(((Enquiry_Search_List) obj).getCust_Tel_Mob().toString());
//        txtProspectNo.setText(((Enquiry_Search_List) obj).getProspect_No().toString());
//        txtenqDate.setText(((Enquiry_Search_List) obj).getEnq_Date().toString());
//        txtCustomer.setText(((Enquiry_Search_List) obj).getCust_Name().toString());
//        txtPlace.setText(((Enquiry_Search_List) obj).getCust_Place() .toString());
//        txtveh.setText(((Enquiry_Search_List) obj).getVeh_Model().toString());
//        txtStatus.setText(((Enquiry_Search_List) obj).getEnq_Status().toString());
//
//
//        BtnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String num ;
//                num =((Enquiry_Search_List) obj).getCust_Tel_Mob().toString();
//                makePhoneCall (num );
//                myDialog.dismiss();
//
//            }
//        });
//
//        BtnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(i_TD);
//                myDialog.dismiss();
//
//            }
//        });
//        txtclose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
    }



    private void makePhoneCall(String number) {

        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Enquiry_Search.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Enquiry_Search.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(Enquiry_Search.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
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


    @Override
    public void onBackPressed() {

        Intent i = new Intent(Enquiry_Search.this, dashboard.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        startActivity(i);
    }

    @Override
    protected void onResume() {

        super.onResume();

        if(SearchFlag){

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                new SearchBK().execute();
            } else {
                new SearchBK().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }


        }


    }

    public void Load_SearchBy(){

        spnSearchBy = (Spinner) myDialog.findViewById(R.id.spnSearchBy );
 		/*List<String> list = new ArrayList<String>();
 		list.add("Customer");
 		list.add("ContactNo");
 		list.add("Vehicle");
 		list.add("Bk.No");
 		list.add("DSE");
	 	ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this, R.layout.spinner_item, list);
	 	spnSearchBy.setAdapter(dataAdapter);*/


        ArrayList<String> values = new ArrayList<String>();

        values.add("Customer");
        values.add("ContactNo");
        values.add("Vehicle");

        ArrayAdapter<String> ObjAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_activated_1, android.R.id.text1, values);

        spnSearchBy.setAdapter(ObjAdapter);

    }

    public String Date_Format(String str) {

        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SimpleDateFormat DesiredFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat DesiredFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (str.equalsIgnoreCase("")){
            str = sourceFormat.format(new Date());
        }

        Date date = null;
        try {
            date = sourceFormat.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String formattedDate = DesiredFormat.format(date.getTime());
        // Now formattedDate have current date/time

        return formattedDate;

    }

    /**
     * Background Async Task to Create new SR
     * */


    class SearchBK extends AsyncTask<String, String, String> {


        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Enquiry_Search.this);
            pDialog.setMessage("Loading enquiry details.." + "\n" + "Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        @SuppressLint("WrongThread")
        protected String doInBackground(String... args) {

            String Qry;



            try{
                if (!FrmDate.equals("")){
                FrmDate = changeDateFormat("dd/MM/yyyy","yyyy-MM-dd",FrmDate);
                ToDate = changeDateFormat("dd/MM/yyyy","yyyy-MM-dd",ToDate);
                }else {
                    ToDate = changeDateFormat("dd/MM/yyyy","yyyy-MM-dd",ToDate);
                }

                if (ToDate.equals("")){
                    ToDate=now_d();
                }

            }catch(Exception Ex){

            }
            try{

                SearchBy = spnSearchBy.getSelectedItem().toString();

            }catch(Exception Ex){

            }


//            Qry = "Select Enq_HeaderId,Enq_Date,Prospect_No,Cust_Name,Cust_Place,Cust_Tel_Mob,Enq_Pref1,Enq_Source,Enq_Type,Cust_FB ," +
//                    "Cust_City,Cust_Pin,Cust_Tel_Office,Cust_Email,Cust_Occupt,Enq_Type, " +
//                    "if(isnull(S.Sc_Name),'',S.Sc_Name) as Sc_Name, " +
//                    "if(isnull(V1.Veh_Model_Name),'',V1.Veh_Model_Name) as Enq_Pref1_Name, " +
//                    "First_Veh, Enq_Source, Enq_Type, PMode_Cash, PMode_Finance, PMode_Exchange, " +
//                    "First_Veh, Test_Drive, Test_Drive_Date, " +
//                    "TestDriveFB, SourceRemarks, Remarks from enquiry_header H " +
//                    "left join sc_details S on S.Sc_Id = H.Sc_Id " +
//                    "left join veh_model V1 on V1.veh_model = H.Enq_Pref1 " +
//                    " where Enq_HeaderId>0  ";


            //Qry += "where 1=1 "


            Log.d("MODE",MODE);


            Qry="Select f.Eq_Fp_Id,f.Fp_Pln_Date,Fp_Pln_Details,f.Fp_Extd_Sc_Id ,H.Enq_HeaderId,Enq_Date,Prospect_No,Cust_Name,Cust_Place,Cust_Tel_Mob,Enq_Pref1,Enq_Source,Enq_Type,Cust_FB ," +
                    "  Cust_City,Cust_Pin,Cust_Tel_Office,Cust_Email,Cust_Occupt,Enq_Type,`Test_Drive_Satisf`,`Ex_BrokerPrice1`,`Ex_CustPrice`,`Ex_BrokerName1`," +
                    "  if(isnull(S.Sc_Name),'',S.Sc_Name) as Sc_Name," +
                    "  if(isnull(V1.Veh_Model_Name),'',V1.Veh_Model_Name) as Enq_Pref1_Name," +
                    "  First_Veh as Exchange_Vehicle, Enq_Source, Enq_Type, PMode_Cash, PMode_Finance, PMode_Exchange," +
                    " First_Veh, Test_Drive, Test_Drive_Date," +
                    " TestDriveFB, SourceRemarks, Remarks from enquiry_header H" +
                    " left join sc_details S on S.Sc_Id = H.Sc_Id" +
                    " left join veh_model V1 on V1.veh_model = H.Enq_Pref1" +
                    " left join enquiry_fp f on f.Enq_HeaderId=H.Enq_HeaderId" +
                    "  where H.Enq_HeaderId >0 And f.Fp_Extd_Sc_Id = '' ";


            //  if (MODE==""){}else if(MODE=="SE"){}else





            if (Sc_Id==""){
            Qry += " And S.Sc_Id='" + Sc_Id + "' ";}else if (!publicshared.getScId().equals("")){
                Qry += " And S.Sc_Id='" + publicshared.getScId() + "' ";
            }



            if (MODE.contains("AF")) {

                if (FrmDate.length() > 0) {
//
                    if ((!FrmDate.equalsIgnoreCase("")) && !ToDate.equalsIgnoreCase("")) {
                        Qry += " And Date(f.Fp_Pln_Date) = '" + FrmDate + "'";
                    } else if (!FrmDate.equalsIgnoreCase("")) {
                        Qry += " And Date(f.Fp_Pln_Date) between '" + FrmDate + "' and '" + ToDate + "'";
                    }
//
                    Qry+="and Enq_Type <>'Delivered' ";
////            if(validate(FrmDate.toString()) == 0){
////                Qry += " And Enq_Date between '" + FrmDate + "' and '" + ToDate  + "'";
////            }
////
//                    }
                }

            } else if (MODE.contains("SE")) {


                if (FrmDate.length() > 0) {
//
                    if ((!FrmDate.equalsIgnoreCase("")) && ToDate.equalsIgnoreCase("")) {
                        Qry += " And Date(Enq_Date) = '" + FrmDate + "'";
                    } else
                        if (!FrmDate.equalsIgnoreCase("")) {
                        Qry += " And Date(Enq_Date)between '" + FrmDate + "' and '" + ToDate + "'";
                    }
                    Qry+=" and Enq_Type <>'Delivered' ";
//
//            if(validate(FrmDate.toString()) == 0){
//                Qry += " And Enq_Date between '" + FrmDate + "' and '" + ToDate  + "'";
                }
            }


            if (SearchFor.toString() != "") {

                Qry+="and Enq_Type <> 'Delivered' ";

                switch (SearchBy.toString()) {

                    case "Customer":
                        if ( SearchFor.toString().length()!=0) {
                            Qry += " And Cust_Name like '%" + SearchFor.toString() + "%' Order by Cust_Name ";
                        }
                        break;
                    case "ContactNo":
                        if ( SearchFor.toString().length()!=0) {
                            Qry += " And Cust_Tel_Mob like '%" + SearchFor.toString() + "%' Order by Cust_Tel_Mob ";
                        }
                        break;
                    case "Vehicle":
                        if ( SearchFor.toString().length()!=0) {
                            Qry += " And V1.Veh_Model_Name like '%" + SearchFor.toString() + "%' Order by Cust_Name ";
                        }
                        break;

                    default:
                        break;
                }


            }


            if (MODE.contains("AF") &&  !Qry.contains("and f.Fp_Pln_Date ")&&  !Qry.contains("And f.Fp_Pln_Date "))  {
                String Filter = " and f.Fp_Pln_Date <='" + now_d() + "'";
                Qry += Filter;

                try {
                    if (Mob.length() > 9) {
                        Qry += " And S.Sc_Id='" + Sc_Id + "' ";
                        Qry += " And Cust_Tel_Mob like '%" + Mob + "%' Order by Cust_Tel_Mob ";
                        Mob = "";
                    }
                } catch (Exception e) {
                }
            }else if (MODE.contains("SE") &&  !Qry.contains("and Enq_Date ")  &&  !Qry.contains("And Enq_Date ") && !Qry.contains("group by") ) {

                String Filter = " and Enq_Date <='" + now_dt() + "'";
                Qry += Filter;
                if (!Qry.contains("like '%")){
                    Qry += "group by Enq_headerID" ;
                    Qry +=" order by Enq_Date Desc";
                }
            }

            Qry += " limit 100 ";



            Log.d("qry",Qry);



            if (MODE.contains("AF")){
                MODE="1";
            }else if (MODE.contains("SE")){
                MODE="2";
            }



            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //Enq_HeaderId,Req_Type,Req_DateTime,Req_ContactNo,Req_Name,Req_Remarks,CreatedOn
            params.add(new BasicNameValuePair("QryBk",Qry));
            params.add(new BasicNameValuePair("MODE",MODE));
            params.add(new BasicNameValuePair("FrmDate",FrmDate));
            params.add(new BasicNameValuePair("ToDate",ToDate));
            params.add(new BasicNameValuePair("SearchBy",SearchBy));
            params.add(new BasicNameValuePair("SearchFor",SearchFor));
            params.add(new BasicNameValuePair("Sc_Id",publicshared.getScId()));

            ToDate="";
            FrmDate="";
            // check for success tag
            JSONArray bookings = null;

            try {

                try {

                    json = jsonParser.makeHttpRequest(url_get_bookings,"GET", params);



                } catch (Exception e) {

                    Log.d("Exception e", e.getMessage().toString());
                    e.printStackTrace();
                    //TODO: handle exception
                }

                // getting JSON Object
                // Note that create product url accepts POST method

                if(json==null){
                    success = false;

                }else{

                    //message0


                    // check log cat fro response
                    Log.d("Create Response", json.toString());



                    try {
                        success = json.getBoolean(TAG_SUCCESS);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    BkList.clear();

                    if (success) {

                        bookings = json.getJSONArray(TAG_TABLE);

                        // Parsing json
                        for (int i = 0; i < bookings.length(); i++) {

                            try {

                                //RId,Code,Name,Place,Img_Url,Stars,Reviews

                                JSONObject obj = bookings.getJSONObject(i);
                                Enquiry_Search_List bk = new Enquiry_Search_List();

                                bk.setEnq_HeaderId(obj.getInt("Enq_HeaderId"));
                                bk.setProspect_No(obj.getString("Prospect_No"));
                                bk.setEnq_Date(obj.getString("Enq_Date"));
                                bk.setCust_Name(obj.getString("Cust_Name"));
                                bk.setCust_Tel_Mob(obj.getString("Cust_Tel_Mob"));
                                bk.setVeh_Model(obj.getString("Enq_Pref1_Name"));
                                bk.setCust_Place(obj.getString("Cust_City"));
                                bk.setSc_Name(obj.getString("Sc_Name"));
                                bk.setCust_City(obj.getString("Cust_Place"));
                                bk.setCust_Pin(obj.getString("Cust_Pin"));
                                bk.setTel_Off(obj.getString("Cust_Tel_Office"));
                                bk.setCust_Email(obj.getString("Cust_Email"));
                                bk.setCust_Occupt(obj.getString("Cust_Occupt"));
                                bk.setEnq_Source(obj.getString("Enq_Source"));
                                bk.setEnq_Status(obj.getString("Enq_Type"));
                                bk.setEnq_Pref(obj.getString("Enq_Pref1"));
                                bk.setExchange_Vehicle(obj.getString("Exchange_Vehicle"));
                                bk.setPMode_Cash(obj.getString("PMode_Cash"));
                                bk.setPMode_Finance(obj.getString("PMode_Finance"));
                                bk.setPMode_Exchange(obj.getString("PMode_Exchange"));
                                bk.setTest_Drive(obj.getString("Test_Drive"));
                                bk.setTestDrive_Date(obj.getString("Test_Drive_Date"));
                                bk.setTestDrive_FB(obj.getString("TestDriveFB"));
                                bk.setSource_Remarks(obj.getString("SourceRemarks"));
                                bk.setRemarks(obj.getString("Remarks"));
                                bk.setFp_Pln_Date(obj.getString("Fp_Pln_Date"));
                                bk.setFp_Pln_Details(obj.getString("Fp_Pln_Details"));
                                bk.setEq_Fp_Id(obj.getString("Eq_Fp_Id"));
                                bk.setCust_FB(obj.getString("Cust_FB"));
                                bk.setTest_Drive_Satisf(obj.getString("Test_Drive_Satisf"));
                                bk.setEqExCustRate(obj.getString("Ex_CustPrice"));
                                bk.setEqExBroRate(obj.getString("Ex_BrokerPrice1"));
                                bk.setEqExBroName(obj.getString("Ex_BrokerName1"));
                                // adding rest to rests array
                                BkList.add(bk);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data

                        }

                    } else {
                        // failed to create product
                    }
                }





            } catch (Exception e) {
                Log.d("Exception e", e.getMessage().toString());
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


                adapter = new Enquiry_Search_List_Adapter(currentActivity, BkList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();



            }else if (json==null){
                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to get data...",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();

        }


    }

    public static boolean validateDate(String strDate)
    {
        /* Check if date is 'null' */
        if (strDate.trim().equals(""))
        {
            return false;
        }
        /* Date is not 'null' */
        else
        {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try
            {
                Date javaDate = sdfrmt.parse(strDate);
                //   System.out.println(strDate+" is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                //  System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }


    public Date getdateformated(String da_te) {


        String[] dateParts = da_te.split("/");
//
//        int pday = 0;
//        int pmonth = 0;
//        int pyear = 0;
//
//        pyear = Integer.parseInt(dateParts[2]);
//        pmonth = Integer.parseInt(dateParts[1]);
//        pday = Integer.parseInt(dateParts[0]);
//        pmonth -=1;
//        sdate = new Date(pday, pmonth, pyear);

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // here set the pattern as you date in string was containing like date/month/year
            sdate = sdf.parse(da_te);
        }catch(ParseException ex){
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }

        return sdate;
    }

    public String now_date(){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mMonth+=1;
        //String Now=mDay+"/"+mMonth+"/"+mYear;
        String Now=String.format("%02d", mDay)+"/"+String.format("%02d", mMonth)+"/"+mYear;
        return Now ;
    }



    public String now_d(){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mMonth+=1;
        String Now=mYear+"-"+mMonth+"-"+mDay;

        return Now ;
    }



    public String now_dt(){


        Calendar calender = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =  mdformat.format(calender.getTime());

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mMonth+=1;

        String Now=mYear+"-"+mMonth+"-"+mDay +" "+strDate;

        return Now ;
    }


    public void Fun_Calender(EditText et){


        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox



                        dayOfMonth = Integer.parseInt(String.format("%02d", dayOfMonth));
                        monthOfYear = Integer.parseInt(String.format("%02d", monthOfYear));

                        et.setText(String.format("%02d", dayOfMonth) + "/"
                                + (String.format("%02d", monthOfYear+1)) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();



    }



    private int  validate(String registerdate) {

        String regEx = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
        Matcher matcherObj = Pattern.compile(regEx).matcher(registerdate);
        if (matcherObj.matches())
        {
            return 1;
        }
        else
        {
            return 0;
        }

    }



    class Update_FollowUp extends AsyncTask<String, String, String> {


        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Enquiry_Search.this);
            pDialog.setMessage("Saving Details.." + "\n" + "Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }



        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            //  String x=txtTestDriveDate.getText().toString();
            String TestDriveDate;

//            if(Test_Drive.equals("1"))
//            {
//              //  TestDriveDate = Date_Format(txtTestDriveDate.getText().toString());
//            }
//            else
//            {
//                TestDriveDate =null;
//            }


            String  exeDate,fpDate;

           exeDate= changeDateFormat("dd/MM/yyyy","yyyy/MM/dd",publicshared.getFp_ExePlanDate());
            fpDate=changeDateFormat("dd/MM/yyyy","yyyy/MM/dd",publicshared.getEq_NextPlanDate());

            params.add(new BasicNameValuePair("Enq_Fp_Id",publicshared.getEnq_Fp_Id()));
            params.add(new BasicNameValuePair("Enq_HeaderId",publicshared.getEq_headerID()));

            params.add(new BasicNameValuePair("Fp_Action_Exe",publicshared.getFp_ExeAction()));
            params.add(new BasicNameValuePair("Fp_Extd_Details",publicshared.getfp_ExeActionPlan()));
            params.add(new BasicNameValuePair("Fp_Extd_Date",exeDate));

            params.add(new BasicNameValuePair("ActionPlan",publicshared.getEq_NextAction()));
            params.add(new BasicNameValuePair("PlannedTask",publicshared.getEq_NextActionPlan()));
            params.add(new BasicNameValuePair("TaskDate",fpDate));

            params.add(new BasicNameValuePair("Sc_Id",publicshared.getScId().toString()));
            params.add(new BasicNameValuePair("Branch_Code",publicshared.getBranch_Code().toString()));

            try {




                json = jsonParser.makeHttpRequest(url_update_Fp, "GET", params);



            } catch (Exception e) {
            }

            if(json==null){	success = false;
            }else
            {
                try {
                    success = json.getBoolean(TAG_SUCCESS);
                    //Bk_FinId  = json.getString("Bk_FinId");
                    String message;
                    message =json.getString("message");
                    Log.d("Message",message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            return null;
        }



        public String Current_DateTime(){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());
            return currentDateandTime;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

            if (success){
                Toast.makeText(getApplicationContext(), "Saved successfully.",Toast.LENGTH_LONG).show();

                publicshared.setEq_NextAction("");
                publicshared.setEq_NextPlanDate("");
                publicshared.setEq_NextActionPlan("");
                publicshared.setFp_ExeActionPlan("");
                publicshared.setFp_ExeAction("");
                publicshared.setFp_ExePlanDate("");
                publicshared.setEq_headerID("");
                finish();
//                if (0 != Integer.parseInt(Enq_HeaderId)) {
//
//                    //  Intent intent_FpList = new Intent(getApplicationContext(), FpList.class);
//
//
//
//                    Intent i = new Intent(Enquiry_Search.this, Home_activity.class);
//// set the new task and clear flags
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    pDialog.dismiss();
//
//                    finish();
//
//                    i.putExtra("Sc_Id",publicshared.getScId());
//                    startActivity(i);
//                    //  startActivity(intent_FpList);
//
//                }

            }else if (json==null){

                Toast.makeText(getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();

            } else{
                Toast.makeText(getApplicationContext(), "Sorry,failed to sent request",Toast.LENGTH_LONG).show();

            }
            pDialog.dismiss();

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




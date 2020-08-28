package com.nandu.autox;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class Enquiry_add extends AppCompatActivity {

    private boolean isBackPressed = false;
    private boolean isBackPressedOnce = false;
    private List<Integer> tabsInBack = new ArrayList<>();

    private Boolean Dimg =false;
    private int x ;
    TabLayout tabs;
    PublicShared publicshared;


    //region Initialize_Controls


    AutoCompleteTextView ac_tv;

    //region Layout

    LinearLayout Line_lay_tab3_td_img;
    LinearLayout Line_lay_tab3_td_details;
    LinearLayout linearLayLImgEx3;
    LinearLayout linearLayLImgEx2;
    LinearLayout linearLayLImgEx1;
    LinearLayout linearLayExchange;

    //endregion Layout

    //region Edit Text

    EditText et_eq_date;
    EditText et_cust_prosp;
    EditText et_Cust_Name ;
    EditText et_Cust_Mobile_Num ;
    EditText et_Cust_Address1 ;
    EditText et_Cust_Address2;
    EditText et_Cust_Pin ;
    EditText et_Cust_Occupation;
    EditText et_Cust_Email ;
    EditText et_Cust_veh_feedback ;
    EditText et_Cust_td_feedback ;
    EditText et_Cust_td_date ;
    EditText et_Cust_ex_veh ;
    EditText et_Cust_ex_rate ;
    EditText et_Brok_name ;
    EditText et_Brok_rate;

    //endregion Edit Text

    //region Spinner

    Spinner sp_veh_model;
    Spinner sp_eq_source;
    Spinner sp_eq_status;
    Spinner sp_td_sati;

    //endregion Spinner

    //region Check Box

    CheckBox cb_pm_cash;
    CheckBox cb_pm_finance;
    CheckBox cb_exchange;

    CheckBox cb_ex_img1;
    CheckBox cb_ex_img2;
    CheckBox cb_ex_img3;
    CheckBox cb_ex_img4;
    CheckBox cb_ex_img5;
    CheckBox cb_ex_img6;

    //endregion Check Box

    //region Radio Button

    RadioButton rb_td_yes;
    RadioButton rb_td_no;


    //endregion Radio Button

    //region Image View

    ImageView iv_td_1;
    ImageView iv_td_2;

    ImageView iv_ex_1;
    ImageView iv_ex_2;
    ImageView iv_ex_3;
    ImageView iv_ex_4;
    ImageView iv_ex_5;
    ImageView iv_ex_6;

    //endregion Image View4

    //region Button

    Button bt_eq_save;
    Button bt_eximg_delete;

    //endregion Button

    //endregion Initialize_Controls

    //region Initialize_Variable

            private  int mYear;
            private  int mMonth;
            private  int mDay;

            private ProgressDialog pDialog;

            private int test_drive=0;
            private int payment_mode_cash=0;
            private int payment_mode_fin=0;
            private int exchange=0;

            private String vehModel="";
            private String enquiry_status="";
            private String enquiry_source="";
            private String td_satisfaction="";
            private String eq_headerID ="0";

            private String Prospects_Number ="";
            private String veh_Model_Id="";

            //image section
            private ImageView td_iv[];
            private ImageView ex_iv[];
            private Bitmap ex_bitmaps[];
            private Bitmap td_bitmaps[];
            private int ImgSelection_Limit=0;
            private  ImageView Img=null;
            private boolean Is_Ex=false;
            private Uri filePath;
            private static final String IMAGE_DIRECTORY = "/DCIM";
            private Bitmap bitmap;



            ArrayList<String> eq_status = new ArrayList<String>();
            ArrayList<String> td_satis = new ArrayList<String>();


            //image
            private int GALLERY = 2, CAMERA = 1;

    //endregion Initialize_Variable

    //region urls

    private static String url_validate = "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/validate_phone.php";
    private static final String url_update_enquiry= "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/update_enquiry.php";
    private static final String url_update_enquiry_Img= "http://logicxnet.com/AUTOX_GRAND/ENQ/app_connector/update_enquiryimg.php";
    public static final String UPLOAD_URL = "http://logicxnet.com/AUTOX_GRAND/ENQ/image_upload.php";
    public static final String UPLOAD_KEY = "image";
    public static final String DOWNLOAD_URL = "http://logicxnet.com/AUTOX_GRAND/ENQ/Uploads/";


    private static final String TAG_SUCCESS = "success";
    static Boolean success = false;
    //endregion urls





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enq_add);

        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);


        //region Initialize_from_Layout

        //region Layouts

        Line_lay_tab3_td_img= findViewById(R.id.Line_lay_tab3_td_img);
        Line_lay_tab3_td_details = findViewById(R.id.Line_lay_tab3_td_details);
        linearLayLImgEx3 =findViewById(R.id.linearLayLImgEx3);
        linearLayLImgEx2=findViewById(R.id.linearLayLImgEx2);
        linearLayLImgEx1=findViewById(R.id.linearLayLImgEx);
        linearLayExchange=findViewById(R.id.linearLayExchange);

        //endregion Layouts


        //region Edit Text
        
        et_Cust_Mobile_Num  =findViewById(R.id.EdTxtMob);
        et_Cust_Name  =findViewById(R.id.EditTxtName );
        et_eq_date =findViewById(R.id.EdTxtEnqDate);
        et_Cust_Pin =findViewById(R.id.EdTxtPinCode);
        et_cust_prosp=findViewById(R.id.EdTxtProsNo);
        et_Cust_Address1 =findViewById(R.id.EditTxtadd1);
        et_Cust_Address2 =findViewById(R.id.EditTxtadd2);
        et_Cust_Email =findViewById(R.id.EditTxtEmail);
        et_Cust_Occupation =findViewById(R.id.EditTxtOccup);
        et_Cust_veh_feedback=findViewById(R.id.txtCustFed );
        et_Cust_td_feedback = findViewById(R.id.txtFeedback);
        et_Cust_td_date =findViewById(R.id.txtTestDriveDate);
        et_Cust_ex_veh= findViewById(R.id.txtExchVeh);
        et_Cust_ex_rate= findViewById(R.id.txtExchVehCustRate);
        et_Brok_name= findViewById(R.id.txtExchVehBroName);
        et_Brok_rate =findViewById(R.id.txtExchVehBroRate);

        //endregion Edit Text

        //region Spinner

        sp_veh_model  = findViewById(R.id.spnVeh);
        sp_eq_source = findViewById(R.id.spnEnqSource);
        sp_eq_status = findViewById(R.id.spnEnqStatus);
        sp_td_sati   = findViewById(R.id.spnTestSatis);

        //endregion Spinner

        //region Radio Button

        rb_td_no    = findViewById(R.id.rdoNo );
        rb_td_yes   = findViewById(R.id.rdoYes);

        //endregion Radio Button

        //region Image View

        td_iv= new ImageView[2];
        ex_iv= new ImageView[6];
        td_bitmaps= new Bitmap[td_iv.length];
        ex_bitmaps= new Bitmap[ex_iv.length];

        iv_td_1 = findViewById(R.id.ImgViewTestDrive1);
        iv_td_2 = findViewById(R.id.ImgViewTestDrive2);

        td_iv[0]=iv_td_1;
        td_iv[1]=iv_td_2;

        iv_ex_1=findViewById(R.id.ImgViewExchange1);
        iv_ex_2=findViewById(R.id.ImgViewExchange2);
        iv_ex_3=findViewById(R.id.ImgViewExchange3);
        iv_ex_4=findViewById(R.id.ImgViewExchange4);
        iv_ex_5=findViewById(R.id.ImgViewExchange5);
        iv_ex_6=findViewById(R.id.ImgViewExchange6);

        ex_iv[0]=iv_ex_1;
        ex_iv[1]=iv_ex_2;
        ex_iv[2]=iv_ex_3;
        ex_iv[3]=iv_ex_4;
        ex_iv[4]=iv_ex_5;
        ex_iv[5]=iv_ex_6;

        //endregion Image View

        //region Check Box

        cb_pm_cash =findViewById(R.id.chkCash);
        cb_pm_finance=findViewById(R.id.chkFinance);
        cb_exchange=findViewById(R.id.chkExchange);

//        cb_ex_img1=findViewById(R.id.ckbimg1);
//        cb_ex_img2=findViewById(R.id.ckbimg2);
//        cb_ex_img3=findViewById(R.id.ckbimg3);
//        cb_ex_img4=findViewById(R.id.ckbimg4);
//        cb_ex_img5=findViewById(R.id.ckbimg5);
//        cb_ex_img6=findViewById(R.id.ckbimg6);

        //endregion Check Box

        //region Button

        //bt_eximg_delete = findViewById(R.id.BtndeleteImg_ex);
        bt_eq_save= findViewById(R.id.BtnSave);

        //endregion Button

        //endregion Initialize_from_Layout


        //region Initialize_Settings


            publicshared=(PublicShared)getApplicationContext();

            eq_status.add("Hot");
            eq_status.add("Warm");
            eq_status.add("Cold");

            td_satis.add("10 Outstanding");
            td_satis.add("9 Excellent");
            td_satis.add("8 Good");
            td_satis.add("6-7 Average");
            td_satis.add("1-5 Bad");
            td_satis.add("0 Not Given");

            Line_lay_tab3_td_img.setVisibility(View.GONE);
            Line_lay_tab3_td_details.setVisibility(View.GONE);
            linearLayExchange.setVisibility(View.GONE);
            linearLayLImgEx1.setVisibility(View.GONE);
            linearLayLImgEx2.setVisibility(View.GONE);
            linearLayLImgEx3.setVisibility(View.GONE);




            
            //region Get_Intent

                    if (getIntent().getExtras().getString("Mode").equals("ADD")){
                        eq_headerID="0";
                        if (publicshared.getEq_ProsNo().length()>0) {
                            et_cust_prosp.setText(publicshared.getEq_ProsNo());
                        }

                        et_eq_date.setText(Fun_nowDate());

                    }else {

                        String Eq_date = changeDateFormat("yyyy-MM-dd", "dd/MM/yyyy", getIntent().getExtras().getString("Enq_Date").replace("00:00:00", ""));
                        String TestDriveDate = changeDateFormat("yyyy-MM-dd", "dd/MM/yyyy", getIntent().getExtras().getString("Test_Drive_Date").replace("00:00:00", ""));

                        eq_headerID=getIntent().getExtras().getString("Enq_HeaderId");

                        DownloadImage();
                        et_Cust_Name.setText(getIntent().getExtras().getString("Cust_Name"));
                        et_Cust_Address2.setText(getIntent().getExtras().getString("Cust_Place"));
                        et_Cust_Address1.setText(getIntent().getExtras().getString("Cust_City"));
                        et_cust_prosp.setText(getIntent().getExtras().getString("Prospect_No").replace("\n", ""));
                        et_eq_date.setText(Eq_date);
                        et_Cust_Mobile_Num.setText(getIntent().getExtras().getString("Cust_Tel_Mob"));
                        et_Cust_Email.setText(getIntent().getExtras().getString("Cust_Email"));
                        et_Cust_Occupation.setText(getIntent().getExtras().getString("Cust_Occupt"));
                        et_Cust_Pin.setText(getIntent().getExtras().getString("Cust_Pin"));
                        vehModel=getIntent().getExtras().getString("Enq_Pref1");
                        if("1".equals(getIntent().getExtras().getString("PMode_Cash"))){cb_pm_cash.setChecked(true);
                        payment_mode_cash =1;}
                        if("1".equals(getIntent().getExtras().getString("PMode_Finance"))){cb_pm_finance.setChecked(true);
                        payment_mode_fin = 1;}
                        if("1".equals(getIntent().getExtras().getString("PMode_Exchange"))){cb_exchange.setChecked(true);
                            Is_Ex =true;
                            linearLayExchange.setVisibility(View.VISIBLE);
                            linearLayLImgEx1.setVisibility(View.VISIBLE);
                            linearLayLImgEx2.setVisibility(View.VISIBLE);
                            linearLayLImgEx3.setVisibility(View.VISIBLE);
                        }
                        if("1".equals(getIntent().getExtras().getString("Test_Drive"))){
                            test_drive=1;
                            rb_td_yes.setChecked(true);
                            Line_lay_tab3_td_img.setVisibility(View.VISIBLE);
                            Line_lay_tab3_td_details.setVisibility(View.VISIBLE);
                        }else{rb_td_no.setChecked(true);}
                        et_Cust_td_date.setText(TestDriveDate);
                        et_Cust_td_feedback.setText(getIntent().getExtras().getString("TestDriveFB"));

                        enquiry_status=getIntent().getExtras().getString("Enq_Status");
                        enquiry_source=getIntent().getExtras().getString("Enq_Source");
                        et_Cust_veh_feedback.setText(getIntent().getExtras().getString("Cust_FB"));
                        et_Cust_ex_veh.setText(getIntent().getExtras().getString("Exchange_Vehicle"));
                        td_satisfaction=getIntent().getExtras().getString("Test_Drive_Satisf");
                        et_Brok_rate.setText(getIntent().getExtras().getString("Ex_BrokerPrice1"));
                        et_Cust_ex_rate.setText(getIntent().getExtras().getString("Ex_CustPrice"));
                        et_Brok_name.setText(getIntent().getExtras().getString("Ex_BrokerName1"));



                        
                    }


            //endregion Get_Intent



            Fun_Load_Veh();
            Fun_Load_EnqSource();
            Fun_Load_Td_Satisf();
            Fun_Load_EnqStatus();



            //region FillEdit



            //endregion FillEdit


        //endregion Initialize_Settings





        //region Events


            //region Edit Text Events

                et_eq_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fun_Calender(et_eq_date);
                    }
                });

                et_eq_date.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        String PreviousDate=et_eq_date.getText().toString();
                        if(PreviousDate.length()>=10){PreviousDate = PreviousDate.substring(0, 10);}

                        Date date1=Fun_dateformated(Fun_nowDate());
                        Date date2=Fun_dateformated(PreviousDate);

                        if(date1.compareTo(date2)==0){
                        }else if(date1.compareTo(date2)>0)
                        {
                            // Toast.makeText(context," Less Than Today",Toast.LENGTH_SHORT).show();
                        }else if(date1.compareTo(date2)<0)
                        {
                            Toast.makeText(Enquiry_add.this,"Invalid Date",Toast.LENGTH_SHORT).show();
                            et_eq_date.setText(Fun_nowDate());
                        }

                        if (!Fun_DateValidation(et_eq_date.getText().toString())){Toast.makeText(Enquiry_add.this,"Invalid Date",Toast.LENGTH_SHORT).show();}

                    }
                });


                et_Cust_td_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fun_Calender(et_Cust_td_date);
                    }
                });

                et_Cust_td_date.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        String PreviousDate=et_Cust_td_date.getText().toString();
                        if(PreviousDate.length()>=10){PreviousDate = PreviousDate.substring(0, 10);}

                        Date date1=Fun_dateformated(Fun_nowDate());
                        Date date2=Fun_dateformated(PreviousDate);

                        if(date1.compareTo(date2)==0){
                        }else if(date1.compareTo(date2)>0)
                        {
                            // Toast.makeText(context," Less Than Today",Toast.LENGTH_SHORT).show();
                        }else if(date1.compareTo(date2)<0)
                        {
                            Toast.makeText(Enquiry_add.this,"Invalid Date",Toast.LENGTH_SHORT).show();
                            et_Cust_td_date.setText(Fun_nowDate());
                        }

                        if (!Fun_DateValidation(et_Cust_td_date.getText().toString())){Toast.makeText(Enquiry_add.this,"Invalid Date",Toast.LENGTH_SHORT).show();}

                    }
                });

                et_Cust_Mobile_Num.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        if (et_Cust_Mobile_Num.getText().length()>10) {
                            Toast.makeText(Enquiry_add.this, "Cannot be Greater than 10 Digit", Toast.LENGTH_SHORT).show();
                            et_Cust_Mobile_Num.getText().delete(et_Cust_Mobile_Num.getText().toString().length() - 1, et_Cust_Mobile_Num.getText().toString().length());
                        }else if (et_Cust_Mobile_Num.getText().toString().length() ==10) {
                            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                                new validate_Phone().execute();
                            } else {
                                new validate_Phone().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            }
                        }
                    }
                });


            //endregion Edit Text Events


            //region Spinner Events

                sp_veh_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            //endregion Spinner Events


            //region Image View Events

                iv_td_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=false;
                        Fun_showimgDialog(Enquiry_add.this,iv_td_1);
                    }
                });


                iv_td_1.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_td_1.setImageDrawable(null);
                        return false;
                    }
                });


                iv_td_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=false;
                        Fun_showimgDialog(Enquiry_add.this,iv_td_2);
                    }
                });


                iv_td_2.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_td_2.setImageDrawable(null);
                        return false;
                    }
                });


                iv_ex_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=true ;
                        Fun_showimgDialog(Enquiry_add.this,iv_ex_1);
                    }
                });


                iv_ex_1.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_ex_1.setImageDrawable(null);
                        return false;
                    }
                });


                iv_ex_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=true;
                        Fun_showimgDialog(Enquiry_add.this,iv_ex_2);
                    }
                });


                iv_ex_2.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_ex_2.setImageDrawable(null);
                        return false;
                    }
                });


                iv_ex_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=true;
                        Fun_showimgDialog(Enquiry_add.this,iv_ex_3);
                    }
                });


                iv_ex_3.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_ex_3.setImageDrawable(null);
                        return false;
                    }
                });


                iv_ex_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=true;
                        Fun_showimgDialog(Enquiry_add.this,iv_ex_4);
                    }
                });


                iv_ex_4.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_ex_4.setImageDrawable(null);
                        return false;
                    }
                });


                iv_ex_5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=true;
                        Fun_showimgDialog(Enquiry_add.this,iv_ex_5);
                    }
                });


                iv_ex_5.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_ex_5.setImageDrawable(null);
                        return false;
                    }
                });


                iv_ex_6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Is_Ex=true;
                        Fun_showimgDialog(Enquiry_add.this,iv_ex_6);
                    }
                });


                iv_ex_6.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        iv_ex_6.setImageDrawable(null);
                        return false;
                    }
                });

            //endregion Image View Events


            //region Radio Button Events

                rb_td_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fun_rb_Click(v);
                    }
                });
                
                rb_td_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fun_rb_Click(v);
                    }
                });

            //endregion Radio Button Events


            //region Check Box Events


                cb_exchange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cb_exchange.isChecked()){
                            Is_Ex=true;
                            linearLayExchange.setVisibility(View.VISIBLE);
                            linearLayLImgEx1.setVisibility(View.VISIBLE);
                            linearLayLImgEx2.setVisibility(View.VISIBLE);
                            linearLayLImgEx3.setVisibility(View.VISIBLE);
                        }else{
                            Is_Ex=false;
                            linearLayExchange.setVisibility(View.GONE);
                            linearLayLImgEx1.setVisibility(View.GONE);
                            linearLayLImgEx2.setVisibility(View.GONE);
                            linearLayLImgEx3.setVisibility(View.GONE);
                        }
                    }
                });

                cb_pm_finance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(cb_pm_finance.isChecked()){cb_pm_cash.setChecked(false);
                            payment_mode_fin=1;
                        }else{cb_pm_cash.setChecked(true);
                            payment_mode_fin=0;}
                    }
                });

                cb_pm_cash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(cb_pm_cash.isChecked()){cb_pm_finance.setChecked(false);
                            payment_mode_cash=1;
                        }else{cb_pm_finance.setChecked(true);
                            payment_mode_cash=0;
                        }
                    }
                });

            //endregion Check Box Events


            //region Button Events

                bt_eq_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Validation()){

                            if ("0".equals(eq_headerID)){
                                showDialog(Enquiry_add.this);
                            }else{

                                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                                    new Update_Enquiry().execute();

                                } else {
                                    new Update_Enquiry().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                }


                            }

                        }

                    }
                });

            //endregion Button Events




        //endregion Events



   }




    public boolean Validation(){

            veh_Model_Id=Fun_GetVehicleId() ;

        if (et_Cust_Mobile_Num.getText().length() !=10){
            Toast.makeText(this,"Mobile Number Should Only Contain 10 Digits",Toast.LENGTH_LONG).show();
            et_Cust_Mobile_Num.requestFocus();
            return false;
        }else if (et_Cust_Name.getText().length()<=0 ){
            Toast.makeText(this,"Must Include Name",Toast.LENGTH_LONG).show();
            et_Cust_Name.requestFocus();
            return false;
        }else if (et_Cust_Address1.getText().length()<=0 ){
            Toast.makeText(this,"Must Include Address",Toast.LENGTH_LONG).show();
            et_Cust_Address1.requestFocus();
            return false;
        }else if (et_Cust_Address2.getText().length()<=0 ){
            Toast.makeText(this,"Must Include Address",Toast.LENGTH_LONG).show();
            et_Cust_Address2.requestFocus();
            return false;
        }else if (et_cust_prosp.getText().length()<=0 ){
            Toast.makeText(this,"Must Include Prospectus No",Toast.LENGTH_LONG).show();
            et_cust_prosp.requestFocus();
            return false;
        }else if (et_eq_date.getText().length()<=0 ){
            Toast.makeText(this,"Must Include Enquiry Date",Toast.LENGTH_LONG).show();
            et_eq_date.requestFocus();
            return false;
        }else if (et_Cust_veh_feedback.getText().length()<=0 ){
            Toast.makeText(this,"Must Include Customer Feed Back",Toast.LENGTH_LONG).show();
            et_Cust_veh_feedback.requestFocus();
            return false;
        }else if ("".equals(veh_Model_Id)){
            Toast.makeText(this,"Must Select Vehicle ",Toast.LENGTH_LONG).show();
            sp_veh_model.requestFocus() ;
            return false;
        }




        if (rb_td_yes.isChecked()){

            if (et_Cust_td_feedback.getText().toString().length()<=0){
                Toast.makeText(this,"Test Drive Feedback Must Include ",Toast.LENGTH_LONG).show();
                et_Cust_td_feedback.requestFocus() ;
                return false;
            }else if (et_Cust_td_date.getText().toString().length()<=0){
                Toast.makeText(this,"Test Drive Date Must Include ",Toast.LENGTH_LONG).show();
                et_Cust_td_date.requestFocus() ;
                return false;
            }else if(sp_td_sati.getSelectedItem().toString().length()<=0){
                Toast.makeText(this,"Test Drive Satisfaction Must Include ",Toast.LENGTH_LONG).show();
                sp_td_sati.requestFocus() ;
                return false;
            }

        }


        return  true;
    }

    //region Function


    private void Fun_rb_Click(View view){
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdoYes:
                if (checked)
                    rb_td_no.setChecked(false);
                    test_drive=1;
                    Line_lay_tab3_td_img.setVisibility(View.VISIBLE);
                    Line_lay_tab3_td_details.setVisibility(View.VISIBLE);
                break;
            case R.id.rdoNo:
                if (checked)
                    test_drive=0;
                    rb_td_yes.setChecked(false);
                    Line_lay_tab3_td_img.setVisibility(View.GONE);
                    Line_lay_tab3_td_details.setVisibility(View.GONE);
                break;
        }
    }

    private void Fun_Load_Td_Satisf() {

        ArrayAdapter<String> ObjAdapter = new ArrayAdapter<String>
                (this.getBaseContext(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, td_satis );

        sp_td_sati.setAdapter(ObjAdapter);

        if ( !td_satisfaction .equals("")) {
            int spinnerPosition = ObjAdapter.getPosition(td_satisfaction);
            sp_td_sati.setSelection(spinnerPosition);
        }

    }

    public void Fun_Load_EnqStatus(){

        ArrayAdapter<String> ObjAdapter = new ArrayAdapter<String>
                (this.getBaseContext(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, eq_status  );

        if ( enquiry_status  != "") {
            int spinnerPosition = ObjAdapter.getPosition(enquiry_status );
            if (spinnerPosition>=0){
                sp_eq_status  .setAdapter(ObjAdapter);
                sp_eq_status.setSelection(spinnerPosition);}
            else{

                eq_status.add(enquiry_status);
                ArrayAdapter<String> ObjAdapter2 = new ArrayAdapter<String>
                        (this.getBaseContext(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, eq_status );
                sp_eq_status .setAdapter(ObjAdapter);
                int spinnerPosition2 = ObjAdapter2.getPosition(enquiry_status);
                sp_eq_status.setSelection(spinnerPosition2);
                sp_eq_status.setEnabled(false);
                sp_veh_model .setEnabled(false);
                sp_eq_source.setEnabled(false);
                sp_td_sati.setEnabled(false);
                et_Cust_veh_feedback .setEnabled(false);

            }

        }else{ sp_eq_status.setAdapter(ObjAdapter);}


    };

    public void Fun_Load_EnqSource(){

        ArrayAdapter<String> ObjAdapter = new ArrayAdapter<String>
                (this.getBaseContext(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, publicshared.getEnq_Source() );

        sp_eq_source.setAdapter(ObjAdapter);

        if ( enquiry_source  != "") {
            int spinnerPosition = ObjAdapter.getPosition(enquiry_source);
            sp_eq_source.setSelection(spinnerPosition);
        }

    };

    public void Fun_Load_Veh(){

        ArrayAdapter<String> ObjAdapter = new ArrayAdapter<String>
                (Objects.requireNonNull(this.getBaseContext()), android.R.layout.simple_list_item_activated_1, android.R.id.text1, publicshared.getVehName());

        sp_veh_model .setAdapter(ObjAdapter);

        //VehModel="MARUTI ALTO 800 LXI";
        if ( vehModel != "") {

            HashMap<Integer,String> veh =new HashMap<>();
            String vehName="";
            veh=publicshared.getVeh_Model();


            Integer Veh=Integer.parseInt(vehModel);

            Set<Map.Entry<Integer, String>> entrySet = veh.entrySet();

            ArrayList<Map.Entry<Integer, String>> listOfEntry = new ArrayList<Map.Entry<Integer,String>>(entrySet);
            for (Map.Entry<Integer, String> entry : listOfEntry)
            {
                Integer veh_model_name=entry.getKey();


                if(veh_model_name== Veh)
                {
                    // i_TD.putExtra("Veh_Name",veh_model_name);
                    vehName=entry.getValue().toString();
                    // ObjAdapter.getPosition(vehName);
                }
                //  System.out.println(entry.getKey()+" : "+entry.getValue());
            }

            int spinnerPosition = ObjAdapter.getPosition(vehName);
            sp_veh_model.setSelection(spinnerPosition);

        }

    };

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

    public String Fun_GetVehicleId(){

        try {

            String veh_modelID = "";

            HashMap<Integer, String> hm = new HashMap<Integer, String>();

            hm = publicshared.getVeh_Model();
            String Veh = sp_veh_model.getSelectedItem().toString();

            Set<Map.Entry<Integer, String>> entrySet = hm.entrySet();

            ArrayList<Map.Entry<Integer, String>> listOfEntry = new ArrayList<Map.Entry<Integer, String>>(entrySet);
            for (Map.Entry<Integer, String> entry : listOfEntry) {
                String veh_model_name = entry.getValue();

                if (veh_model_name == Veh) {
                    // i_TD.putExtra("Veh_Name",veh_model_name);
                    veh_modelID = entry.getKey().toString();
                }
                //  System.out.println(entry.getKey()+" : "+entry.getValue());
            }

            return veh_modelID;

        }catch (Exception e){

            return "";
        }
    }

    private boolean Fun_HasImage(@NonNull ImageView view) {

        try{

            Drawable drawable = view.getDrawable();
            boolean hasImage = (drawable != null);

            if (hasImage && (drawable instanceof BitmapDrawable)) {
                hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
            }

            return hasImage;
        }catch(Exception e){return false;}
    }

    public Date Fun_dateformated(String da_te) {

        Date SDate=null;

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // here set the pattern as you date in string was containing like date/month/year
            SDate = sdf.parse(da_te);
        }catch(ParseException ex){
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }

        return SDate;
    }

    public String Fun_nowDate(){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mMonth+=1;



        String Now=String.format("%02d", mDay)+"/"+String.format("%02d", mMonth)+"/"+mYear;

        return Now ;
    }

    public static boolean Fun_validate_Date_rev(String strDate){
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
            SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy/MM/dd");
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try
            {
                DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = originalFormat.parse(strDate);
                String formattedDate = targetFormat.format(date);

                Date javaDate = sdfrmt.parse(formattedDate);
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


    public static boolean Fun_DateValidation (String strDate){
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

    public String now_date_yyyy_MM_dd(){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mMonth+=1;
        String Now=mYear+"-"+mMonth+"-"+mDay;
        return Now ;
    }




    //region Image_Fuctions

    private void Fun_showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(Enquiry_add.this);
        pictureDialog.setTitle("Choose Action");
        String[] pictureDialogItems = {
                "Select from gallery",
                "Capture from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    public void Fun_showimgDialog(Activity activity, ImageView imgviewmodel) {

            Img=imgviewmodel;

        if (Fun_HasImage(imgviewmodel)) {

            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.image_view);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            ImageView imgfull = dialog.findViewById(R.id.img_view_ful);

            imgfull.setImageDrawable(imgviewmodel.getDrawable());

            imgfull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });


            dialog.show();
        }else{
            Fun_showPictureDialog();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (resultCode == RESULT_CANCELED) {
                return;
            }
            if (requestCode == GALLERY && resultCode == RESULT_OK) {


                if (requestCode == GALLERY) {

                    //detect single or multiple
                    Boolean single_img =false;
                    try {
                        Log.e("++data", "" + data.getClipData().getItemCount());// Get count of image here.

                        Log.e("++count", "" + data.getClipData().getItemCount());
                    } catch (Exception e){

                        single_img=true;


                    }

                    //  DataAdapter adapter = new DataAdapter(getContext(),getActivity(), imagesUriArrayList);

                    if(Is_Ex){
                        ImgSelection_Limit=6;
                    }else{ImgSelection_Limit =2;}


                    if (single_img){

                        filePath =data.getData();
                        Bitmap imgBitmap;
                        Uri imageUri = filePath;
                        imgBitmap = Fun_decodeUri(this, imageUri, 200);
                        Img.setImageBitmap(imgBitmap);


                    }else if (data.getClipData().getItemCount() > ImgSelection_Limit) {

                        Snackbar snackbar = Snackbar
                                .make(findViewById(R.id.imageView), "You can not select more than "+ImgSelection_Limit+" images", Snackbar.LENGTH_LONG)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent();
                                        intent.setType("image/*");
                                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                                        intent.setAction(Intent.ACTION_GET_CONTENT);
                                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);
                                    }
                                });
                        snackbar.setActionTextColor(Color.BLUE);
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                        textView.setTextColor(Color.RED);
                        snackbar.show();

                    } else {

                        ImageView img_all[];

                        if (Is_Ex){
                            img_all= new ImageView[ex_iv.length];
                            img_all= ex_iv;
                        }else{
                            img_all= new ImageView[td_iv.length];
                            img_all= td_iv;
                        }

                        boolean start =false;


                        for (int i = 0; i < data.getClipData().getItemCount(); i++) {

                            Bitmap imgBitmap = null;

                                if (img_all[i]==Img){
                                    start=true;
                                }
                            if (start) {

                                Img=img_all[i];
                                // Img.setImageURI(null);
                                imgBitmap = Fun_decodeUri(this, data.getClipData().getItemAt(i).getUri(), 200);
                                // Img.setImageURI(imagesUriArrayList.get(i));
                                Img.setImageBitmap(imgBitmap);
                            }

                        }


                    }

                }

            } else if (requestCode == CAMERA) {

                filePath = data.getData();
                Bitmap bitmap;
                bitmap = (Bitmap) data.getExtras().get("data");

                Img.setImageBitmap(bitmap);
                Fun_saveImage(bitmap);

                filePath = data.getData();

                Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }
        }catch ( Exception e){
            Log.d("img2iv",e.getMessage());
        }
    }


    public String Fun_saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();


            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    public static Bitmap Fun_decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;

        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

    public String Fun_getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private boolean DownloadImage(){
        class DownImage extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = ProgressDialog.show(Enquiry_add.this, "Downloading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                loading.dismiss();

                Toast.makeText(Enquiry_add.this,s,Toast.LENGTH_LONG).show();
                Setbitmaps_toIV();
            }



            @Override
            protected String doInBackground(String... params) {
                String ss =params[0];

                 // eq_headerID


                for (int i =0 ;i < td_iv.length;i++) {
                    try {
                        String Url1 = DOWNLOAD_URL + publicshared.getBranch_Code() + "/" + eq_headerID + "/" + eq_headerID + "TD"+i+".jpg";
                        td_bitmaps[i]=(BitmapFactory.decodeStream((InputStream) new URL(Url1).getContent()));

                    }catch (MalformedURLException me){
                        me.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                for (int i =0 ;i < ex_iv.length;i++) {
                    try {
                        String Url2 = DOWNLOAD_URL + publicshared.getBranch_Code() + "/" + eq_headerID + "/" + eq_headerID + "EXC"+i+".jpg";
                        ex_bitmaps[i]=(BitmapFactory.decodeStream((InputStream) new URL(Url2).getContent()));

                    }catch (MalformedURLException me){
                        me.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }



                return "Ok";

            }
        }


        DownImage di = new DownImage();

        di.execute("");
        return true;
    }

    private void Setbitmaps_toIV() {

        for (int i =0 ;i < td_bitmaps.length;i++) {
            try {

                td_iv[i].setImageBitmap( td_bitmaps[i]);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        for (int i =0 ;i < ex_bitmaps.length;i++) {
            try {

                ex_iv[i].setImageBitmap( ex_bitmaps[i]);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private boolean uploadImagenew(){
        class UploadImage extends AsyncTask<String,Integer,ArrayList<String>> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //imgUpload=false;
                loading = ProgressDialog.show(Enquiry_add.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

                loading.dismiss();

                // Toast.makeText(getActivity().getApplicationContext(),values.toString(),Toast.LENGTH_LONG).show();

                // loading = ProgressDialog.show(getActivity(), "Uploading Image", values.toString(),true,true);

                //Update the progress of current task

                loading = ProgressDialog.show(Enquiry_add.this, "Uploading Image", "Uploading... "+values[0].toString(),true,true);

            }


            @Override
            protected void onPostExecute(ArrayList<String> s) {
                super.onPostExecute(s);
                loading.dismiss();
                // pevUploadid=Uploadid;
//                if (pevUploadid==2){buttonChoose.setEnabled(false);
//                buttonChoose.setClickable(false);
//                }

                Enquiry_add.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


                String Response=" ";
                for (int i=0;i<s.size();i++) {

                    Response=Response + s.get(i).toString()+"\n";
                }

                Toast.makeText(Enquiry_add.this.getApplicationContext(),Response,Toast.LENGTH_SHORT).show();




                Enquiry_add.this.finish();

            }



            @SuppressLint("WrongThread")
            @Override
            protected ArrayList<String> doInBackground(String... params) {
                String ee = params[0];
                ArrayList<String> result=new ArrayList<>();
                String resp=" ";
                int i,j=0;
                String typo="TD";

                if (test_drive == 1 && typo =="TD") {
                    for(i=0;i<td_iv.length;i++){
                        try{
                            publishProgress(i);

                            resp="";
                            ImageView imagePreview =td_iv[i];

                            bitmap = ((BitmapDrawable) imagePreview.getDrawable()).getBitmap();


                            HashMap<String, String> datas = new HashMap<>();
                            datas.put(UPLOAD_KEY, " ");
                            // data.put("name",getFileName(filePath));
                            datas.put("delete", "1");
                            datas.put("EnqId", publicshared.getBranch_Code() + "/" + eq_headerID);
                            datas.put("name", eq_headerID + typo + i + ".jpg");
                            resp = i + "Error";
                            resp = rh.postRequest(UPLOAD_URL, datas);



                            String uploadImage = Fun_getStringImage(bitmap);

                            HashMap<String,String> data = new HashMap<>();
                            data.put(UPLOAD_KEY, uploadImage);
                            // data.put("name",getFileName(filePath));
                            data.put("EnqId",publicshared.getBranch_Code()+"/"+eq_headerID);
                            data.put("delete","0");
                            data.put("name",eq_headerID+typo+i+".jpg");
                            resp=i+"Error";

                            resp=rh.postRequest(UPLOAD_URL, data);

                            result.add(i, resp);

                        }catch (Exception e){
                            try {
                                if (!resp.contains("Your Image Has Been Uploaded.")) {
                                    resp="";
                                    HashMap<String, String> data = new HashMap<>();
                                    data.put(UPLOAD_KEY, " ");
                                    // data.put("name",getFileName(filePath));
                                    data.put("delete", "1");
                                    data.put("EnqId", publicshared.getBranch_Code() + "/" + eq_headerID);
                                    data.put("name", eq_headerID + typo + i + ".jpg");
                                    resp = i + "Error";
                                    resp = rh.postRequest(UPLOAD_URL, data);
                                }
                            }catch (Exception f){}
                        }


                    }
                }else{
                    for(i=0;i<td_iv.length;i++) {
                        try {
                            HashMap<String, String> data = new HashMap<>();
                            data.put(UPLOAD_KEY, " ");
                            // data.put("name",getFileName(filePath));
                            data.put("delete","1");
                            data.put("EnqId",publicshared.getBranch_Code()+"/"+eq_headerID);
                            data.put("name", eq_headerID + typo + i + ".jpg");
                            resp = i + "Error";
                            resp = rh.postRequest(UPLOAD_URL, data);
                        }catch (Exception e){}
                    }
                }



                i=0;
                typo="EXC";

                if (Is_Ex && typo=="EXC") {
                    for(i=0;i<ex_iv.length;i++) {

                        try {
                            publishProgress(i);

                            // bitmap= publicShared.getExchangeImg().get(i);
                            ImageView imgPreview = ex_iv[i];
                            bitmap = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();

                            String uploadImage = Fun_getStringImage(bitmap);

                            HashMap<String,String> data = new HashMap<>();
                            data.put(UPLOAD_KEY, uploadImage);
                            data.put("delete","0");
                            data.put("EnqId",publicshared.getBranch_Code()+"/"+eq_headerID);
                            // data.put("name",getFileName(filePath));
                            data.put("name",eq_headerID+typo+i+".jpg");


                            resp = i + "Error";
                            resp=rh.postRequest(UPLOAD_URL, data);

                            result.add(resp);



                        } catch (Exception e) {

                            try {
                                HashMap<String, String> data = new HashMap<>();
                                data.put(UPLOAD_KEY, " ");
                                // data.put("name",getFileName(filePath));
                                data.put("delete","1");
                                data.put("EnqId",publicshared.getBranch_Code()+"/"+eq_headerID);
                                data.put("name", eq_headerID + typo + i + ".jpg");
                                resp = i + "Error";

                                resp = rh.postRequest(UPLOAD_URL, data);
                            }catch (Exception g){}

                        }
                    }
                }else
                {
                    for(i=0;i<ex_iv.length;i++) {
                        try {
                            HashMap<String, String> data = new HashMap<>();
                            data.put(UPLOAD_KEY, " ");
                            // data.put("name",getFileName(filePath));
                            data.put("delete","1");
                            data.put("EnqId",publicshared.getBranch_Code()+"/"+eq_headerID);
                            data.put("name", eq_headerID + typo + i + ".jpg");
                            resp = i + "Error";

                            resp = rh.postRequest(UPLOAD_URL, data);
                        }catch (Exception e){}
                    }
                }
                return result;


            }
        }

        UploadImage ui = new UploadImage();

        ui.execute("");
        return true;
    }



    //endregion Image_Functions


    //endregion function

    //region showDialog

    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.fragment_enquiry_add_tab5);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));

        TextInputLayout TIL1= dialog.findViewById(R.id.TXTILspnExe);
        TextInputLayout TIL2= dialog.findViewById(R.id.txtinputLayoutExeAction);
        TextInputLayout TIL3= dialog.findViewById(R.id.TxtILEXeDate);
        TextView TvExe=dialog.findViewById(R.id.TVExeFp);


        TIL1.setVisibility(View.GONE);
        TIL2.setVisibility(View.GONE);
        TIL3.setVisibility(View.GONE);
        TvExe.setVisibility(View.GONE);


        final Button BtnFpSave = dialog.findViewById(R.id.BtnFp_Save);
        Button BtnFpCancel = dialog.findViewById(R.id.BtnFp_Cancel);
        final EditText EditTxtFpDate = dialog.findViewById(R.id.EditTxt_NextActionPlanDate);
        final EditText EditTxtFp = dialog.findViewById(R.id.EditTxt_NextActionPlan);
        String Items[] = {"Call","Field Visit","Showroom Visit","Other Actions"};

// Selection of the spinner
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spnNxtFollowup);

// Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Enquiry_add.this,   android.R.layout.simple_spinner_item, Items);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);




        EditTxtFpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(Enquiry_add.this);
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

                String PreviousDate=EditTxtFpDate.getText().toString();
                if(PreviousDate.length()>=10){PreviousDate = PreviousDate.substring(0, 10);}
//                Date c = Calendar.getInstance().getTime();

                Date date1=Fun_dateformated(Fun_nowDate());
                Date date2=Fun_dateformated(PreviousDate);

//                if (date1.compareTo(date2) <= 0) {
//                    System.out.println("earlier");
//                }


                if(date1.compareTo(date2)==0){




                }else if(date1.compareTo(date2)>0)
                {
                    Toast.makeText(Enquiry_add.this,"Invalid Date",Toast.LENGTH_SHORT).show();
                    EditTxtFpDate.setText(Fun_nowDate());
                    // Toast.makeText(context," Less Than Today",Toast.LENGTH_SHORT).show();
                }else if(date1.compareTo(date2)<0)
                {
                    // publicshared.setEnquiry_Tab_Id(1);
                    // String a= changeDateFormat("dd/MM/yyyy","yyyy/MM/dd",EditTxtFpDate.getText().toString());
                }

                if (Fun_validate_Date_rev(EditTxtFpDate.getText().toString())) {

                    publicshared.setEq_NextPlanDate(EditTxtFpDate.getText().toString());
//                        String a= changeDateFormat("dd/MM/yyyy","yyyy/MM/dd",EditTxtFpDate.getText().toString());
//
//                        Toast.makeText(getActivity(),a,Toast.LENGTH_SHORT).show();

                }else{
                    publicshared.setEq_NextPlanDate(EditTxtFpDate.getText().toString());
                    Toast.makeText(Enquiry_add.this,"Invalid Date",Toast.LENGTH_SHORT).show();}

            }
        });


        BtnFpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation=true;
                if(EditTxtFp.getText().toString().length()==0){validation=false;}
                else if (EditTxtFpDate.getText().toString().length()==0){validation=false;}
                else if (spinner.getSelectedItem().toString().length()==0){validation=false;}

                if (publicshared.getEq_NextPlanDate().toString().length()==0){
                    validation=false;
                }


                if(validation) {

                    publicshared.setEq_NextAction(spinner.getSelectedItem().toString());
                    publicshared.setEq_NextActionPlan(EditTxtFp.getText().toString());

                    if (!publicshared.getBranch_Id().equals("") && !publicshared.getScId().equals("")) {

                        BtnFpSave.setEnabled(false);

                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) {
                            new Update_Enquiry().execute();

                        } else {
                            new Update_Enquiry().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }

                    }else{
                        Toast.makeText(Enquiry_add.this,"Failed To Load Sc Details",Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(Enquiry_add.this,"Fill Required Fields",Toast.LENGTH_LONG).show();
                }




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

    //endregion ShowDialogue

   //region AsyncTask


    //region Phone_Number_Verification

    class validate_Phone extends AsyncTask<String, String, String> {

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
            pDialog = new ProgressDialog(Enquiry_add.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        @SuppressLint("WrongThread")
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("Mob_no",et_Cust_Mobile_Num.getText().toString()));

            // check for success tag
            try {

                json = jsonParser.makeHttpRequest(url_validate,"GET", params);
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



                        Prospects_Number = json.getString("Mob_No");

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


                if (!Prospects_Number.equals("NULL")) {
                    Toast.makeText(Enquiry_add.this, Prospects_Number + " Enquiry Already Exist", Toast.LENGTH_LONG).show();
                    et_Cust_Mobile_Num .setText("");
                    Prospects_Number="NULL";
                }

            }else if (json==null){
                Toast.makeText(Enquiry_add.this, "Please check your internet connection...",Toast.LENGTH_LONG).show();
            } else{
                 // Toast.makeText(Enquiry_add.this , "Sorry,failed to check details",Toast.LENGTH_LONG).show();
            }
            pDialog.dismiss();

        }

    }

    //endregion Phone_Number_Verification

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


    //region UpdateEnquiry

    class Update_Enquiry extends AsyncTask<String, String, String> {


        JSONParser jsonParser = new JSONParser();
        JSONParser jsonParserimg = new JSONParser();
        JSONObject json = null;
        JSONObject jsonimg = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Enquiry_add.this);
            pDialog.setMessage("Saving Details.." + "\n" + "Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @SuppressLint("WrongThread")
        protected String doInBackground(String... args) {

            publicshared.setEnq_Date(changeDateFormat("dd/MM/yyyy","yyyy/MM/dd",et_eq_date.getText().toString()));
            publicshared.setEq_NextPlanDate(changeDateFormat("dd/MM/yyyy","yyyy/MM/dd",publicshared.getEq_NextPlanDate()));


            Boolean validation=true;

            if( !Fun_validate_Date_rev(publicshared.getEnq_Date())){
                publicshared.setEnq_Date(now_date_yyyy_MM_dd());
                Toast.makeText(Enquiry_add.this,et_eq_date.getText().toString(),Toast.LENGTH_LONG).show();

                if( !Fun_validate_Date_rev(publicshared.getEnq_Date())) {
                    validation=false;
                }
            }else{

            }

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();


            params.add(new BasicNameValuePair("Enq_HeaderId",eq_headerID));
            params.add(new BasicNameValuePair("Cust_Place",et_Cust_Address1.getText().toString()));
            params.add(new BasicNameValuePair("Cust_Occupt",et_Cust_Occupation.getText().toString()));
            params.add(new BasicNameValuePair("Cust_Name",et_Cust_Name.getText().toString()));
            params.add(new BasicNameValuePair("Cust_City",et_Cust_Address2.getText().toString()));
            params.add(new BasicNameValuePair("Cust_Tel_Mob",et_Cust_Mobile_Num.getText().toString()));
            params.add(new BasicNameValuePair("Cust_Tel_Office",""));
            params.add(new BasicNameValuePair("Cust_Email",et_Cust_Email.getText().toString()));
            params.add(new BasicNameValuePair("Cust_Pin",et_Cust_Pin.getText().toString()));
            params.add(new BasicNameValuePair("Prospect_No",et_cust_prosp.getText().toString()));
            params.add(new BasicNameValuePair("Enq_Date",publicshared.getEnq_Date()));
            params.add(new BasicNameValuePair("Enq_Pref1",veh_Model_Id));
            params.add(new BasicNameValuePair("Enq_Source",sp_eq_source.getSelectedItem().toString()));
            params.add(new BasicNameValuePair("Enq_Type",sp_eq_status.getSelectedItem().toString()));
            params.add(new BasicNameValuePair("PMode_Cash",String.valueOf(payment_mode_cash)));
            params.add(new BasicNameValuePair("PMode_Finance",String.valueOf(payment_mode_fin)));
            params.add(new BasicNameValuePair("PMode_Exchange",Is_Ex ? "1" : "0" ));
            params.add(new BasicNameValuePair("Exchange_Vehicle",et_Cust_ex_veh.getText().toString()));
            params.add(new BasicNameValuePair("Test_Drive",String.valueOf(test_drive)));
            params.add(new BasicNameValuePair("Branch_Id",publicshared.getBranch_Id()));
            params.add(new BasicNameValuePair("SourceRemarks",publicshared.getEq_SourceRemarks()));
            params.add(new BasicNameValuePair("ActionPlan",publicshared.getEq_NextActionPlan()));
            params.add(new BasicNameValuePair("PlannedTask",publicshared.getEq_NextAction()));
            params.add(new BasicNameValuePair("TaskDate",publicshared.getEq_NextPlanDate()));
            params.add(new BasicNameValuePair("Sc_Id",publicshared.getScId().toString()));
            params.add(new BasicNameValuePair("Branch_Code",publicshared.getBranch_Code()));
            params.add(new BasicNameValuePair("Branch_Id",publicshared.getBranch_Id()));
            params.add(new BasicNameValuePair("Cust_FB",et_Cust_veh_feedback.getText().toString()));
            params.add(new BasicNameValuePair("Test_Drive_Satisf",sp_td_sati.getSelectedItem().toString()));

            params.add(new BasicNameValuePair("Ex_BrokerPrice1",et_Brok_rate.getText().toString()));
            params.add(new BasicNameValuePair("Ex_CustPrice",et_Cust_ex_rate.getText().toString()));
            params.add(new BasicNameValuePair("Ex_BrokerName1",et_Brok_name.getText().toString()));


            try {

                publicshared.setEq_TestDriveDate(changeDateFormat("dd/MM/yyyy", "yyyy/MM/dd", et_Cust_td_date.getText().toString()));

                if(!Fun_validate_Date_rev(publicshared.getEq_TestDriveDate())){publicshared.setEq_TestDriveDate(now_date_yyyy_MM_dd());}


                params.add(new BasicNameValuePair("Test_Drive_Date",publicshared.getEq_TestDriveDate()));


            }catch (Exception e){

                params.add(new BasicNameValuePair("Test_Drive_Date",now_date_yyyy_MM_dd()));
            }


            params.add(new BasicNameValuePair("TestDriveFB",et_Cust_td_feedback.getText().toString()));
            params.add(new BasicNameValuePair("Remarks",publicshared.getEq_remarks()));





            try {

                if(validation) {
                    json = jsonParser.makeHttpRequest(url_update_enquiry, "GET", params);
                }else{

                }

            } catch (Exception e) {}

            if(json==null){	success = false;
            }else
            {
                try {
                    String key ="\"Enq_HeaderId\"";
                    success = json.getBoolean(TAG_SUCCESS);
                    success = json.getBoolean("successFp");
                    //Bk_FinId  = json.getString("Bk_FinId");
                    publicshared.setEq_headerID(json.getString("Enq_HeaderId"));


                    Log.d("Json",json.getString("successProspnum"));
                    Log.d("Json",json.getString("successFp"));
                    Log.d("Json",json.getString("messageFp"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }



        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();

            Enquiry_add.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            if (success){

                Toast.makeText(Enquiry_add.this, "Saved successfully.",Toast.LENGTH_LONG).show();

                bitmap=null;
                uploadImagenew();

            }else if (json==null){

                Toast.makeText(Enquiry_add.this, "Please check your internet connection...",Toast.LENGTH_LONG).show();

            } else{
                Toast.makeText(Enquiry_add.this, "Sorry,failed to sent request",Toast.LENGTH_LONG).show();

            }

        }
    }

    //endregion UpdateEnquiry








   //endregion AsyncTask




















    @Override
    public void onBackPressed() {
        isBackPressed = true;
        if (tabsInBack != null && tabsInBack.size() > 0) {
            if (tabs.getSelectedTabPosition() == tabsInBack.get(tabsInBack.size() - 1)) {
                tabsInBack.remove(tabsInBack.size() - 1);

            }
            if (tabsInBack != null && tabsInBack.size() > 0) {
                tabs.getTabAt(tabsInBack.get(tabsInBack.size() - 1)).select();
                tabsInBack.remove(tabsInBack.size() - 1);
                if(isBackPressedOnce )
                {
                    super.onBackPressed();
                }
                else
                {
                    isBackPressedOnce = true;
                }
            } else {
//
                super.onBackPressed();
            }
        } else {

            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_my_custom);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));

            MaterialButton bt_positive =dialog.findViewById(R.id.btPositive);
            MaterialButton bt_negative =dialog.findViewById(R.id.btNegative);

            bt_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            bt_positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            dialog.show();
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

package com.nandu.autox;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Enquiry_add_Tab5 extends DialogFragment {


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_Enq_HeaderId= "Enq_HeaderId";
    private static final String url_update_enquiry= "http://logicxnet.com/mcsms/grandbajaj/app_connector/update_enquiry.php";
    public static final String UPLOAD_URL = "http://logicxnet.com/mcsms/grandbajaj/app_connector/image_upload.php";
    public static final String UPLOAD_KEY = "image";
    ProgressDialog pDialog;
    PublicShared publicShared;
    Context context;
    static Boolean success = false;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_enquiry_add_tab5, container, false);
//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        context= getContext();

        publicShared =(PublicShared) context.getApplicationContext();




        return root;
    }


















//
//    private boolean uploadImage(){
//        class UploadImage extends AsyncTask<Bitmap,Void,String> {
//            ProgressDialog loading;
//            RequestHandler rh = new RequestHandler();
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(getContext(), "Uploading Image", "Please wait...",true,true);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                // pevUploadid=Uploadid;
//
////                if (pevUploadid==2){buttonChoose.setEnabled(false);
////                buttonChoose.setClickable(false);
////                }
//                Toast.makeText(getContext().getApplicationContext(),s,Toast.LENGTH_LONG).show();
//            }
//
//
//
//            @Override
//            protected String doInBackground(Bitmap... params) {
//                Bitmap bitmap = params[0];
//                String uploadImage = getStringImage(bitmap);
//
//                HashMap<String,String> data = new HashMap<>();
//                data.put(UPLOAD_KEY, uploadImage);
//                // data.put("name",getFileName(filePath));
//                data.put("name","TestDrive"+Uploadid+".jpg");
//
//
//                String result = rh.postRequest(UPLOAD_URL,data);
//                if (result.contains("Please Try Again")){
//
//                }else {if (pevUploadid==2){
////                    buttonChoose.setEnabled(false);
//                    buttonUpload.setClickable(false);
//                }}
//                Uploadid+=1;
//                return result;
//
//
//
//            }
//        }
//
//
//        UploadImage ui = new UploadImage();
//        ui.execute(bitmap);
//        return true;
//    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    class Update_Enquiry extends AsyncTask<String, String, String> {


        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Saving Details.." + "\n" + "Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();


            params.add(new BasicNameValuePair("Enq_HeaderId",publicShared.getEq_headerID()));
            params.add(new BasicNameValuePair("Cust_City",publicShared.getAddress1()));
            params.add(new BasicNameValuePair("Cust_Occupt",publicShared.getOccupation()));
            params.add(new BasicNameValuePair("Cust_Name",publicShared.getName()));
            params.add(new BasicNameValuePair("Cust_Place",publicShared.getAddress2()));
            params.add(new BasicNameValuePair("Cust_Tel_Mob",publicShared.getEqMobileNo().toString()));
            params.add(new BasicNameValuePair("Cust_Tel_Office",""));
            params.add(new BasicNameValuePair("Cust_Email",publicShared.getE_mail()));
            params.add(new BasicNameValuePair("Cust_Pin",publicShared.getPinCode()));
            params.add(new BasicNameValuePair("Prospect_No",publicShared.getEq_ProsNo()));
            params.add(new BasicNameValuePair("Enq_Date",publicShared.getEnq_Date()));
            params.add(new BasicNameValuePair("Enq_Pref1",publicShared.getVehPref()));
            params.add(new BasicNameValuePair("Enq_Source",publicShared.getEqsource()));
            params.add(new BasicNameValuePair("Enq_Type",publicShared.getEqStatus()));
            params.add(new BasicNameValuePair("PMode_Cash",publicShared.getEq_Cash()));
            params.add(new BasicNameValuePair("PMode_Finance",publicShared.getEq_Finance()));
            params.add(new BasicNameValuePair("PMode_Exchange",publicShared.getExchange()));
            params.add(new BasicNameValuePair("Exchange_Vehicle",publicShared.getEq_ExVeh()));
            params.add(new BasicNameValuePair("Test_Drive",publicShared.getEq_TestDrive()));

            params.add(new BasicNameValuePair("SourceRemarks",publicShared.getEq_SourceRemarks()));
            params.add(new BasicNameValuePair("ActionPlan",publicShared.getEq_NextActionPlan()));
            params.add(new BasicNameValuePair("PlannedTask",publicShared.getEq_NextAction()));
            params.add(new BasicNameValuePair("TaskDate",publicShared.getEq_NextPlanDate()));
            params.add(new BasicNameValuePair("Sc_Id",publicShared.getScId().toString()));
            params.add(new BasicNameValuePair("Branch_Code",publicShared.getBranch_Code()));
            params.add(new BasicNameValuePair("Branch_Id",publicShared.getBranch_Id()));

            if(publicShared.getEq_TestDrive()=="1"){

                params.add(new BasicNameValuePair("Test_Drive_Date",publicShared.getEq_TestDriveDate()));
                params.add(new BasicNameValuePair("TestDriveFB",publicShared.getEq_TestDriveFb()));
                params.add(new BasicNameValuePair("Remarks",publicShared.getEq_remarks()));

                for (int i= 0;i<publicShared.getTestImg().size();i++){


                    String imgData= getStringImage(publicShared.getTestImg().get(i));
                    params.add(new BasicNameValuePair("TestDrivePic"+i+"",imgData));

                }

            }

            if (publicShared.getExchange()=="1"){

                for (int i= 0;i< publicShared.getExchangeImg().size();i++) {
                    String imgData= getStringImage(publicShared.getExchangeImg().get(i));
                    params.add(new BasicNameValuePair("ExchangePic"+i+"",imgData));

                }

            }



            try {

                json = jsonParser.makeHttpRequest(url_update_enquiry, "GET", params);


            } catch (Exception e) {}

            if(json==null){	success = false;
            }else
            {
                try {
                    String key ="\"Enq_HeaderId\"";
                    success = json.getBoolean(TAG_SUCCESS);


                    //Bk_FinId  = json.getString("Bk_FinId");
                    publicShared.setEq_headerID(json.getString("Enq_HeaderId"));

                    Log.d("Json",json.getString("successProspnum"));
                    Log.d("Json",json.getString("successFp"));
                    Log.d("Json",json.getString(" messageFp"));

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

            if (success){

                Toast.makeText(getContext().getApplicationContext(), "Saved successfully.",Toast.LENGTH_LONG).show();
//                Intent intent_BkList = new Intent(getContext().getApplicationContext(), Enquiry_Search.class);
//                intent_BkList.putExtra("Sc_Id",publicShared.getScId());
//                startActivity(intent_BkList);
//                //ExePlanDate=null;
//                getActivity().finish();

            }else if (json==null){

                Toast.makeText(getContext().getApplicationContext(), "Please check your internet connection...",Toast.LENGTH_LONG).show();

            } else{
                Toast.makeText(getContext().getApplicationContext(), "Sorry,failed to sent request",Toast.LENGTH_LONG).show();

            }
            pDialog.dismiss();

        }

















    }


}

package com.nandu.autox;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public class PublicShared extends Application {
	
	private String Sc_Id="";
	private String Sc_Name="";
    private String Sc_Desig_Code;
    private String Prospects_num;
    private String Branch_Code;
    private String Ver;
    private String BranchId;
    private String BranchName;
    private String Sc_Desig_Name;
    private String MobileNum="0";
    private String BookingCount;
    private String EnqCount;
    private String Propic_Url;
    private String Cust_Fed;
    private double Enquiry_TabId=0;
    private String Password;
    private String eqcount ;
    private String bkcount;
    private String delcount;
    private String invcount;
    private String pencount;
    private String testdrive_count;



    private String  MobileNo ="";
    private String  Address1 ="";
    private String  Address2="" ;
    private String  Name ="";
    private String  Pin ="";
    private String  Enq_Date ="";
    private String  E_mail ="";
    private String  Occupation ="";
    private String  VehPref ="";
    private String  Eqsource ="";
    private String  EqStatus ="";
    private String  Finance ="";
    private String  Exchange ="";
    private String  Eq_headerID="0";
    private String  Eq_ProsNo="";
    private String  Eq_ExVeh="";
    private String  Eq_Cash="";
    private String  Eq_TestDrive="";
    private String  Eq_TestDriveFb="";
    private String  Eq_TestDriveDate="";
    private String  Eq_remarks="";
    private String  Eq_SourceRemarks="";
    private String  Eq_NextActionPlan="";
    private String  Eq_NextPlanDate="";
    private String  Eq_NextAction="";
    private String  Eq_ExeActionPlan="";
    private String  Eq_ExePlanDate="";
    private String  Eq_ExeAction="";
    private String  Enq_Fp_Id;
    private String Test_Drive_Satisf="";
    private String Server_Version="";
    private String EqExCustRate="";
    private String EqExBroRate="";
    private String EqExBroName="";



    private ArrayList<Bitmap> ExchangeImg;
    private ArrayList<Bitmap> TestImg;
    private boolean SkipAppReq;
	private ArrayList<String> Fin_Company;
	private ArrayList<String> Cust_Occupation;
	private ArrayList<String> Customer_City;
	private ArrayList<String> Veh_Model_Name;
	private ArrayList<String> Enq_Source;
	private ArrayList<String> Enq_Status;
    private ArrayList<String> BrokeName;

    HashMap<Integer, String> vehModel = new HashMap<Integer, String>();

    public static PublicShared instance;
	private static Context context;

    public void onCreate(){
        super.onCreate();
        PublicShared.context = getApplicationContext();
        instance = this;
    }



    public static Context getAppContext() {
        return PublicShared.context;
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }



    public static PublicShared getInstance() {
        return instance;
    }




    public String gettestdrive_count() {

        return testdrive_count;
    }

    public void setTestdrive_count (String testdrivecount) {

        testdrive_count = testdrivecount;

    }




    public String geteqcount() {

        return eqcount;
    }

    public void seteqcount(String Enq_Count) {

        eqcount = Enq_Count;

    }


    public String getpencount() {

        return pencount;
    }

    public void setpencount(String Pen_Count) {

        pencount = Pen_Count;

    }

    public String getBkcount() {

        return bkcount;
    }

    public void setBkcount(String bk_count) {

        bkcount = bk_count;

    }

    public String getinvcount() {

        return invcount;
    }

    public void setinvcount(String inv_count) {

        invcount = inv_count;

    }

    public String getdelcount() {

        return delcount;
    }

    public void setdelcount(String del_count) {

        delcount = del_count;

    }




























    public String getMobileNum() {

        return MobileNum;
    }

    public void setMobileNum(String mob_num) {

        MobileNum = mob_num;

    }

    public String getEqExBroName() {

        return EqExBroName;
    }

    public void setEqExBroName(String EqExBro_Name) {

        EqExBroName = EqExBro_Name;

    }


    public String getEqExCustRate() {

        return EqExCustRate;
    }

    public void setEqExCustRate(String EqExCust_Rate) {

        EqExCustRate = EqExCust_Rate;

    }




    public String getEqExBroRate() {

        return EqExBroRate;
    }

    public void setEqExBroRate(String EqExBro_Rate) {

        EqExBroRate = EqExBro_Rate;

    }














    public String getPassword() {

        return Password;
    }

    public void setPassword(String psw) {

        Password = psw;

    }

    public String getCust_Fed() {

        return Cust_Fed;
    }

    public void setCust_Fed(String custfed) {

        Cust_Fed = custfed;

    }

    //Server_Version

    public String getServer_Version() {

        return Server_Version;
    }

    public void setServer_Version(String ServerVersion) {

        Server_Version = ServerVersion;

    }



    public String getTest_Drive_Satisf() {

        return Test_Drive_Satisf;
    }

    public void setTest_Drive_Satisf(String Test_DriveSatisf) {

        Test_Drive_Satisf = Test_DriveSatisf;

    }




    public String getBookingCount() {

        return BookingCount;
    }

    public void setBookingCount(String book_count) {

        BookingCount = book_count;

    }


    public String GetPropicUrl() {

        return Propic_Url;
    }

    public void setPropic_Url (String url) {

        Propic_Url = url;

    }


    public String getEnqCount() {

        return EnqCount;
    }

    public void setEnqCount(String Enq_count) {

        EnqCount = Enq_count ;

    }




    public double getEnquiry_Tab_Id() {

        return Enquiry_TabId;
    }

    public void setEnquiry_Tab_Id(double Enquiry_Add_Id) {

        Enquiry_TabId = Enquiry_Add_Id;

    }












    public String getBranchName() {

        return BranchName;
    }

    public void setBranchName(String branch_Name) {

        BranchName = branch_Name;

    }

    public void setSc_Desig_Name(String scDesigName) {
        Sc_Desig_Name = scDesigName;

    }

    public String getSc_Desig_Name() {

        return Sc_Desig_Name;
    }


    public String getScName() {
         
        return Sc_Name;
    }

    public void setScName(String ScName) {
        
    	Sc_Name = ScName;
         
    }

    public String getSc_Desig_Code() {

        return Sc_Desig_Code;
    }

    public void setSc_Desig_Code(String ScDesigCode) {

        Sc_Desig_Code = ScDesigCode;

    }


    public String getBranch_Id() {

        return BranchId;
    }

    public void setBranch_Id(String Branch_Id) {

        BranchId  = Branch_Id;

    }




    public String getBranch_Code() {

        return Branch_Code;
    }

    public void setBranch_Code(String BranchCode) {

        Branch_Code = BranchCode;

    }

    
    public String getScId() {
        
        return Sc_Id;
    }
     
    public void setScId(String ScId) {
        
    	Sc_Id = ScId;
         
    }


    public String getVer() {

        return Ver;
    }

    public void setVer(String VerNo) {

        Ver = VerNo;

    }




    public Boolean getSkip_AppReq() {
        
        return SkipAppReq;
    }
     
    public void setSkip_AppReq(Boolean Skip_App_Req) {
        
    	SkipAppReq = Skip_App_Req;
         
    }
    
    public ArrayList<String> getFinCompany() {
        
        return Fin_Company;
    }
     
    public void setFinCompany( ArrayList<String> fin_company) {

    	Fin_Company = fin_company;
         
    }

    public ArrayList<String> getCust_Occupt() {

        return Cust_Occupation;
    }

    public void setCust_Occupt( ArrayList<String> Cust_Occupt) {

        Cust_Occupation = Cust_Occupt;

    }

    public ArrayList<String> getCust_City() {

        return Customer_City;
    }

    public void setCust_City( ArrayList<String> Cust_City) {

        Customer_City = Cust_City;

    }


    public void setProspects_number( String Prospects) {

        Prospects_num = Prospects;

    }

    public String GetProspects_number() {

        return Prospects_num;
    }


    public HashMap<Integer, String> getVeh_Model() {

        return vehModel;
    }

    public void setVeh_Model(HashMap<Integer, String> veh_Model) {

        vehModel = veh_Model;

    }



    public ArrayList<String> getVehName() {

        return Veh_Model_Name;
    }

    public void setVehName( ArrayList<String> vehName) {

        Veh_Model_Name = vehName;

    }

    public ArrayList<String> getEnq_Source() {

        return Enq_Source;
    }

    public void setEnq_Source( ArrayList<String> enqSource) {

        Enq_Source = enqSource;

    }

    public ArrayList<String> getEnq_Status() {

        return Enq_Status;
    }

    public void setEnq_Status( ArrayList<String> enqStatus) {

        Enq_Status = enqStatus;

    }

    public ArrayList<String> getBrokName() {

        return BrokeName;
    }

    public void setBrokeName( ArrayList<String> Broke_Name) {

        BrokeName = Broke_Name;

    }









    public String getEqMobileNo() {

        return MobileNo;
    }

    public void setEqMobileNum(String mob_num) {

        MobileNo = mob_num;

    }

    public String getAddress1() {

        return Address1;
    }

    public void setAddress1(String Address_1) {

        Address1 = Address_1;

    }

    public String getAddress2() {

        return Address2;
    }

    public void setAddress2(String Address_2) {

        Address2 = Address_2;

    }


    public String getName() {

        return Name;
    }

    public void setName(String name) {

        Name = name;

    }


    public String getEnq_Date() {

        return Enq_Date;
    }

    public void setEnq_Date(String EnqDate) {

        Enq_Date = EnqDate;

    }


    public String getE_mail() {

        return E_mail;
    }

    public void setE_mail(String Email) {

        E_mail = Email;

    }


    public String getOccupation() {

        return Occupation;
    }

    public void setOccupation(String Occup) {

        Occupation = Occup;

    }


    public String getVehPref() {

        return VehPref;
    }

    public void setVehPref(String Veh_Pref) {

        VehPref = Veh_Pref;

    }

    public String getEqsource() {

        return Eqsource;
    }

    public void setEqsource(String source) {

        Eqsource = source;

    }

    public String getEqStatus() {

        return EqStatus;
    }

    public void setEqStatus(String Status) {

        EqStatus = Status;

    }


    public String getEq_Finance() {

        return Finance;
    }

    public void setEq_Finance(String Fin) {

        Finance = Fin;

    }


    public String getExchange() {

        return Exchange;
    }

    public void setEq_Exchange(String Exc) {

        Exchange = Exc;

    }


    public String getEq_Cash() {

        return Eq_Cash;
    }

    public void setEq_Cash(String Eqcash) {

        Eq_Cash = Eqcash;

    }



    public String getEnq_Fp_Id() {

        return Enq_Fp_Id;
    }

    public void setEnq_Fp_Id(String Fp_Id) {

        Enq_Fp_Id = Fp_Id;

    }

    public String getEq_headerID() {

        return Eq_headerID;
    }

    public void setEq_headerID(String headerid) {

        Eq_headerID = headerid;

    }

    public String getEq_ProsNo() {

        return Eq_ProsNo;
    }

    public void setEq_ProsNo(String prosNo) {

        Eq_ProsNo = prosNo;

    }

    public String getEq_ExVeh() {

        return Eq_ExVeh;
    }

    public void setEq_ExVeh(String ExVeh) {

        Eq_ExVeh = ExVeh;

    }



    public String getEq_TestDrive() {

        return Eq_TestDrive;
    }

    public void setEq_TestDrive(String testdri) {

        Eq_TestDrive = testdri;

    }

    public String getEq_TestDriveFb() {

        return Eq_TestDriveFb;
    }

    public void setEq_TestDrivefb(String testdrivefb) {

        Eq_TestDriveFb = testdrivefb;

    }

    public String getEq_TestDriveDate() {

        return Eq_TestDriveDate;
    }

    public void setEq_TestDriveDate(String testdrivedate) {

        Eq_TestDriveDate = testdrivedate;

    }

    public String getEq_remarks()  {

        return Eq_remarks;
    }

    public void setEq_remarks(String remarks) {

        Eq_remarks = remarks;

    }



    public String getEq_SourceRemarks()  {

        return Eq_SourceRemarks;
    }

    public void setEq_SourceRemarks (String Srcremarks) {

        Eq_SourceRemarks = Srcremarks;

    }



    public String getPinCode() {

        return Pin;
    }

    public void setPinCode(String pin) {

        Pin = pin;

    }



    public String getEq_NextActionPlan() {

        return Eq_NextActionPlan;
    }

    public void setEq_NextActionPlan(String Nxt_ActnPln) {

        Eq_NextActionPlan = Nxt_ActnPln;

    }

    public String getEq_NextAction() {

        return Eq_NextAction;
    }

    public void setEq_NextAction(String Nxt_Actn) {

        Eq_NextAction = Nxt_Actn;

    }

    public String getEq_NextPlanDate() {

        return Eq_NextPlanDate;
    }

    public void setEq_NextPlanDate(String Nxt_ActnPlnDate) {

        Eq_NextPlanDate = Nxt_ActnPlnDate;

    }

    public ArrayList<Bitmap> getExchangeImg() {

        return ExchangeImg;
    }

    public void setExchangeImg( ArrayList<Bitmap> Ex_Img) {

        ExchangeImg = Ex_Img;

    }

    public ArrayList<Bitmap> getTestImg() {
        return TestImg;
    }

    public void setTestImg( ArrayList<Bitmap> Test_Img) {

        TestImg = Test_Img;

    }


    public String getfp_ExeActionPlan() {

        return Eq_ExeActionPlan;
    }

    public void setFp_ExeActionPlan(String Exe_ActnPln) {

        Eq_ExeActionPlan = Exe_ActnPln;

    }

    public String getFp_ExeAction() {

        return Eq_ExeAction;
    }

    public void setFp_ExeAction(String Exe_Actn) {

        Eq_ExeAction = Exe_Actn;

    }

    public String getFp_ExePlanDate() {

        return Eq_ExePlanDate;
    }

    public void setFp_ExePlanDate (String Exe_ActnPlnDate) {

        Eq_ExePlanDate = Exe_ActnPlnDate;

    }






}

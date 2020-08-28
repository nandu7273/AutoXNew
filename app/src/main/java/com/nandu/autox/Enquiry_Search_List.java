package com.nandu.autox;

public class Enquiry_Search_List {

    private int Enq_HeaderId;
    private String Bk_FinId;
    private String Prospect_No;
    private String Enq_Date;
    private String Cust_Name;
    private String Cust_Tel_Mob;
    private String Cust_Place;
    private String Veh_Model;
    private String Sc_Name;
    private String Fin_Payment_Mode;
    private String Fin_in_out;
    private String Fin_Company;
    private String Fin_Remarks;
    private String Fin_Details;
    private String Cust_City;
    private String Cust_Pin;
    private String Cust_Tel_Office;
    private String Cust_Email;
    private String Cust_Occupt;
    private String Enq_Source;
    private String Enq_Status;
    private String Enq_Pref;
    private String Exchange_Vehicle;
    private String PMode_Cash;
    private String PMode_Finance;
    private String PMode_Exchange;
    private String Test_Drive;
    private String Test_Drive_Date;
    private String TestDriveFB;
    private String SourceRemarks;
    private String Remarks;

    private String Eq_Fp_Id;
    private String Fp_Date;
    private String Fp_Pln_Date;
    private String Fp_Pln_Details;
    private String Fp_Pln_Sc_Id;
    private String Fp_Extd_Date;
    private String Fp_Extd_Details;
    private String Fp_Extd_Sc_Id;
    private String UpdatedOn;
    private String Cre_Private;
    private String device_type;
    private String Fp_Action_Pln;
    private String Fp_Action_Exe;
    private String Fp_AfterSale;
    private String Enq_Pref1;
    private String Enq_Pref2;
    private String Cust_FB;
    private String Test_Drive_Satisf;

    private String EqExCustRate="";
    private String EqExBroRate="";
    private String EqExBroName="";



    //private ArrayList<String> genre;

    public Enquiry_Search_List() {
    }


    public int getEnq_HeaderId() {
        return Enq_HeaderId;
    }

    public void setEnq_HeaderId(int Enq_HeaderId) {
        this.Enq_HeaderId = Enq_HeaderId;
    }

    public String getBk_FinId() {
        return Bk_FinId;
    }

    public void setBk_FinId(String bk_finid) {
        this.Test_Drive_Satisf = bk_finid;
    }

    public String getTest_Drive_Satisf() {
        return Test_Drive_Satisf;
    }

    public void setTest_Drive_Satisf(String Test_DriveSatisf) {
        this.Test_Drive_Satisf = Test_DriveSatisf;
    }


    public String getCust_FB() {
        return Cust_FB;
    }

    public void setCust_FB(String custfed) {
        this.Cust_FB = custfed;
    }



    public String getEqExBroRate() {
        return EqExBroRate;
    }

    public void setEqExBroRate(String EqExBro_Rate) {
        this.EqExBroRate = EqExBro_Rate;
    }



    public String getEqExCustRate() {
        return EqExCustRate;
    }

    public void setEqExCustRate(String EqExCust_Rate) {
        this.EqExCustRate = EqExCust_Rate;
    }


    public String getEqExBroName() {
        return EqExBroName;
    }

    public void setEqExBroName(String EqExBro_Name) {
        this.EqExBroName = EqExBro_Name;
    }


    public String getProspect_No() {
        return Prospect_No;
    }

    public void setProspect_No(String Prospect_No) {
        this.Prospect_No = Prospect_No;
    }

    public String getEnq_Date() {        return Enq_Date;    }

    public void setEnq_Date(String Enq_Date) {
        this.Enq_Date = Enq_Date;
    }

    public String getCust_Name() {
        return Cust_Name;
    }

    public void setCust_Name(String cust_name) {
        this.Cust_Name = cust_name;
    }

    public String getCust_Tel_Mob() {
        return Cust_Tel_Mob;
    }

    public void setCust_Tel_Mob(String cust_tel_mob) {
        this.Cust_Tel_Mob = cust_tel_mob;
    }

    public String getVeh_Model() {
        return Veh_Model;
    }

    public void setVeh_Model(String veh_model) {
        this.Veh_Model = veh_model;
    }

    public String getCust_Place() {   return Cust_Place;   }

    public void setCust_Place(String cust_place) {
        this.Cust_Place = cust_place;
    }

    public String getSc_Name() {
        return Sc_Name;
    }

    public void setSc_Name(String sc_name) {
        this.Sc_Name = sc_name;
    }

    public void setCust_City(String city) {
        this.Cust_City = city;
    }

    public String getCust_Pin() {
        return Cust_Pin;
    }
    public void setCust_Pin(String pin_no) {
        this.Cust_Pin = pin_no;
    }

    public String getTel_Off() {
        return Cust_Tel_Office;
    }
    public void setTel_Off(String telno) {
        this.Cust_Tel_Office = telno;
    }

    public String getCust_Email() {
        return Cust_Email;
    }

    public void setCust_Email(String cust_email) {
        this.Cust_Email = cust_email;
    }

    public String getCust_Occupt() {        return Cust_Occupt;    }

    public void setCust_Occupt(String occup) {
        this.Cust_Occupt = occup;
    }

    public String getCust_City() {        return Cust_City;    }

    public String getEnq_Source() {        return Enq_Source;    }

    public void setEnq_Source(String enq_Source ) {
        this.Enq_Source = enq_Source;
    }

    public String getEnq_Status() {        return Enq_Status;    }

    public String getEnq_Pref() {    return Enq_Pref;    }

    public String getExchange_Vehicle() {    return Exchange_Vehicle;    }

    public String getPMode_Cash() {    return PMode_Cash;    }

    public String getPMode_Finance() {    return PMode_Finance;    }

    public String getPMode_Exchange() {    return PMode_Exchange;    }

    public String getTest_Drive() {    return Test_Drive;    }

    public String getTestDrive_Date() {    return Test_Drive_Date;    }

    public String getTestDrive_FB() {    return TestDriveFB;    }

    public String getSource_Remarks() {    return SourceRemarks;    }

    public String getRemarks() {    return Remarks;    }

    public void setEnq_Status(String enq_Status ) {
        this.Enq_Status = enq_Status;
    }

    public void setEnq_Pref(String enq_Pref ) {        this.Enq_Pref = enq_Pref;    }

    public void setExchange_Vehicle(String exch_veh ) {        this.Exchange_Vehicle = exch_veh;    }

    public void setPMode_Exchange(String pmode_exch ) {        this.PMode_Exchange = pmode_exch;    }

    public void setPMode_Cash(String pmode_cash ) {        this.PMode_Cash = pmode_cash;    }

    public void setPMode_Finance(String pmode_fin ) {        this.PMode_Finance = pmode_fin;    }

    public void setTest_Drive(String test_drive ) {        this.Test_Drive = test_drive;    }

    public void setTestDrive_Date(String test_drive_dt ) {        this.Test_Drive_Date = test_drive_dt;    }

    public void setTestDrive_FB(String test_drive_fb ) {        this.TestDriveFB = test_drive_fb;    }

    public void setSource_Remarks(String source_remarks ) {        this.SourceRemarks = source_remarks;    }

    public void setRemarks(String remarks ) {        this.Remarks = remarks;    }

    public String getFin_Company() {
        return Fin_Company;
    }

    public void setFin_Company(String fin_company) {
        this.Fin_Company = fin_company;
    }

    public String getFin_in_out() {
        return Fin_in_out;
    }

    public void setFin_in_out(String fin_in_out) {
        this.Fin_in_out = fin_in_out;
    }

    public String getFin_Payment_Mode() {
        return Fin_Payment_Mode;
    }

    public void setFin_Payment_Mode(String fin_payment_mode) {
        this.Fin_Payment_Mode = fin_payment_mode;
    }

    public String getFin_Remarks() {
        return Fin_Remarks;
    }

    public void setFin_Remarks(String fin_remarks) {
        this.Fin_Remarks = fin_remarks;
    }

    public String getFin_Details() {
        return Fin_Details;
    }

    public void setFin_Details(String fin_details) {
        this.Fin_Details = fin_details;
    }
   /* public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }*/








    public String getEq_Fp_Id() {
        return Eq_Fp_Id;
    }

    public void setEq_Fp_Id(String Eq_FpId) {
        this.Eq_Fp_Id = Eq_FpId;
    }






    public String getFp_Date() {        return Fp_Date;    }



    public String getFp_Pln_Date() {    return Fp_Pln_Date;    }

    public void setFp_Pln_Date(String Fp_PlanDate ) {        this.Fp_Pln_Date = Fp_PlanDate;    }

    public String getFp_Pln_Details() {    return Fp_Pln_Details;    }

    public void setFp_Pln_Details(String Fp_PlanDetails ) {        this.Fp_Pln_Details = Fp_PlanDetails;    }

    public String getFp_Pln_Sc_Id() {    return Fp_Pln_Sc_Id;    }

    public void setFp_Pln_Sc_Id(String Fp_Plan_Sc_Id ) {        this.Fp_Pln_Sc_Id = Fp_Plan_Sc_Id;    }

    public String getFp_Extd_Date() {    return Fp_Extd_Date;    }

    public void setFp_Extd_Date(String Fp_ExtdDate ) {        this.Fp_Extd_Date = Fp_ExtdDate;    }

    public String getFp_Extd_Details() {    return Fp_Extd_Details;    }

    public void setFp_Extd_Details(String Fp_ExtdDetails ) {        this.Fp_Extd_Details = Fp_ExtdDetails;    }

    public String getFp_Extd_Sc_Id() {    return Fp_Extd_Sc_Id;    }

    public void setFp_Extd_Sc_Id(String Fp_Extd_ScId ) {        this.Fp_Extd_Sc_Id = Fp_Extd_ScId;    }

    public String getUpdatedOn() {    return UpdatedOn;    }

    public void setUpdatedOn(String Updated_On ) {        this.UpdatedOn = Updated_On;    }


    public String getCre_Private() {    return Cre_Private;    }

    public void setCre_Private(String CrePrivate ) {        this.Cre_Private = CrePrivate;    }

    public String getdevice_type() {    return device_type;    }

    public void setdevice_type(String devicetype ) {        this.device_type = devicetype;    }

    public String getFp_Action_Pln() {    return Fp_Action_Pln;    }

    public void setFp_Action_Pln(String Fp_ActionPln ) {        this.Fp_Action_Pln = Fp_ActionPln;    }

    public String getFp_Action_Exe() {    return Fp_Action_Exe;    }

    public void setFp_Action_Exe(String Fp_ActionExe ) {        this.Fp_Action_Exe = Fp_ActionExe;    }

    public String getFp_AfterSale() {    return Fp_AfterSale;    }

    public void setFp_AfterSale(String Fp_After_Sale ) {        this.Fp_AfterSale = Fp_After_Sale;    }

    public String getEnq_Pref1() {    return Enq_Pref1;    }

    public void setEnq_Pref1(String Enq_Pref_1 ) {        this.Enq_Pref1 = Enq_Pref_1;    }

    public String getEnq_Pref2() {    return Enq_Pref2;    }

    public void setEnq_Pref2(String Enq_Pref_2 ) {        this.Enq_Pref2 = Enq_Pref_2;    }









}

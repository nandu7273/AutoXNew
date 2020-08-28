package com.nandu.autox;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {


	public static final String DataBase_Name = "sc.db";
	public static final String Table_sc_details = "sc_details";
	public static final String Sc_Id = "Sc_Id";
	public static final String Sc_MNo = "Sc_MNo";
	public static final String Sc_Code = "Sc_Code";
    public static final String Sc_Name = "Sc_Name";
    public static final String Sc_Desig_Code = "Sc_Desig_Code";
	public static final String Branch_Code = "Branch_Code";
	public static final String BranchId ="BranchId";
	public static final String BranchName ="Branch_Name";
	public static final String Sc_Desig_Name ="Sc_Desig_Name";
	public static final String MobileNum ="Mobile";


	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
	"CREATE TABLE IF NOT EXISTS " + Table_sc_details + " (" +
				Sc_Id + " INTEGER PRIMARY KEY," +
				Sc_MNo + TEXT_TYPE + COMMA_SEP +
	    		Sc_Code + TEXT_TYPE + COMMA_SEP +
                Sc_Desig_Code + TEXT_TYPE + COMMA_SEP +
				Branch_Code + TEXT_TYPE + COMMA_SEP +
				BranchId + TEXT_TYPE + COMMA_SEP +
				BranchName + TEXT_TYPE + COMMA_SEP +
				Sc_Desig_Name + TEXT_TYPE + COMMA_SEP +
				MobileNum + TEXT_TYPE + COMMA_SEP +
	    		Sc_Name + TEXT_TYPE  + ");";

//	private static final String SQL_DELETE_ENTRIES =
//	"DROP TABLE IF EXISTS " + Table_sc_details;

	public Boolean DBExist;

	public DB_Helper(Context context) {
		
		super(context, DataBase_Name, null, 1 );
		// TODO Auto-generated constructor stub
			
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try
		{
			db.execSQL(SQL_CREATE_ENTRIES);
			DBExist = false;
		}catch (SQLException e){
			DBExist = true;
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
//		
		//	db.execSQL(SQL_DELETE_ENTRIES);
		//	onCreate(db);
//		
		     
	}
	
	 public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        //onUpgrade(db, oldVersion, newVersion);
	 }
	 
	 /*public Boolean exportDB() {
			 		 
		 try {
			    File sd = Environment.getExternalStorageDirectory();
			    File data = Environment.getDataDirectory();

			    if (sd.canWrite()) {
			        String currentDBPath = "//data//com.example.myshop//databases//Myshop.db";
			        String backupDBPath = "Myshop.db";
			        File currentDB = new File(data, currentDBPath);
			        File backupDB = new File(sd, backupDBPath);

			        FileChannel src = new FileInputStream(currentDB).getChannel();
			        FileChannel dst = new FileOutputStream(backupDB).getChannel();
			        dst.transferFrom(src, 0, src.size());
			        src.close();
			        dst.close();
			      	       
			        return true; 
			    }else{
			    	 return false;
			    }
			   		   
			    
			} catch (Exception e) {
			   return false;
			}
		 
		
	 }*/
	

}

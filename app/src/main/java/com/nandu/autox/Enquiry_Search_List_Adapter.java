package com.nandu.autox;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Enquiry_Search_List_Adapter extends BaseAdapter {

    //private Activity activity;
    private LayoutInflater inflater;
    private List<Enquiry_Search_List> bkItems;


    public Enquiry_Search_List_Adapter(Context context, List<Enquiry_Search_List> bkItems) {
        inflater = LayoutInflater.from(context);
        this.bkItems = bkItems;
        //this.activity =(Activity) context;
    }

    @Override
    public int getCount() {
        return bkItems.size();
    }

    @Override
    public Object getItem(int location) {
        return bkItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.enquiry_list_row, null);
            holder = new ViewHolder();

            holder.desc1 = (TextView) convertView.findViewById(R.id.desc1);
            holder.desc2 = (TextView) convertView.findViewById(R.id.desc2);
            holder.desc3 = (TextView) convertView.findViewById(R.id.desc3);
            //holder.Imgcall=(ImageButton) convertView.findViewById(R.id.ImgCall);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }


        // getting data for the row
        Enquiry_Search_List m = bkItems.get(position);
        String Fin_Details = "";

     /*   if(m.getFin_Payment_Mode().toString().compareTo("Cash")==0){
        	Fin_Details = "CASH";
        	holder.desc2.setTextColor(Color.RED);
        }else if (m.getFin_Payment_Mode().toString().compareTo("No Stock")==0) {
        	Fin_Details = "NO STOCK";
        	holder.desc2.setTextColor(Color.DKGRAY);
		}else if (m.getFin_Payment_Mode().toString().compareTo("Finance")==0) {
			Fin_Details = m.getFin_Company().toUpperCase() + " - " + (m.getFin_in_out().compareTo("1")==0 ? "IN" : "OUT") ;
			holder.desc2.setTextColor(Color.BLACK);
		}*/

        bkItems.get(position).setFin_Details(Fin_Details);

        holder.desc1.setTag(m.getEnq_HeaderId());
        holder.desc1.setText("Name :"+String.valueOf(m.getCust_Name()) + "\n" +"Place :"+String.valueOf(m.getCust_Place()) + "\n" +"Veh Prefer:"+
                String.valueOf(m.getVeh_Model()) + "\n" +"Last FollowUp:"+
                String.valueOf(m.getFp_Pln_Details()+ "-" + m.getFp_Pln_Date().replace("00:00:00",""))
        );
        holder.desc1.setText(holder.desc1.getText().toString().replace("\n", "\n"));
        //holder.desc2.setVisibility(View.GONE );
        holder.desc2.setTag(m.getEq_Fp_Id());
        holder.desc3.setTag(m.getCust_Name()+"\n"+m.getVeh_Model());

        if (Fin_Details.compareTo("") == 0) {
            holder.desc2.setVisibility(View.GONE);
        } else {
            holder.desc2.setVisibility(View.VISIBLE);
            holder.desc2.setText(Fin_Details);
        }

        holder.desc3.setVisibility(View.GONE);
        holder.desc1.setTextColor(position % 2 == 0 ? Color.WHITE : Color.WHITE);
        convertView.setBackgroundColor(position % 2 == 0 ? Color.rgb(26,144,176) : Color.rgb(0, 131, 166));

        return convertView;

    }



    /*@Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.bk_list_row, null)  ;
        }


        TextView desc1 = (TextView) convertView.findViewById(R.id.desc1);
        TextView desc2 = (TextView) convertView.findViewById(R.id.desc2);
        TextView desc3 = (TextView) convertView.findViewById(R.id.desc3);
        //RadioButton inHouse = (RadioButton) convertView.findViewById(R.id.rbtIn);


        // getting data for the row
        Bk m = bkItems.get(position);
        String Fin_Details = "";

        if(m.getFin_Payment_Mode().toString().compareTo("Cash")==0){
        	Fin_Details = "CASH";
        }else if (m.getFin_Payment_Mode().toString().compareTo("No Stock")==0) {
        	Fin_Details = "NO STOCK";
		}else if (m.getFin_Payment_Mode().toString().compareTo("Finance")==0) {
			Fin_Details = m.getFin_Company().toUpperCase() + " - " + (m.getFin_Company()=="1" ? "IN" : "OUT") ;
		}

        desc1.setTag(m.getEnq_HeaderId());
        desc1.setText(String.valueOf(m.getCust_Name()) + " - " + String.valueOf(m.getCust_Place()) + "\n" +
        		      String.valueOf(m.getVeh_Model()) + "\n" +
        		      String.valueOf(m.getProspect_No()  + " - " + m.getSc_Name())
        		      );
        desc1.setText(desc1.getText().toString().replace("\\n", "\n"));
        //desc2.setVisibility(View.GONE );
        desc2.setTag(m.getBk_FinId());

        if(Fin_Details.compareTo("")==0){
        	desc2.setVisibility(View.GONE );
        }else{
        	desc2.setText(Fin_Details);
        }

        desc3.setVisibility(View.GONE );
        desc1.setTextColor(position % 2 == 0 ? Color.DKGRAY : Color.BLACK);
        convertView.setBackgroundColor(position % 2 == 0 ? Color.WHITE : Color.rgb(232, 247, 246));

        return convertView;
    }*/


    static class ViewHolder {
        TextView desc1;
        TextView desc2;
        TextView desc3;
        //ImageButton Imgcall;

    }
}


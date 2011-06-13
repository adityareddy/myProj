package com.myProj;

import java.util.ArrayList;

import com.myProj.R;
import com.myProj.RestClient.RequestMethod;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityThree extends Activity {
	ArrayAdapter<String> adapter = null;
	ArrayList<String> lv_arr = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
       
     	 RestClient client = new RestClient("http://www.google.co.in/");
         try {    
             client.Execute(RequestMethod.POST); 
              }
         catch (Exception e) {
                  e.printStackTrace();
               }
         String data1= client.getResponse();	

          TextView tv = (TextView) findViewById(R.id.Title);
          tv.setText("jgvjg"+data1);
        
//        lv_arr.add("Italian Riviera");
//        lv_arr.add("Jersey Shore");
//        lv_arr.add("Puerto Rico");
//        lv_arr.add("Los Cabos Corridor");
//        lv_arr.add("Lubmin");
//        lv_arr.add("Coney Island");
//        lv_arr.add("Karlovy Vary");
//        lv_arr.add("Bourbon-l'Archambault");
//        lv_arr.add("Walt Disney World Resort");
//        lv_arr.add("Barbados");
//      
//        ListView lv = (ListView) findViewById(R.id.listView1);
//        adapter=new LvAdapt();
//        lv.setAdapter(adapter);
 


    }
    
class LvAdapt extends ArrayAdapter<String>{
    LvAdapt(){
    	super(ActivityThree.this,android.R.layout.simple_list_item_1,lv_arr);
    }
    	
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater=getLayoutInflater();
    	View row=inflater.inflate(R.layout.row, parent, false);
    	
    	TextView label1=(TextView)row.findViewById(R.id.title);
    	label1.setText((CharSequence) (lv_arr.toArray())[position]);
    	TextView label2=(TextView)row.findViewById(R.id.address);
    	label2.setText((CharSequence) (lv_arr.toArray())[position]);
    	ImageView icon=(ImageView)row.findViewById(R.id.icon);
    	Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
    	Bitmap bm1 = Bitmap.createScaledBitmap(bm, 72, 72, true);
    	icon.setImageBitmap(bm1);
    
    	return row;
    	}
    }



}

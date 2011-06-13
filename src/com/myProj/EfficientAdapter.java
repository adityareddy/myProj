package com.myProj;



import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EfficientAdapter extends BaseAdapter {
	private Activity activity;
	private ArrayList<String> data;
	private ArrayList<String> data1;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;
	 ViewHolder holder;

	EfficientAdapter(Activity a, ArrayList<String> d, ArrayList<String> d1) {

		activity = a;
		data = d;
		data1 = d1;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
		

	}

	@Override
	public int getCount() {
		return data.toArray().length;
		
	}

	@Override
	public Object getItem(int position) {
		
		return position;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}
   
	public static class ViewHolder{
        public TextView label;
        public TextView addr;
        public ImageView image;
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  View vi=convertView;
	       
	        if(convertView==null){
	            vi = inflater.inflate(R.layout.row, null);
	            holder=new ViewHolder();
	            holder.label=(TextView)vi.findViewById(R.id.title);
	            holder.addr=(TextView)vi.findViewById(R.id.address);
	            holder.image=(ImageView)vi.findViewById(R.id.icon);
	            vi.setTag(holder);
	        }
	        else
	            holder=(ViewHolder)vi.getTag();
	        
	       	
	        holder.label.setText((CharSequence) (data.toArray())[position]);
	        holder.addr.setText((CharSequence) (data1.toArray())[position]);
        	
 	     	imageLoader.DisplayImage((data1.toArray())[position].toString(), activity, holder.image,72,72);

 	      	return vi;
	}

}

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

public class imageAdapter extends BaseAdapter {
	Activity mActivity;
	ArrayList<String> imgThumbs;
	ArrayList<String> names;
	ImageLoader imageLoader;
	LayoutInflater inflater;

	public imageAdapter(Activity a, ArrayList<String> nms, ArrayList<String> arr) {
		names = nms;
		mActivity = a;
		imgThumbs = arr;
		imageLoader = new ImageLoader(a.getApplicationContext());
		inflater = (LayoutInflater) a
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		return (imgThumbs.toArray()).length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

		View v;
		if (convertView == null) {
			v = inflater.inflate(R.layout.grid, null);
			TextView tv = (TextView) v.findViewById(R.id.icon_text);
			tv.setText((names.toArray())[position].toString());
			ImageView iv = (ImageView) v.findViewById(R.id.icon_image);
			imageLoader.DisplayImage(
					(imgThumbs.toArray())[position].toString(), mActivity, iv,
					72, 72);

		} else {
			v = convertView;
		}
		return v;

	}

}
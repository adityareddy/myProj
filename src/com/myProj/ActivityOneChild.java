/***
	Copyright (c) 2008-2009 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.myProj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.commonsware.cwac.endless.EndlessAdapter;
import java.util.ArrayList;

public class ActivityOneChild extends Activity {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.list);
		
			
		ArrayList<Integer> items=new ArrayList<Integer>();
		
		for (int i=0;i<25;i++) { items.add(i); }
		
		ListView lv = (ListView)findViewById(R.id.listView1);
		lv.setAdapter(new DemoAdapter(items));
		
	}
	@Override
	public void onResume(){
		super.onResume();
		TextView wtitle = (TextView)myProjMain.inst.findViewById(R.id.windowtitle);
		ImageButton wicon  = (ImageButton)myProjMain.inst.findViewById(R.id.windowicon);
		Button wbackbutton  = (Button)myProjMain.inst.findViewById(R.id.titlebutton);
		
		wtitle.setText("test");
		wicon.setImageResource(R.drawable.settingsicon);
		wicon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent settingsActivity = new Intent(getBaseContext(),Preferences.class);
				startActivity(settingsActivity);
			
			}
		});
		
		wbackbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TabGroupActivity parentActivity = (TabGroupActivity) getParent();
				parentActivity.onBackPressed();
				
			}
		});

	}


	
	class DemoAdapter extends EndlessAdapter {
		private RotateAnimation rotate=null;
		
		DemoAdapter(ArrayList<Integer> list) {
			super(new ArrayAdapter<Integer>(ActivityOneChild.this,
																			R.layout.row,
																			R.id.title,
																			list));
			
			rotate=new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
																	0.5f, Animation.RELATIVE_TO_SELF,
																	0.5f);
			rotate.setDuration(600);
			rotate.setRepeatMode(Animation.RESTART);
			rotate.setRepeatCount(Animation.INFINITE);
		}
		
		@Override
		protected View getPendingView(ViewGroup parent) {
			View row=getLayoutInflater().inflate(R.layout.row, null);
			
			View child=row.findViewById(R.id.title);
			
			child.setVisibility(View.GONE);
			
			child=row.findViewById(R.id.throbber);
			child.setVisibility(View.VISIBLE);
			child.startAnimation(rotate);
			
			return(row);
		}
		
		@Override
		protected boolean cacheInBackground() {
			SystemClock.sleep(10000);				// pretend to do work
			
			return(getWrappedAdapter().getCount()<75);
		}
		
		@Override
		protected void appendCachedData() {
			if (getWrappedAdapter().getCount()<75) {
				@SuppressWarnings("unchecked")
				ArrayAdapter<Integer> a=(ArrayAdapter<Integer>)getWrappedAdapter();
				
				for (int i=0;i<25;i++) { a.add(a.getCount()); }
			}
		}
	}
    //-----------------------------------------------TABS-----------
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Overrides the default implementation for KeyEvent.KEYCODE_BACK 
     * so that all systems call onBackPressed().
     */
	public static final long Double_Press_Interval = 1000000000;
	long lastPressTime;
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long pressTime = System.nanoTime();
			if(pressTime - lastPressTime >= Double_Press_Interval){
			TabGroupActivity parentActivity = (TabGroupActivity) getParent();
			parentActivity.onBackPressed();
			}lastPressTime = pressTime;

			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
}
package com.myProj;

import java.util.List;

import com.myProj.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class myProjMain extends TabActivity {
	/** Called when the activity is first created. */
	BroadcastReceiver connReceiver;
	public boolean connectivity;
	Boolean noConnectivityD = false;
	AlertDialog alrt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		if (new Connectivity(myProjMain.this).isOnline()) {

			IntentFilter filter = new IntentFilter();
			filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
			connReceiver = new BroadcastReceiver() {

				@Override
				public void onReceive(Context context, Intent intent) {
					boolean noConnectivity = intent.getBooleanExtra(
							ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
					String reason = intent
							.getStringExtra(ConnectivityManager.EXTRA_REASON);
					boolean isFailover = intent.getBooleanExtra(
							ConnectivityManager.EXTRA_IS_FAILOVER, false);
					NetworkInfo currentNetworkInfo = (NetworkInfo) intent
							.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
					NetworkInfo otherNetworkInfo = (NetworkInfo) intent
							.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);
					Log.i("connectivity", "Status : " + noConnectivity
							+ ", Reason :" + reason + ", FailOver :"
							+ isFailover + ", Current Network Info : "
							+ currentNetworkInfo + ", OtherNetwork Info :"
							+ otherNetworkInfo);

					if (noConnectivity && !noConnectivityD) {
						if (alrt != null)
							alrt.dismiss();

						alrt = new AlertDialog.Builder(context)
								.setTitle("No Internet Connection")
								.setMessage(
										"Do you want to check connection settings?")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {

											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												finish();
											}
										}).show();

						noConnectivityD = !noConnectivityD;

					} else if (!noConnectivity && noConnectivityD) {
						if (alrt != null)
							alrt.dismiss();
						alrt = new AlertDialog.Builder(context)
								.setTitle("Connection Established")
								.setMessage("Connection to Network Established")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {

											}
										}).show();
						noConnectivityD = !noConnectivityD;

					}

				}

			};
			registerReceiver(connReceiver, filter);

			Resources res = getResources(); // Resource object to get Drawables
			final TabHost tabHost = getTabHost(); // The activity TabHost
			TabHost.TabSpec spec; // Resusable TabSpec for each tab
			Intent intent; // Reusable Intent for each tab

			// Create an Intent to launch an Activity for the tab (to be reused)
			intent = new Intent().setClass(this, GridViewActivity.class);

			// Initialize a TabSpec for each tab and add it to the TabHost
			spec = tabHost
					.newTabSpec("artists")
					.setIndicator("Users",
							res.getDrawable(android.R.drawable.ic_dialog_alert))
					.setContent(intent);
			tabHost.addTab(spec);

			// Do the same for the other tabs
			intent = new Intent().setClass(this, ActivityOne.class);
			spec = tabHost
					.newTabSpec("albums")
					.setIndicator(
							"Albums",
							res.getDrawable(android.R.drawable.ic_btn_speak_now))
					.setContent(intent);
			tabHost.addTab(spec);

			intent = new Intent().setClass(this, ActivityTwo.class);
			spec = tabHost
					.newTabSpec("Places")
					.setIndicator("Songs",
							res.getDrawable(android.R.drawable.ic_input_add))
					.setContent(intent);
			tabHost.addTab(spec);

			tabHost.setCurrentTab(2);
			setTabColor(tabHost);

			tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

				@Override
				public void onTabChanged(String tabId) {
					setTabColor(tabHost);

				}
			});
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(connReceiver);
	}

	public void setTabColor(TabHost tabhost) {
		for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
			tabhost.getTabWidget().getChildAt(i)
					.setBackgroundColor(Color.argb(1, 136, 85, 19)); // unselected
		}
		tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
				.setBackgroundColor(Color.argb(140, 132, 114, 18)); // selected
		tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
				.setBackgroundResource(R.drawable.listback);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.help:
			Intent settingsActivity = new Intent(getBaseContext(),Preferences.class);
			startActivity(settingsActivity);
			return true;
		case R.id.newgame:
			return true;
		case R.id.three:
			return true;
		case R.id.four:
			return true;
		default:
			return false;
		}

	}

	class Connectivity {
		public Activity activity;

		Connectivity(Activity a) {
			activity = a;
			if (!isOnline())
				alertInput();

		}

		private void alertInput() {

			new AlertDialog.Builder(activity)
					.setTitle("No Internet Connection")
					.setMessage("Do you want to check connection settings?")
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									finish();
								}
							}).show();

		}

		public boolean isOnline() {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnectedOrConnecting()) {
				return true;
			}
			return false;
		}

	}

}
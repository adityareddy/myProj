package com.myProj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Toast;

public class Preferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.layout.preferences);
		// Get the custom preference
		Preference customPref = (Preference) findPreference("customPref");
		customPref
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					public boolean onPreferenceClick(Preference preference) {
					    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
					      
					    String aEmailList[] = { "android-support@myproj.com" }; 
					    
					    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
					    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "myProj 1.1.2");  
					    emailIntent.setType("plain/text");  
					    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");  
					      
					    startActivity(emailIntent);  
					    
					    
					    
						SharedPreferences customSharedPreference = getSharedPreferences(
								"myCustomSharedPrefs", Activity.MODE_PRIVATE);
						SharedPreferences.Editor editor = customSharedPreference
								.edit();
						editor.putString("myCustomPref",
								"The preference has been clicked");
						editor.commit();
						return true;
					}

				});
	}
}
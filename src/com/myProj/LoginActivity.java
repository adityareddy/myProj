package com.myProj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText etUsername, etPassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		String android_id = Secure.getString(getBaseContext().getContentResolver(),
                Secure.ANDROID_ID);
		
		etUsername = (EditText) findViewById(R.id.txt_username);
		etPassword = (EditText) findViewById(R.id.txt_password);

		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = etUsername.getText().toString();
				String password = etPassword.getText().toString();

				if (username.equals("guest") && password.equals("guest")) {
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, myProjMain.class);
					startActivity(intent);
				} else {
					
					Toast.makeText(
							LoginActivity.this,
							"Login failed. Username and/or password doesn't match.",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

}

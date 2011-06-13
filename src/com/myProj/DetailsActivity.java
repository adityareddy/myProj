package com.myProj;

import com.myProj.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);

		Bundle bun = new Bundle();
		bun = getIntent().getExtras();
		String name = bun.getString("name");
		String link = bun.getString("link");

		TextView tv = (TextView) findViewById(R.id.Title);
		ImageView img = (ImageView) findViewById(R.id.DisplayImage);
		ImageLoader imageLoader = new ImageLoader(this);

		tv.setText(name);
		imageLoader.DisplayImage(link, DetailsActivity.this, img, 150, 150);

	}

	// //-----------------------------------------------TABS-----------
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// preventing default implementation previous to
			// android.os.Build.VERSION_CODES.ECLAIR
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Overrides the default implementation for KeyEvent.KEYCODE_BACK so that
	 * all systems call onBackPressed().
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			TabGroupActivity parentActivity = (TabGroupActivity) getParent();
			parentActivity.onBackPressed();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

}
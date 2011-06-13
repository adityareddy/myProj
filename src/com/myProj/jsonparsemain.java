package com.myProj;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.myProj.RestClient.RequestMethod;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class jsonparsemain extends Activity {
	ListView lv1;
	TextView tv1;
	ProgressDialog ShowProgress;
	ArrayList<String> placenames = new ArrayList<String>();
	ArrayList<String> images = new ArrayList<String>();
	String data1;ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		lv1 = (ListView) findViewById(R.id.listView1);

		new loadingTask()
				.execute("http://www.myappdemo.com/checkout/services/GetPlaces.php");
//		 ShowProgress = ProgressDialog.show(jsonparsemain.this,"",
//				 "Loading. Please wait...", true);
	}

	public void jsonparser(String data) throws JSONException {
		try{
		JSONArray job1 = new JSONArray(data);
		
		for (int i = 0; i < job1.length(); i++) {
			placenames.add(job1.getJSONObject(i).getString("placename")
					.toString());
			images.add(job1.getJSONObject(i).getString("image").toString());
		}
		}catch(NullPointerException e){}

	}

	class loadingTask extends AsyncTask<String, Void, String> {


		protected String doInBackground(String... urls) {
			RestClient client = new RestClient(urls[0]);
			try {
				client.Execute(RequestMethod.POST);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//client.AddParam("memberid", "200");
			String data1 = client.getResponse();

			return data1;

		}

		protected void onPostExecute(String s) {
			try {
				jsonparser(s);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			lv1.setAdapter(new EfficientAdapter(jsonparsemain.this, placenames,
					images));

			AnimationSet set = new AnimationSet(true);

			Animation animation = new AlphaAnimation(0.0f, 1.0f);
			animation.setDuration(500);
			set.addAnimation(animation);

			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
					0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, -0.1f,
					Animation.RELATIVE_TO_SELF, 0.0f);
			animation.setDuration(500);
			set.addAnimation(animation);

			LayoutAnimationController controller = new LayoutAnimationController(
					set, 0.25f);

			lv1.setLayoutAnimation(controller);

			lv1.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent previewMessage = new Intent(getParent(),
							DetailsActivity.class);
					
					Bundle bun = new Bundle();
					bun.putString("name", placenames.toArray()[position].toString());
					bun.putString("link", images.toArray()[position].toString());
					previewMessage.putExtras(bun);
					
					TabGroupActivity parentActivity = (TabGroupActivity) getParent();
					parentActivity.startChildActivity("DetailsActivity",
							previewMessage);
					overridePendingTransition(android.R.anim.fade_in,
							android.R.anim.fade_out);
					// startActivity(new Intent(jsonparsemain.this,
					// ActivityTwoChild.class));
					// overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
				}
			});
			// ShowProgress.dismiss();

		}
	}

	// -----------------------------------------------TABS-----------
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

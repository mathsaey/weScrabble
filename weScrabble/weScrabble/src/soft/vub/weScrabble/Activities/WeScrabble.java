/**
 * AmbientTalk/2 Project
 * (c) Software Languages Lab, 2006 - 2011
 * Authors: Software Languages Lab - Ambient Group
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package soft.vub.weScrabble.Activities;

import soft.vub.weScrabble.R;
import soft.vub.weScrabble.WeScrabbleAssetInstaller;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import edu.vub.at.IAT;
import edu.vub.at.android.util.IATAndroid;
import edu.vub.at.android.util.IATSettings;
import edu.vub.at.android.util.IATSettings.IATOptions;

public class WeScrabble extends Activity {

	private static IAT iat;
	public static Handler mHandler;
	private static final int _ASSET_INSTALLER_ = 0;
	
	//StartIATTask based on WeScribble.java from lab sessions
	public class StartIATTask extends AsyncTask<Void, String, Void> {
		private ProgressDialog pd;

		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			pd.setMessage(values[0]);
		}

		protected void onPreExecute() {
			super.onPreExecute();
			pd = ProgressDialog.show(WeScrabble.this, "weScrabble", "Loading AmbientTalk...");
		}
		
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			pd.dismiss();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				IATOptions iatOptions = IATSettings.getIATOptions(WeScrabble.this);
				iatOptions.networkName_ = "AmbientTalk"; //TODO
				
				iat = IATAndroid.create(WeScrabble.this, iatOptions);
				
				this.publishProgress("Loading weScrabble code");
				iat.evalAndPrint("import /.demo.weScribble.weScribble.makeWeScribble()", System.err);
				
			} catch (Exception e) {
				Log.e("AmbientTalk", "Could not start IAT", e);
			}
			return null;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (iat == null) {
			Intent i = new Intent(this, WeScrabbleAssetInstaller.class);
			startActivityForResult(i, _ASSET_INSTALLER_);
		}
	}

	/*
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.v("WeScribble", "Return of Asset Installer activity");
		switch (requestCode) {
		case (_ASSET_INSTALLER_):
			if (resultCode == Activity.RESULT_OK) {
				Thread lt = new Tre();
				lt.start();
				mHandler = lt.mHandler;
				new StartIATTask().execute((Void)null);
			}
			break;
		}
	}
	*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

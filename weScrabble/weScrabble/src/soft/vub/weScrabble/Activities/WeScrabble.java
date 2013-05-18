package soft.vub.weScrabble.Activities;

import soft.vub.weScrabble.R;
import soft.vub.weScrabble.WeScrabbleAssetInstaller;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import edu.vub.at.IAT;
import edu.vub.at.android.util.IATAndroid;
import edu.vub.at.android.util.IATSettings;
import edu.vub.at.android.util.IATSettings.IATOptions;

public class WeScrabble extends Activity {
	private static IAT iat;
	
	private TextView progressText;
	private ProgressBar progressSpinner;
	
	public class StartIATTask extends AsyncTask<Void, String, Void> {

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			progressText.setText(values[0]);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressText.setText("Loading...");
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressText.setText("Done!");
			progressSpinner.setVisibility(View.INVISIBLE);
		}

		@Override
		protected Void doInBackground(Void... arg0) {			
			try {
				Looper.prepare();
				
				//Install the ambienttalk assets
				this.publishProgress("Installing AmbientTalk Assets");
				
				if (iat == null) {
					WeScrabbleAssetInstaller installer = new WeScrabbleAssetInstaller();
					installer.launch(WeScrabble.this);
				}
				
				//Launch the interpreter
				this.publishProgress("Starting the AmbientTalk interpreter");
				
				IATOptions iatOptions = IATSettings.getIATOptions(WeScrabble.this);				
				iat = IATAndroid.create(WeScrabble.this, iatOptions);
				
				//Start code evaluation
				this.publishProgress("Loading weScrabble code");
				iat.evalAndPrint("import /.weScrabble.atTest.test()", System.err);
				
			} catch (Exception e) {
				this.publishProgress("Failed to load ambientTalk...");
				Log.e("AmbientTalk", "Could not start IAT", e);
			}
			return null;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StartIATTask task;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressText = (TextView) findViewById(R.id.progressText);
		progressSpinner = (ProgressBar) findViewById(R.id.progressBar);
		
		task = new StartIATTask();	
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

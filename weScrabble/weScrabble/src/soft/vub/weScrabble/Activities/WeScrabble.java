package soft.vub.weScrabble.activities;

import soft.vub.weScrabble.AmbientTalkDidNotLaunchException;
import soft.vub.weScrabble.AmbientTalkManager;
import soft.vub.weScrabble.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * This is the first activity users see,
 * it loads ambienttalk and afterwards, it allows the users
 * to decide if they want to play a new game or join an existing one
 * 
 * @author mathsaey
 *  */
public class WeScrabble extends Activity {	
	
	// UI Variables
	private Button joinButton;
	private Button createButton;
	private TextView playerName; 
	private TextView progressText;
	private ProgressBar progressSpinner;
	
	/**
	 * Task responsible for starting the AT interpreter and 
	 * loading the assets 
	 * 
	 * @author mathsaey
	 */
	public class StartIATTask extends AsyncTask<Void, String, Void> {
		boolean didLoad = false;
		
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
			progressSpinner.setVisibility(View.INVISIBLE);
			
			if (didLoad) {
				progressText.setText("Please enter your name");
				playerName.setVisibility(View.VISIBLE);
			}
			
		}

		@Override
		protected Void doInBackground(Void... arg0) {			
			try {
				Looper.prepare();
				
				//Install the ambienttalk assets
				this.publishProgress("Installing AmbientTalk and assets...");
				AmbientTalkManager.getSharedManager().installAndLaunchAT(WeScrabble.this);
				didLoad = true;
				
			} catch (AmbientTalkDidNotLaunchException e) {
				this.publishProgress("Failed to load ambientTalk :(");
			}
			return null;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		playerName = (TextView) findViewById(R.id.nameField);
		joinButton = (Button) findViewById(R.id.joinGameButton);
		progressText = (TextView) findViewById(R.id.progressText);
		createButton = (Button) findViewById(R.id.createGameButton);
		progressSpinner = (ProgressBar) findViewById(R.id.progressBar);
		
		joinButton.setEnabled(false);
		createButton.setEnabled(false);
		playerName.setVisibility(View.INVISIBLE);
				
		//Start ambienttalk
		StartIATTask task = new StartIATTask();	
		task.execute();
		
		// We only allow the player to continue after entering a name
		playerName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {	
				AmbientTalkManager m = AmbientTalkManager.getSharedManager();
				m.setCoreInterface(m.getCoreInterface().init(playerName.getText().toString()));
				
				joinButton.setEnabled(true);
				createButton.setEnabled(true);
			}});
		}

	/**
	 * This method is called by the system when the
	 * create game button is pressed
	 * @param v
	 */
	public void createButtonOnClick(View v) {
		Intent intent = new Intent(this, CreateGameActivity.class);
		startActivity(intent);
	}
	
	/**
	 * This method is called by the system when the
	 * join game button is pressed
	 * @param v
	 */
	public void joinButtonOnClick(View v) {
		Intent intent = new Intent(this, JoinGameActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Generate a hashed id of the device
	 * @return a unique identifier for this device
	 */
	public String getDeviceID() {
	    final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
	    final String id = tm.getDeviceId();
	    return Integer.toString(id.hashCode()); //Hash the id for privacy
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return false;
	}
}

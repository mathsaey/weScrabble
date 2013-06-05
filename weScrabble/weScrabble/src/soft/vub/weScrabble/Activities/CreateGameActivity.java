package soft.vub.weScrabble.activities;

import soft.vub.weScrabble.AmbientTalkManager;
import soft.vub.weScrabble.R;
import soft.vub.weScrabble.atInterfaces.CoreInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import edu.vub.at.exceptions.XAmbienttalk;

public class CreateGameActivity extends Activity {
	private TextView team0NameField;
	private TextView team1NameField;
	private TextView gameNameField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_game);
		
		team0NameField = (TextView) findViewById(R.id.team0Name);
		team1NameField = (TextView) findViewById(R.id.team1Name);
		gameNameField = (TextView) findViewById(R.id.gameName);
	}

	/**
	 * Starts a new game with the given team names
	 * @param v
	 */
	public void startButtonOnClick(View v) {
		CoreInterface core = AmbientTalkManager.getSharedManager().getCoreInterface();
		
		String team0Name = team0NameField.getText().toString();
		String team1Name = team1NameField.getText().toString();
		String gameName = gameNameField.getText().toString();
		String t0 = (!team0Name.equals("") ? team0Name : team0NameField.getHint().toString());
		String t1 = (!team1Name.equals("") ? team1Name : team1NameField.getHint().toString());
		String name = (!gameNameField.equals("") ? gameName : gameNameField.getHint().toString());
		String[] teams= {t0,t1};
		
		try {
			core.startGame(name, teams);
		} catch (XAmbienttalk e) {
			e.printStackTrace();
			Log.e("weScrabble", "not all teams were added");
		}
		
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
}
	
}

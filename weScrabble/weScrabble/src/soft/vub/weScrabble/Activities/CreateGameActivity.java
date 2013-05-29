package soft.vub.weScrabble.activities;

import edu.vub.at.exceptions.XAmbienttalk;
import soft.vub.weScrabble.AmbientTalkManager;
import soft.vub.weScrabble.R;
import soft.vub.weScrabble.atInterfaces.GameInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CreateGameActivity extends Activity {
	private TextView team0NameField;
	private TextView team1NameField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_game);
		
		team0NameField = (TextView) findViewById(R.id.team0Name);
		team1NameField = (TextView) findViewById(R.id.team1Name);
	}

	/**
	 * Starts a new game with the given team names
	 * @param v
	 */
	public void startButtonOnClick(View v) {
		GameInterface game = AmbientTalkManager.getSharedManager().getGameInterface().create();
		
		String team0Name = team0NameField.getText().toString();
		String team1Name = team1NameField.getText().toString();
		String t0 = (!team0Name.equals("") ? team0Name : team0NameField.getHint().toString());
		String t1 = (!team1Name.equals("") ? team1Name : team1NameField.getHint().toString());
		String[] teams= {t0,t1};
		
		try {
			game.addTeams(teams);
		} catch (XAmbienttalk e) {
			Log.e("WeScrabble", "ignored additional teams");
		}
		
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
}
	
}

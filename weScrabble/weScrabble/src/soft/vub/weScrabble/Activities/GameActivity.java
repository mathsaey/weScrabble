package soft.vub.weScrabble.activities;

import soft.vub.weScrabble.AmbientTalkManager;
import soft.vub.weScrabble.R;
import soft.vub.weScrabble.atInterfaces.CoreInterface;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends Activity {
	private TextView gameName;
	private TextView playerName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		gameName = (TextView) findViewById(R.id.gameName);
		gameName = (TextView) findViewById(R.id.playerNames);
		
		CoreInterface core = AmbientTalkManager.getSharedManager().getCoreInterface();
		gameName.setText(core.currentGame().name());
	}

}

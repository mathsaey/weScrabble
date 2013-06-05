package soft.vub.weScrabble.activities;

import soft.vub.weScrabble.AmbientTalkManager;
import soft.vub.weScrabble.R;
import soft.vub.weScrabble.atInterfaces.GameInterface;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class JoinGameActivity extends Activity {
	private ListView gameList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AmbientTalkManager manager = AmbientTalkManager.getSharedManager();

		gameList = (ListView) findViewById(R.id.gameList);
		ArrayAdapter<String> a = new ArrayAdapter<String>(this, R.id.gameList);
		for (GameInterface g : manager.getCoreInterface().getJoinableGames()) {
			a.add(g.name());
		}
	}

}

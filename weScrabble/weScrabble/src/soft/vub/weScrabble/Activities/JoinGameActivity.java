package soft.vub.weScrabble.activities;

import soft.vub.weScrabble.AmbientTalkManager;
import soft.vub.weScrabble.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class JoinGameActivity extends Activity {
	final Handler handler = new Handler();
	final Runnable runnable = new Runnable() {
		public void run() {
			updateListView();
			handler.postDelayed(this, 1000);}
		};
	
	private ListView gameList;
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		gameList = (ListView) findViewById(R.id.gameList);
		adapter = new ArrayAdapter<String>(this, R.id.gameList);
		updateListView();
		handler.postDelayed(runnable, 1000);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		handler.removeCallbacks(runnable);
	}
	
	private void updateListView() {
		AmbientTalkManager manager = AmbientTalkManager.getSharedManager();
		String[] names = manager.getCoreInterface().getGameNames();
		adapter.clear();
		for (String s : names) {
			adapter.add(s);
		}
		adapter.notifyDataSetChanged();
	}
}
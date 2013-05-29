package soft.vub.weScrabble;

import android.app.Activity;
import edu.vub.at.android.util.AssetInstaller;

public class WeScrabbleAssetInstaller extends AssetInstaller {
	
	//Ensures the assets are copied to the device
	public WeScrabbleAssetInstaller() {
		super();
	}
	
	//Installs the assets, this avoids having to use an intent to do this
	public void launch(Activity a) {
		copyDefaultAssets(a);
		copyAssets(a);
	}
}

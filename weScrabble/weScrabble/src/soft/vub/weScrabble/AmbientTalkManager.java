package soft.vub.weScrabble;

import soft.vub.weScrabble.atInterfaces.GameInterface;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import edu.vub.at.IAT;
import edu.vub.at.android.util.IATAndroid;
import edu.vub.at.android.util.IATSettings;
import edu.vub.at.android.util.IATSettings.IATOptions;
import edu.vub.at.exceptions.XIllegalOperation;
import edu.vub.at.exceptions.XTypeMismatch;

/**
 * This class is a central place for 
 * activities to talk with ambienttalk
 * 
 * @author mathsaey
 */
public class AmbientTalkManager {
	// Ivars
	private static AmbientTalkManager sharedManager;
	private IAT iat;
	
	// Singleton Pattern & initialization methods
	
	/**
	 * Initializes the manager
	 */
	private AmbientTalkManager() {
		super();
		sharedManager = this;
	}
	
	/**
	 * Gets the singleton instance of the AmbientTalkManager
	 * @return the singleton instance
	 * @throws AmbientTalkDidNotLaunchException
	 */
	public static synchronized AmbientTalkManager getSharedManager() {
		if (sharedManager == null) {
			sharedManager = new AmbientTalkManager();
		}
		return sharedManager;
	}
	
	@Override // to prevent cloning
	public synchronized Object clone()  {
		return AmbientTalkManager.getSharedManager();
		}
	
	/**
	 * Installs the ambienttalk assets on the system and starts
	 * the ambienttalk interpreter
	 * 
	 * @param a
	 * 	An initialized activity, the asses installer needs this
	 * @throws AmbientTalkDidNotLaunchException
	 */
	public synchronized void installAndLaunchAT(Activity a) throws AmbientTalkDidNotLaunchException {
		try {
			//Install the ambienttalk assets
			if (iat == null) {
				WeScrabbleAssetInstaller installer = new WeScrabbleAssetInstaller();
				installer.launch(a);
			}
	
			//Launch the interpreter
			IATOptions iatOptions = IATSettings.getIATOptions(a);			
			IAT iat = IATAndroid.create(a, iatOptions);
			this.iat = iat;

		} catch (Exception e) {
			Log.e("AmbientTalk", "Could not start IAT", e);
			throw new AmbientTalkDidNotLaunchException();
		}
	}
	
	/**
	 * Generate a hashed id of the device
	 * @return a unique identifier for this device
	 */
	public String getDeviceID() {
	   // final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
	   // final String id = tm.getDeviceId();
	   // return Integer.toString(id.hashCode()); //Hash the id for privacy
		return "lol";
	}
	
	// Wrappers
	public GameInterface getGameInterface() {
		try {
			return (GameInterface) iat.evalAndWrap("/.weScrabble.gameLogic", GameInterface.class);
		} catch (XTypeMismatch e) {
			e.printStackTrace();
			return null;
		} catch (XIllegalOperation e) {
			e.printStackTrace();
			return null;
		}
	}
}

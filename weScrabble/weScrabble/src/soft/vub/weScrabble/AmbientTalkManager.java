package soft.vub.weScrabble;

import soft.vub.weScrabble.atInterfaces.CoreInterface;
import android.app.Activity;
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
	private CoreInterface core;
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
			iatOptions.networkName_ = "weScrabble"; 
			IAT iat = IATAndroid.create(a, iatOptions);
			this.iat = iat;
			
			loadCoreInterface();
			
		} catch (Exception e) {
			Log.e("AmbientTalk", "Could not start IAT", e);
			throw new AmbientTalkDidNotLaunchException();
		}
	}
	
	private void loadCoreInterface() {
		try {
			core = (CoreInterface) iat.evalAndWrap("/.weScrabble.localCore.core", CoreInterface.class);
		} catch (XTypeMismatch e) {
			e.printStackTrace();
		} catch (XIllegalOperation e) {
			e.printStackTrace();
		}
	}
		
	public CoreInterface getCoreInterface() {
		if (core == null) {
			loadCoreInterface();
		}
		return core;
	}
	
	public void setCoreInterface(CoreInterface core) {
		this.core = core;
	}
}

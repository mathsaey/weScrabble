package soft.vub.weScrabble.atInterfaces;


/**
 * Interface for a game object,
 * the game object represents the global state
 * of the game for all players in both teams
 * 
 * @author mathsaey
 */
public interface GameInterface {
	/**
	 * Returns an array containing all the teams in the game..
	 * @return
	 * 	An array containing all the teams in the game
	 */
	public TeamInterface[] teams();
	
	/**
	 * gets the name of the game
	 * @return the name of the game
	 */
	public String name();
}

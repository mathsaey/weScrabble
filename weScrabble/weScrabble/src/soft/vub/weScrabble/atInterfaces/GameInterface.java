package soft.vub.weScrabble.atInterfaces;

import edu.vub.at.exceptions.XAmbienttalk;

/**
 * Interface for a game object,
 * the game object represents the global state
 * of the game for all players in both teams
 * 
 * @author mathsaey
 */
public interface GameInterface {
	/**
	 * Creates a new game object
	 * @return The new game object
	 */
	public GameInterface create();
	
	/**
	 * Adds multiple teams to the game object
	 * @param names 
	 * 	 An array containing the names of the teams you wish to add
	 * @throws XAmbienttalk
	 * 	Thrown when the game already reached the maximum amount of teams
	 */
	public void addTeams(String[] names) throws XAmbienttalk;
	
	/**
	 * Returns an array containing all the teams in the game..
	 * @return
	 * 	An array containing all the teams in the game
	 */
	public TeamInterface[] teams();
	
	/**
	 * Gets the game id
	 * @return
	 * 	The identifier of the game
	 */
	public String id();
}

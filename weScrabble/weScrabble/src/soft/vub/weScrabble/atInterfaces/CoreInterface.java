package soft.vub.weScrabble.atInterfaces;

import android.widget.ArrayAdapter;
import edu.vub.at.exceptions.XAmbienttalk;

public interface CoreInterface {
	
	/**
	 * Return the current game instance.
	 * @return the current game instance, wrapped inside a GameInterface interface
	 */
	public GameInterface currentGame();
	
	/**
	 * Initialises the core with a player name.
	 * 
	 * @param playerName 
	 * 	Name that other players will see when playing with this player
	 * @return
	 * 	Fresh CoreInterface instance.
	 */
	public CoreInterface init(String playerName);
	
	/**
	 * Creates a new game.
	 * @param gameName Name of the game
	 * @param teamNames Team names
	 * @return the initialized game
	 * @throws XAmbienttalk thrown when too many team names are supplied
	 */
	public CoreInterface startGame(String gameName, String[] teamNames) throws XAmbienttalk;
	
	/**
	 * Returns an array containing all the games that can be joined
	 * @return array with all the games in range.
	 */
	public String[] getGameNames();
	
	/**
	 * join a game in progress
	 * @param gameIdx index of the game in the joinableGames array
	 * @param teamIdx index of the team the player wants to join
	 * @return the game that has been joined
	 * @throws XAmbienttalk Thrown when an issue arises when joining.
	 */
	public GameInterface joinGame(int gameIdx, int teamIdx) throws XAmbienttalk;
}

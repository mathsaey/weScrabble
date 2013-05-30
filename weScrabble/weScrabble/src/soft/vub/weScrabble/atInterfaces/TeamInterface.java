package soft.vub.weScrabble.atInterfaces;

/**
 * This interface wraps around an ambienttalk team interface
 * @author mathsaey
 */
public interface TeamInterface {
	public String name();

	public TeamInterface init(String name);
	
	public PlayerGameInterface[] allPlayers();
	public PlayerGameInterface[] activePlayers();
	public PlayerGameInterface[] resolvedPlayers();
}

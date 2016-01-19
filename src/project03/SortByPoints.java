package project03;

import java.util.Comparator;

public class SortByPoints implements Comparator<PlayerStats> {
	
	private  static SortByPoints pointsInstance = new SortByPoints();
	
	
	private SortByPoints() {}
	
	public static SortByPoints getInstance() {
		   return pointsInstance;
	}
	
	public int compare(PlayerStats thisPlayer, PlayerStats otherPlayer) {
		// TODO Auto-generated method stub
		int gamesComp =thisPlayer.points > otherPlayer.points ? 1 : (thisPlayer.points < otherPlayer.points ? -1 : 0) ;
		
		return (  (gamesComp == 0) ? thisPlayer.playerID.compareTo (otherPlayer.playerID ):gamesComp );
	}
	

}

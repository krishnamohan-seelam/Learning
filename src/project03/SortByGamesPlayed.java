package project03;

import java.util.Comparator;

public class SortByGamesPlayed implements Comparator<PlayerStats> {
         
   private static SortByGamesPlayed  sortInstance =  new SortByGamesPlayed();
   
   private SortByGamesPlayed() {
	   
   }
   
   public static SortByGamesPlayed getInstance()
   {
	   return sortInstance;
   }

	@Override
	public int compare(PlayerStats thisPlayer, PlayerStats otherPlayer) {
		// TODO Auto-generated method stub
		int gamesComp =thisPlayer.gamesPlayed > otherPlayer.gamesPlayed ? 1 : (thisPlayer.gamesPlayed < otherPlayer.gamesPlayed ? -1 : 0) ;
		
		return (  (gamesComp == 0) ? thisPlayer.playerID.compareTo (otherPlayer.playerID ):gamesComp );
	}

}

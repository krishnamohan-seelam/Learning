package project03;

import java.util.Comparator;



public class PlayerStats implements Comparable <PlayerStats>
{
	
	 String playerID;
	 String firstName;
	 String lastName;
	 float efficiency;
	 int gamesPlayed;
	 int points;
	 
	 
	  public PlayerStats(String playerID,String firstName,String lastName, float efficiency, int gamesPlayed,int points) 
	  {
		  this.playerID=playerID;
		  this.firstName=firstName;
		  this.lastName=lastName;
		  this.efficiency=efficiency;
		  this.gamesPlayed =gamesPlayed;
		  this.points =points;
	  }
	  public String getPlayerID()
	  {
		  return this.playerID;
		  }
	  
	  public String getFirstName()
	  {
		  return this.firstName;
		  
	  }
	  public String getLastName()
	  {
		  return this.lastName;
		  
	  }
	  
	  public float getEfficiency()
	  {
		  return this.efficiency;
	  }
	  
	  public int  getGamesPlayed()
	  {
		  return this.gamesPlayed;
	  }
	  
	  public int  getPoints()
	  {
		  return this.points;
	  }
	  @Override
	  public boolean equals(Object otherObj)
	  {
		  if(this==otherObj) return true;
		  if(otherObj==null) return false;
		  if(getClass()!=otherObj.getClass()) return false;
		  PlayerStats otherPlayer = (PlayerStats) otherObj;
		  return  (otherPlayer.playerID == this.playerID &&  otherPlayer.efficiency==this.efficiency);
		  
	  }
	  
	  @Override
	  public int compareTo(PlayerStats otherPlayer)
	  {
		  
		  
		  if (this.efficiency== otherPlayer.efficiency)
	            return 0;
	        else if (this.efficiency > otherPlayer.efficiency)
	            return 1;
	        else
	            return -1;
	  }
	
	  
	  public static class SortByPoints  implements Comparator<PlayerStats>
	  {

		@Override
		public int compare(PlayerStats thisPlayer, PlayerStats otherPlayer) {
			// TODO Auto-generated method stub
			int gamesComp =thisPlayer.points > otherPlayer.points ? 1 : (thisPlayer.points < otherPlayer.points ? -1 : 0) ;
			
			return (  (gamesComp == 0) ? thisPlayer.playerID.compareTo (otherPlayer.playerID ):gamesComp );
		}
		
	  
	  }
	  
	  
}

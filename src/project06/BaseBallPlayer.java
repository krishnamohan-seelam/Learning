package project06;

import java.util.Comparator;



public class BaseBallPlayer {

	  String playerName,teamID;
	  int gamesPlayed,atBats,runScored,hits,doubles,triples,homeruns;
	  float batAvg,slugPercentage;
	
	public BaseBallPlayer(String playerName, String teamID, int gamesPlayed,
			int atBats, int runScored, int hits, int doubles, int triples,
			int homeruns,float batAvg,float slugPercentage) {
		super();
		this.playerName = playerName;
		this.teamID = teamID;
		this.gamesPlayed = gamesPlayed;
		this.atBats = atBats;
		this.runScored = runScored;
		this.hits = hits;
		this.doubles = doubles;
		this.triples = triples;
		this.homeruns = homeruns;
		this.batAvg =batAvg;
		this.slugPercentage=slugPercentage;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getTeamID() {
		return teamID;
	}
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public int getAtBats() {
		return atBats;
	}
	public void setAtBats(int atBats) {
		this.atBats = atBats;
	}
	public int getRunScored() {
		return runScored;
	}
	public void setRunScored(int runScored) {
		this.runScored = runScored;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getDoubles() {
		return doubles;
	}
	public void setDoubles(int doubles) {
		this.doubles = doubles;
	}
	public int getTriples() {
		return triples;
	}
	public void setTriples(int triples) {
		this.triples = triples;
	}
	public int getHomeruns() {
		return homeruns;
	}
	public void setHomeruns(int homeruns) {
		this.homeruns = homeruns;
	}
	
	public float getBatAvg() {
		
		return this.batAvg;
	}
	
	public void setBatAvg(float batAvg) {
		this.batAvg=batAvg;
	
	}
	public float getSlugPercentage() {
	
		return this.slugPercentage;
	}
	public void setSlugpercentage(float slugPercentage) {
		this.slugPercentage=slugPercentage;
	}
	@Override
	public String toString()
	{
		String playerString = String.format("%-3s - %-20s BatAverage %-4.2f SlugPercentage %-4.2f ",this.teamID, this.playerName,this.batAvg ,this.slugPercentage);
		return playerString;
		 
		
	}
	
	public static final Comparator <BaseBallPlayer> NameComparator = new  Comparator<BaseBallPlayer>(){

		@Override
		public int compare(BaseBallPlayer thisObj, BaseBallPlayer otherObj) {
			// TODO Auto-generated method stub
			return  thisObj.getPlayerName().compareTo(otherObj.getPlayerName());
		}
	
	};
	
	public static final Comparator <BaseBallPlayer> HitsComparator = new  Comparator<BaseBallPlayer>(){

		@Override
		public int compare(BaseBallPlayer thisObj, BaseBallPlayer otherObj) {
			// TODO Auto-generated method stub
			return  Integer.valueOf(thisObj.getHits()).compareTo(Integer.valueOf(otherObj.getHits()));
		}
	
	};
	public static final Comparator <BaseBallPlayer> BatAvgComparator = new  Comparator<BaseBallPlayer>(){

		@Override
		public int compare(BaseBallPlayer thisObj, BaseBallPlayer otherObj) {
			// TODO Auto-generated method stub
			return  Float.valueOf(thisObj.getBatAvg()).compareTo(Float.valueOf(otherObj.getBatAvg()));
		}
	
	};
	
	public static final Comparator <BaseBallPlayer> SlugComparator = new  Comparator<BaseBallPlayer>(){

		@Override
		public int compare(BaseBallPlayer thisObj, BaseBallPlayer otherObj) {
			// TODO Auto-generated method stub
			return  Float.valueOf(thisObj.getSlugPercentage()).compareTo(Float.valueOf(otherObj.getSlugPercentage()));
		}
	
	};
}

package project06;

import java.io.BufferedReader;
import java.util.stream.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



import com.csvreader.CsvReader;
//mlb.txt
public class BaseBallMain {
	
	
	public static final String validCmds[] ={"QUIT","HELP" ,"INPUT","TEAM","REPORT"};
	public static final String validRptCmds[]={"HITS", "BATTING","SLUGGING"};
	public static String filePath ;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> validList =Arrays.asList(validCmds);
		List<BaseBallPlayer> bbPlayers=null;
		List<BaseBallPlayer> teamPlayers=null;
		 checkArgs(args);
		filePath= getpath(args[0]);
		String fileName;
	    do
	    {
	    	 String cmd[] =getCommand(validList) ;
	    	 boolean validFlag =processCmd(cmd);
	    	 if (validFlag)  
	    	 {
	    		 switch (cmd[0])
		    	 {
		    	    case  "QUIT"  : System.out.println("Quit Command  issued ...ending program");
					 				System.exit(0);
		    	    case  "HELP"  : processHelp(cmd); break;
		    	    case  "INPUT" : fileName =cmd[1];
		    	    	            bbPlayers =processInput(fileName);
		    	    	            
		    	    	            //for (BaseBallPlayer bbp :bbPlayers)
		    	    	            //	System.out.println(bbp );
		    	    	            break;
		    	    case  "TEAM"  : teamPlayers=processTeam(cmd[1],bbPlayers);
		    	    				Collections.sort(teamPlayers,BaseBallPlayer.NameComparator);
		    	    				for(BaseBallPlayer player:teamPlayers)
		    	    					System.out.println(player);
		    	    				break;
		    	    case  "REPORT" :processReport(cmd[1], cmd[2],bbPlayers);
		    	    				break;
		    	    	         
		    	 } 
	    	 }
	    	
	    	 
	    	 
	    } while(true);
		
		
		
		
		
	}
	
private  static boolean processCmd(String[] cmd) throws FileNotFoundException, IOException {
		boolean validFlag=true;
		List<String> ReportCmd=Arrays.asList(validRptCmds);
		
	    switch (cmd[0])
	    {
	    	case   "QUIT" :  if (cmd.length >1) 
	    						{ System.out.println("Invalid quit command");
	    						  validFlag= false;
	    						  }
	    					    					 
	    					 break;
	    	case   "HELP":   if (cmd.length != 2 ) 
	    						{ System.out.println("Invalid help command");
	    						  validFlag= false;
	    						  }
	    					     	
	    					  break;
	    	case   "INPUT": if (cmd.length !=2) 
							{ System.out.println("Invalid Input command");
								validFlag= false;
								}
	    					 
	    						 break;
	    	
	    	case  "TEAM": if (cmd.length !=2) 
							{   System.out.println("Invalid Team command");
								validFlag= false;
								}
	    				   
	    						 break; 
	    	case "REPORT" :	 if (cmd.length !=3) 
							  {   System.out.println("Invalid report command");
								  validFlag= false; 
							  	}
	    					if ( validFlag &&  !isNumeric(cmd[1]))
	    					{
	    						System.out.println("Second argument in report command must be numeric");
							    validFlag= false; 
						  	}
	    					if ( validFlag &&  !ReportCmd.contains(cmd[2]) )
	    					{
	    						System.out.println("Third argument in report command must be HITS/BATTING/SLUGGING");
							    validFlag= false; 
						  	}
	    					break;
	    					
	    }
	    return validFlag;
	    
	}

private  static void processReport(String num,String reportType,List<BaseBallPlayer> bbPlayers) {
	 
	  int nlist = Integer.parseInt(num);
	  List<BaseBallPlayer> subList =null;
	 
	  switch  (reportType)
	  {
	  			case  "HITS" : Collections.sort(bbPlayers,BaseBallPlayer.HitsComparator);break;
	  			case  "BATTING":Collections.sort(bbPlayers,BaseBallPlayer.BatAvgComparator);break;
	  			case  "SLUGGING":Collections.sort(bbPlayers,BaseBallPlayer.SlugComparator);break;
	  			default:break;	
	  }
	  Collections.reverse(bbPlayers);
	  subList =nlist < bbPlayers.size()?  bbPlayers.subList(0,nlist)   :bbPlayers  ;
	  for (BaseBallPlayer player :subList)
		  System.out.println(player);
		  
}

private  static List<BaseBallPlayer> processTeam(String team,List<BaseBallPlayer> bbPlayers ) {
	// TODO Auto-generated method stub
	 List<BaseBallPlayer> teamPlayers =bbPlayers.stream().filter(bbPlayer-> bbPlayer.getTeamID().equalsIgnoreCase(team)).collect(Collectors.toList());
	 
	return teamPlayers;
	 
}

private static List<BaseBallPlayer> processInput(String fileName) throws FileNotFoundException ,IOException{
	// TODO Auto-generated method stub
	 List<BaseBallPlayer> bbPlayers = new ArrayList<BaseBallPlayer>();
	
	CsvReader reader= new CsvReader(filePath+"\\"+fileName);
	reader.setDelimiter(';');
	String playerName ,teamID;
	int gamesPlayed,atBats,runScored,hits,doubles,triples,homeruns;
	while (reader.readRecord()) {
		      
		      playerName =reader.get(0);
		      teamID = reader.get(1);
		      gamesPlayed= isNumeric(reader.get(2)) ? Integer.parseInt(reader.get(2)):0;
		      atBats= isNumeric(reader.get(3)) ? Integer.parseInt(reader.get(3)):0;
		      runScored=isNumeric(reader.get(4)) ? Integer.parseInt(reader.get(4)):0;
		      hits=isNumeric(reader.get(5)) ? Integer.parseInt(reader.get(5)):0;
		      doubles =isNumeric(reader.get(6)) ? Integer.parseInt(reader.get(6)):0;
		      triples =isNumeric(reader.get(7)) ? Integer.parseInt(reader.get(7)):0;
		      homeruns=isNumeric(reader.get(8)) ? Integer.parseInt(reader.get(8)):0;
		     
		      int singles = hits - (doubles+triples+homeruns);
			  int totalBases = singles + 2 *(doubles)+3*(triples)+4*(homeruns);
			  
			  float batavg = (float)hits/(float)atBats;
			  float slugPercentage = atBats!=0 ?((float)totalBases/(float)atBats) :0;
			  
		      bbPlayers.add(new BaseBallPlayer(playerName ,teamID,gamesPlayed,atBats,runScored,hits,doubles,triples,homeruns ,batavg ,slugPercentage));
	}
		      
	reader.close();
	return bbPlayers;
}

private  static void processHelp(String[] helpcmd) {
	 
	
}

private static String[] getCommand(List<String>validList) throws IOException {
		 
		System.out.println("QUIT OR HELP"+  "\n"+
							"INPUT filename OR TEAM identifier" +  "\n"+
							"REPORT n HITS OR REPORT n BATTING OR REPORT n SLUGGING" +  "\n");
		 
		 
		 BufferedReader inputReader= new BufferedReader(new InputStreamReader(System.in));
		 String inputCmd=null;
		 String tokens[]=null;
	
		 do
		 {   System.out.println("Enter command:");
			 inputCmd =inputReader.readLine();
			 tokens=inputCmd.toUpperCase().split("\\s+");
			 
		 }while(!validList.contains(tokens[0]));
		 
		
		 return tokens;
	}

private static String getpath(String path) {
	
	 	Path inputPath = Paths.get(path);
	 	Path fullPath = inputPath.toAbsolutePath();
	 	return fullPath.toString()+"\\";
	}

private static  void checkArgs(String[] args) {
		
		if (args.length == 0) {
			
			System.err.println("No Path specified");
			
			System.exit(0);
			}
	}
private static  boolean isNumeric(String inputData) {
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(inputData, pos);
	  return inputData.length() == pos.getIndex();
	}
}

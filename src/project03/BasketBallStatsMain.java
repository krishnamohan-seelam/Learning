package project03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.csvreader.CsvReader;


//  This program implements Singleton design pattern for sorting  of top n players by number of games played,
//   top n players by total number of points scored
public class BasketBallStatsMain {
	final static Logger logger = Logger.getLogger(BasketBallStatsMain.class);
	public static void main(String[] args) throws IOException 
	{   
		logger.info("Program execution started ");
		if (args.length == 0) {
		
		System.err.println("No Path specified");
		logger.error("No Path specified");
		System.exit(0);
		}
		// TODO Auto-generated method stub
		Path inputPath = Paths.get(args[0]);

		Path fullPath = inputPath.toAbsolutePath();
		BufferedReader inputReader= new BufferedReader(new InputStreamReader(System.in));
		
		
		
		String fileName;
		String outFileName;
		ArrayList<PlayerStats> playerList =null;
		List<PlayerStats> topNList =new ArrayList<>();
		String playerName;
		int lineSize =80;
		char[] starChar =null;
		int arraySize;
		while(true)
		{
			System.out.println("Enter filename or  press x to exit:");
			inputReader= new BufferedReader(new InputStreamReader(System.in));
			
			
			String input =inputReader.readLine();
			if (input.equalsIgnoreCase("x"))
				break;
			
			fileName = fullPath.toString() + "\\" + input;
			
			String newName = input.substring(0,input.indexOf(".csv", 0))    ;
			System.out.println(newName);
			outFileName=fullPath.toString() + "\\" +newName+"_out.txt" ;
			if (!fileName.endsWith("csv"))
			{
				logger.info("Requires csv file as input...");
			}
			else
			{
				playerList =processCsvFile(fileName);
				int size=50;
				size = playerList.size() >size ? size:playerList.size();
				BufferedWriter outputWriter =new BufferedWriter(new FileWriter(outFileName));
				
				Collections.sort(playerList);
				Collections.reverse(playerList);
			    topNList= playerList.subList(0, size);
				Iterator<PlayerStats> itr =topNList.iterator();
				 while(itr.hasNext())
				 {
					 PlayerStats psts = itr.next();
					 playerName =psts.getFirstName() + "," + psts.getLastName();
					 arraySize =playerName.length() > 50 ? 15:lineSize-playerName.length();
					 starChar=new char[arraySize];
					 for (int i=0;i<arraySize;i++)
					   starChar[i]='\u0000';
					 outputWriter.write( playerName+ new String(starChar)+" Efficiency: " + psts.getEfficiency() +"\n");
					 
				 }
				 
				 
				 
				 printOtherStats('g',playerList);
				 printOtherStats('p',playerList);			
				 	 			 
				 
				 outputWriter.close();
				 
			}
              
            
		}
		inputReader.close();
		
	}
	
	
	private static void printOtherStats(char c ,ArrayList< PlayerStats> playerList) {
		// TODO Auto-generated method stub
		
		 switch (c){
		 			case 'g':System.out.println("============Sort by no of games played===========");
		 					 Collections.sort(playerList,SortByGamesPlayed.getInstance());
		 			break;
		 			case 'p':System.out.println("============Sort by points scored===========");
		 				
					 		 Collections.sort(playerList,SortByPoints.getInstance());
		 			break;
		 }
		 
		 List<PlayerStats> topNList =new ArrayList<>();
		 Collections.reverse(playerList);
		 topNList= playerList.subList(0, 5);
		 Iterator<PlayerStats> itrgp =topNList.iterator();
		 	
		 while(itrgp.hasNext())
		 {
			 PlayerStats psts = itrgp.next();
			 
			 
					 
			 String gamesp =String.valueOf(psts.getGamesPlayed());
			 System.out.println(psts.getFirstName() + "," + psts.getLastName());
			 switch (c){
			 				case 'g' :System.out.print(" GamesPlayed:  "+gamesp  +"\n");break;
			 				case 'p' :  String points =String.valueOf(psts.getPoints());
			 							System.out.print("points :"+points  +"\n");
			 							break;
			 			}
			 
		 }
	}


	private static ArrayList<PlayerStats> processCsvFile(String fileName ) throws IOException {
		// TODO Auto-generated method stub
		CsvReader reader=null;
		String ilkid=null;String year=null;String firstname=null;String lastname=null ;
		String team=null ;String leag=null;String gp=null;String minutes=null;
		String pts=null;String oreb=null ;String dreb=null; String reb=null;
		String asts=null;String stl=null;String blk=null;String turnover=null;String pf=null;
		String fga=null;String  fgm=null;String fta=null;String ftm=null;String tpa=null;String tpm=null;
		int ptsI ,rebI , astsI, stlI , blkI,fgaI , fgmI,  ftaI, ftmI,turnoverI,gpI;
		System.out.println(fileName);
		ArrayList<PlayerStats> playerList =new ArrayList<PlayerStats>();
		try{
			reader = new CsvReader(fileName);
			reader.readHeaders();
			String headers[]=reader.getHeaders();
			
				
			while (reader.readRecord())
				{
					ilkid=reader.get("ilkid");
					year =reader.get("year");
					firstname=reader.get("firstname");
					lastname=reader.get("lastname");
					team=reader.get("team");
					leag=reader.get("leag");
					gp =reader.get("gp");
					minutes=reader.get("firstname");
					pts=reader.get("pts");
					oreb=reader.get("team");
					dreb=reader.get("dreb");
					reb =reader.get("reb");
							
					asts=reader.get("asts");
					stl=reader.get("stl");
					blk=reader.get("blk");
					turnover=reader.get("turnover");
					pf=reader.get("pf");
					fga=reader.get("fga");
					fgm=reader.get("fgm");
					fta=reader.get("fta");
					ftm=reader.get("ftm");
					tpa=reader.get("tpa");
					tpm=reader.get("tpm");
					
					ptsI= (isNumeric(pts)==true) ? Integer.parseInt(pts):0;
					rebI=(isNumeric(reb )==true) ? Integer.parseInt(reb):0;
					astsI=(isNumeric(asts)==true)? Integer.parseInt(asts):0;
					stlI=(isNumeric(stl )==true) ? Integer.parseInt(stl):0;
					blkI=(isNumeric(blk )==true) ? Integer.parseInt(blk):0;
					fgaI=(isNumeric(fga )==true) ? Integer.parseInt(fga):0;
					fgmI=(isNumeric(fgm  )==true)? Integer.parseInt(fgm):0;
					ftaI=(isNumeric(fta  )==true)? Integer.parseInt(fta):0;
					ftmI=(isNumeric(ftm )==true )? Integer.parseInt(ftm):0;
					
					turnoverI=(isNumeric(turnover )==true )? Integer.parseInt(turnover):0;
					
					gpI=(isNumeric(gp )==true )? Integer.parseInt(gp):0;
					
					float efficiency=0;
					
					efficiency= gpI!=0 ?(ptsI+ rebI + astsI + stlI + blkI) - (( fgaI -fgmI) + ( ftaI -ftmI) + turnoverI)/gpI :0;
					playerList.add(new PlayerStats(ilkid,firstname,lastname,efficiency,gpI,ptsI ))	;
					
				}
			
			
		}
		
		catch(FileNotFoundException fe)
		{
			System.out.println(fe);
			
		}
		 return playerList;
	}
	public static boolean isNumeric(String inputData) {
		  NumberFormat formatter = NumberFormat.getInstance();
		  ParsePosition pos = new ParsePosition(0);
		  formatter.parse(inputData, pos);
		  return inputData.length() == pos.getIndex();
		}

}

package project04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;



import org.apache.log4j.Logger;

import com.csvreader.CsvReader;


//riskfactors.csv
//  
//  Analyze Center for Disease Control data on health risks
//
public class RiskFactorMain {
	final static Logger logger = Logger.getLogger(RiskFactorMain.class);
	
	public static void main(String[] args) throws IOException {
			
			String fileName = getFileName(args[0]);
			String header=String.format("%s%23s%3s%30s", "Indicator",":" ," Min","Max");
			String printValue;
			checkArgs(args);
			
			ArrayList <RiskFactor> riskFactorList = processFile(fileName);
				
			System.out.println(header);
			char ch[] =new char[91];
			java.util.Arrays.fill(ch, '-');
			System.out.println(new String(ch));
			RiskFactor hdMax =Collections.max(riskFactorList,RiskFactor.HDComparator);
			RiskFactor hdMin =Collections.min(riskFactorList,RiskFactor.HDComparator);
			
			printValue= getFormattedValues(hdMax,hdMin,"HD") ;
			System.out.println(printValue)		;
			
			RiskFactor mvMax =Collections.max(riskFactorList,RiskFactor.MVComparator);
			RiskFactor mvMin =Collections.min(riskFactorList,RiskFactor.MVComparator);
		    printValue = getFormattedValues(mvMax,mvMin,"MV") ;
			System.out.println(printValue)		;
			
			RiskFactor tbMax =Collections.max(riskFactorList,RiskFactor.TBComparator);
			RiskFactor tbMin =Collections.min(riskFactorList,RiskFactor.TBComparator);
			printValue = getFormattedValues(tbMax,tbMin,"TB") ;
			System.out.println(printValue)		;
			RiskFactor asMax =Collections.max(riskFactorList,RiskFactor.ASComparator);
			RiskFactor asMin =Collections.min(riskFactorList,RiskFactor.ASComparator);
			printValue = getFormattedValues(asMax,asMin,"AS") ;
			System.out.println(printValue)		;
			RiskFactor aoMax =Collections.max(riskFactorList,RiskFactor.AOComparator);
			RiskFactor aoMin =Collections.min(riskFactorList,RiskFactor.AOComparator);
			printValue = getFormattedValues(aoMax,aoMin,"AO") ;
			System.out.println(printValue)		;
			
			System.out.println("Ending program" );
			
	   }

	
	private static String getFormattedValues(RiskFactor max, RiskFactor min, String choice) {
		// TODO Auto-generated method stub
		
		

	    String output=null;
	    String format ="%-30s: %-22s%6.1f   %-22s %6.1f";
		
		switch (choice)
		{
			case  "HD":		
							output=String.format(format,"Heart Disease Death Rate (2007)",min.getState(),min.getHdDeathRate(),max.getState(),max.getHdDeathRate());
							break;
			case  "MV":
							
							output=String.format(format,"Motor Vehicle Death Rate (2009)",min.getState(),min.getMvDeathRate(),max.getState(),max.getMvDeathRate());
							
							break;
			case  "AS":
							output=String.format(format,"Adult Smoking (2010)           ",min.getState(),min.getAdultSmoking(),max.getState(),max.getAdultSmoking());

							break;
			case  "TB":
							output=String.format(format,"Teen Birth Rate (2009)         ",min.getState(),min.getTeenBirthRate(),max.getState(),max.getTeenBirthRate());

							break;
			case  "AO":
							output=String.format(format,"Adult Obesity (2010)           ",min.getState(),min.getAdultObsesity(),max.getState(),max.getAdultObsesity());

							break;
		}	
		

		
		return output;
	}


	private static ArrayList<RiskFactor> processFile(String fileName) throws IOException,FileNotFoundException
	{
		ArrayList <RiskFactor> riskFactorList=new ArrayList<>() ;
		CsvReader reader= new CsvReader(fileName);
		for(int i =0;i< 5;i+=1)
			reader.skipRecord();
		reader.readHeaders();
		String headers[]=reader.getHeaders();
		
		while (reader.readRecord())
		{
			
			String state = reader.get("State");
			
			String hdDeathRateStr =reader.get("Heart Disease Death Rate (2007)").replace('%', ' ').trim();			
			float  hdDeathRate = isNumeric(hdDeathRateStr) ? Float.valueOf(hdDeathRateStr):0;
			
			String mvDeathRateStr =reader.get("Motor Vehicle Death Rate (2009)").replace('%', ' ').trim();
			
			float  mvDeathRate = isNumeric(mvDeathRateStr) ? Float.valueOf(mvDeathRateStr):0;
		    
			String teenBirthRateStr =reader.get("Teen Birth Rate (2009)").replace('%', ' ').trim();
			float  teenBirthRate = isNumeric(teenBirthRateStr) ? Float.valueOf(teenBirthRateStr):0;
			
			String adultSmokingStr =reader.get("Adult Smoking (2010)").replace('%', ' ').trim();
			float  adultSmoking = isNumeric(adultSmokingStr) ? Float.valueOf(adultSmokingStr):0;
			
			String adultObesityStr =reader.get("Adult Obesity (2010)").replace('%', ' ').trim();
			float  adultObesity = isNumeric(adultObesityStr) ? Float.valueOf(adultObesityStr):0;
			
			 riskFactorList.add(new RiskFactor(state,hdDeathRate,mvDeathRate,teenBirthRate,adultSmoking,adultObesity));
			}
		if (reader !=null)
		reader.close();
		
		return riskFactorList;
	}


	private static String getFileName(String path) throws IOException
	{
		 Path inputPath = Paths.get(path);
		 Path fullPath = inputPath.toAbsolutePath();
		 String fileName;
		 System.out.println("Enter filename:");
		 BufferedReader inputReader= new BufferedReader(new InputStreamReader(System.in));
		 while(true)
		 {
			 
			 String input =inputReader.readLine();
			 fileName = fullPath.toString() + "\\" + input;
			 if (!fileName.endsWith("csv"))
			 {
				 System.out.println("File entered "+ fileName + " not valid csv file.");
				 System.out.println("Re-Enter FileName:");
				 continue;
			 }
			 else
			 {
				 File file = new File(fileName);
				 if (file.exists()) return fileName;
				 System.out.println("File " + fileName + " does not exist.");
				 
			 } 
			 System.out.println("Re-Enter FileName:");
			 
		 }
		  
	}

	private static boolean isNumeric(String inputData) {
		  NumberFormat formatter = NumberFormat.getInstance();
		  ParsePosition pos = new ParsePosition(0);
		  formatter.parse(inputData, pos);
		  return inputData.length() == pos.getIndex();
		}

	private static void checkArgs(String[] args) {
		
		if (args.length == 0) {
			
			System.err.println("No Path specified");
			logger.error("No Path specified");
			System.exit(0);
			}
	}

}

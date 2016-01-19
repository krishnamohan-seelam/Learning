package project05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Iterator;

import com.csvreader.CsvReader;
//smallvc.txt
public class VenusCraterMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = getFileName(args[0]);
		System.out.println("Checking arguements...");
		checkArgs(args);
		System.out.println("Processing input file:"+ fileName);
		ArrayList<VenusCrater> vcList = processFile(fileName);
		System.out.println("filtering craters...");
		ArrayList<VenusCrater> outList =filterCraters(vcList);
		
		writeCraters(fileName,outList);
		System.out.println("Ending program");
		
	}
	
	private static void writeCraters(String fileName,ArrayList<VenusCrater> outList) {
		// TODO Auto-generated method stub
		
		String outFile;
		if(fileName.endsWith(".txt"))
		{
			int lastIndex = fileName.lastIndexOf(".txt");
			outFile = fileName.substring(0,lastIndex)+"_craters.txt";
			
		}
		else
		{
			outFile =fileName+"_craters_out.txt";
		}
		//System.out.format("%2s%10s %25s %15s \n","ID" ,"Name","Latitude","Longitude");
		
		String out_header = String.format("%2s%10s %15s %12s %12s", "ID" ,"Name","Latitude","Longitude","Diameter");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));){
			writer.append(out_header);
			writer.newLine();
		for (VenusCrater venusCrater : outList) {
			String outLine=String.format("%3d  %-15s %9.2f  %9.2f  %9.2f",venusCrater.getCraterId() ,venusCrater.getCraterName(),venusCrater.getLatitude(),venusCrater.getLongitude(),venusCrater.getDiameter());
			writer.append(outLine);
			writer.newLine();
			}
		}
		catch (IOException ie)
		{
			System.out.println(ie.getMessage());
		}
		System.out.println("Crater output file :"+outFile+" created.");
	}
	private static ArrayList<VenusCrater> filterCraters(
			ArrayList<VenusCrater> vcList) {
		    ArrayList<VenusCrater> outList = new ArrayList<VenusCrater>();
		   Iterator<VenusCrater> itr = vcList.iterator();
		   
		   while (itr.hasNext()) {
			VenusCrater vc =  itr.next();
			if ( (vc.getDiameter() > 60) && (vc.getLongitude() >= 40 && vc.getLongitude()<=135 ) &&
				(vc.getLatitude() >-40 && vc.getLatitude() <= 50) )
			{
				outList.add(vc);
			}
		}
		
		return outList;
	}
	private static ArrayList<VenusCrater> processFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<VenusCrater> vcList=new ArrayList<>();
		CsvReader reader= new CsvReader(fileName);
		reader.setDelimiter('\t');
		reader.skipRecord();
		reader.skipRecord();
		
		reader.readHeaders();
		
		String headers[]=reader.getHeaders();
		while (reader.readRecord()) {
			      String idStr = reader.get("#");
			      String craterName =reader.get("Name");
			      String latStr=reader.get("Lat");
			      String lngStr=reader.get("Lon");
			      String diameterStr=reader.get("D");
			      int id = isNumeric(idStr) && !idStr .equals("")?Integer.parseInt(idStr):0;
			      float lat =isNumeric(latStr)&& !latStr .equals("") ?Float.parseFloat(latStr):0;
			      float lng=isNumeric(lngStr) && !lngStr .equals("") ?Float.parseFloat(lngStr):0;
			      float diameter =isNumeric(diameterStr) && !diameterStr .equals("")  ?Float.parseFloat(diameterStr):0;
			      vcList.add(new VenusCrater(id,craterName,lat,lng,diameter));
			      
		}
			      
		reader.close();
		return vcList;
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
			 if (!fileName.endsWith("txt"))
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
private static void checkArgs(String[] args) {
		
		if (args.length == 0) {
			
			System.err.println("No Path specified");
			
			System.exit(0);
			}
	}
private static boolean isNumeric(String inputData) {
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(inputData, pos);
	  return inputData.length() == pos.getIndex();
	}
}

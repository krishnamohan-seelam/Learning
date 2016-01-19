package project02;


import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ScrambledMain {
	final static Logger logger = Logger.getLogger(ScrambledMain.class);
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		if (args.length == 0) {
			
			System.err.println("No Path specified");
			logger.error("No Path specified");
			System.exit(0);
		}

		Path inputPath = Paths.get(args[0]);

		Path fullPath = inputPath.toAbsolutePath();
		BufferedReader in;
		
		String fileName;
		while(true)
		{
			System.out.println("Enter filename or  press x to exit:");
			in= new BufferedReader(new InputStreamReader(System.in));
			
			
			String input =in.readLine();
			if (input.equalsIgnoreCase("x"))
				break;
			
			fileName = fullPath.toString() + "\\" + input;
            processFile(fileName);
            
		}
		 
		
		
		
	}
	private static void processFile(String fileName) throws Exception
	{
		// TODO Auto-generated method stub
		 
		String line,output=null;
		StringBuffer strBuffer =new StringBuffer();
		 
		String outfile =fileName+"_out.txt";
		Scrambled scr =new Scrambled();
		try
		(       BufferedReader file = new BufferedReader(new FileReader(fileName));
				BufferedWriter writer=new BufferedWriter(new FileWriter(outfile))  ;
		)
		{
			 
			 
			 
			 logger.info("BufferedReader object instantiated");
			 logger.info("BufferedWriter object instantiated");
			 while( (line =file.readLine())!=null)
			 {
				output =scr.getScrambledLine(line) ;
				writer.append(output);
				writer.newLine();
			 }
			
			
		}
		catch (IOException ex)
		{
			logger.error("Error:"+ex.getMessage());
			throw ex;
		}
		
		
	}
	

}

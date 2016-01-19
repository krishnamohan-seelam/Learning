package project02;


import java.util.Random;
import java.util.StringTokenizer;

public class Scrambled {
	String fileName;
	public Scrambled()
	{
		
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public  String getScrambledLine(String line) {
		// TODO Auto-generated method stub
		
		StringTokenizer tokenizer = new StringTokenizer(line);
		StringBuffer newLine =  new StringBuffer();
		String scrambled_word=null;
		String token =null;
		 while (tokenizer.hasMoreTokens())
		 {
			 token =tokenizer.nextToken();
			 
			 scrambled_word = token.length() > 3 ? getscrambledWord(token):token;
			 
			 newLine.append(scrambled_word + " ");
		 }
		 return newLine.toString();
	}
	private String getscrambledWord(String nextToken) {
		// TODO Auto-generated method stub
		char charArray[]=nextToken.toCharArray();
	
		int endIndex=charArray.length -1;
		
		while(!Character.isLetterOrDigit(charArray[endIndex]))
			endIndex--;
		Random randomGen=new Random();
		 
		for (int index=1;index< endIndex ;index+=1)
		{
			 int randomIndex =randomGen.nextInt(endIndex);
			 
			 if (index!=randomIndex && randomIndex!=0)
			 {
				 
				 if (Character.isLetterOrDigit(charArray[index]) && Character.isLetterOrDigit(charArray[randomIndex]))
				 {
					 char ch =charArray[index];
					 charArray[index] =charArray[randomIndex];
					 charArray[randomIndex]=ch;
				 }
			 }
			 
		}
		
		
		return new String(charArray);
	}


}

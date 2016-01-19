package project02;

import java.util.Random;

public class Example {
 public static void main (String args[])
 {
	 String nextToken="university!!!!!!";
	 
		char charArray[]=nextToken.toCharArray();
		int endIndex=charArray.length -1;
		
		while (!Character.isLetterOrDigit(charArray[endIndex]))
			endIndex=endIndex-1;
		Random randomGen=new Random();
		 
		for (int index=1;index< endIndex;index+=1)
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
		System.out.println(charArray);
 }
}

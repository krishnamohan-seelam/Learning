package project08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TagCloudDriver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			String stopWordFileName = null;
			String debateFileName = null;
			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter stopwords file:");
			stopWordFileName =consoleReader.readLine();
			System.out.println("Enter debate file:");
			debateFileName=consoleReader.readLine();
			consoleReader.close();
			TagCloud tgCloud = new TagCloud();
			boolean buildStopList_bool=tgCloud.buildStopWordList(stopWordFileName);
			if (!buildStopList_bool)
				 System.out.println("Stop words not loaded, Tag Cloud will  include all common English words");
			boolean buildTags_bool =tgCloud.buildCloudTagMaps(debateFileName);
			
			if (!buildTags_bool)
				 System.out.println("Tag words not loaded, Tag Cloud program ending");
			System.out.println("Ending Program...");
			System.exit(0);
			
			
	}

}

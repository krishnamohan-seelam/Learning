/**
 * 
 */

/**
 * @author geetha

 *
 */
package project01;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Concordance {

	private String fileName;

	private ArrayList<String> contentsList;
	private HashMap<String, ArrayList<String>> indexMap;

	public Concordance() {
		super();

	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public ArrayList<String> readFile(String fileName) throws IOException {
		contentsList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;

		// Read file line by line and load the list
		while ((line = br.readLine()) != null) {
			contentsList.add(line);
		}
		// Close the buffer reader
		br.close();
		return contentsList;
	}

	public HashMap<String, ArrayList<String>> buildIndex(
			ArrayList<String> inputList, ArrayList<String> omitList) {
		indexMap = new HashMap<String, ArrayList<String>>();
		ArrayList<String> valueList = null;
		StringTokenizer defaultTokenizer = null;
		for (String input : inputList) {
			defaultTokenizer = new StringTokenizer(input);
			while (defaultTokenizer.hasMoreTokens()) {
				String nextKey = defaultTokenizer.nextToken().replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
				if (!omitList.contains(nextKey)) {
					valueList = indexMap.containsKey(nextKey) ? indexMap
							.get(nextKey) : new ArrayList<String>();
					valueList.add(input);
					indexMap.put(nextKey, valueList);

				}

			}
		}
		return indexMap;
	}

}

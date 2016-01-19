package project08;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class TagCloud {

	private static ArrayList<String> stopWordList;
	private final static String debate_CNDT1 ="PALIN:";
	private final static String debate_CNDT2 ="BIDEN:";
	private final static String HOST="IFILL:";
	private HashMap<String ,Integer> dbt_CNDT1_Map ,dbt_CNDT2_Map;

	public TagCloud()
	{

	}

	public boolean buildStopWordList(String stopWordFile) 
	{
		// TODO Auto-generated method stub
		stopWordList = new ArrayList<String>();
		BufferedReader reader=null ;
		String line =null;
		try{
			reader =new BufferedReader(new FileReader(stopWordFile));
			while((line=reader.readLine())!=null)
			{
				stopWordList.add(line);
			}
		}
		catch(FileNotFoundException fne)
		{
			System.out.printf("File %s not found \n",stopWordFile);
			return false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally
		{
			try {
				if (reader!=null) reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		System.out.printf(" No of stop words loaded %d \n", stopWordList.size());
		return true;
	}

	public boolean buildCloudTagMaps(String debateFileName)
	{
		BufferedReader reader=null;
		String lineData = null;
		boolean db_CNDT1_Tag =false;
		boolean db_CNDT2_Tag =false;
		dbt_CNDT1_Map =new HashMap<String,Integer>();
		dbt_CNDT2_Map =new HashMap<String,Integer>();
		try {
			reader= new BufferedReader(new FileReader(debateFileName));
			while ((lineData = reader.readLine())!=null)
			{
				if (lineData.trim().isEmpty()) continue;

				if (lineData.startsWith(debate_CNDT1) ||lineData.startsWith(debate_CNDT2)|| lineData.startsWith(HOST))
				{
					if (lineData.startsWith(debate_CNDT1))
					{
						db_CNDT1_Tag =true;
						db_CNDT2_Tag =false;
						lineData =lineData.replaceAll(debate_CNDT1, "").trim();
					}
					if (lineData.startsWith(debate_CNDT2))
					{
						db_CNDT1_Tag =false;
						db_CNDT2_Tag =true;
						lineData =lineData.replaceAll(debate_CNDT2, "").trim();
					}
					if (lineData.startsWith(HOST))
					{
						db_CNDT1_Tag =false;
						db_CNDT2_Tag =false;
					}

				}

				lineData =lineData.replaceAll("[^a-zA-Z0-9'\\s]" ,"");



				if (db_CNDT1_Tag  && !db_CNDT2_Tag)
				{
					//System.out.println(lineData);
					buildTagMap(lineData ,dbt_CNDT1_Map);
				}
				if (!db_CNDT1_Tag  && db_CNDT2_Tag)
				{
					buildTagMap(lineData ,dbt_CNDT2_Map);
				}
				if (!db_CNDT1_Tag  && !db_CNDT2_Tag) continue;


			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.printf("File %s not found \n",debateFileName);
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally
		{
			if ( reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		//printTagMap(dbt_CNDT1_Map);
		System.out.println(debate_CNDT1 + " Map") ;


		Map<String, Integer> sorted_dbt_CNDT1_Map = sortByComparator(dbt_CNDT1_Map);
		printTagMap(sorted_dbt_CNDT1_Map);

		System.out.println(debate_CNDT2 + " Map") ;
		Map<String, Integer> sorted_dbt_CNDT2_Map = sortByComparator(dbt_CNDT2_Map);
		printTagMap(sorted_dbt_CNDT2_Map);


		return true;

	}







	private Map<String, Integer> sortByComparator(
			HashMap<String, Integer> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> a,
					Map.Entry<String, Integer> b) {
				return (a.getValue()).compareTo(b.getValue());
			}
		});
		Collections.reverse(list);
		// Convert sorted map back to a Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;		


	}

	private void printTagMap(Map<String, Integer> tagMap) {
		// TODO Auto-generated method stub

		/*for (Map.Entry<String,Integer> entry : tagMap.entrySet() )
		 {
			 String key =entry.getKey();
			 int value =entry.getValue();
			 System.out.printf("Key is %s  : value is %d \n", key,value);
		 }*/

		int mapSize = tagMap.size() <  40  ? tagMap.size() : 40;

		Iterator <Map.Entry<String,Integer>> itr =tagMap.entrySet().iterator();
		//System.out.println("No of Elements :" +tagMap);
		for (int i =0;i<mapSize;i++)
		{
			Map.Entry<String,Integer> entry = itr.next();
			String key =entry.getKey().trim();
			int value =entry.getValue();
			// System.out.printf("Key is %s  : value is %d \n", key,value);
			System.out.format("key :%-15s value:%3d\n", key,value);

		}

	}

	private void buildTagMap(String lineData,
			HashMap<String, Integer> tagMap) {
		// TODO Auto-generated method stub

		int count =1;
		for (String word : lineData.split("[\\s]"))
		{

			if (stopWordList.contains(word.toLowerCase()) || word.trim().isEmpty()) continue;

			count = tagMap.containsKey(word.toLowerCase())?  tagMap.get(word.toLowerCase()) +1 : 1 ;
			tagMap.put(word.toLowerCase(),count);

		}

	}


}

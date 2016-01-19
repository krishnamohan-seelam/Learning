package project08;

import java.util.Comparator;
import java.util.Map;

public class TagComparator implements  Comparator<String> {

	private Map<String ,Integer>  tagMap;
	public TagComparator(Map<String ,Integer>  tagMap)
	{
		this.tagMap=tagMap;
	}
	
	@Override
	public int compare(String a, String b) {
		// TODO Auto-generated method stub
		return tagMap.get(a).compareTo(tagMap.get(b)) * -1;
	}

}

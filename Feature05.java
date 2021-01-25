package keywords;

import java.io.*;
import java.util.*;

import edu.wofford.wordoff.*;

public class Feature05 {
	public String[] getGuessesForWord(String s){
		AnagramSearcher f = new AnagramSearcher("/commonwords.txt");
		String y = String.valueOf(f.getAnagram(s));
		y = y.replaceAll("\\[","");
        y = y.replaceAll("\\]", "");
        y = y.replaceAll("\\,", "");
		y = y.replaceAll(s,"");
		y = y.replaceAll("\\s+"," ");
		java.util.List<String> list = new ArrayList<String>(Arrays.asList(y.split(" ")));
		
		String[] x = list.toArray(new String[0]);
		String[] myArray = Arrays.copyOfRange(x, 1, x.length);
		return myArray;
	}
}
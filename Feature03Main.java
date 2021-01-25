package edu.wofford.wordoff;

import java.util.*;

public class Feature03Main {
    public static void main(String[] args) {
		AnagramSearcher a = new AnagramSearcher("/allwords.txt");
		String s = args[0];
		if (a.isWord(s)) {
			String x = String.valueOf(a.getAllAnagrams(s));
			x = x.replaceAll("\\[","");
			x = x.replaceAll("\\]", "");
			x = x.replaceAll("\\,", "");
			System.out.println(x);
		} 
		else {
		   System.out.println("");
		}
    }
}
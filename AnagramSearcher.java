package edu.wofford.wordoff;
import java.sql.*;
//import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnagramSearcher {

    public Map<String, ArrayList<String>> wordMap;
	public int jump = 1;
    public AnagramSearcher() {
        this.wordMap = new HashMap<>();
    }
	
	private String sortString(String unsort){
		unsort = unsort.toLowerCase();
        char[] strChar = unsort.toCharArray();
        Arrays.sort(strChar);
        String sortd_str = new String(strChar);
		return sortd_str;
	}
	
    public AnagramSearcher(String filename) {
        this();
        InputStream is = getClass().getResourceAsStream(filename);
        Scanner s = new Scanner(is);

        while (s.hasNext()) {
            String str = s.next();
            String sortStr = sortString(str);
            if (!wordMap.containsKey(sortStr)) {
                wordMap.put(sortStr, new ArrayList<String>());
            }
            wordMap.get(sortStr).add(str);
        }
    }
	
	public boolean isWord(String word) {
		String sorted_word = sortString(word);
		if (wordMap.containsKey(sorted_word)){
			if(wordMap.get(sorted_word).contains(word)){
				return true;
			}
		}
		return false;
	}
	
	
    //When fed a string, this outputs the associated anagrams, if any.
    //If the word isn't there, it just outputs an empty string
    public List<String> getAnagram(String word) {
		word = word.toLowerCase();
        String sortStr = sortString(word);
        ArrayList<String> anagrams = new ArrayList<String>();
        if (wordMap.containsKey(sortStr)) {
            List<String> s = wordMap.get(sortStr);
            if (s.contains(word)|| jump == 0) {
                return s;
            }
            return anagrams;
        } else {
            return anagrams;
        }
    }

    //When fed a string, this outputs the associated anagrams, if any.
    //If the word isn't there, it just outputs an empty string


    //When fed an integer, this picks a random key from all the keys of that length and returns the associated anagrams
    public List<String> getRandagram(Integer num) {
        Set<String> keyset = wordMap.keySet();
        ArrayList<String> randCandidates = new ArrayList<>();
        if (num == 0) {

            return randCandidates;
        }
        List<String> stringList = new ArrayList<String>(keyset);

        for (int i = 0; i < keyset.size(); i++) {
            ArrayList<String> blank = (ArrayList<String>) wordMap.get(stringList.get(i));
            if (blank.size() == num) {
                randCandidates.add(stringList.get(i));
            }
        }
        int rNum;
        if (randCandidates.size() == 1) {
            rNum = ThreadLocalRandom.current().nextInt(0, randCandidates.size());
            int a = rNum;

        } else {
            rNum = ThreadLocalRandom.current().nextInt(0, randCandidates.size() - 1);
        }
        String rString = randCandidates.get(rNum);
        return wordMap.get(rString);
    }

	public List<String> getAllAnagrams(String text){
		List<String> combination = new ArrayList<String>();
		for (int i = 0; i < text.length(); i++) {
        // Record size as the list will change
        int combinationLength = combination.size();
			for (int j = 0; j < combinationLength; j++) {
				combination.add(text.charAt(i) + combination.get(j));
			}
				combination.add(Character.toString(text.charAt(i)));
		}
		
		for (int i = 0; i < combination.size(); i++){
			char[] chars = combination.get(i).toCharArray();
			Arrays.sort(chars);
			String sorted = new String(chars);
			combination.set(i,sorted);
		}
		
		jump = 0;
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < combination.size(); i++){
			String y = String.valueOf(getAnagram(combination.get(i)));
			y = y.replaceAll("\\[","");
			y = y.replaceAll("\\]", "");
			y = y.replaceAll("\\,", "");
			result.add(y);
		}
		
		String finalResult = String.valueOf(result);
		finalResult = finalResult.replaceAll("\\[","");
		finalResult = finalResult.replaceAll("\\]", "");
		finalResult = finalResult.replaceAll(",", "");
		finalResult = finalResult.replaceAll(" ,","");
		finalResult = finalResult.replaceAll(", ","");
		List<String> words = new ArrayList<String>(Arrays.asList(finalResult.split(" ")));
		Set<String> eliminateDuplicates = new HashSet<>();
		eliminateDuplicates.addAll(words);
		words.clear();
		words.addAll(eliminateDuplicates);
		Collections.sort(words);
		words.remove("");
		return words;
	}

    public boolean isAnagram(String s1, String s2){
        if (s1.length() != s2.length()) {
            return false;
        }
        String sc1 = sortString(s1);
        String sc2 = sortString(s2);
        return sc1.equals(sc2);
	}
	public String play_random(int len){
		ArrayList<String> all_words_with_length = new ArrayList<>();
		for (String key : wordMap.keySet()){
			if (key.length() == len){
				all_words_with_length.add(key);
			}
		}
		Random rand = new Random();
		String random_word = all_words_with_length.get(rand.nextInt(all_words_with_length.size()));
		wordMap.get(random_word).get(rand.nextInt(wordMap.get(random_word).size()));
		if (len <= 1){random_word = " ";}
		return random_word;
	}
	//Move database stuff into its own class
	public void insertIntoScoreboard(String word, int difficulty, int secondsRemaining, String url){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int score = (((difficulty * 10)-secondsRemaining)/difficulty);
        String insertStatement = "INSERT INTO Leaderboard(Word,Difficulty,Seconds Remaining, Score) VALUES(?,?,?,?)";


    }
}

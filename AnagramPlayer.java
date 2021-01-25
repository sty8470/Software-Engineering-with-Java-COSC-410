package edu.wofford.wordoff;

import java.util.*;
import java.io.*;

public class AnagramPlayer{
	private AnagramSearcher a;
	private String chosen_word;
	
	public AnagramPlayer(String filename, String word_or_int){
		a = new AnagramSearcher(filename);

		try{
			if ( word_or_int == ""|| word_or_int==null){
				throw new IllegalArgumentException("null");
			}
			int num = Integer.parseInt(word_or_int);
            if(num < 3){
                throw new ArgsLessThanTwo("You dun goofed");
            }
			chosen_word = a.play_random(num);
			play();
		} 
		catch(NumberFormatException e){
			chosen_word = word_or_int.toLowerCase();
			play();
		}
		catch(NullPointerException f){}
		catch(IllegalArgumentException b){}
		catch(ArgsLessThanTwo d){}
	}
	
	public void play(){
		
		Scanner input = new Scanner(System.in);
		List<String> alist = a.getAllAnagrams(chosen_word);
		alist.remove(chosen_word);

		Map<String, List<String>> words = new HashMap<String,List<String>>();
		String pre = "[len";
		String pos = "]";
		
		for(int i = 2; i <= chosen_word.length(); i++){
			for (int j = 0; j < alist.size(); j++){
				String k = Integer.toString(i);
				if (!words.containsKey(k)) {
					words.put(k,new ArrayList<String>());
				}
				if(alist.get(j).length()==i){
					words.get(k).add(alist.get(j));
				}
			}
		}
		System.out.println("The word is \"" + chosen_word + "\". ");
		
		System.out.print("There are ");
		
		for (int i = words.size()+1; i > 1; i--){
			String k = Integer.toString(i);
			System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
		}
		System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
		
		
		while(alist.size()>0){
			if(alist.size()==0){
				System.out.println("There are 0 anagrams remaining.");
			}
			String guess = input.nextLine();
			guess = guess.toLowerCase();
			if(alist.contains(guess)){
				alist.remove(guess);
				if (alist.size()==0){
					System.out.println("There are 0 anagrams remaining.");
				}
				else{
					String s = Integer.toString(guess.length());
					words.get(s).remove(guess);
					System.out.println();
					System.out.print("There are ");
					for (int i = words.size()+1; i > 1; i--){
					String k = Integer.toString(i);
					System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
					}
					System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
				}
			}
			else{
				System.out.println();
				System.out.print("There are ");
				for (int i = words.size()+1; i > 1; i--){
				String k = Integer.toString(i);
				System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
				}
				System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
			}
		}
	}

}
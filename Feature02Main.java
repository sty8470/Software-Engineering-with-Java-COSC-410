package edu.wofford.wordoff;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Feature02Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        try{int i = Integer.parseInt(args[0]);
            if(i <2){
                throw new ArgsLessThanTwo("You dun goofed");
            }
        AnagramSearcher a = new AnagramSearcher("/allwords.txt");
        ArrayList<String> answerKey = (ArrayList<String>) a.getRandagram(i);
        String wordKey = answerKey.get(0);
        char[] strChar = wordKey.toCharArray();
        Arrays.sort(strChar);
        int r = ThreadLocalRandom.current().nextInt(0, answerKey.size());

        System.out.print("The word is" );
        System.out.print("\"");
        System.out.print(answerKey.get(r));
        answerKey.remove(r);
        System.out.print("\"");
        System.out.print("\n");
        System.out.print("There are ");
        System.out.print(answerKey.size());
        System.out.print(" anagrams remaining:");
        while(answerKey.size()>0){
            String guess = keyboard.nextLine();
            String guessLower = guess.toLowerCase();
            if(answerKey.contains(guessLower)){
                answerKey.remove(guessLower);
                answerKey.trimToSize();
            }
            if(answerKey.size() != 0){
                System.out.print("There are ");
                System.out.print(answerKey.size());
                System.out.print(" anagrams remaining:");
            }
            if(answerKey.size() == 0){
                System.out.print("There are ");
                System.out.print(answerKey.size());
                System.out.print(" anagrams remaining.");
            }

        }
        //System.out.println("There are 0 anagrams remaining");
        //use a try-catch for the unique exit conditions
    }
    catch (NumberFormatException e){}
    catch (NullPointerException a){}
    catch(IllegalArgumentException b){}
    catch(ArrayIndexOutOfBoundsException c){}
    catch (ArgsLessThanTwo d){}
    //catch(){}
    }
}

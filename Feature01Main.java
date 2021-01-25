package edu.wofford.wordoff;
//import com.sun.xml.internal.fastinfoset.util.StringArray;
import java.util.Scanner;

import java.util.*;
import java.io.*;

public class Feature01Main {
    public  static void main(String[] args) {
        AnagramSearcher a = new AnagramSearcher("/allwords.txt");

                try {
                    int i = Integer.parseInt(args[0]);
                    String x = String.valueOf(a.getRandagram(i));
                    x = x.replaceAll("\\[","");
                    x = x.replaceAll("\\]", "");
                    x = x.replaceAll("\\,", "");
                    System.out.println(x);
                }
                catch(NumberFormatException e){
                    String s = args[0];
                    String x = String.valueOf(a.getAnagram(s));
                    x = x.replaceAll("\\[","");
                    x = x.replaceAll("\\]", "");
                    x = x.replaceAll("\\,", "");
                    System.out.println(x);
        }
    }
}


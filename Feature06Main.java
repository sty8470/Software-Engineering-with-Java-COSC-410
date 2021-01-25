package edu.wofford.wordoff;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.border.Border;


public class Feature06Main{

    public static void main(String[] args) {
        AnagramSearcher a = new AnagramSearcher("/commonwords.txt");
        try{int num = Integer.parseInt(args[0]);
            if(num < 1){
                throw new ArgsLessThanTwo("You dun goofed");
            }
            String x = String.valueOf(a.getRandagram(num+1));
            x = x.replaceAll("\\[","");
            x = x.replaceAll("\\]", "");
            x = x.replaceAll("\\,", "");
            java.util.List<String> words = new ArrayList<String>(Arrays.asList(x.split(" ")));
            GuiMain c = new GuiMain(6,words.get(0),num);
        }
        catch(IllegalArgumentException b){}
        catch (ArgsLessThanTwo d){}
    }
}
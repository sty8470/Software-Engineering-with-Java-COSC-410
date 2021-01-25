package edu.wofford.wordoff;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.border.Border;


public class GuiMain extends JFrame implements ActionListener {
    String[] anagrams_without_word;
    JButton button;
    JLabel[] label;
    String guess_word;
    JTextField guess;
    Border border;
    int count;
    JLabel target;
    JLabel timer;
    int number_of_anagrams;
    int timer_count;
    javax.swing.Timer t;
    int min;
    int sec;
    String[] clock = new String[2];

    public void actionPerformed(ActionEvent event) {

        Object src = event.getSource();
        guess_word = guess.getText();
        if (src == button){
            for (int i = 0; i< anagrams_without_word.length;i++){
                if (guess_word.equals(anagrams_without_word[i])){
                    label[i].setText(guess_word);
                    count ++;
                }
            }
            guess.setText("");
            if (count == anagrams_without_word.length){
                border = BorderFactory.createLineBorder(Color.GREEN, 5);
                target.setBorder(border);
                guess.setEnabled(false);
                button.setEnabled(false);
            }
        }
    }
    public GuiMain(int feature, String chosen_word, int number_of_anagrams) {
        AnagramSearcher b = new AnagramSearcher("/commonwords.txt");
        String y = String.valueOf(b.getAnagram(chosen_word));
        y = y.replaceAll("\\[","");
        y = y.replaceAll("\\]", "");
        y = y.replaceAll("\\,", "");
        y = y.replaceAll(chosen_word,"");
        y = y.replaceAll("\\s+"," ");
        java.util.List<String> lista = new ArrayList<String>(Arrays.asList(y.split(" ")));

        String[] x = lista.toArray(new String[0]);
        anagrams_without_word = Arrays.copyOfRange(x, 1, x.length);

        JFrame frame = new JFrame();

        button = new JButton("Click");
        button.setName("button");
        button.addActionListener(this);
        count = 0;
        timer_count = number_of_anagrams*10;

        JPanel bottom_panel = new JPanel(new FlowLayout());
        JPanel top_panel = new JPanel(new FlowLayout());

        target = new JLabel(chosen_word,JLabel.CENTER);
        target.setName("target");
        border = BorderFactory.createLineBorder(Color.RED, 5);
        target.setBorder(border);
        JPanel main_panel = new JPanel(new BorderLayout());
        if (feature !=5) {
            JLabel timer = new JLabel(Integer.toString(timer_count));
            timer.setName("timer");
            JLabel time_label = new JLabel("Time Remaining ");

            ActionListener time_task = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    timer_count = timer_count - 1;
                    min = timer_count / 60;
                    sec = timer_count - min * 60;
                    String clock = Integer.toString(min) + ":" + Integer.toString(sec);
                    timer.setText(clock);
                    if (timer_count == 0 || count == number_of_anagrams) {
                        guess.setEnabled(false);
                        button.setEnabled(false);
                        t.stop();
                    }
                }
            };
            t = new javax.swing.Timer(1000, time_task);
            t.start();

            top_panel.add(time_label);
            top_panel.add(timer);
            main_panel.add(top_panel, BorderLayout.PAGE_START);
        }
        //JPanel main_panel = new JPanel(new BorderLayout());
        JPanel word_panel = new JPanel(new GridLayout(number_of_anagrams+1,1));
        word_panel.add(target);

        guess = new JTextField(10);
        guess.setName("guess");
        guess_word = guess.getText();
        bottom_panel.add(guess);
        bottom_panel.add(button);

        label = new JLabel[number_of_anagrams];
        for (int i = 0; i < number_of_anagrams; i++) {
            label[i] = new JLabel("anagram" + i,JLabel.CENTER);
            word_panel.add(label[i]);
        }

        //main_panel.add(top_panel, BorderLayout.PAGE_START);
        main_panel.add(word_panel);
        main_panel.add(bottom_panel, BorderLayout.PAGE_END);

        frame.setTitle("WordOff");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(main_panel);
        frame.pack();
        frame.setVisible(true);
    }
}
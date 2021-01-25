package edu.wofford.wordoff;

public class ArgsLessThanTwo extends Exception {
    public ArgsLessThanTwo(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public ArgsLessThanTwo(String you_dun_goofed) {
    }
}

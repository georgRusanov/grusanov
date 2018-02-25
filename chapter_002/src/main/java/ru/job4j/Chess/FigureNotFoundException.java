package ru.job4j.tracker;

public class FigureNotFoundException extends RuntimeException{

    public FigureNotFoundException(String msg){
        super(msg);
    }
}

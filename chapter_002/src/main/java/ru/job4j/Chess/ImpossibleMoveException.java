package ru.job4j.Chess;

public class ImpossibleMoveException extends RuntimeException{

    public ImpossibleMoveException(String msg){
        super(msg);
    }
}

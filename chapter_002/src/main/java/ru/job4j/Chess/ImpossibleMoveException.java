package ru.job4j.Chess;

public class ImposibleMoveException extends RuntimeException{

    public ImposibleMoveException(String msg){
        super(msg);
    }
}

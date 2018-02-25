package ru.job4j.tracker;

public class OccupiedWayException extends RuntimeException{

    public OccupiedWayException(String msg){
        super(msg);
    }
}

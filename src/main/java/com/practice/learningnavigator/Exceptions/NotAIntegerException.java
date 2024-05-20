package com.practice.learningnavigator.Exceptions;

public class NotAIntegerException extends RuntimeException{

    public NotAIntegerException(){
        super();
    }

    public NotAIntegerException(String msg){
        super(msg);
    }
}

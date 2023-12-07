package com.awsm.api.randomizerapp.exception;

public class DuplicateCandidateException extends Exception{

    public DuplicateCandidateException(){
        super();
    }

    public  DuplicateCandidateException(String msg){
        super(msg);
    }

    public DuplicateCandidateException(String msg, Throwable cause){
        super(msg, cause);
    }
}

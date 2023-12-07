package com.awsm.api.randomizerapp.exception;

public class EmptyRegistryException extends Exception{

    public EmptyRegistryException(){
        super();
    }

    public  EmptyRegistryException(String msg){
        super(msg);
    }

    public EmptyRegistryException(String msg, Throwable cause){
        super(msg, cause);
    }
}

package com.SpringbootTest.inventoryservice.Exception;

public class NotFoundException extends  RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}

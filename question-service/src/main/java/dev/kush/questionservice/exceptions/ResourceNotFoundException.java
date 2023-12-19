package dev.kush.questionservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("resource not found in server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
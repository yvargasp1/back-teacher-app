package com.ucapp.teachermanager.exception;

public class UserNotFoundException extends RuntimeException {
     public UserNotFoundException(String message){
         super(message);

     }
}

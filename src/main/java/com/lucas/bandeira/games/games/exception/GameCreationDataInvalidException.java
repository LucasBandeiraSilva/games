package com.lucas.bandeira.games.games.exception;


public class GameCreationDataInvalidException extends RuntimeException{

    public GameCreationDataInvalidException(String msg){
        super(msg);
    }
    public GameCreationDataInvalidException(){
        super("Invalid data, please verify the request fields");
    }


}

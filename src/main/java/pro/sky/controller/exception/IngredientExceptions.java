package pro.sky.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientExceptions extends RuntimeException{
    public IngredientExceptions(){
        super("this ingredient is already available");
    }
    public IngredientExceptions(String message){
        super(message);
    }
    public IngredientExceptions(String message,Throwable cause){
        super(message,cause);
    }
    public IngredientExceptions(Throwable cause){
        super(cause);
    }
    protected IngredientExceptions(String message,Throwable cause,boolean activateSuppression,
                                   boolean writableStackTrace){
        super(message,cause,activateSuppression,writableStackTrace);}

}

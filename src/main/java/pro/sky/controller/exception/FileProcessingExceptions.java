package pro.sky.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileProcessingExceptions extends RuntimeException{
    public FileProcessingExceptions(){super("the problem with reading the file");

    }
    public FileProcessingExceptions(String message){
        super(message);
    }
    public FileProcessingExceptions(String message,Throwable cause){
        super(message, cause);
    }
    public FileProcessingExceptions(Throwable cause){
        super(cause);
    }
    protected FileProcessingExceptions(String message,Throwable cause,boolean activateSuppression,
                                       boolean writableStackTrace){
        super(message,cause,activateSuppression,writableStackTrace);
    }
}

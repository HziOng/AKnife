package org.aknife.user.exception;

/**
 * @ClassName UserException
 * @Author HeZiLong
 * @Data 2021/1/12 17:26
 */
public class UserException extends RuntimeException{

    public UserException(){}

    public UserException(String message){
        super(message);
    }
}

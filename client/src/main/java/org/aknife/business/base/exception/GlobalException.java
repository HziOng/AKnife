package org.aknife.business.base.exception;

/**
 * @ClassName GlobalException
 * @Author HeZiLong
 * @Data 2021/1/14 18:41
 */
public class GlobalException extends RuntimeException{
    
    public GlobalException(){}
    
    public GlobalException(String msg){
        super(msg);
    }
}

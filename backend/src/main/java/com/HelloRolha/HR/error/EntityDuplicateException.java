package com.HelloRolha.HR.error;


public class EntityDuplicateException extends BusinessException{

    public EntityDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}

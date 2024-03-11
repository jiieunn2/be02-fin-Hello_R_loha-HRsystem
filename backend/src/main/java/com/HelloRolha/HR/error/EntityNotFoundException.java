package com.HelloRolha.HR.error;



public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}

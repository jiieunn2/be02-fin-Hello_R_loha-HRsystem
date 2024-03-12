package com.HelloRolha.HR.error;

public class ApproveNotFoundException extends EntityNotFoundException{

    public ApproveNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static ApproveNotFoundException forIdx(Integer idx) {
        return new ApproveNotFoundException(ErrorCode.USER_NOT_EXISTS, String.format("ApproveIdx [ %s ] is not exists.", idx));
    }
}

package com.HelloRolha.HR.error;

public class DepartmentNotFoundException extends EntityNotFoundException{

    public DepartmentNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static DepartmentNotFoundException forIdx(Integer idx) {
        return new DepartmentNotFoundException(ErrorCode.USER_NOT_EXISTS, String.format("DepartmentIdx [ %s ] is not exists.", idx));
    }
}

package com.HelloRolha.HR.error.exception;

import com.HelloRolha.HR.error.BusinessException;
import com.HelloRolha.HR.error.ErrorCode;
import com.HelloRolha.HR.error.UserDuplicateException;

public class AlreadyCommuteException extends BusinessException {
    public AlreadyCommuteException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    // 이미 출근
    public static AlreadyCommuteException alreadyCommuteException() {
        return new AlreadyCommuteException(ErrorCode.AlreadyCommute, "하루에 2번 출근은 안됩니다.");
    }
    
}

package com.example.springboot.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 8272927706491415506L;

    private int exceptionType = 0;

    public static final int EXCEPTION_VALIDATION = 99;
    public static final int EXCEPTION_TRANSACTION_DECLINED = 101;

    private String messageCode = null;
    private Object[] messageSrcParams;

    public ServiceException() {
        super();
    }

    public ServiceException(String messageCode) {
        super();
        this.messageCode = messageCode;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(int exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }

    public ServiceException(int exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public ServiceException(int exceptionType, String message, String messageCode) {
        super(message);
        this.messageCode = messageCode;
        this.exceptionType = exceptionType;
    }


    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Object[] getMessageSrcParams() {
        return messageSrcParams;
    }

    public void setMessageSrcParams(Object[] messageSrcParams) {
        this.messageSrcParams = messageSrcParams;
    }
}

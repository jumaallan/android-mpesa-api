package com.androidstudy.daraja.callback;

public class DarajaException extends Exception{

    public DarajaException() {
        super();
    }

    public DarajaException(String message) {
        super(message);
    }

    public DarajaException(String message, Throwable cause) {
        super(message, cause);
    }

    public DarajaException(Throwable cause) {
        super(cause);
    }
}

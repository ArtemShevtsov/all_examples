package com.company.exception;

/**
 * Created by Artem_Shevtsov on 5/12/2016.
 */
public class MyException extends Exception {
    @Override
    public String getMessage() {
        return "My Exception Throwed";
    }
}

package com.xmlprojekat.exception;

public class NonexistentFileException extends Exception {
    public NonexistentFileException() {
        super("Chosen file does not exist.");
    }

    public NonexistentFileException(String msg) {
        super(msg);
    }
}

package com.library.model;

public interface Readable {
    String getFormat();

    default void printBasicInfo() {
        System.out.println("Format: " + getFormat());
    }
}

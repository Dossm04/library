package com.library.service.interfaces;

public interface Validatable<T> {

    void validate();

    default boolean isValid() {
        try {
            validate();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    static void requireText(String v, String message) {
        if (v == null || v.isBlank()) {
            throw new RuntimeException(message);
        }
    }

    static void requirePositiveNumber(double v, String message) {
        if (v <= 0) {
            throw new RuntimeException(message);
        }
    }
}

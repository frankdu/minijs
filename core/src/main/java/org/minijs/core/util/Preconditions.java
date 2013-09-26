package org.minijs.core.util;

public class Preconditions {

    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public static void checkState(boolean state) {
        checkState(state, null);
    }

    public static void checkState(boolean state, String message) {
        if (!state) {
            if (message != null) {
                throw new IllegalStateException(message);
            } else {
                throw new IllegalStateException();
            }
        }
    }
}

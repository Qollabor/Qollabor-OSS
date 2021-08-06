package org.qollabor.cmmn.instance.sentry;

public interface StandardEvent<T extends Enum> {
    T getTransition();
}

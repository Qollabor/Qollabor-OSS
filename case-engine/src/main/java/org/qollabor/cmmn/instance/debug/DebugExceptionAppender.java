package org.qollabor.cmmn.instance.debug;

@FunctionalInterface
public interface DebugExceptionAppender {
    Throwable exceptionInfo();
}

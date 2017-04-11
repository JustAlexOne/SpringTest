package com.justalex.spring.loggers;

import com.justalex.spring.beans.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}

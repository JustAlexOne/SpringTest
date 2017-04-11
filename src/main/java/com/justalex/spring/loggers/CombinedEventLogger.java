package com.justalex.spring.loggers;

import com.justalex.spring.beans.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {

    private List<EventLogger> loggers;

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }
}

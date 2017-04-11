package com.justalex.spring;

import com.justalex.spring.enums.EventType;
import com.justalex.spring.loggers.EventLogger;
import com.justalex.spring.beans.Client;
import com.justalex.spring.beans.Event;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    Client client;
    EventLogger defaultLogger;
    Map<EventType, EventLogger> loggers;

    private static ConfigurableApplicationContext ctx;

    private void logEvent(EventType type, String msg) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        logger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        app.client = (Client) ctx.getBean("client");

//        app.defaultLogger = (EventLogger) ctx.getBean("cacheFileEventLogger");

        app.logEvent(EventType.INFO, "Log info 1");
        app.logEvent(EventType.ERROR, "Log error 1");
        app.logEvent(EventType.INFO, "Log info 2");
        app.logEvent(EventType.ERROR, "Log error 2");
        app.logEvent(null, "Log null 1");

        ctx.close();
    }

}

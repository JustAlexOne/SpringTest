package com.justalex.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    Client client;
    EventLogger eventLogger;

    private static ConfigurableApplicationContext ctx;

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        Event event = (Event) ctx.getBean("event");
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        app.client = (Client) ctx.getBean("client");
        app.eventLogger = (EventLogger) ctx.getBean("cacheFileEventLogger");

        app.logEvent("Some event for user 1");
        app.logEvent("Some event for user 2");
        app.logEvent("Some event for user 3");
        app.logEvent("Some event for user 4");

        ctx.close();
    }

}

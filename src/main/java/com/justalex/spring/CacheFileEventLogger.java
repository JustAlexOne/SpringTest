package com.justalex.spring;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;



    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        try {
            FileUtils.writeLines(file, cache.stream().map(Event::getMsg).collect(Collectors.toList()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    public void init() {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new IllegalStateException("Can't write to file [" + file.getPath() + "]");
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }
}

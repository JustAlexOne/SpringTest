package com.justalex.spring;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    public String fileName;
    public File file;

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(fileName), event.getMsg() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        this.file = new File(fileName);
        if (!file.canWrite()) {
            throw new IllegalStateException("Can't write to file [" + file.getPath() + "]");
        }
    }

}

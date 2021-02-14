package com.fynd.task.consumer;

import com.fynd.task.model.Message;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class Consumer {

    @Autowired
    private Environment environment;

    @KafkaListener(groupId = "${kafka.config.groupId}", topics = "#{'${kafka.config.topics}'.split(',')}")
    public void consume(Message message) throws IOException {

        String fileSize = environment.getProperty(message.getEventType() + ".file.size");
        String timeLimit = environment.getProperty(message.getEventType() + ".file.time-limit");
        String eventType = message.getEventType();
        boolean isAppend = true;
        long timestamp = System.currentTimeMillis() / (1000 * 60 * Integer.parseInt(timeLimit));
        File theDir = new File("../files");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        File file = new File("../files/" + eventType + "_"+timestamp+".txt");
        if (file.exists()) {
            long fileSizeInMB = getFileSize(file);
            if (fileSizeInMB > Integer.parseInt(fileSize)) {
                isAppend = false;
            }
        }
        try (FileWriter fileWriter = new FileWriter(file, isAppend);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter);) {
            Gson gson = new Gson();
            String json = gson.toJson(message);
            printWriter.print(json);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private long getFileSize(File file) {
        // Get length of file in bytes
        long fileSizeInBytes = file.length();
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        return fileSizeInKB / 1024;
    }
}

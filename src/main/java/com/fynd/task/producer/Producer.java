package com.fynd.task.producer;

import com.fynd.task.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void produce(Message message) {
        this.kafkaTemplate.send(message.getEventType(), message);
    }

}

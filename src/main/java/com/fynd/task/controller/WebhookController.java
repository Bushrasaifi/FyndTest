package com.fynd.task.controller;

import com.fynd.task.model.Message;
import com.fynd.task.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook/")
public class WebhookController {

    @Autowired
    private Producer producer;

    @PostMapping(value = "publish")
    public void sendMessage(@RequestBody Message message){
        producer.produce(message);
    }
}

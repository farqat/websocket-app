package com.example.websocketapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepo repo;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageService(MessageRepo repo, SimpMessagingTemplate messagingTemplate) {
        this.repo = repo;
        this.messagingTemplate = messagingTemplate;
    }

    public List<Message> getMessages() {
        List<Message> all = repo.findAll();
        messagingTemplate.convertAndSend("/topic/hi", all);
        return all;
    }

    public Message setMessage(Message message) {
        return repo.save(message);
    }

    @Scheduled(fixedRate = 10000)
    private void sendMessage(){
        Message message = new Message();
        message.setMessage("Message from Server");
        message.setAuthor("Server");
        messagingTemplate.convertAndSend("/topic/message", message);
        System.out.println(message);
    }
}

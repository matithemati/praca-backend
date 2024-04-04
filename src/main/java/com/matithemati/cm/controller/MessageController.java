package com.matithemati.cm.controller;

import com.matithemati.cm.model.Message;
import com.matithemati.cm.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    @PostMapping("/create")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageRepository.save(message));
    }
}

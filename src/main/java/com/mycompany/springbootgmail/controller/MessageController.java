package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.Message;
import com.mycompany.springbootgmail.dto.ModifyMessageDto;
import com.mycompany.springbootgmail.service.GmailMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final GmailMessageService gmailMessageService;

    public MessageController(GmailMessageService gmailMessageService) {
        this.gmailMessageService = gmailMessageService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Message> getMessages(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "labelIds", required = false) List<String> labelIds) throws IOException {
        return gmailMessageService.getMessages(query, labelIds);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{messageId}")
    public Message getMessage(@PathVariable String messageId) throws IOException {
        return gmailMessageService.getMessage(messageId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{messageId}/labels")
    public List<String> modifyMessageLabels(@PathVariable String messageId,
                                            @RequestBody ModifyMessageDto modifyMessageDto) throws IOException {
        return gmailMessageService.modifyMessageLabels(messageId, modifyMessageDto.getLabelsToAdd(), modifyMessageDto.getLabelsToRemove());
    }

}

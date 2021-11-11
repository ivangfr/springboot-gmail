package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.model.Message;

import java.util.List;

public interface GmailMessageService {

    Message getMessage(String messageId);

    List<Message> getMessages(String query, List<String> labelIds);

    List<String> modifyMessageLabels(String messageId, List<String> labelsToAdd, List<String> labelsToRemove);
}

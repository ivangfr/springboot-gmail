package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.util.List;

public interface GmailMessageService {

    Message getMessage(String messageId) throws IOException;

    List<Message> getMessages(String query, List<String> labelIds) throws IOException;

    List<String> modifyMessageLabels(String messageId, List<String> labelsToAdd, List<String> labelsToRemove) throws IOException;

}

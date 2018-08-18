package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ModifyMessageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GmailMessageServiceImpl implements GmailMessageService {

    private static final String USER_ID = "me";

    private final Gmail gmail;

    public GmailMessageServiceImpl(Gmail gmail) {
        this.gmail = gmail;
    }

    @Override
    public Message getMessage(String messageId) throws IOException {
        return gmail.users().messages().get(USER_ID, messageId).execute();
    }

    @Override
    public List<Message> getMessages(String query, List<String> labelIds) throws IOException {
        ListMessagesResponse response = gmail.users().messages().list(USER_ID)
                .setQ(query).setLabelIds(labelIds).execute();

        List<Message> messages = new ArrayList<>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = gmail.users().messages().list(USER_ID).setQ(query)
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        return messages;
    }

    @Override
    public List<String> modifyMessageLabels(String messageId, List<String> labelsToAdd, List<String> labelsToRemove) throws IOException {
        ModifyMessageRequest mods = new ModifyMessageRequest();
        if (!StringUtils.isEmpty(labelsToAdd)) {
            mods.setAddLabelIds(labelsToAdd);
        }
        if (!StringUtils.isEmpty(labelsToRemove)) {
            mods.setRemoveLabelIds(labelsToRemove);
        }

        Message message = gmail.users().messages().modify(USER_ID, messageId, mods).execute();
        return message.getLabelIds();
    }

}

package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.ModifyMessageRequest;
import com.mycompany.springbootgmail.exception.GmailMessageServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.springbootgmail.config.GmailConfig.USER_ID_ME;

@RequiredArgsConstructor
@Service
public class GmailMessageServiceImpl implements GmailMessageService {

    private final Gmail gmail;

    @Override
    public Message getMessage(String messageId) {
        try {
            return gmail.users().messages().get(USER_ID_ME, messageId).execute();
        } catch (IOException e) {
            throw new GmailMessageServiceException(e);
        }
    }

    @Override
    public List<Message> getMessages(String query, List<String> labelIds) {
        List<Message> messages = new ArrayList<>();
        try {
            ListMessagesResponse response = gmail.users().messages().list(USER_ID_ME)
                    .setQ(query).setLabelIds(labelIds).execute();

            while (response.getMessages() != null) {
                messages.addAll(response.getMessages());
                if (response.getNextPageToken() != null) {
                    String pageToken = response.getNextPageToken();
                    response = gmail.users().messages().list(USER_ID_ME).setQ(query)
                            .setPageToken(pageToken).execute();
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            throw new GmailMessageServiceException(e);
        }
        return messages;
    }

    @Override
    public List<String> modifyMessageLabels(String messageId, List<String> labelsToAdd, List<String> labelsToRemove) {
        ModifyMessageRequest mods = new ModifyMessageRequest();
        if (!StringUtils.isEmpty(labelsToAdd)) {
            mods.setAddLabelIds(labelsToAdd);
        }
        if (!StringUtils.isEmpty(labelsToRemove)) {
            mods.setRemoveLabelIds(labelsToRemove);
        }

        try {
            return gmail.users().messages().modify(USER_ID_ME, messageId, mods).execute().getLabelIds();
        } catch (IOException e) {
            throw new GmailMessageServiceException(e);
        }
    }

}

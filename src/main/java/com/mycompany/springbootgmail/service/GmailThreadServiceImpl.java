package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;
import com.mycompany.springbootgmail.exception.GmailThreadServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.springbootgmail.config.GmailConfig.USER_ID_ME;

@RequiredArgsConstructor
@Service
public class GmailThreadServiceImpl implements GmailThreadService {

    private final Gmail gmail;

    @Override
    public Thread getThread(String threadId) {
        try {
            return gmail.users().threads().get(USER_ID_ME, threadId).execute();
        } catch (IOException e) {
            throw new GmailThreadServiceException(e);
        }
    }

    @Override
    public List<Thread> getThreads(String query, List<String> labelIds) {
        List<Thread> threads = new ArrayList<>();
        try {
            ListThreadsResponse response = gmail.users().threads().list(USER_ID_ME)
                    .setQ(query).setLabelIds(labelIds).execute();

            while (response.getThreads() != null) {
                threads.addAll(response.getThreads());
                if (response.getNextPageToken() != null) {
                    String pageToken = response.getNextPageToken();
                    response = gmail.users().threads().list(USER_ID_ME).setQ(query).setPageToken(pageToken).execute();
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            throw new GmailThreadServiceException(e);
        }
        return threads;
    }
}

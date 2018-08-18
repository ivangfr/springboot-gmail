package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GmailThreadServiceImpl implements GmailThreadService {

    private static final String USER_ID = "me";

    private final Gmail gmail;

    public GmailThreadServiceImpl(Gmail gmail) {
        this.gmail = gmail;
    }

    @Override
    public Thread getThread(String threadId) throws IOException {
        return gmail.users().threads().get(USER_ID, threadId).execute();
    }

    @Override
    public List<Thread> getThreads(String query, List<String> labelIds) throws IOException {
        ListThreadsResponse response = gmail.users().threads().list(USER_ID)
                .setQ(query).setLabelIds(labelIds).execute();

        List<Thread> threads = new ArrayList<>();
        while (response.getThreads() != null) {
            threads.addAll(response.getThreads());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = gmail.users().threads().list(USER_ID).setQ(query).setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        return threads;
    }

}

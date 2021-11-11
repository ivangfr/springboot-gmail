package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.model.Thread;

import java.util.List;

public interface GmailThreadService {

    Thread getThread(String threadId);

    List<Thread> getThreads(String query, List<String> labelIds);
}

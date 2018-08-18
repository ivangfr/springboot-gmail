package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.model.Thread;

import java.io.IOException;
import java.util.List;

public interface GmailThreadService {

    Thread getThread(String threadId) throws IOException;

    List<Thread> getThreads(String query, List<String> labelIds) throws IOException;

}

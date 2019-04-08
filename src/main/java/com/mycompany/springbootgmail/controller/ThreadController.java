package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.Thread;
import com.mycompany.springbootgmail.service.GmailThreadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/threads")
public class ThreadController {

    private final GmailThreadService gmailThreadService;

    public ThreadController(GmailThreadService gmailThreadService) {
        this.gmailThreadService = gmailThreadService;
    }

    @GetMapping
    public List<Thread> getThreads(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "labelIds", required = false) List<String> labelIds) throws IOException {
        return gmailThreadService.getThreads(query, labelIds);
    }

    @GetMapping("/{threadId}")
    public Thread getThread(@PathVariable String threadId) throws IOException {
        return gmailThreadService.getThread(threadId);
    }

}

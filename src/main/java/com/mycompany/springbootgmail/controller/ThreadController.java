package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.Thread;
import com.mycompany.springbootgmail.service.GmailThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/threads")
public class ThreadController {

    private final GmailThreadService gmailThreadService;

    @GetMapping
    public List<Thread> getThreads(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "labelIds", required = false) List<String> labelIds) {
        return gmailThreadService.getThreads(query, labelIds);
    }

    @GetMapping("/{threadId}")
    public Thread getThread(@PathVariable String threadId) {
        return gmailThreadService.getThread(threadId);
    }

}

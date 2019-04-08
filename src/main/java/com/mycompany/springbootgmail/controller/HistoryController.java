package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.History;
import com.mycompany.springbootgmail.service.GmailHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v1/histories")
public class HistoryController {

    private final GmailHistoryService gmailHistoryService;

    public HistoryController(GmailHistoryService gmailHistoryService) {
        this.gmailHistoryService = gmailHistoryService;
    }

    @GetMapping("/{startHistoryId}")
    public List<History> getHistory(@PathVariable BigInteger startHistoryId) throws IOException {
        return gmailHistoryService.getHistory(startHistoryId);
    }

}

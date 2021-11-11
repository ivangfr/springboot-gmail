package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.History;
import com.mycompany.springbootgmail.service.GmailHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/histories")
public class HistoryController {

    private final GmailHistoryService gmailHistoryService;

    @GetMapping("/{startHistoryId}")
    public List<History> getHistory(@PathVariable BigInteger startHistoryId) {
        return gmailHistoryService.getHistory(startHistoryId);
    }
}

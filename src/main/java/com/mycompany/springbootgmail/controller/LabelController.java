package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.Label;
import com.mycompany.springbootgmail.service.GmailLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/labels")
public class LabelController {

    private final GmailLabelService gmailLabelService;

    public LabelController(GmailLabelService gmailLabelService) {
        this.gmailLabelService = gmailLabelService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Label> getLabels() throws IOException {
        return gmailLabelService.getLabels();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{labelName}")
    public Label getLabelId(@PathVariable String labelName) throws IOException {
        return gmailLabelService.getLabel(labelName);
    }

}

package com.mycompany.springbootgmail.controller;

import com.google.api.services.gmail.model.Label;
import com.mycompany.springbootgmail.service.GmailLabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final GmailLabelService gmailLabelService;

    @GetMapping
    public List<Label> getLabels() {
        return gmailLabelService.getLabels();
    }

    @GetMapping("/{labelName}")
    public Label getLabelId(@PathVariable String labelName) {
        return gmailLabelService.getLabel(labelName);
    }
}

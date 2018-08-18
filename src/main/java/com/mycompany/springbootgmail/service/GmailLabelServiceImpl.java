package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GmailLabelServiceImpl implements GmailLabelService {

    private static final String USER_ID = "me";

    private final Gmail gmail;

    public GmailLabelServiceImpl(Gmail gmail) {
        this.gmail = gmail;
    }

    @Override
    public List<Label> getLabels() throws IOException {
        return gmail.users().labels().list(USER_ID).execute().getLabels();
    }

    @Override
    public Label getLabel(String labelName) throws IOException {
        List<Label> labels = gmail.users().labels().list(USER_ID).execute().getLabels();

        return labels.stream()
                .filter(l -> l.getName().equalsIgnoreCase(labelName))
                .findFirst()
                .orElse(null);
    }

}

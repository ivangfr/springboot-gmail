package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.mycompany.springbootgmail.exception.GmailLabelServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.mycompany.springbootgmail.config.GmailConfig.USER_ID_ME;

@RequiredArgsConstructor
@Service
public class GmailLabelServiceImpl implements GmailLabelService {

    private final Gmail gmail;

    @Override
    public List<Label> getLabels() {
        try {
            return gmail.users().labels().list(USER_ID_ME).execute().getLabels();
        } catch (IOException e) {
            throw new GmailLabelServiceException(e);
        }
    }

    @Override
    public Label getLabel(String labelName) {
        try {
            return gmail.users().labels().list(USER_ID_ME).execute().getLabels()
                    .stream()
                    .filter(l -> l.getName().equalsIgnoreCase(labelName))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            throw new GmailLabelServiceException(e);
        }
    }

}

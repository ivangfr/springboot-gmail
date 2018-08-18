package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.History;
import com.google.api.services.gmail.model.ListHistoryResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class GmailHistoryServiceImpl implements GmailHistoryService {

    private static final String USER_ID = "me";

    private final Gmail gmail;

    public GmailHistoryServiceImpl(Gmail gmail) {
        this.gmail = gmail;
    }

    @Override
    public List<History> getHistory(BigInteger startHistoryId) throws IOException {
        List<History> histories = new ArrayList<>();
        ListHistoryResponse response = gmail.users().history().list(USER_ID).setStartHistoryId(startHistoryId).execute();

        while (response.getHistory() != null) {
            histories.addAll(response.getHistory());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = gmail.users().history().list(USER_ID).setPageToken(pageToken)
                        .setStartHistoryId(startHistoryId).execute();
            } else {
                break;
            }
        }

        return histories;
    }

}

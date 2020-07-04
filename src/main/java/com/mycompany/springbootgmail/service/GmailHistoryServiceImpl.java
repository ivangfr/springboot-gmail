package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.History;
import com.google.api.services.gmail.model.ListHistoryResponse;
import com.mycompany.springbootgmail.exception.GmailHistoryServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.springbootgmail.config.GmailConfig.USER_ID_ME;

@RequiredArgsConstructor
@Service
public class GmailHistoryServiceImpl implements GmailHistoryService {

    private final Gmail gmail;

    @Override
    public List<History> getHistory(BigInteger startHistoryId) {
        List<History> histories = new ArrayList<>();
        try {
            ListHistoryResponse response = gmail.users().history().list(USER_ID_ME).setStartHistoryId(startHistoryId).execute();

            while (response.getHistory() != null) {
                histories.addAll(response.getHistory());
                if (response.getNextPageToken() != null) {
                    String pageToken = response.getNextPageToken();
                    response = gmail.users().history().list(USER_ID_ME).setPageToken(pageToken)
                            .setStartHistoryId(startHistoryId).execute();
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            throw new GmailHistoryServiceException(e);
        }
        return histories;
    }

}

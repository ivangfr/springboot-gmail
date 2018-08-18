package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.model.History;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public interface GmailHistoryService {

    List<History> getHistory(BigInteger startHistoryId) throws IOException;

}

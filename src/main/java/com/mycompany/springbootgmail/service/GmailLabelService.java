package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.model.Label;

import java.io.IOException;
import java.util.List;

public interface GmailLabelService {

    List<Label> getLabels() throws IOException;

    Label getLabel(String labelName) throws IOException;

}

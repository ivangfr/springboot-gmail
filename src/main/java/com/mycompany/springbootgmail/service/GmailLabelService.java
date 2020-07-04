package com.mycompany.springbootgmail.service;

import com.google.api.services.gmail.model.Label;

import java.util.List;

public interface GmailLabelService {

    List<Label> getLabels();

    Label getLabel(String labelName);

}

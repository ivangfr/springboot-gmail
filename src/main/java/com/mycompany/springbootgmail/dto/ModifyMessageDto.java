package com.mycompany.springbootgmail.dto;

import lombok.Data;

import java.util.List;

@Data
public class ModifyMessageDto {

    private List<String> labelsToRemove;
    private List<String> labelsToAdd;
}

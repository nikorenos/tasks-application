package com.crud.tasks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BadgesDto {
    private int votes;
    private AttachmentByTypeDto attachmentsByType;
    /*private String pos;
    private String idList;*/
}
package com.tlc.group.seven.marketdataservice.log.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class SystemLog {
    private String title;
    private String event;
    private String description;
    private String service;
}

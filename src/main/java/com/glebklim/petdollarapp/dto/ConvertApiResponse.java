package com.glebklim.petdollarapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertApiResponse {
    private boolean success;
    private Map<String, String> query;
    private Map<String, Number> info;
    private boolean historical;
    private Date date;
    private Double result;
}

package com.glebklim.petdollarapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LatestApiResponse {

    private boolean success;
    private Long timestamp;
    private String base;
    private Date date;
    private Map<String, Double> rates;
}

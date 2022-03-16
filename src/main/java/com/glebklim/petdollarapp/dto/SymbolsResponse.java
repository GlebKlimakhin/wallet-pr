package com.glebklim.petdollarapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymbolsResponse {
    private boolean success;
    private Map<String, String> symbols;
}

package com.texgraphicscompany.biznewz.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterParams {

    private Map<String, Map> paramMap = new HashMap<>();
    private Map<String, String> countryMap = new HashMap<>();

    public Map<String, String> getCountries(){

        Map<String, String> countryMap = new HashMap<>();
        countryMap.put("Australia", "au");
        countryMap.put("Netherlands", "nl");
        countryMap.put("France", "fr");
        countryMap.put("Mexico", "mx");
        countryMap.put("Russia", "ru");
        countryMap.put("Poland", "pl");
        countryMap.put("China", "cn");
        countryMap.put("Switzerland", "ch");
        countryMap.put("Germany", "de");
        countryMap.put("United Kingdom", "gb");
        countryMap.put("South Africa", "za");
        countryMap.put("United Arab Emirates", "ae");
        countryMap.put("Canada", "ca");
        countryMap.put("United States", "us");
        countryMap.put("India", "in");
        countryMap.put("Israel", "il");
        countryMap.put("Nigeria", "ng");

        return countryMap;
    }
    

}

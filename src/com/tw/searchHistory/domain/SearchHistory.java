package com.tw.searchHistory.domain;

public class SearchHistory {
    private String ipAddress;
    private String searchString;
    private String dateTime;

    public SearchHistory(String ipAddress, String searchString, String dateTime) {

        this.ipAddress = ipAddress;
        this.searchString = searchString;
        this.dateTime = dateTime;
    }
}

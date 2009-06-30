package com.tw.searchHistory.factory;

import com.tw.searchHistory.domain.SearchHistory;

public class SearchHistoryFactory {
    public SearchHistory createSearchHistory(String ipAddress, String searchString, String dateTime) {
        return new SearchHistory(ipAddress, searchString, dateTime);
    }
}

package com.tw.searchHistory.repository;

import com.tw.searchHistory.domain.SearchHistory;

public interface IRepository {
    void save(SearchHistory searchHistory);
}

package com.tw.searchHistoryTest;

import com.tw.searchHistory.domain.SearchHistory;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;

public class SearchHistoryTest{

    @Test
    public void shouldAbleToCreateSearchHistoryObjectThroughFactory(){
        SearchHistory searchHistory = new SearchHistory("blah", "foo", "123");
        assertNotNull(searchHistory);
    }

}

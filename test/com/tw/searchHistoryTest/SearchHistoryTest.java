package com.tw.searchHistoryTest;

import com.tw.searchHistory.domain.SearchHistory;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SearchHistoryTest{

    @Test
    public void equalityTest(){
        SearchHistory expected = new SearchHistory("blah", "foo", "123");
        SearchHistory actual = new SearchHistory("blah", "foo", "123");
        assertEquals(expected, actual);
    }

}

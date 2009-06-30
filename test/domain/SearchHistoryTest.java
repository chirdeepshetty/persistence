import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.factory.SearchHistoryFactory;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SearchHistoryTest{

    @Test
    public void shouldAbleToCreateSearchHistoryObject(){
        SearchHistory searchHistory = new SearchHistoryFactory().createSearchHistory("blah", "foo", "123");
        assertNotNull(searchHistory);
    }

}

package com.tw.searchHistoryTest;

import org.junit.Test;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Expectations;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import com.tw.searchHistory.repository.Repository;
import com.tw.searchHistory.domain.SearchHistory;

import java.text.SimpleDateFormat;
import java.text.ParseException;


public class RepositoryTest {
    @Test
    public void shouldCallSessionSaveOnRepositorySave(){
        JUnit4Mockery mockery = new JUnit4Mockery();
        final SessionFactory factory = mockery.mock(SessionFactory.class);
        final Session session = mockery.mock(Session.class);
        final Transaction transaction = mockery.mock(Transaction.class);

        mockery.checking(new Expectations(){{
            oneOf(factory).openSession();
            will(returnValue(session));
            oneOf(session).beginTransaction();
            will(returnValue(transaction));
            oneOf(transaction).commit();
            try {
                oneOf(session).save(new SearchHistory("ip", "query", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-7-2 11:7:4")));
            } catch (ParseException e) {
                e.printStackTrace(); 
            }
            oneOf(session).close();
        }});

        Repository repository = new Repository(factory);
        try {
            repository.save(new SearchHistory("ip", "query", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-7-2 11:7:4")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mockery.assertIsSatisfied();
    }   
}

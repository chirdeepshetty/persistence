package com.tw.searchHistoryTest;

import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.util.HibernateUtil;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.helpers.DateTimeDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class SearchHistoryTest{

    @Test
    public void equalityTest(){        
        try {
            SearchHistory expected = new SearchHistory("blah", "foo", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-7-2 11:7:4"));
            SearchHistory actual = new SearchHistory("blah", "foo", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-7-2 11:7:4"));
            assertEquals(expected, actual);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Ignore
    public void persistenceTest(){
        SearchHistory searchHistory = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            searchHistory = new SearchHistory("10.7.20.100", "foo barx", dateFormat.parse("2009-7-2 11:7:4"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(searchHistory);
		tx.commit();
		session.close();
		HibernateUtil.shutdown();
    }

    @Test
    public void dummy(){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-7-2 11:7:4");
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace(); 
        }
    }


}

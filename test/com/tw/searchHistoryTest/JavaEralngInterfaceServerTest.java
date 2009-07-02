package com.tw.searchHistoryTest;

import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.repository.IRepository;
import com.tw.searchHistory.server.JavaErlangInterfaceServer;
import com.tw.searchHistory.server.OtpInbox;
import static junit.framework.Assert.assertNull;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class JavaEralngInterfaceServerTest {
    private JUnit4Mockery mockery;
    private IRepository repository;
    private OtpInbox inbox;
    private JavaErlangInterfaceServer server;
    private OtpErlangTuple erlangTuple;

    @Before
    public void before(){
        mockery = new JUnit4Mockery();
        repository = mockery.mock(IRepository.class);
        inbox = mockery.mock(OtpInbox.class);
        server = new JavaErlangInterfaceServer(inbox, repository);
        erlangTuple = new OtpErlangTuple(new OtpErlangString[]{new OtpErlangString("ip"), new OtpErlangString("query"), new OtpErlangString("2009-7-2 11:7:4")});
         mockery.checking(new Expectations(){{
             try {
                 oneOf(repository).save(new SearchHistory("ip", "query", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-7-2 11:7:4")));
             } catch (ParseException e) {
                 e.printStackTrace();
             }
             oneOf(inbox).receive();
            will(returnValue(erlangTuple));
        }});
    }

    @Test
    public void shouldMapTupleToSearchHistoryDomain(){
        SearchHistory actual = null;
        try {
            actual = server.toSearchHistory(erlangTuple);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SearchHistory expected = null;
        try {
            expected = new SearchHistory("ip", "query", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2009-7-2 11:7:4"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void shouldProcessAndSaveCallRepositorySave(){
        try {
            server.recieveMessageAndSave();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mockery.assertIsSatisfied();
    }
}

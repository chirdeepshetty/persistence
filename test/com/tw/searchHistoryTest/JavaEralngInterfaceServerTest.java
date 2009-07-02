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
        erlangTuple = new OtpErlangTuple(new OtpErlangString[]{new OtpErlangString("ip"), new OtpErlangString("query"), new OtpErlangString("time")});
         mockery.checking(new Expectations(){{
            oneOf(repository).Save(new SearchHistory("ip", "query", "time"));
            oneOf(inbox).receive();
            will(returnValue(erlangTuple));
        }});
    }

    @Test
    public void shouldMapTupleToSearchHistoryDomain(){
        SearchHistory actual = server.toSearchHistory(erlangTuple);
        SearchHistory expected = new SearchHistory("ip", "query", "time");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldProcessAndSaveCallRepositorySave(){
        server.recieveMessageAndSave();
        mockery.assertIsSatisfied();
    }
}

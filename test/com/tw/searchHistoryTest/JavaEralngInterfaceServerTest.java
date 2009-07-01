package com.tw.searchHistoryTest;

import com.ericsson.otp.erlang.OtpErlangString;
import com.ericsson.otp.erlang.OtpErlangTuple;
import com.ericsson.otp.erlang.OtpMbox;
import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.repository.IRepository;
import com.tw.searchHistory.server.JavaErlangInterfaceServer;
import static junit.framework.Assert.assertNull;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JavaEralngInterfaceServerTest {
    @Test
    public void serverCreationTest(){
        JUnit4Mockery mockery = new JUnit4Mockery();
        IRepository repository = mockery.mock(IRepository.class);

        JavaErlangInterfaceServer server = new JavaErlangInterfaceServer((OtpMbox)null, repository);
        assertNull(server.getInbox());
        assertEquals(repository, server.getRepository());
    }

    @Test
    public void shouldMapTupleToSearchHistoryDomain(){
        JUnit4Mockery mockery = new JUnit4Mockery();
        final IRepository repository = mockery.mock(IRepository.class);

        JavaErlangInterfaceServer server = new JavaErlangInterfaceServer((OtpMbox)null, repository);
        OtpErlangTuple erlangTuple = new OtpErlangTuple(new OtpErlangString[]{new OtpErlangString("ip"), new OtpErlangString("query"), new OtpErlangString("time")});

        SearchHistory actual = server.searchHistoryMapper(erlangTuple);
        SearchHistory expected = new SearchHistory("ip", "query", "time");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldProcessAndSaveCallRepositorySave(){
        JUnit4Mockery mockery = new JUnit4Mockery();
        final IRepository repository = mockery.mock(IRepository.class);

        JavaErlangInterfaceServer server = new JavaErlangInterfaceServer((OtpMbox)null, repository);
        OtpErlangTuple erlangTuple = new OtpErlangTuple(new OtpErlangString[]{new OtpErlangString("ip"), new OtpErlangString("query"), new OtpErlangString("time")});

        mockery.checking(new Expectations(){{
            oneOf(repository).Save(new SearchHistory("ip", "query", "time"));
            with(returnValue("successId"));
        }});
        
        server.processAndSave(erlangTuple);
        mockery.assertIsSatisfied();
    }
}

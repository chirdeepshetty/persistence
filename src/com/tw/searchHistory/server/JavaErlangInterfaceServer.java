package com.tw.searchHistory.server;

import com.ericsson.otp.erlang.*;
import com.tw.searchHistory.util.PropertyReader;
import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.repository.IRepository;

import java.io.IOException;

public class JavaErlangInterfaceServer {
    private OtpMbox inbox;
    private IRepository repository;

    public IRepository getRepository() {
        return repository;
    }

    public OtpMbox getInbox() {
        return inbox;
    }

    public JavaErlangInterfaceServer(OtpMbox inbox, IRepository repository) {
        this.repository = repository;
        this.inbox = inbox;
    }

    public JavaErlangInterfaceServer(PropertyReader propertyReader, IRepository repository) throws IOException{
        this(new OtpNode(propertyReader.getServerName(), propertyReader.getCookieName()).createMbox(propertyReader.getMailBoxName()), repository);
    }

    public void run() {
        while(true){
            try{
                OtpErlangTuple erlangTuple = (OtpErlangTuple) inbox.receive();
                processAndSave(erlangTuple);
            }
            catch(Exception ex){
               System.out.println(ex.getMessage()); 
            }
        }
    }

    public void processAndSave(OtpErlangTuple erlangTuple) {
        SearchHistory searchHistory = searchHistoryMapper(erlangTuple);
        repository.Save(searchHistory);
    }

    public SearchHistory searchHistoryMapper(OtpErlangTuple erlangTuple) {
        OtpErlangObject[] erlangStrings =  erlangTuple.elements();
        return new SearchHistory(encode(erlangStrings[0]), encode(erlangStrings[1]), encode(erlangStrings[2]));
    }

    private String encode(OtpErlangObject erlangString) {
        return ((OtpErlangString)erlangString).stringValue();
    }

}

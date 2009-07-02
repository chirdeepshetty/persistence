package com.tw.searchHistory.server;

import com.ericsson.otp.erlang.*;
import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.repository.IRepository;

public class JavaErlangInterfaceServer {
    private OtpInbox inbox;
    private IRepository repository;

    public IRepository getRepository() {
        return repository;
    }

    public JavaErlangInterfaceServer(OtpInbox inbox, IRepository repository) {
        this.repository = repository;
        this.inbox = inbox;
    }

    public void run() {
        while(true){
            try{
                recieveMessageAndSave();
            }
            catch(Exception ex){
               System.out.println(ex.getMessage()); 
            }
        }
    }

    public void recieveMessageAndSave() {
        OtpErlangTuple erlangTuple = inbox.receive();
        SearchHistory searchHistory = toSearchHistory(erlangTuple);
        repository.Save(searchHistory);
    }

    public SearchHistory toSearchHistory(OtpErlangTuple erlangTuple) {
        OtpErlangObject[] erlangObjects =  erlangTuple.elements();
        return new SearchHistory(stringify(erlangObjects[0]), stringify(erlangObjects[1]), stringify(erlangObjects[2]));
    }

    private String stringify(OtpErlangObject erlangObject) {
        return ((OtpErlangString)erlangObject).stringValue();
    }

}

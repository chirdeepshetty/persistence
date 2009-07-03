package com.tw.searchHistory.server;

import com.ericsson.otp.erlang.*;
import com.tw.searchHistory.domain.SearchHistory;
import com.tw.searchHistory.repository.IRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaErlangInterfaceServer {
    private OtpInbox inbox;
    private IRepository repository;

    public IRepository getRepository() {
        return repository;
    }

    public JavaErlangInterfaceServer(OtpInbox inbox, IRepository repository) {
        this.inbox = inbox;
        this.repository = repository;
    }

    public void run() {
        while(true){
            try{
                recieveMessageAndSave();
            }
            catch(Exception ex){
               ex.printStackTrace(); 
            }
        }
    }

    public void recieveMessageAndSave() throws Exception{
        OtpErlangTuple erlangTuple = inbox.receive();
        SearchHistory searchHistory = toSearchHistory(erlangTuple);
        repository.save(searchHistory);
    }

    public SearchHistory toSearchHistory(OtpErlangTuple erlangTuple) throws Exception{
        OtpErlangObject[] erlangObjects =  erlangTuple.elements();
        return new SearchHistory(stringify(erlangObjects[0]), stringify(erlangObjects[1]), queryTime(erlangObjects));
    }

    private Date queryTime(OtpErlangObject[] erlangObjects) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = stringify(erlangObjects[2]);
//        System.out.println("datestring"+dateString);
        return simpleDateFormat.parse(dateString);
    }

    private String stringify(OtpErlangObject erlangObject) {
        return ((OtpErlangString)erlangObject).stringValue();
    }

}

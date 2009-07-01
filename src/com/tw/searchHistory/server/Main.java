package com.tw.searchHistory.server;

import com.tw.searchHistory.util.PropertyReader;
import com.tw.searchHistory.repository.Repository;
import com.ericsson.otp.erlang.OtpNode;

import java.io.IOException;

public class Main {  

    public static void main(String args[]) throws IOException{
        new JavaErlangInterfaceServer(new PropertyReader(), new Repository()).run();   
    }
}

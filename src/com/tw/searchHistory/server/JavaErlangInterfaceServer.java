package com.tw.searchHistory.server;

import com.ericsson.otp.erlang.OtpNode;

public class JavaErlangInterfaceServer {
    private OtpNode otpNode;
    private String messageBox;

    public JavaErlangInterfaceServer(OtpNode otpNode, String messageBox) {

        this.otpNode = otpNode;
        this.messageBox = messageBox;
    }

    public void Run() {
        while(true){
            
        }
    }

    public static void main(String args[]){
        
    }
}

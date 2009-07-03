package com.tw.searchHistory.server;

import com.ericsson.otp.erlang.*;
import com.tw.searchHistory.util.PropertyReader;

import java.io.IOException;

public class InboxFactory {
    public static OtpInbox createInbox(final PropertyReader propertyReader) throws IOException {
        return new OtpInbox(){
            OtpMbox mailbox = new OtpNode(propertyReader.getServerName() + "@" + java.net.InetAddress.getLocalHost().getHostName(), propertyReader.getCookieName()).createMbox(propertyReader.getMailBoxName());

            public OtpErlangTuple receive() {
                try {
                    return (OtpErlangTuple) mailbox.receive();
                } catch (Exception otpErlangExit) {
                    throw new PersistenceException("Error in receive");
                }
            }
        };
    }
}
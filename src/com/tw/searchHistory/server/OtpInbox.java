package com.tw.searchHistory.server;

import com.ericsson.otp.erlang.OtpErlangTuple;

public interface OtpInbox {
    OtpErlangTuple receive();
}

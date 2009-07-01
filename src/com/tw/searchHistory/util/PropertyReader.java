package com.tw.searchHistory.util;

import java.util.ResourceBundle;

public class PropertyReader {
    public static String propertyFileName = "persistence";
    private ResourceBundle resourceBundle;

    public PropertyReader() {
        this(ResourceBundle.getBundle(propertyFileName));
    }

    public PropertyReader(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String getMailBoxName(){
        return resourceBundle.getString("mailBoxName");
    }

    public String getServerName(){
        return resourceBundle.getString("serverName");
    }

    public String getCookieName(){
        return resourceBundle.getString("authenticationCookie");
    }
}

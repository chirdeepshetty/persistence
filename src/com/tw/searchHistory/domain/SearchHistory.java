package com.tw.searchHistory.domain;

import java.util.Date;
import java.text.SimpleDateFormat;

public class SearchHistory {
    private static final long serialVersionUID = 1L;
    private long id = 1L;
    private String ipAddress;
    private String searchString;
    private Date dateTime;

    public void setFormattedDateTime(String formattedDateTime) {
        this.formattedDateTime = formattedDateTime;
    }

    private String formattedDateTime;

    public String getFormattedDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
    }    

    private SearchHistory(){

    }

    public SearchHistory(String ipAddress, String searchString, Date dateTime) {

        this.ipAddress = ipAddress;
        this.searchString = searchString;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchHistory that = (SearchHistory) o;

        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (searchString != null ? !searchString.equals(that.searchString) : that.searchString != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ipAddress != null ? ipAddress.hashCode() : 0;
        result = 31 * result + (searchString != null ? searchString.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }
}

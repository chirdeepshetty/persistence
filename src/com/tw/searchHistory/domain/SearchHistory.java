package com.tw.searchHistory.domain;

public class SearchHistory {
    private String ipAddress;
    private String searchString;
    private String dateTime;

    public SearchHistory(String ipAddress, String searchString, String dateTime) {

        this.ipAddress = ipAddress;
        this.searchString = searchString;
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

package com.bisht.GoodReadsClone.search;

import java.util.List;

public class SearchResult {
    private int numFound;
    private List<SearchBook> searchBook;

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public List<SearchBook> getSearchBook() {
        return searchBook;
    }

    public void setSearchBook(List<SearchBook> searchBook) {
        this.searchBook = searchBook;
    }
}

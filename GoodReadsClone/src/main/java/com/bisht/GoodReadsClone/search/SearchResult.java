package com.bisht.GoodReadsClone.search;

import java.util.List;

public class SearchResult {
    private int numFound;
    private List<SearchBook> docs;

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public List<SearchBook> getDocs() {
        return docs;
    }

    public void setDocs(List<SearchBook> docs) {
        this.docs = docs;
    }
}

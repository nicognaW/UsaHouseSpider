package com.nicognaw.usahousespider.entity.request;

@SuppressWarnings("ALL")
public class RequestData {
    SearchParameters SearchParameters;

    public RequestData(SearchParameters searchParameters) {
        this.setSearchParameters(searchParameters);
    }

    // Getter Methods

    public SearchParameters getSearchParameters() {
        return SearchParameters;
    }

    // Setter Methods

    public void setSearchParameters(SearchParameters searchParametersObject) {
        this.SearchParameters = searchParametersObject;
    }
}

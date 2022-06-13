package com.nicognaw.usahousespider.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class ResponseData {
    @JsonIgnore
    public String searchListingsParameters = null;
    public ArrayList<DataRecord> listings = new ArrayList<>();
    @JsonIgnore
    public String mapListings = null;
    public String url;
    public String titleTag;
    public String h1Tag;
}

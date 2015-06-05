package com.lifeisle.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Jekton Luo
 * @version 0.01 6/4/2015.
 */
public class QueryString {

    private StringBuilder query = new StringBuilder();

    public QueryString() {
    }

    public void add(String key, String value) throws UnsupportedEncodingException {
        query.append('&');
        query.append(URLEncoder.encode(key, "UTF-8"));
        query.append('=');
        query.append(URLEncoder.encode(value, "UTF-8"));
    }

    public String getQueryString() {
        return query.toString();
    }

}

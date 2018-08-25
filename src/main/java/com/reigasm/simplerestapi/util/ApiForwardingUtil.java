package com.reigasm.simplerestapi.util;

import javax.servlet.http.HttpServletRequest;

public class ApiForwardingUtil {
    private String destinationUrl;
    private String issuedToken;
    private HttpServletRequest req;

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public String getIssuedToken() {
        return issuedToken;
    }

    public void setIssuedToken(String issuedToken) {
        this.issuedToken = issuedToken;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }
}

package com.kreyzon.prospectfinder.common;


import org.springframework.http.HttpHeaders;

public class HttpUtils {

    public static HttpHeaders generateHttpHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constant.HEADER_AUTHORIZATION,  Constant.HEADER_AUTHORIZATION_VALUE_PREFIX_XEMAIL + " " + token);
        headers.set(Constant.HEADER_CONTENT_TYPE, Constant.HEADER_CONTENT_TYPE_VALUE);

        return headers;
    }
}

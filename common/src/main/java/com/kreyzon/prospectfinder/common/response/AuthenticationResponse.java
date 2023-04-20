package com.kreyzon.prospectfinder.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse extends GenericResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public AuthenticationResponse(String result, String message, Object object, String token) {
        super(result, message, object);
        this.token = token;
    }

    public AuthenticationResponse(String result, String message) {
        super(result, message);
    }

    public AuthenticationResponse(String result, String message, String token) {
        super(result, message);
        this.token = token;
    }
}

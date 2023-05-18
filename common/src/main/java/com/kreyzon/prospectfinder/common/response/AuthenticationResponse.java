package com.kreyzon.prospectfinder.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kreyzon.prospectfinder.common.dto.AppUserDto;
import com.kreyzon.prospectfinder.common.request.AuthenticationRequest;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse extends GenericResponse {

    private AppUserDto appUserDto;

    public AuthenticationResponse() {}

    public AuthenticationResponse(AppUserDto appUserDto) {
        this.appUserDto = appUserDto;
    }

    public AuthenticationResponse(String result, String message, Object object, AppUserDto appUserDto) {
        super(result, message, object);
        this.appUserDto = appUserDto;
    }

    public AuthenticationResponse(String result, String message) {
        super(result, message);
    }

    public AuthenticationResponse(String result, String message, AppUserDto appUserDto) {
        super(result, message);
        this.appUserDto = appUserDto;
    }
}

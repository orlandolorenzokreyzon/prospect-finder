package com.kreyzon.prospectfinder.common.request;

import lombok.Data;


public record UserCreateRequest (String email, String password, String confirmPassword){
}

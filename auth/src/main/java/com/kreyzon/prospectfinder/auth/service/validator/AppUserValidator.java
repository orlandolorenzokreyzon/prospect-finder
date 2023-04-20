package com.kreyzon.prospectfinder.auth.service.validator;

import com.kreyzon.prospectfinder.auth.model.AppUser;
import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.request.PasswordUpdateRequest;
import com.kreyzon.prospectfinder.common.request.UserCreateRequest;
import com.kreyzon.prospectfinder.common.response.AuthenticationResponse;
import com.kreyzon.prospectfinder.common.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppUserValidator {

    private final PasswordEncoder passwordEncoder;

    public GenericResponse validateUserCreation(UserCreateRequest userCreateRequest) {
        if (!userCreateRequest.password().equals(userCreateRequest.confirmPassword()))
            return new GenericResponse(Constant.RESULT_NOK, "Passwords do not match");

        if(!EmailValidator.getInstance().isValid(userCreateRequest.email()))
            return new GenericResponse(Constant.RESULT_NOK, "Email format not valid");

        return new GenericResponse(Constant.RESULT_OK, "User validated", userCreateRequest);
    }

    public AuthenticationResponse validateUserLogin(AppUser appUser) {
        if (!appUser.getEnabled())
            return new AuthenticationResponse(Constant.RESULT_NOK, "User is not enabled");

        if (appUser.getDeleted())
            return new AuthenticationResponse(Constant.RESULT_NOK, "User is deleted");

        return new AuthenticationResponse(Constant.RESULT_OK, "User is ok");
    }

    public GenericResponse validateUserUpdatePassword(AppUser appUser, PasswordUpdateRequest passwordUpdateRequest) {
        Boolean checkIfGivenPasswordMatchesUserPassword = checkIfGivenPasswordMatchesUserPassword(appUser, passwordUpdateRequest.oldPassword());
        if (!checkIfGivenPasswordMatchesUserPassword)
            return new GenericResponse(Constant.RESULT_NOK, "Given password does not match user's password");

        if (!passwordUpdateRequest.newPassword().equals(passwordUpdateRequest.newPasswordConfirmation()))
            return new GenericResponse(Constant.RESULT_NOK, "Passwords do not match");

        if (passwordUpdateRequest.newPassword().equals(passwordUpdateRequest.oldPassword()))
            return new GenericResponse(Constant.RESULT_NOK, "New password matches with old password. Please choose a different one");

        return new GenericResponse(Constant.RESULT_OK, "Password is validated");
    }

    private boolean checkIfGivenPasswordMatchesUserPassword(AppUser appUser, String givenPassword) {
        String userPassword = appUser.getPassword();
        String encodedGivenPassword = passwordEncoder.encode(givenPassword);
        return passwordEncoder.matches(givenPassword, userPassword);
    }
}

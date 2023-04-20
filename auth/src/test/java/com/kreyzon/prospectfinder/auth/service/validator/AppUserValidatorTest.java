package com.kreyzon.prospectfinder.auth.service.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.kreyzon.prospectfinder.common.request.UserCreateRequest;
import com.kreyzon.prospectfinder.common.response.GenericResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserValidator.class})
@ExtendWith(SpringExtension.class)
class AppUserValidatorTest {
    @Autowired
    private AppUserValidator appUserValidator;

    /**
     * Method under test: {@link AppUserValidator#validateUserCreation(UserCreateRequest)}
     */
    @Test
    void testValidateUserCreation() {
        UserCreateRequest userCreateRequest = new UserCreateRequest("jane.doe@example.org", "iloveyou", "iloveyou");

        GenericResponse actualValidateUserCreationResult = appUserValidator.validateUserCreation(userCreateRequest);
        assertEquals("User validated", actualValidateUserCreationResult.getMessage());
        assertEquals("OK", actualValidateUserCreationResult.getResult());
        assertSame(userCreateRequest, actualValidateUserCreationResult.getObject());
    }

    /**
     * Method under test: {@link AppUserValidator#validateUserCreation(UserCreateRequest)}
     */
    @Test
    void testValidateUserCreation2() {
        UserCreateRequest userCreateRequest = new UserCreateRequest("john.smith@example.org", "iloveyou", "iloveyou");

        GenericResponse actualValidateUserCreationResult = appUserValidator.validateUserCreation(userCreateRequest);
        assertEquals("User validated", actualValidateUserCreationResult.getMessage());
        assertEquals("OK", actualValidateUserCreationResult.getResult());
        assertSame(userCreateRequest, actualValidateUserCreationResult.getObject());
    }

    /**
     * Method under test: {@link AppUserValidator#validateUserCreation(UserCreateRequest)}
     */
    @Test
    void testValidateUserCreation3() {
        GenericResponse actualValidateUserCreationResult = appUserValidator
                .validateUserCreation(new UserCreateRequest("U@U", "iloveyou", "iloveyou"));
        assertEquals("Email format not valid", actualValidateUserCreationResult.getMessage());
        assertEquals("NOK", actualValidateUserCreationResult.getResult());
    }

    /**
     * Method under test: {@link AppUserValidator#validateUserCreation(UserCreateRequest)}
     */
    @Test
    void testValidateUserCreation4() {
        GenericResponse actualValidateUserCreationResult = appUserValidator
                .validateUserCreation(new UserCreateRequest("\\U\\U\\U.\\U\\U\\U.\\U\\U\\U", "iloveyou", "iloveyou"));
        assertEquals("Email format not valid", actualValidateUserCreationResult.getMessage());
        assertEquals("NOK", actualValidateUserCreationResult.getResult());
    }

    /**
     * Method under test: {@link AppUserValidator#validateUserCreation(UserCreateRequest)}
     */
    @Test
    void testValidateUserCreation5() {
        GenericResponse actualValidateUserCreationResult = appUserValidator
                .validateUserCreation(new UserCreateRequest("jane.doe@example.org", "U@U", "iloveyou"));
        assertEquals("Passwords do not match", actualValidateUserCreationResult.getMessage());
        assertEquals("NOK", actualValidateUserCreationResult.getResult());
    }
}


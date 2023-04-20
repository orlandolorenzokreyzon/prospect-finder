package com.kreyzon.prospectfinder.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kreyzon.prospectfinder.auth.model.AppUser;
import com.kreyzon.prospectfinder.auth.repository.AppUserRepository;
import com.kreyzon.prospectfinder.auth.service.validator.AppUserValidator;
import com.kreyzon.prospectfinder.auth.utils.IdUtils;
import com.kreyzon.prospectfinder.common.request.UserCreateRequest;
import com.kreyzon.prospectfinder.common.response.GenericResponse;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserService.class})
@ExtendWith(SpringExtension.class)
class AppUserServiceTest {
    @MockBean
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @MockBean
    private AppUserValidator appUserValidator;

    @MockBean
    private IdUtils idUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;

    /**
     * Method under test: {@link AppUserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        AppUser appUser = new AppUser();
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = appUserService.loadUserByUsername("jane.doe@example.org");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(appUserRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link AppUserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        AppUser appUser = mock(AppUser.class);
        when(appUser.getEnabled()).thenReturn(true);
        when(appUser.getEmail()).thenReturn("jane.doe@example.org");
        when(appUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(appUser).setCreationDate((LocalDateTime) any());
        doNothing().when(appUser).setDeleted((Boolean) any());
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setEnabled((Boolean) any());
        doNothing().when(appUser).setLastLoginDate((LocalDateTime) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setUserId((Integer) any());
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(appUserRepository.findByEmail((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = appUserService.loadUserByUsername("jane.doe@example.org");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(appUserRepository).findByEmail((String) any());
        verify(appUser).getEnabled();
        verify(appUser).getEmail();
        verify(appUser).getPassword();
        verify(appUser).setCreationDate((LocalDateTime) any());
        verify(appUser).setDeleted((Boolean) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setEnabled((Boolean) any());
        verify(appUser).setLastLoginDate((LocalDateTime) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setUserId((Integer) any());
    }

    /**
     * Method under test: {@link AppUserService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        when(appUserRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        AppUser appUser = mock(AppUser.class);
        when(appUser.getEnabled()).thenReturn(true);
        when(appUser.getEmail()).thenReturn("jane.doe@example.org");
        when(appUser.getPassword()).thenReturn("iloveyou");
        doNothing().when(appUser).setCreationDate((LocalDateTime) any());
        doNothing().when(appUser).setDeleted((Boolean) any());
        doNothing().when(appUser).setEmail((String) any());
        doNothing().when(appUser).setEnabled((Boolean) any());
        doNothing().when(appUser).setLastLoginDate((LocalDateTime) any());
        doNothing().when(appUser).setPassword((String) any());
        doNothing().when(appUser).setUserId((Integer) any());
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        assertThrows(UsernameNotFoundException.class, () -> appUserService.loadUserByUsername("jane.doe@example.org"));
        verify(appUserRepository).findByEmail((String) any());
        verify(appUser).setCreationDate((LocalDateTime) any());
        verify(appUser).setDeleted((Boolean) any());
        verify(appUser).setEmail((String) any());
        verify(appUser).setEnabled((Boolean) any());
        verify(appUser).setLastLoginDate((LocalDateTime) any());
        verify(appUser).setPassword((String) any());
        verify(appUser).setUserId((Integer) any());
    }

    /**
     * Method under test: {@link AppUserService#create(UserCreateRequest)}
     */
    @Test
    void testCreate() {
        AppUser appUser = new AppUser();
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        when(appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(appUserValidator.validateUserCreation((UserCreateRequest) any()))
                .thenReturn(new GenericResponse("Result", "Not all who wander are lost"));
        when(idUtils.generateAppUserId()).thenReturn(1);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        GenericResponse actualCreateResult = appUserService
                .create(new UserCreateRequest("jane.doe@example.org", "iloveyou", "iloveyou"));
        assertEquals("User created", actualCreateResult.getMessage());
        assertEquals("OK", actualCreateResult.getResult());
        Object object = actualCreateResult.getObject();
        assertTrue(object instanceof AppUser);
        assertEquals(1, ((AppUser) object).getUserId().intValue());
        assertEquals("secret", ((AppUser) object).getPassword());
        assertTrue(((AppUser) object).getEnabled());
        assertEquals("jane.doe@example.org", ((AppUser) object).getEmail());
        assertFalse(((AppUser) object).getDeleted());
        verify(appUserRepository).save((AppUser) any());
        verify(appUserValidator).validateUserCreation((UserCreateRequest) any());
        verify(idUtils).generateAppUserId();
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AppUserService#create(UserCreateRequest)}
     */
    @Test
    void testCreate2() {
        AppUser appUser = new AppUser();
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        when(appUserRepository.save((AppUser) any())).thenReturn(appUser);
        when(appUserValidator.validateUserCreation((UserCreateRequest) any()))
                .thenReturn(new GenericResponse("Result", "Not all who wander are lost"));
        when(idUtils.generateAppUserId()).thenReturn(1);
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new UsernameNotFoundException("NOK"));
        assertThrows(UsernameNotFoundException.class,
                () -> appUserService.create(new UserCreateRequest("jane.doe@example.org", "iloveyou", "iloveyou")));
        verify(appUserValidator).validateUserCreation((UserCreateRequest) any());
        verify(idUtils).generateAppUserId();
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link AppUserService#create(UserCreateRequest)}
     */
    @Test
    void testCreate3() {
        AppUser appUser = new AppUser();
        appUser.setCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setDeleted(true);
        appUser.setEmail("jane.doe");
        appUser.setEnabled(true);
        appUser.setLastLoginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        appUser.setPassword("iloveyou");
        appUser.setUserId(1);
        when(appUserRepository.save((AppUser) any())).thenReturn(appUser);
        GenericResponse genericResponse = new GenericResponse("NOK", "Not all who wander are lost");

        when(appUserValidator.validateUserCreation((UserCreateRequest) any())).thenReturn(genericResponse);
        when(idUtils.generateAppUserId()).thenReturn(1);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertSame(genericResponse,
                appUserService.create(new UserCreateRequest("jane.doe@example.org", "iloveyou", "iloveyou")));
        verify(appUserValidator).validateUserCreation((UserCreateRequest) any());
    }
}


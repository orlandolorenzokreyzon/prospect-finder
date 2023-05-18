package com.kreyzon.prospectfinder.auth.service;

import com.kreyzon.prospectfinder.auth.model.AppUser;
import com.kreyzon.prospectfinder.auth.repository.AppUserRepository;
import com.kreyzon.prospectfinder.auth.service.validator.AppUserValidator;
import com.kreyzon.prospectfinder.auth.utils.IdUtils;
import com.kreyzon.prospectfinder.common.Constant;
import com.kreyzon.prospectfinder.common.dto.AppUserDto;
import com.kreyzon.prospectfinder.common.request.PasswordUpdateRequest;
import com.kreyzon.prospectfinder.common.request.UserCreateRequest;
import com.kreyzon.prospectfinder.common.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.gnu.GNUObjectIdentifiers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository  appUserRepository;

    private final AppUserValidator appUserValidator;

    private final IdUtils idUtils;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));

        return new User(
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getEnabled(),
                true,
                true,
                true,
                getAuthorities("USER")
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role));
        return grantedAuthorityList;
    }

    public GenericResponse getUserInfoById(Integer userId) {
        AppUser appUser = appUserRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Id not found"));

        AppUserDto appUserDto = new AppUserDto(appUser.getUserId(), appUser.getEmail(), null);

        return new GenericResponse(Constant.RESULT_OK, "User found", appUserDto);
    }

    public GenericResponse create(UserCreateRequest userCreateRequest) {
        GenericResponse validationResponse = appUserValidator.validateUserCreation(userCreateRequest);
        if (validationResponse.isResultNOK())
            return validationResponse;

        AppUser appUser = AppUser
                .builder()
                .userId(idUtils.generateAppUserId())
                .email(userCreateRequest.email())
                .password(passwordEncoder.encode(userCreateRequest.password()))
                .enabled(true)
                .deleted(false)
                .creationDate(LocalDateTime.now())
                .lastLoginDate(LocalDateTime.now())
                .build();

        appUserRepository.save(appUser);

        return new GenericResponse(Constant.RESULT_OK, "User created", appUser);
    }

    public GenericResponse updatePassword(Integer userId, PasswordUpdateRequest passwordUpdateRequest) {
        AppUser appUser = appUserRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Id not found"));

        GenericResponse validationResponse = appUserValidator.validateUserUpdatePassword(appUser, passwordUpdateRequest);
        if (validationResponse.isResultNOK())
            return validationResponse;

        appUser.setPassword(passwordEncoder.encode(passwordUpdateRequest.newPassword()));
        appUserRepository.save(appUser);

        return new GenericResponse(Constant.RESULT_OK, "Password updated");
    }

    private boolean checkIfGivenPasswordMatchesUserPassword(AppUser appUser, String givenPassword) {
        String userPassword = appUser.getPassword();
        String encodedGivenPassword = passwordEncoder.encode(givenPassword);
        return passwordEncoder.matches(givenPassword, userPassword);
    }
}

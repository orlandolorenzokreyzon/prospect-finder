package com.kreyzon.prospectfinder.common.request;

public record PasswordUpdateRequest (String oldPassword, String newPassword, String newPasswordConfirmation) {
}

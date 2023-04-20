package com.kreyzon.prospectfinder.auth.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @Id
    private Integer userId;

    private String email;

    private String password;

    private LocalDateTime creationDate;

    private LocalDateTime lastLoginDate;

    private Boolean enabled;

    private Boolean deleted;
}
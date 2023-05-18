package com.kreyzon.prospectfinder.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Setup {
    @Id
    private Integer id;

    private String businessUsername;

    private String businessPassword;

    private Integer businessAvailableCredits;
}
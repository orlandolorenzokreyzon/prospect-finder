package com.kreyzon.prospectfinder.api.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    @Id
    @Column(name = "session_id")
    private Integer id;

    private String userId;

    private String name;

    private String scrapeInfo;

    private String scrapeInfoFile;

    private String scrapeType;

    private Integer maximumEmails;

    private Integer scrapedEmails;

    private String downloadSheet;

    private String status;

    private String externalUserId;

    private String externalSessionId;

    private LocalDateTime creationDate;
}
package com.kreyzon.prospectfinder.api.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionResponse {
    private String id;
    private String name;
    private String scrape_info;
    private String scrape_info_file;
    private String scrape_type;
    private String maximum_emails;
    private String scraped_emails;
    private String download_sheet;
    private String status;
    private Integer user;
}

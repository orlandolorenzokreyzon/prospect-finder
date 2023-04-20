package com.kreyzon.prospectfinder.api.request;

public record SessionRequest (String userId, String name, String scrape_info, String scrape_type, String maximum_emails){
}

package com.kreyzon.prospectfinder.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "endpoint")
public class Endpoint {
    @Id
    @Column(name = "endpoint_id")
    private String id;

    // Example: xEmailExtractor, InfluFinder...
    private String domain;

    private String fullUrl;
}
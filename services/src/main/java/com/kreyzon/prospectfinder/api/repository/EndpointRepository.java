package com.kreyzon.prospectfinder.api.repository;

import com.kreyzon.prospectfinder.api.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, String> {
}
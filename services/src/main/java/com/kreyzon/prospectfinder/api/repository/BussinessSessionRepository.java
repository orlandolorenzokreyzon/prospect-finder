package com.kreyzon.prospectfinder.api.repository;

import com.kreyzon.prospectfinder.api.model.BusinessSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BussinessSessionRepository extends JpaRepository<BusinessSession, Integer> {
    @Query(value = "SELECT MAX (session_id) FROM business_session", nativeQuery = true)
    Integer getLastId();

    @Query(value = "SELECT * FROM business_session WHERE external_session_id = ? ORDER BY external_session_id DESC LIMIT 1", nativeQuery = true)
    String findByExternalSessionId(String externalSessionId);

    List<BusinessSession> findByUserId(String userId);
}
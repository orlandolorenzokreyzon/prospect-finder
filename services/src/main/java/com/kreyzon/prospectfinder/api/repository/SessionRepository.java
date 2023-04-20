package com.kreyzon.prospectfinder.api.repository;

import com.kreyzon.prospectfinder.api.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    @Query(value = "SELECT MAX (session_id) FROM session", nativeQuery = true)
    Integer getLastId();

    @Query(value = "SELECT * FROM session WHERE external_session_id = ? ORDER BY external_session_id DESC LIMIT 1", nativeQuery = true)
    String findByExternalSessionId(String externalSessionId);
}
package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    void addVisit(Long patientId, Long doctorId, java.time.LocalDateTime time, String description);

    @Query("SELECT p FROM PatientEntity p WHERE p.lastName = :lastName")
    List<PatientEntity> findPatientsByLastName(@Param("lastName") String lastName);

    @Query("SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId")
    List<VisitEntity> findVisitsByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :visitCount")
    List<PatientEntity> findPatientsWithMoreThanXVisits(@Param("visitCount") int visitCount);

    @Query("SELECT p FROM PatientEntity p WHERE p.isInsured = true")
    List<PatientEntity> findInsuredPatients();
}

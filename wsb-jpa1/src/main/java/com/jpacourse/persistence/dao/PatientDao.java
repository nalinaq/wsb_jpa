package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    void addVisit(Long patientId, Long doctorId, java.time.LocalDateTime time, String description);

    List<PatientEntity> findPatientsByLastName(String lastName);

    List<VisitEntity> findVisitsByPatientId(Long patientId);

    List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount);

    List<PatientEntity> findInsuredPatients();
}

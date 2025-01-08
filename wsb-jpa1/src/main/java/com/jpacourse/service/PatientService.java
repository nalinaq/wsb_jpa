package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

import java.time.LocalDateTime;

public interface PatientService {

    PatientTO findById(Long id);

    void deletePatient(Long id);

    void addVisit(Long patientId, Long doctorId, String description, LocalDateTime time);
}

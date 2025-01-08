package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        PatientEntity patient = patientDao.findOne(id);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found with ID: " + id);
        }
        return PatientMapper.mapToTO(patient);
    }

    @Override
    public void deletePatient(Long id) {
        PatientEntity patient = patientDao.findOne(id);
        if (patient != null) {
            patientDao.delete(patient);
        } else {
            throw new IllegalArgumentException("Patient not found with ID: " + id);
        }
    }

    @Override
    public void addVisit(Long patientId, Long doctorId, String description, LocalDateTime time) {
        patientDao.addVisit(patientId, doctorId, time, description);
    }
}

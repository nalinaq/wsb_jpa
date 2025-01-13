package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addVisit(Long patientId, Long doctorId, LocalDateTime time, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient != null && doctor != null) {
            VisitEntity visit = new VisitEntity();
            visit.setTime(time);
            visit.setDescription(description);
            visit.setPatient(patient);
            visit.setDoctor(doctor);

            patient.getVisits().add(visit);
            entityManager.merge(patient); // Kaskadowy zapis
        } else {
            throw new IllegalArgumentException("Nie znaleziono pacjenta lub doktora");
        }
    }

    @Override
    public List<PatientEntity> findPatientsByLastName(String lastName) {
        return null;
    }

    @Override
    public List<VisitEntity> findVisitsByPatientId(Long patientId) {
        return null;
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(int visitCount) {
        return null;
    }

    @Override
    public List<PatientEntity> findInsuredPatients() {
        return null;
    }

    @Override
    public PatientEntity save(PatientEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public PatientEntity getOne(Long id) {
        return entityManager.getReference(PatientEntity.class, id);
    }

    @Override
    public PatientEntity findOne(Long id) {
        return entityManager.find(PatientEntity.class, id);
    }

    @Override
    public List<PatientEntity> findAll() {
        return entityManager.createQuery("FROM PatientEntity", PatientEntity.class).getResultList();
    }

    @Override
    public PatientEntity update(PatientEntity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(PatientEntity entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            entityManager.remove(entityManager.merge(entity));
        }
    }

    @Override
    public void delete(Long id) {
        PatientEntity entity = findOne(id);
        if (entity != null) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM PatientEntity").executeUpdate();
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(p) FROM PatientEntity p", Long.class).getSingleResult();
    }

    @Override
    public boolean exists(Long id) {
        return findOne(id) != null;
    }
}

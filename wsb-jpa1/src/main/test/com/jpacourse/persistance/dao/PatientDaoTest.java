package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldFindPatientsByLastName() {
        // given
        String lastName = "Wiśniewski";

        // when
        List<PatientEntity> patients = patientDao.findPatientsByLastName(lastName);

        // then
        assertThat(patients).hasSize(1);
        assertThat(patients.get(0).getFirstName()).isEqualTo("Marek");
    }

    @Transactional
    @Test
    public void testShouldFindVisitsByPatientId() {
        // given
        Long patientId = 1L;

        // when
        List<VisitEntity> visits = patientDao.findVisitsByPatientId(patientId);

        // then
        assertThat(visits).hasSize(2); // Sprawdź, ile wizyt ma pacjent o ID 1
        assertThat(visits.get(0).getDescription()).isEqualTo("Kontrola rutynowa");
    }

    @Transactional
    @Test
    public void testShouldFindPatientsWithMoreThanXVisits() {
        // given
        int visitCount = 1;

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(visitCount);

        // then
        assertThat(patients).hasSize(2); // Pacjenci, którzy mają więcej niż 1 wizytę
        assertThat(patients.get(0).getVisits()).hasSizeGreaterThan(1);
    }

    @Transactional
    @Test
    public void testShouldFindInsuredPatients() {
        // when
        List<PatientEntity> patients = patientDao.findInsuredPatients();

        // then
        assertThat(patients).hasSize(2); // Dwóch pacjentów z isInsured = true
        assertThat(patients.get(0).isInsured()).isTrue();
    }

    @Transactional
    @Test
    public void testOptimisticLocking() {
        // given
        PatientEntity patient1 = patientDao.findOne(1L);
        PatientEntity patient2 = patientDao.findOne(1L);

        // when
        patient1.setFirstName("Zmiana 1");
        patientDao.update(patient1);

        patient2.setFirstName("Zmiana 2");

        // then
        assertThatThrownBy(() -> patientDao.update(patient2))
                .isInstanceOf(javax.persistence.OptimisticLockException.class);
    }
}

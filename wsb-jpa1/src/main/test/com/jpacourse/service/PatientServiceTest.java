package com.jpacourse.service;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldDeletePatientAndCascadeVisits() {
        // given
        Long patientId = 1L;

        // when
        patientService.deletePatient(patientId);

        // then
        // Sprawdzenie, czy pacjent został usunięty
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNull();

        // Sprawdzenie, czy wizyty pacjenta zostały usunięte
        List<VisitEntity> remainingVisits = patientDao.findAll()
                .stream()
                .flatMap(p -> p.getVisits().stream())
                .collect(Collectors.toList());
        assertThat(remainingVisits).isEmpty();

        // Sprawdzenie, czy lekarze pozostali w bazie danych
        List<DoctorEntity> doctors = patientDao.findAll()
                .stream()
                .flatMap(p -> p.getVisits().stream())
                .map(VisitEntity::getDoctor)
                .distinct()
                .collect(Collectors.toList());
        assertThat(doctors).isNotEmpty();
    }
}

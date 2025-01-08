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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Test
    public void testShouldAddVisitToPatient() {
        // given
        Long patientId = 1L;
        Long doctorId = 1L;
        String description = "Nowa wizyta";
        LocalDateTime time = LocalDateTime.of(2025, 1, 10, 10, 0);

        // when
        patientDao.addVisit(patientId, doctorId, time, description);

        // then
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).hasSize(2);

        VisitEntity newVisit = patient.getVisits().get(1);
        assertThat(newVisit.getDescription()).isEqualTo(description);
        assertThat(newVisit.getTime()).isEqualTo(time);
    }
}

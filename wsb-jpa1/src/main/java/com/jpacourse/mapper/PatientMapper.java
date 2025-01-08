package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class PatientMapper {

    private PatientMapper() {
        // Prywatny konstruktor, aby zapobiec tworzeniu instancji klasy
    }

    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }

        // Mapowanie listy wizyt
        List<VisitTO> visits = patientEntity.getVisits()
                .stream()
                .map(PatientMapper::mapVisitToTO)
                .collect(Collectors.toList());

        // Tworzenie obiektu PatientTO
        PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setInsured(patientEntity.isInsured()); // Mapowanie nowego pola
        patientTO.setVisits(visits);

        return patientTO;
    }

    public static VisitTO mapVisitToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        // Mapowanie listy typów leczenia
        List<String> treatmentTypes = visitEntity.getTreatments()
                .stream()
                .map(treatment -> treatment.getType().toString())
                .collect(Collectors.toList());

        // Tworzenie obiektu VisitTO
        VisitTO visitTO = new VisitTO();
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorFirstName(visitEntity.getDoctor().getFirstName());
        visitTO.setDoctorLastName(visitEntity.getDoctor().getLastName());
        visitTO.setTreatmentTypes(treatmentTypes);

        return visitTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO) {
        if (patientTO == null) {
            return null;
        }

        // Tworzenie obiektu PatientEntity
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setInsured(patientTO.isInsured()); // Mapowanie nowego pola
        // Uwaga: Wizyty w PatientEntity muszą być ustawiane oddzielnie, jeśli wymagane

        return patientEntity;
    }
}

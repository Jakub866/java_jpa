package com.jpacourse.mapper;


import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public final class PatientMapper {
    public static PatientTO mapToTO(final PatientEntity patientEntity)
    {
        if (patientEntity == null)
        {
            return null;
        }
        final PatientTO patientTO = new PatientTO();

        patientTO.setId(patientEntity.getId());
        patientTO.setBirthDate(patientEntity.getDateOfBirth());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setGender(patientEntity.getGender());

        patientTO.setVisits(patientEntity.getVisits().stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList()));

        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        if(patientTO == null)
        {
            return null;
        }
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setDateOfBirth(patientTO.getBirthDate());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setGender(patientTO.getGender());
        // To do - mapping Visits do TO and back
        //patientEntity.setVisits(patientTO.getVisits());


        return patientEntity;
    }
}

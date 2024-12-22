package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public final class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }
        VisitTO visitTO = new VisitTO();
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorLastName(visitEntity.getDoctor().getLastName());
        visitTO.setDoctorFirstName(visitEntity.getDoctor().getFirstName());
        visitTO.setTreatmentTypes(
                visitEntity.getMedicalTreatments()
                        .stream().map(MedicalTreatmentEntity::getType)
                        .collect(Collectors.toList())
        );

        return visitTO;
    }

}

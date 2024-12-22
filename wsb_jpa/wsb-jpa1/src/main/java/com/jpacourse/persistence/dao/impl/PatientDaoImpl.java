package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    public void save_visit(long patientId, long doctorId, LocalDateTime visitDate, String description) {

        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new EntityNotFoundException("Patient with ID " + patientId + " not found.");
        }

        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new EntityNotFoundException("Doctor with ID " + doctorId + " not found.");
        }

        VisitEntity visit = new VisitEntity();
        visit.setTime(visitDate);
        visit.setDescription(description);
        visit.setDoctor(doctor);

        patient.getVisits().add(visit);
        entityManager.merge(patient);
    }
}

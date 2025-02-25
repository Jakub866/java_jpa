package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
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
        visit.setPatient(patient);

        patient.getVisits().add(visit);
        entityManager.merge(patient);
    }

    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }


    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(int x) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :x", PatientEntity.class)
                .setParameter("x", x)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findIfGenderContainsAKeyWord(String keyWord) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE str(p.gender) LIKE :keyWord", PatientEntity.class)
                .setParameter("keyWord", "%" + keyWord + "%")
                .getResultList();
    }
}
